/*
 *	Drifting Souls 2
 *	Copyright (c) 2006 Christopher Jung
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.driftingsouls.ds2.server.tick.regular;

import java.sql.Blob;

import net.driftingsouls.ds2.server.ContextCommon;
import net.driftingsouls.ds2.server.battles.Battle;
import net.driftingsouls.ds2.server.comm.PM;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.User;
import net.driftingsouls.ds2.server.framework.UserIterator;
import net.driftingsouls.ds2.server.framework.db.Database;
import net.driftingsouls.ds2.server.framework.db.SQLQuery;
import net.driftingsouls.ds2.server.framework.db.SQLResultRow;
import net.driftingsouls.ds2.server.scripting.ScriptParser;
import net.driftingsouls.ds2.server.tasks.Task;
import net.driftingsouls.ds2.server.tasks.Taskmanager;
import net.driftingsouls.ds2.server.tick.TickController;

/**
 * Berechnet sonstige Tick-Aktionen, welche keinen eigenen TickController haben
 * @author Christopher Jung
 *
 */
public class RestTick extends TickController {

	@Override
	protected void prepare() {
		// EMPTY
	}
	
	/*
		Sprungantrieb
	*/
	private void doJumps() {
		Database db = getContext().getDatabase();
		
		this.log("Sprungantrieb");
		SQLQuery jump = db.query("SELECT id,x,y,system,shipid FROM jumps");
		while( jump.next() ) {
			this.log( jump.getInt("shipid")+" springt nach "+jump.getInt("system")+":"+jump.getInt("x")+"/"+jump.getInt("y"));
			
			db.update("UPDATE ships SET x=",jump.getInt("x"),",y=",jump.getInt("y"),",system=",jump.getInt("system")," WHERE id>0 AND id=",jump.getInt("shipid")," OR docked='",jump.getInt("shipid"),"' OR docked='l ",jump.getInt("shipid"),"'");
			db.update("DELETE FROM jumps WHERE id=",jump.getInt("id"));
		}
		jump.free();
	}
	
	/*
		Statistiken
	*/
	private void doStatistics() {
		Database db = getContext().getDatabase();
		
		this.log("");
		this.log("Erstelle Statistiken");
		
		int shipcount = db.first("SELECT count(*) count FROM ships WHERE id>0 AND owner>1").getInt("count");
		long crewcount = db.first("SELECT sum(crew) totalcrew FROM ships WHERE id>0 AND owner > 0").getLong("totalcrew");
		int tick = getContext().get(ContextCommon.class).getTick();
		
		db.update("INSERT INTO stats_ships (tick,shipcount,crewcount) VALUES (",tick,",",shipcount,",",crewcount,")");
	}

	/*
		Vac-Modus
	*/
	private void doVacation() {
		Database db = getDatabase();
		
		this.log("");
		this.log("Bearbeite Vacation-Modus");
		db.update("UPDATE users SET name=REPLACE(name,' [VAC]',''),nickname=REPLACE(nickname,' [VAC]','') WHERE vaccount=1");
		this.log("\t"+db.affectedRows()+" Spieler haben den VAC-Modus verlassen");
		db.update("UPDATE users SET vaccount=vaccount-1 WHERE vaccount>0 AND wait4vac=0");
		
		UserIterator iter = getContext().createUserIterator("SELECT id,ally FROM users WHERE wait4vac=1");
		for( User user : iter ) {
			SQLResultRow newcommander = null;
			if( user.getAlly() > 0 ) {
				newcommander = db.first("SELECT id,name FROM users WHERE ally=",user.getAlly(),"  AND inakt <= 7 AND vaccount=0 AND (wait4vac>6 OR wait4vac=0)");
			}
			
			SQLQuery battleid = db.query("SELECT id FROM battles WHERE commander1=",user.getID()," OR commander2=",user.getID());
			while( battleid.next() ) {
				Battle battle = new Battle();
				battle.load(battleid.getInt("id"), user.getID(), 0, 0, 0 );
				
				if( newcommander != null ) {
					this.log("\t\tUser"+user.getID()+": Die Leitung der Schlacht "+battleid.getInt("id")+" wurde an "+newcommander.getString("name")+" ("+newcommander.getInt("id")+") uebergeben");
					
					battle.logenemy("<action side=\""+battle.getOwnSide()+"\" time=\""+Common.time()+"\" tick=\""+getContext().get(ContextCommon.class).getTick()+"\"><![CDATA[\n");
		
					PM.send(getContext(), user.getID(), newcommander.getInt("id"), "Schlacht &uuml;bernommen", "Die Leitung der Schlacht bei "+battle.getSystem()+" : "+battle.getX()+"/"+battle.getY()+" wurde dir automatisch &uuml;bergeben, da der bisherige Kommandant in den Vacationmodus gewechselt ist");
			
					battle.logenemy(Common._titleNoFormat(newcommander.getString("name"))+" kommandiert nun die gegnerischen Truppen\n\n");
			
					battle.setCommander(battle.getOwnSide(), newcommander.getInt("id"));
			
					battle.logenemy("]]></action>\n");
			
					battle.logenemy("<side"+(battle.getOwnSide()+1)+" commander=\""+battle.getCommander(battle.getOwnSide())+"\" ally=\""+battle.getAlly(battle.getOwnSide())+"\" />\n");
			
					battle.setTakeCommand(battle.getOwnSide(), 0);
			
					battle.save(true);
					
					battle.writeLog();
				}
				else {
					this.log("\t\tUser"+user.getID()+": Die Schlacht $battleid wurde beendet");
				
					battle.endBattle(0, 0, true);
					PM.send(getContext(), battle.getCommander(battle.getOwnSide()), battle.getCommander(battle.getEnemySide()), "Schlacht beendet", "Die Schlacht bei "+battle.getSystem()+" : "+battle.getX()+"/"+battle.getY()+" wurde automatisch beim wechseln in den Vacation-Modus beendet, da kein Ersatzkommandant ermittelt werden konnte!");
				}
			}
			battleid.free();
		}
		iter.free();
		
		db.update("UPDATE users SET name=CONCAT(name,' [VAC]'),nickname=CONCAT(nickname,' [VAC]') WHERE wait4vac=1");
		this.log("\t"+db.affectedRows()+" Spieler sind in den VAC-Modus gewechselt");
		db.update("UPDATE users SET wait4vac=wait4vac-1 WHERE wait4vac>0");
	}
	
	/*
	
		Neue Felsbrocken spawnen lassen
			
	*/	
	private void doFelsbrocken() {
		// TODO
		Common.stub();
	}
	
	/*
		Quests bearbeiten
	*/
	private void doQuests() {
		Database db = getContext().getDatabase();
		
		this.log("Bearbeite Quests [ontick]");
		SQLQuery rquest = db.query("SELECT * FROM quests_running WHERE ontick IS NOT NULL ORDER BY questid");
		if( rquest.numRows() == 0 ) { 
			rquest.free();
			return;
		}
		ScriptParser scriptparser = getContext().get(ContextCommon.class).getScriptParser(ScriptParser.NameSpace.QUEST);
		scriptparser.setShip(null);
		scriptparser.setLogFunction(ScriptParser.LOGGER_NULL);	
		
		while( rquest.next() ) {
			scriptparser.cleanup();
			try {
				Blob execdata = rquest.getBlob("execdata");
				if( (execdata != null) && (execdata.length() > 0) ) { 
					scriptparser.setExecutionData( execdata.getBinaryStream() );
				}
					
				this.log("* quest: "+rquest.getInt("questid")+" - user:"+rquest.getInt("userid")+" - script: "+rquest.getInt("ontick"));
					
				String script = db.first("SELECT script FROM scripts WHERE id='"+rquest.getInt("ontick")+"'").getString("script");
				scriptparser.setRegister("USER", Integer.toString(rquest.getInt("userid")) );
				scriptparser.setRegister("QUEST", "r"+rquest.getInt("id"));
				scriptparser.setRegister("SCRIPT", Integer.toString(rquest.getInt("ontick")) );		
				scriptparser.executeScript(db, script, "0");
					
				int usequest = Integer.parseInt(scriptparser.getRegister("QUEST"));
					
				if( usequest != 0 ) {
					scriptparser.writeExecutionData(execdata.setBinaryStream(1));	
				}
			}
			catch( Exception e ) {
				this.log("[FEHLER] Konnte Quest-Tick fuehr Quest "+rquest.getInt("questid")+" (Running-ID: "+rquest.getInt("id")+") nicht ausfuehren."+e);
				e.printStackTrace();
			}
		}
		rquest.free();
	}
	
	/*
	 * 
 	 * Tasks bearbeiteten (Timeouts)
	 * 
	 */
	private void doTasks() {
		this.log("Bearbeite Tasks [tick_timeout]");
		
		Taskmanager taskmanager = Taskmanager.getInstance();
		Task[] tasklist = taskmanager.getTasksByTimeout(1);
		for( int i=0; i < tasklist.length; i++ ) {
			this.log("* "+tasklist[i].getTaskID()+" ("+tasklist[i].getType()+") -> sending tick_timeout");
			taskmanager.handleTask( tasklist[i].getTaskID(), "tick_timeout" );	
		}
		
		taskmanager.reduceTimeout(1);
	}
	
	@Override
	protected void tick() {
		Database db = getContext().getDatabase();
		
		this.log("Transmissionen - gelesen+1");
		db.update("UPDATE transmissionen SET gelesen=gelesen+1 WHERE gelesen>=2");
		
		this.log("Loesche alte Transmissionen");
		db.update("DELETE FROM transmissionen WHERE gelesen>=10");
		
		this.log("Erhoehe Inaktivitaet der Spieler");
		db.update("UPDATE users SET inakt=inakt+1 WHERE vaccount=0");
		
		this.log("Erhoehe Tickzahl");
		db.update("UPDATE config SET ticks=ticks+1");
		
		
		this.doJumps();
		this.doStatistics();
		this.doVacation();
		this.doFelsbrocken();
		this.doQuests();
		this.doTasks();
		
		this.log("Zaehle Timeout bei Umfragen runter");
		db.update("UPDATE surveys SET timeout=timeout-1 WHERE timeout>0");
		
		this.log("Entsperre alle evt noch durch den Tick gesperrten Accounts");
		this.unblock(0);
	}

}
