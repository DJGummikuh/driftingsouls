package net.driftingsouls.ds2.server.framework.pipeline.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import net.driftingsouls.ds2.interfaces.annotations.controllers.Action;
import net.driftingsouls.ds2.interfaces.annotations.pipeline.Module;
import net.driftingsouls.ds2.server.framework.Configuration;

/**
 * Standardmodul fuer den Angular-Client. Dient
 * als Einstiegsmodul in den Javascript-Client.
 * @author Christopher Jung
 *
 */
@Module(name="client")
public class ClientController extends Controller
{
	/**
	 * Konstruktor.
	 */
	public ClientController() {
		super();
	}

	@Action(value = ActionType.DEFAULT, outputHandler = NgAppHtmlOutputHandler.class)
	public final void defaultAction() throws IOException
	{
		Writer echo = getResponse().getWriter();

		File tmpl = new File(Configuration.getAbsolutePath()+"data/cltemplates/ds.html");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(tmpl), "UTF-8")))
		{
			char[] buffer = new char[8192];
			int cnt;
			while ((cnt = reader.read(buffer)) != -1)
			{
				echo.write(buffer, 0, cnt);
			}
		}
	}
}
