package net.driftingsouls.ds2.server.comm;

import net.driftingsouls.ds2.interfaces.annotations.controllers.UrlParamKonverterFuer;
import net.driftingsouls.ds2.server.entities.User;
import net.driftingsouls.ds2.interfaces.framework.ContextMap;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.ParameterReader;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.UrlParamKonverter;

/**
 * Konvertiert einen URL-Parameter in einen Ordner mittels der Ordner-ID.
 */
@UrlParamKonverterFuer(Ordner.class)
public class OrdnerUrlParamKonverter implements UrlParamKonverter<Ordner>
{
	@Override
	public Ordner konvertiere(ParameterReader parameterReader, String parameterName)
	{
		int id = (Integer) parameterReader.readParameterAsType(parameterName, Integer.TYPE);
		User user = (User) ContextMap.getContext().getActiveUser();
		if (user != null)
		{
			return Ordner.getOrdnerByID(id, user);
		}
		return Ordner.getOrdnerByID(id);
	}
}
