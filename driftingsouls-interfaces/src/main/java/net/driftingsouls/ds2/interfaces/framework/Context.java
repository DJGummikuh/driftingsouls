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
package net.driftingsouls.ds2.interfaces.framework;


import net.driftingsouls.ds2.interfaces.framework.pipeline.Request;
import net.driftingsouls.ds2.interfaces.framework.pipeline.Response;

/**
 * Repraesentiert einen Kontext. Bei einem Kontext handelt es sich um einen
 * Aufruf des Systems z.B. ueber HTTP. Die mit dem Aufruf verbundenen Daten koennen
 * hier abgerufen werden. Zudem kann die Ausgabe hier getaetigt werden.
 * Zudem verfuegt der Kontext noch ueber Caches fuer diverse Dinge, die direkt
 * an einen Aufruf gebunden sein muessen und nicht dauerhaft gecached werden koennen.
 *
 * @author Christopher Jung
 *
 * X = javax.persistence.EntityManager
 * Y = org.hibernate.Session
 */
public interface Context<X,Y> extends PermissionResolver {
	/**
	 * Gibt die passende Instanz des EntityManagers zurueck.
	 * @return Die EntityManager-Instanz
	 * @see #getDB()
	 */
	public X getEM();

	/**
	 * Liefert eine Instanz der berwendeten DB-Session zurueck.
	 * @return Die DB-Session
	 */
	public Y getDB();

	/**
	 * Liefert den gerade aktiven User.
	 *
	 * @return Das zum gerade aktiven User gehoerende User-Objekt
	 */
	public IBasicUser getActiveUser();

	/**
	 * Setzt den gerade aktiven User auf das angebene User-Objekt.
	 *
	 * @param user Der neue aktive User
	 */
	public void setActiveUser(IBasicUser user);

	/**
	 * Fuegt einen Fehler zur Fehlerliste hinzu.
	 *
	 * @param error Die Beschreibung des Fehlers
	 */
	public void addError(String error);

	/**
	 * Fuegt einen Fehler zur Fehlerliste hinzu und bietet zudem eine Ausweich-URL an.
	 *
	 * @param error Die Beschreibung des Fehlers
	 * @param link Die Ausweich-URL
	 */
	public void addError(String error, String link);

	/**
	 * Liefert den letzten Fehler zurueck.
	 *
	 * @return Der letzte Fehlers
	 *
	 * @see #addError(String, String)
	 * @see #addError(String)
	 */
	public Error getLastError();

	/**
	 * Liefert eine Liste aller Fehler zurueck.
	 *
	 * @return Eine Liste aller Fehlerbeschreibungen
	 */
	public Error[] getErrorList();

	/**
	 * Liefert die Request fuer diesen Aufruf.
	 * @return Die Request des Aufrufs
	 */
	public Request getRequest();

	/**
	 * Liefert die zum Aufruf gehoerende Response.
	 * @return Die Response des Aufrufs
	 */
	public Response getResponse();

	/**
	 * Setzt das zum Aufruf gehoerende Response-Objekt.
	 * @param response Das Response-Objekt
	 */
	public void setResponse(Response response);

	/**
	 * Liefert eine unter einem bestimmten Scope einmalige Instanz einer Klasse.
	 * Sollte keine Instanz dieser Klasse im Scope vorhanden sein,
	 * wird dieses erstellt.
	 *
	 * @param <T> Eine Klasse
	 * @param cls Die gewuenschte Klasse
	 * @return Eine Instanz der Klase
	 */
	public <T> T get(Class<T> cls);

	/**
	 * Entfernt die unter einem bestimmten Scope gueltige Instanz dieser Klasse.
	 * @param cls Die Klasse
	 */
	public void remove(Class<?> cls);

	/**
	 * Setzt eine Kontext-lokale Variable auf einen angegebenen Wert.
	 * @param cls Die Klasse, welche die Variable setzen moechte - fungiert als zusaetzlicher Schluessel
	 * @param varname Der Name der Variablen
	 * @param value Der neue Wert der Variablen
	 */
	public void putVariable(Class<?> cls, String varname, Object value);

	/**
	 * Liefert eine Kontext-lokale Variable zurueck.
	 * @param cls Die Klasse, welche die Variable abrufen moechte - fungiert als zusaetzlicher Schluessel
	 * @param varname Der Name der Variablen
	 * @return Die Variable oder <code>null</code>, falls die Variable nicht existiert
	 */
	public Object getVariable(Class<?> cls, String varname);

	/**
	 * Registriert einen Kontext-Observer im Kontextobjekt. Der Observer wird
	 * fortan ueber Ereignisse des Kontexts informiert
	 * @param listener Der Listener
	 */
	public void registerListener(ContextListener listener);

	/**
	 * Setzt den vom Kontext verwendeten {@link PermissionResolver}.
	 * @param permissionResolver Der PermissionResolver
	 */
	public void setPermissionResolver(PermissionResolver permissionResolver);

	/**
	 * Fuehrt ein Autowiring auf der angegebenen Beaninstanz durch. Alle in Spring
	 * hinterlegten Beans werden, sofern die entsprechende Property/Methode
	 * mit {@link org.springframework.beans.factory.annotation.Autowired} markiert ist,
	 * automatisch injiziert. Sofern die Bean als {@link org.springframework.context.ApplicationContextAware}
	 * markiert ist wird auch der {@link org.springframework.context.ApplicationContext} injiziert.
	 * @param bean Die zu verarbeitende Bean
	 */
	void autowireBean(Object bean);

	/**
	 * Ermittelt die Spring-Bean mit dem angegebenen Namen und Typ.
	 * @param cls Der Typ der Bean
	 * @param name Der Name der Bean
	 * @param <T> Der Typ der Bean
	 * @return Die Bean
	 * @throws IllegalArgumentException Falls die angegebene Bean nicht gefunden werden konnte
	 */
	<T> T getBean(Class<T> cls, String name) throws IllegalArgumentException;
}
