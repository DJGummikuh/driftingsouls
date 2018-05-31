package net.driftingsouls.ds2.interfaces.framework.pipeline;

import net.driftingsouls.ds2.interfaces.framework.pipeline.Response;

import java.io.IOException;

/**
 * Ein Ergebnis einer Controller-Action mit einer gesonderten (speziellen) Serialisierungsmethode.
 */
public interface ViewResult
{
	/**
	 * Serialisiert das Ergebnis und schreibt dieses in die Antwort.
	 * @param response Die Antwort
	 */
	public void writeToResponse(Response response) throws IOException;
}
