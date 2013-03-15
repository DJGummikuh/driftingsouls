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
package net.driftingsouls.ds2.server.modules.stats;

import net.driftingsouls.ds2.server.entities.User;
import net.driftingsouls.ds2.server.entities.ally.Ally;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.Context;
import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.modules.StatsController;
import net.driftingsouls.ds2.server.ships.ShipClasses;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Zeigt die Liste der groessten Flotten an.
 * @author Christopher Jung
 *
 */
public class StatBiggestFleet extends AbstractStatistic implements Statistic {
	private boolean allys;

	/**
	 * Konstruktor.
	 * @param allys Sollten Allianzen (<code>true</code>) angezeigt werden?
	 */
	public StatBiggestFleet(boolean allys) {
		this.allys = allys;
	}

	@Override
	public void show(StatsController contr, int size) throws IOException {
		Context context = ContextMap.getContext();
		org.hibernate.Session db = context.getDB();

		Integer[] zuIgnorierendeSchiffsklassen = {
				ShipClasses.UNBEKANNT.ordinal(),
				ShipClasses.TRANSPORTER.ordinal(),
				ShipClasses.TANKER.ordinal(),
				ShipClasses.CONTAINER.ordinal(),
				ShipClasses.SCHROTT.ordinal(),
				ShipClasses.RETTUNGSKAPSEL.ordinal(),
				ShipClasses.EMTPY.ordinal(),
				ShipClasses.FELSBROCKEN.ordinal()};

		String sumStatement = "sum(COALESCE(sm.size, st.size)*COALESCE(sm.size, st.size)*s.crew/COALESCE(sm.crew, st.crew))";

		String url;

		List<?> tmp;
		if( !allys ) {
			tmp = db.createQuery("select "+sumStatement+" as cnt,o " +
					"from Ship s join s.owner o join s.shiptype st left join s.modules sm " +
					"where s.id > 0 and " +
					"	o.id > :minid and " +
					"	(o.vaccount=0 or o.wait4vac>0) and " +
					"	(((sm.id is null) and st.cost>0) or ((sm.id is not null) and sm.cost>0)) and " +
					"	st.shipClass not in (:classes) " +
					"group by o " +
					"order by "+sumStatement+" desc, o.id asc")
				.setInteger("minid", StatsController.MIN_USER_ID)
				.setParameterList("classes", zuIgnorierendeSchiffsklassen)
				.setMaxResults(size)
				.list();

			url = getUserURL();
		}
		else {
			tmp = db.createQuery("select "+sumStatement+" as cnt,ally " +
					"from Ship s join s.owner o join o.ally ally join s.shiptype st left join s.modules sm " +
					"where s.id > 0 and " +
					"	ally is not null and " +
					"	o.id > :minid and " +
					"	(o.vaccount=0 or o.wait4vac>0) and " +
					"	(((sm.id is null) and st.cost>0) or ((sm.id is not null) and sm.cost>0)) and " +
					"	st.shipClass not in (:classes) " +
					"group by ally " +
					"order by "+sumStatement+" desc, ally.id asc")
					.setInteger("minid", StatsController.MIN_USER_ID)
					.setParameterList("classes", zuIgnorierendeSchiffsklassen)
					.setMaxResults(size)
					.list();

			url = getAllyURL();
		}

		Writer echo = getContext().getResponse().getWriter();

		echo.append("<h1>Die größten Flotten:</h1>");
		echo.append("<table class='stats'>\n");

		int count = 0;
		for( Object obj : tmp )
		{
			Object[] values = (Object[])obj;

			echo.append("<tr><td>"+(count+1)+".</td>\n");
			if( values[1] instanceof User )
			{
				User owner = (User)values[1];
				echo.append("<td><a class=\"profile\" href=\""+url+owner.getId()+"\">"+ Common._title(owner.getName())+" ("+owner.getId()+")</a></td>\n");
			}
			else
			{
				Ally ally = (Ally)values[1];
				echo.append("<td><a class=\"profile\" href=\""+url+ally.getId()+"\">"+ Common._title(ally.getName())+" ("+ally.getId()+")</a></td>\n");
			}

			count++;
			echo.append("</tr>");
		}

		echo.append("</table>\n");
	}
}
