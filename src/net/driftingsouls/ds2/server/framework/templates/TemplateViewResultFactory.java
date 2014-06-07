package net.driftingsouls.ds2.server.framework.templates;

import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.framework.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class TemplateViewResultFactory
{
	private static final String MASTERTEMPLATE = "__MASTERTEMPLATE";
	private Version version;

	@Autowired
	public TemplateViewResultFactory(Version version)
	{
		this.version = version;
	}

	private void setzeTemplateViaControllerNamen(TemplateEngine engine, Object controller)
	{
		String clsName = controller.getClass().getSimpleName();
		if( clsName.endsWith("Controller") ) {
			clsName = clsName.substring(0, clsName.length()-"Controller".length());
		}
		setTemplate(engine, clsName.toLowerCase() + ".html");
	}

	private TemplateEngine createTemplateEngine(String masterTemplateId)
	{
		TemplateEngine templateEngine = new TemplateEngine(masterTemplateId);

		templateEngine.setVar("global.module", ContextMap.getContext().getRequest().getParameter("module"));
		templateEngine.setVar("global.version", version.getVersion());

		return templateEngine;
	}

	private void setTemplate(TemplateEngine templateEngine, String file)
	{
		if (!templateEngine.setFile(MASTERTEMPLATE, file))
		{
			throw new IllegalArgumentException("Konnte Template nicht laden: "+file);
		}

		if (ContextMap.getContext().getActiveUser() != null)
		{
			ContextMap.getContext().getActiveUser().setTemplateVars(templateEngine);
		}
	}

	/**
	 * Erzeugt eine Templateinstanz fuer die angegebene Controllerklasse.
	 * Der Templatename wird aus dem Namen des Controllers abgeleitet.
	 * @param controller Der Controller
	 * @return Die Templateinstanz
	 */
	public TemplateEngine createFor(@Nonnull Object controller)
	{
		TemplateEngine engine = createTemplateEngine(MASTERTEMPLATE);
		setzeTemplateViaControllerNamen(engine, controller);

		return engine;
	}

	/**
	 * Erzeugt eine Templateinstanz fuer das angegebene Template.
	 * @param templateName Der Controller
	 * @return Die Templateinstanz
	 */
	public TemplateEngine createForTemplate(@Nonnull String templateName)
	{
		TemplateEngine engine = createTemplateEngine(MASTERTEMPLATE);
		setTemplate(engine, templateName);

		return engine;
	}
}
