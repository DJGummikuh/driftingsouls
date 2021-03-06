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

/**
 * Fehler bei ungueltigen Rollendefinitionen.
 * @author Christopher Jung
 *
 */
public class IllegalRoleDefinitionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt eine neue Exception.
	 * @param message Die Fehlerbeschreibung
	 * @param cause Der Grund
	 */
	public IllegalRoleDefinitionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erstellt eine neue Exception.
	 * @param message Die Fehlerbeschreibung
	 */
	public IllegalRoleDefinitionException(String message) {
		super(message);
	}
}
