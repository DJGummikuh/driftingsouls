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
package net.driftingsouls.ds2.server.config;

/**
 * Repraesentiert einen Rang in DS
 * @author Christopher Jung
 *
 */
public class Rang {
	private int id;
	private String name;	
	
	/**
	 * Erstellt einen neuen Rang
	 * @param id die ID des Ranges
	 * @param name der Name des Ranges
	 */
	protected Rang(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gibt die ID des Ranges zurueck
	 * @return die ID des Ranges
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Gibt den Namen des Ranges zurueck
	 * @return der Name des Ranges
	 */
	public String getName() {
		return name;
	}
}
