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
package net.driftingsouls.ds2.server.modules;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import net.driftingsouls.ds2.server.entities.User;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.Context;
import net.driftingsouls.ds2.server.framework.pipeline.Module;
import net.driftingsouls.ds2.server.framework.pipeline.generators.Action;
import net.driftingsouls.ds2.server.framework.pipeline.generators.ActionType;
import net.driftingsouls.ds2.server.framework.pipeline.generators.DSGenerator;
import net.driftingsouls.ds2.server.modules.admin.AdminMenuEntry;
import net.driftingsouls.ds2.server.modules.admin.AdminPlugin;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

/**
 * Der Admin.
 * @author Christopher Jung
 *
 * @urlparam Integer act Die auszufuehrende Aktions-ID (ein Plugin wird ueber Seiten- und Aktions-ID identifiziert)
 * @urlparam String page Die anzuzeigende Seite (ID der Seite)
 * @urlparam Integer cleanpage Falls != 0 werden keine zusaetzlichen GUI-Elemente angezeigt
 * @urlparam String namedplugin Der Name des Plugins, welches angezeigt werden soll (Alternative zu Seiten- und Aktions-ID)
 */
@Module(name="admin")
public class AdminController extends DSGenerator {
	private static final List<Class<? extends AdminPlugin>> plugins = new ArrayList<Class<? extends AdminPlugin>>();
	static
	{
		try
		{
			URL[] urls = ClasspathUrlFinder.findResourceBases("META-INF/ds.marker");
			AnnotationDB db = new AnnotationDB();
			db.scanArchives(urls);
			SortedSet<String> entityClasses = new TreeSet<String>(db.getAnnotationIndex().get(AdminMenuEntry.class.getName()));
			for( String cls : entityClasses )
			{
				final Class<? extends AdminPlugin> adminPluginClass = Class.forName(cls)
					.asSubclass(AdminPlugin.class);
				plugins.add(adminPluginClass);
			}
		}
		catch( IOException e )
		{
			throw new RuntimeException(e);
		}
		catch( ClassNotFoundException e )
		{
			throw new RuntimeException(e);
		}
	}

	private static class MenuEntry implements Comparable<MenuEntry>
	{
		String name;
		SortedSet<MenuEntry> actions = new TreeSet<MenuEntry>();
		Class<? extends AdminPlugin> cls;

		MenuEntry( String name )
		{
			this.name = name;
		}

		@Override
		public int compareTo(MenuEntry o)
		{
			return name.compareTo(o.name);
		}

		@Override
		public boolean equals(Object object)
		{
			if(object == null)
			{
				return false;
			}

			if(object.getClass() != this.getClass())
			{
				return false;
			}

			MenuEntry other = (MenuEntry) object;
			return this.name.equals(other.name);
		}

		@Override
		public int hashCode()
		{
			return this.name.hashCode();
		}
	}

	TreeMap<String,MenuEntry> menu = new TreeMap<String,MenuEntry>();
	Set<String> validPlugins = new HashSet<String>();

	/**
	 * Konstruktor.
	 * @param context Der zu verwendende Kontext
	 */
	public AdminController(Context context)
	{
		super(context);

		parameterNumber("act");
		parameterString("page");
		parameterNumber("cleanpage");
		parameterString("namedplugin");
	}

	private void addMenuEntry( Class<? extends AdminPlugin> cls, String menuentry, String submenuentry )
	{
		if( !this.menu.containsKey(menuentry) )
		{
			this.menu.put(menuentry, new MenuEntry(menuentry));
		}

		MenuEntry entry = new MenuEntry(submenuentry);
		entry.cls = cls;
		this.menu.get(menuentry).actions.add(entry);
	}

	@Override
	protected boolean validateAndPrepare(String action)
	{
		if( !hasPermission("admin", "sichtbar") ) {
			addError("Sie sind nicht berechtigt diese Seite aufzurufen");
			return false;
		}

		for( Class<? extends AdminPlugin> cls : plugins )
		{

			if( validPlugins.contains(cls.getName()) )
			{
				continue;
			}
			if( !hasPermission("admin", cls.getSimpleName()) ) {
				continue;
			}
			validPlugins.add(cls.getName());

			AdminMenuEntry adminMenuEntry = cls.getAnnotation(AdminMenuEntry.class);

			addMenuEntry(cls, adminMenuEntry.category(), adminMenuEntry.name());
		}

		if( this.getInteger("cleanpage") > 0 )
		{
			this.setDisableDebugOutput(true);
			this.setDisablePageMenu(true);
		}

		return true;
	}

	/**
	 * Fuehrt ein Admin-Plugin aus.
	 */
	@Action(ActionType.AJAX)
	public void ajaxAction()
	{
		int act = getInteger("act");
		String page = getString("page");
		String namedplugin = getString("namedplugin");

		if( page.length() > 0 || namedplugin.length() > 0 )
		{
			if( act > 0 )
			{
				callPlugin(page, act);
			}
			else if( (namedplugin.length() > 0) && (validPlugins.contains(namedplugin)) )
			{
				callNamedPlugin(namedplugin);
			}
		}
	}

	/**
	 * Zeigt die Gui an und fuehrt ein Admin-Plugin (sofern ausgewaehlt) aus.
	 */
	@Override
	@Action(ActionType.DEFAULT)
	public void defaultAction() throws IOException
	{
		int cleanpage = getInteger("cleanpage");
		int act = getInteger("act");
		String page = getString("page");
		String namedplugin = getString("namedplugin");

		Writer echo = getContext().getResponse().getWriter();
		if( cleanpage == 0 )
		{
			echo.append("<div align=\"center\">\n");
			echo.append(Common.tableBegin( 700, "center" ));
			echo.append("<table class=\"noBorderX\" width=\"700\"><tr><td align=\"center\" class=\"noBorderX\">\n");
			boolean first = true;
			for( MenuEntry entry : this.menu.values() )
			{
				if( first )
				{
					echo.append("<a class=\"forschinfo\" href=\"./ds?module=admin&page="+entry.name+"\">"+entry.name+"</a>\n");
					first = false;
				}
				else
				{
					echo.append(" | <a class=\"forschinfo\" href=\"./ds?module=admin&page="+entry.name+"\">"+entry.name+"</a>\n");
				}
			}
			echo.append("</td></tr></table>\n");
			echo.append(Common.tableEnd());
			echo.append("</div><br />\n");
		}

		if( page.length() > 0 || namedplugin.length() > 0 )
		{
			if( cleanpage == 0 )
			{
				echo.append("<table class=\"noBorder\"><tr><td class=\"noBorder\" valign=\"top\">\n");

				echo.append(Common.tableBegin( 220, "left" ));
				echo.append("<table class=\"noBorderX\" width=\"100%\">\n");
				echo.append("<tr><td align=\"center\" class=\"noBorderX\">Aktionen:</td></tr>\n");
				if( this.menu.containsKey(page) && (this.menu.get(page).actions.size() > 0) )
				{
					SortedSet<MenuEntry> actions = this.menu.get(page).actions;
					int index = 1;
					for( MenuEntry entry : actions )
					{
						echo.append("<tr><td align=\"left\" class=\"noBorderX\"><a class=\"forschinfo\" href=\"./ds?module=admin&page="+page+"&act="+(index++)+"\">"+entry.name+"</a></td></tr>\n");
					}
				}
				echo.append("</table>\n");
				echo.append(Common.tableEnd());

				echo.append("</td><td class=\"noBorder\" valign=\"top\" width=\"40\">&nbsp;&nbsp;&nbsp;</td>\n");
				echo.append("<td class=\"noBorder\" valign=\"top\">\n");
			}
			if( act > 0 )
			{
				callPlugin(page, act);
			}
			else if( (namedplugin.length() > 0) && (validPlugins.contains(namedplugin)) )
			{
				callNamedPlugin(namedplugin);
			}

			if( cleanpage == 0 )
			{
				echo.append("</td></tr></table>");
			}
		}
	}

	private void callNamedPlugin(String namedplugin)
	{
		try {
			int act = 0;
			String page = "";

			Class<? extends AdminPlugin> aClass = null;
			for( String aPage : this.menu.keySet() )
			{
				int index = 1;
				SortedSet<MenuEntry> actions = this.menu.get(aPage).actions;
				for( MenuEntry entry : actions )
				{
					if( entry.cls.getName().equals(namedplugin) )
					{
						aClass = entry.cls;
						page = aPage;
						act = index;
						break;
					}
					index++;
				}
			}

			AdminPlugin plugin = aClass.newInstance();
			plugin.output(this, page, act);
		}
		catch( IOException e )
		{
			addError("Fehler beim Aufruf des Admin-Plugins: "+e);

			throw new RuntimeException(e);
		}
		catch( RuntimeException e )
		{
			addError("Fehler beim Aufruf des Admin-Plugins: "+e);

			throw e;
		}
		catch( InstantiationException e )
		{
			addError("Fehler beim Aufruf des Admin-Plugins: "+e);
			e.printStackTrace();
		}
		catch( IllegalAccessException e )
		{
			addError("Fehler beim Aufruf des Admin-Plugins: "+e);
			e.printStackTrace();
		}
	}

	private void callPlugin(String page, int act)
	{
		if( this.menu.containsKey(page) && (this.menu.get(page).actions.size() > 0) )
		{
			SortedSet<MenuEntry> actions = this.menu.get(page).actions;
			if( act <= actions.size() )
			{
				int index = 1;
				MenuEntry calledEntry = null;
				for( MenuEntry entry : actions )
				{
					if( act == index++ )
					{
						calledEntry = entry;
						break;
					}
				}
				Class<? extends AdminPlugin> cls = calledEntry.cls;
				try
				{
					AdminPlugin plugin;
					plugin = cls.newInstance();
					plugin.output(this, page, act);
				}
				catch( InstantiationException e )
				{
					addError("Fehler beim Aufruf des Admin-Plugins: "+e);
				}
				catch( IllegalAccessException e )
				{
					addError("Fehler beim Aufruf des Admin-Plugins: "+e);
				}
				catch( IOException e )
				{
					addError("Fehler beim Aufruf des Admin-Plugins: "+e);

					throw new RuntimeException(e);
				}
			}
		}
	}
}
