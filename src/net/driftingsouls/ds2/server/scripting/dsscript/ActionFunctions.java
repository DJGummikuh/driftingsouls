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
package net.driftingsouls.ds2.server.scripting.dsscript;

import net.driftingsouls.ds2.server.ContextCommon;
import net.driftingsouls.ds2.server.Location;
import net.driftingsouls.ds2.server.battles.Battle;
import net.driftingsouls.ds2.server.cargo.Cargo;
import net.driftingsouls.ds2.server.cargo.ResourceID;
import net.driftingsouls.ds2.server.cargo.Resources;
import net.driftingsouls.ds2.server.comm.PM;
import net.driftingsouls.ds2.server.entities.User;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.scripting.ShipUtils;
import net.driftingsouls.ds2.server.scripting.transfer.TransferStrategy;
import net.driftingsouls.ds2.server.scripting.transfer.TransferStrategyFactory;
import net.driftingsouls.ds2.server.ships.Ship;
import net.driftingsouls.ds2.server.tasks.Taskmanager;

/**
 * Scriptfunktionen fuer Schiffsaktionsscripte
 * @author Christopher Jung
 *
 */
public class ActionFunctions {
	void registerFunctions(ScriptParser parser) {
		parser.registerCommand( "SHIPMOVE", new ShipMove(), ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "WAIT", new Wait(), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "NJUMP", new NJump(), ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "KJUMP", new KJump(), ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "MSG", new Msg(), ScriptParser.Args.PLAIN_REG,ScriptParser.Args.REG,ScriptParser.Args.REG );
		parser.registerCommand( "START", new Dock(Ship.DockMode.START, true), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "UNDOCK", new Dock(Ship.DockMode.UNDOCK, true), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "LAND", new Dock(Ship.DockMode.LAND, false), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "DOCK", new Dock(Ship.DockMode.DOCK, false), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "GETRESOURCE", new GetResource(), ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "TRANSFERCARGO", new TransferCargo(), ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "ATTACK", new Attack(), ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "RESETSCRIPT", new ResetScript(), ScriptParser.Args.PLAIN_VARIABLE );
		parser.registerCommand( "EXECUTETASK", new ExecuteTask(), ScriptParser.Args.PLAIN_REG, ScriptParser.Args.PLAIN_REG );
		parser.registerCommand( "GETSHIPOWNER", new GetShipOwner(), ScriptParser.Args.PLAIN_REG);
	}
	
	class ShipMove implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			Ship ship = scriptparser.getShip();
			
			Location target = Location.fromString(command[1]);
			scriptparser.log(target+"\n");
			
			int maxcount = Integer.MAX_VALUE;
			if( (command.length > 2) && (command[2] != null) && command[2].length() > 0 ) {
				maxcount = Integer.parseInt(command[2]);
			}
			scriptparser.log("maxcount: "+maxcount+"\n");
			
			if( ship.getSystem() != target.getSystem() ) {
				User source = (User)ContextMap.getContext().getDB().get(User.class, -1);
				
				PM.send(source, ship.getOwner().getId(), "Scriptparser Error - Schiff "+ship.getId(), 
						"Konnte Befehl !SHIPMOVE "+command[1]+" nicht ausfuehren. Ihr Schiff befindet sich im falschen System");
				scriptparser.log("Falsches System - Ausfuehrung bis zum naechsten Tick angehalten\n\n");
				return STOP;
			}
			
			if( !ShipUtils.move(ship, target, maxcount) ) {
				scriptparser.log(ShipUtils.MESSAGE.getMessage());
				return STOP;
			}
			
			scriptparser.log(ShipUtils.MESSAGE.getMessage());
			
			return CONTINUE;
		}
	}
	
	class Wait implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			org.hibernate.Session db = ContextMap.getContext().getDB();
			Ship ship = scriptparser.getShip();
			
			String cmd = (command.length > 1 ? command[1] : "");
			
			if( cmd.equals("shipInRange") ) {	
				int shipid = Integer.parseInt(command[2]);
				int range = 0;
				if( (command.length > 3) && (command[3] != null) && (command[3].length() > 0) ) {
					range = Integer.parseInt(command[3]);
				}

				scriptparser.log("Warte auf Schiff "+shipid+" im Umkreis von "+range+" Feldern\n\n");
				
				Location shipLoc = ship.getLocation();
				
				Ship result = (Ship)db.get(Ship.class, shipid);
				
				if( (result != null) && (result.getId() > 0) && shipLoc.sameSector(range, result.getLocation(), 0) ) {
					return CONTINUE;
				}
				return STOP;
			}
			else if( cmd.equals("tick") ) {
				int tick = ContextMap.getContext().get(ContextCommon.class).getTick();
				int waittick = Integer.parseInt(command[2]);
				
				scriptparser.log("Warte auf Tick "+waittick+" - aktuell: "+tick+"\n\n");
				
				if( tick < waittick ) {
					return STOP;
				}
				return CONTINUE;
			}
			else {
				scriptparser.log("Ausfuehrung bis zum naechsten Tick angehalten\n\n");
			}
			
			return STOP_AND_INC;
		}
	}
	
	class KJump implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			Ship ship = scriptparser.getShip();
			
			int nodeid = Integer.parseInt(command[1]);
			scriptparser.log("knodeid: "+nodeid+"\n"); 
			boolean result = ship.jump(nodeid, true); 
			scriptparser.log(Common._stripHTML(Ship.MESSAGE.getMessage()));
			if( result ) {
				scriptparser.log("Ausfuehrung bis zum naechsten Tick angehalten\n\n");
				return STOP;
			}
			
			scriptparser.setShip(ship);
			
			scriptparser.log("\n");
			
			return CONTINUE;
		}
	}
	
	class NJump implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			Ship ship = scriptparser.getShip();
			
			int nodeid = Integer.parseInt(command[1]);
			scriptparser.log("nodeid: "+nodeid+"\n"); 
			boolean result = ship.jump(nodeid, false); 
			scriptparser.log(Common._stripHTML(Ship.MESSAGE.getMessage()));
			if( result ) {
				scriptparser.log("Ausfuehrung bis zum naechsten Tick angehalten\n\n");
				return STOP;
			}
			
			scriptparser.setShip(ship);
			
			scriptparser.log("\n");
			
			return CONTINUE;
		}
	}
	
	class Msg implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			int to = Integer.parseInt(command[1]);
			
			scriptparser.log("receiver: "+to+"\n");
			
			String title = command[2];
			scriptparser.log("title: "+title+"\n");  
			
			String msg = command[3];
			scriptparser.log("msg: "+msg+"\n\n");
			
			Ship ship = scriptparser.getShip();
			
			PM.send( ship.getOwner(), to, title, msg );
			
			return CONTINUE;
		}
	}
	
	class Dock implements SPFunction {
		private boolean allowAll = false;
		private Ship.DockMode mode = Ship.DockMode.DOCK;
		
		/**
		 * Erstellt eine Scriptfunktion als Wrapper um {@link net.driftingsouls.ds2.server.ships.Ship#dock(net.driftingsouls.ds2.server.ships.Ship.DockMode, Ship[])}
		 * @param mode Der Dock-Modus
		 * @param allowAll Soll <code>all</code> (alle gedockten Schiffe) zugelassen werden?
		 */
		public Dock(Ship.DockMode mode, boolean allowAll) {
			this.allowAll = allowAll;
			this.mode = mode;
		}
		
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			org.hibernate.Session db = ContextMap.getContext().getDB();
			Ship ship = scriptparser.getShip();
			
			Ship[] dockids = null;
			if( !allowAll || !command[1].equals("all") ) {		
				dockids = new Ship[command.length-1];
				for( int i=1; i < command.length; i++ ) { 
					dockids[i-1] = (Ship)db.get(Ship.class, Integer.parseInt(command[i]));
				}
			}
					
			boolean result = ship.dock(mode, dockids); 
			
			scriptparser.log(Common._stripHTML(Ship.MESSAGE.getMessage()));
			if( result ) {
				scriptparser.log("Ausfuehrung bis zum naechsten Tick angehalten\n\n");
				return STOP;
			}					
			
			return CONTINUE;
		}
	}
	
	class GetResource implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			org.hibernate.Session db = ContextMap.getContext().getDB();
			
			int shipid = Integer.parseInt(command[1]);
			scriptparser.log("shipid: "+shipid+"\n");
			
			String resid = command[2];
			scriptparser.log("resid: "+resid+"\n");
			
			Ship ship = scriptparser.getShip();
			if( shipid > 0 ) {
				User owner = ship.getOwner();
				ship = (Ship)db.get(Ship.class, shipid);
				
				if( (ship == null) || (ship.getId() < 0) || (ship.getOwner() != owner) ) {
					scriptparser.log("FEHLER: Das angegebene Schiff existiert nicht oder geh&ouml;rt nicht diesem Spieler\n");	
					return STOP;
				}
			}
			Cargo cargo = ship.getCargo();
			long rescount = cargo.getResourceCount(Resources.fromString(resid));
			
			scriptparser.setRegister("A",Long.toString(rescount));
			
			return CONTINUE;
		}
	}
	
	class TransferCargo implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			String transferType = command[1].toLowerCase();
			
			int idOne = Integer.parseInt(command[2]);
			scriptparser.log("shipid1: "+idOne+"\n");
			
			String way = command[3].toLowerCase();
			scriptparser.log("way: "+way+"\n");
			
			int idTwo = Integer.parseInt(command[4]);
			scriptparser.log("shipid2: "+idTwo+"\n");
			
			ResourceID resource = Resources.fromString(command[5]);
			scriptparser.log("resid: "+resource+"\n");
			
			long count = Value.Long(command[6]);
			scriptparser.log("count: "+count+"\n");
			
			int from = idOne;
			int to = idTwo;
			
			boolean reversed = false;
			if( way.equals("to") ) {
				from = idOne;
				to = idTwo;
			}
			else if( way.equals("from") ) {
				from = idTwo;
				to = idOne;
				reversed = true;
			}
			else {
				scriptparser.log("FEHLER: Transferweg darf nur from/to sein - war aber:"+way+"\n");
				return CONTINUE;
			}
			
			
			TransferStrategy transferStrategy = TransferStrategyFactory.getStrategy(transferType, reversed, from, to);
			

			if( transferStrategy == null ) {
				scriptparser.log("FEHLER: Konnte Transfertyp nicht identifizieren.\n Unterstuetzt werden Schiff zu Schiff (sts), Schiff zu Basis (stb), Basis zu Schiff (bts), Basis zu Basis (btb)\n");
				return CONTINUE;
			}
			
			String answer = transferStrategy.transfer(resource, count);
			
			scriptparser.log(answer);
			
			return CONTINUE;
		}
	}
	
	class Attack implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			org.hibernate.Session db = ContextMap.getContext().getDB();
			
			int playerid = Integer.parseInt(command[1]);
			scriptparser.log("playerid: "+playerid+"\n");
			
			Ship ship = scriptparser.getShip();
			Ship aship = (Ship)db.createQuery("from Ship where owner=? and system=? and x=? and y=? amd battle is null")
				.setInteger(0, playerid)
				.setInteger(1, ship.getSystem())
				.setInteger(2, ship.getX())
				.setInteger(3, ship.getY())
				.setMaxResults(1)
				.uniqueResult();
			
			if( aship == null ) {
				scriptparser.log("Kein passendes Schiff gefunden. Ausfuehrung bis zum naechsten Tick angehalten\n");
				return STOP;	
			}
			
			Battle.create(ship.getOwner().getId(), ship.getId(), aship.getId());
			
			// TODO: Nach der Migration von Batte wieder entfernen
			db.refresh(ship);
			scriptparser.setShip(ship);
			
			return CONTINUE;
		}
	}
	
	class ResetScript implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			Ship ship = scriptparser.getShip();
			
			ship.setScript(null);
			ship.setScriptExeData(null);
			
			scriptparser.setShip(ship);
			
			return CONTINUE;
		}
	}
	
	class ExecuteTask implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			String taskid = command[1];
			scriptparser.log("taskid: "+taskid+"\n");
			
			String cmd = command[2];
			scriptparser.log("cmd: spa_"+cmd+"\n");
			
			Taskmanager taskmanager = Taskmanager.getInstance();
			taskmanager.handleTask(taskid, "spa_"+cmd);
			
			return CONTINUE;
		}
	}
	
	class GetShipOwner implements SPFunction {
		public boolean[] execute( ScriptParser scriptparser, String[] command ) {
			org.hibernate.Session db = ContextMap.getContext().getDB();
			int shipid = Integer.parseInt(command[1]);
			scriptparser.log("shipid: "+shipid+"\n");
			
			Ship ship = scriptparser.getShip();
			User ashipowner = (User)db.createQuery("select s.owner from Ship s where s.id=? and s.system=? and s.x=? and s.y=?")
				.setInteger(0, shipid)
				.setInteger(1, ship.getSystem())
				.setInteger(2, ship.getX())
				.setInteger(3, ship.getY())
				.setMaxResults(1)
				.uniqueResult();
			
			scriptparser.setRegister("A",ashipowner == null ? "0" : Integer.toString(ashipowner.getId()));
			
			return CONTINUE;
		}
	}
}