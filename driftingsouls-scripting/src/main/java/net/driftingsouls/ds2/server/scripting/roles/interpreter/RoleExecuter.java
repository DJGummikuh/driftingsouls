/*
 *	Drifting Souls 2
 *	Copyright (c) 2007 Christopher Jung
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
package net.driftingsouls.ds2.server.scripting.roles.interpreter;

import net.driftingsouls.ds2.server.scripting.roles.Role;

import javax.script.ScriptContext;
import javax.script.ScriptException;

/**
 * Interface fuer Klassen, welche eine konkrete Rollendefinition ausfuehren koennen.
 * @author Christopher Jung
 *
 */
public interface RoleExecuter {
	/**
	 * Gibt die Instanz der Rollenimplementierung fuer diese Ausfuehrung der Rolle zurueck.
	 * @return Die Instanz
	 */
	public Role getRole();
	
	/**
	 * Fuehrt die Rolle aus.
	 * @param context Der Ausfuehrungskontext
	 * @throws ScriptException Falls die Ausfuehrung mit einem Fehler abbricht
	 */
	public void execute(ScriptContext context) throws ScriptException;
}
