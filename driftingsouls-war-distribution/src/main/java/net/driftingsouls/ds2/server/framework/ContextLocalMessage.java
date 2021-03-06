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
package net.driftingsouls.ds2.server.framework;

import net.driftingsouls.ds2.interfaces.framework.ContextListener;
import net.driftingsouls.ds2.interfaces.framework.ContextMap;

/**
 * Lognachrichten auf Contextbasis.
 * @author Christopher Jung
 *
 */
public class ContextLocalMessage implements ContextListener {
	private final ThreadLocal<StringBuilder> msg = new ThreadLocal<StringBuilder>() {
		@Override
		protected StringBuilder initialValue() {
			ContextMap.getContext().registerListener(ContextLocalMessage.this);
			return new StringBuilder();
		}
	};
	
	/**
	 * Konstruktor.
	 *
	 */
	public ContextLocalMessage() {
		// EMPTY
	}
	
	/**
	 * Gibt die zuletzt gesetzen Nachrichten zurueck und leert dann den Nachrichtenpuffer.
	 * @return Die zuletzt gesetzten Nachrichten
	 */
	public String getMessage() {
		String msgString = msg.get().toString();
		msg.get().setLength(0);
		return msgString;
	}
	
	/**
	 * Gibt den internen Nachrichtenpuffer des Threads als <code>StringBuilder</code> zurueck.
	 * Aenderungen im Objekt wirken sich direkt auf den Puffer aus.
	 * @return Der interne Nachrichtenpuffer
	 */
	public StringBuilder get() {
		return msg.get();
	}

	@Override
	public void onContextDestory() {
		msg.remove();
	}
}
