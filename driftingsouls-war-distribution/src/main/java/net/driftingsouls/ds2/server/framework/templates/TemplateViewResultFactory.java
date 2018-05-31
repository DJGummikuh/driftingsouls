package net.driftingsouls.ds2.server.framework.templates;

import net.driftingsouls.ds2.interfaces.framework.templates.ITemplateEngine;
import net.driftingsouls.ds2.interfaces.framework.ContextMap;
import net.driftingsouls.ds2.server.framework.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class TemplateViewResultFactory
{
	private static final String MASTERTEMPLATE = "__MASTERTEMPLATE";
	private Version version;
	private TemplateLoader templateLoader;

	@Autowired
	public TemplateViewResultFactory(Version version, TemplateLoader templateLoader)
	{
		this.version = version;
		this.templateLoader = templateLoader;
	}

	private void setzeTemplateViaControllerNamen(ITemplateEngine engine, Object controller)
	{
		String clsName = controller.getClass().getSimpleName();
		if( clsName.endsWith("Controller") ) {
			clsName = clsName.substring(0, clsName.length()-"Controller".length());
		}
		setTemplate(engine, clsName.toLowerCase() + ".html");
	}

	private ITemplateEngine createTemplateEngine(String masterTemplateId)
	{
		ITemplateEngine templateEngine = new TemplateEngine(masterTemplateId, templateLoader);

		templateEngine.setVar("global.module", ContextMap.getContext().getRequest().getParameter("module"));
		templateEngine.setVar("global.version", version.getVersion());

		return templateEngine;
	}

	private void setTemplate(ITemplateEngine templateEngine, String file)
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
	 * Erzeugt eine leere Templateinstanz ohne geladenes Haupttemplate.
	 * @return Die Templateinstanz
	 */
	public ITemplateEngine createEmpty()
	{
		return createTemplateEngine(MASTERTEMPLATE);
	}

	/**
	 * Erzeugt eine Templateinstanz fuer die angegebene Controllerklasse.
	 * Der Templatename wird aus dem Namen des Controllers abgeleitet.
	 * @param controller Der Controller
	 * @return Die Templateinstanz
	 */
	public ITemplateEngine createFor(@Nonnull Object controller)
	{
		ITemplateEngine engine = createTemplateEngine(MASTERTEMPLATE);
		setzeTemplateViaControllerNamen(engine, controller);

		return engine;
	}

	/**
	 * Erzeugt eine Templateinstanz fuer das angegebene Template.
	 * @param templateName Der Controller
	 * @return Die Templateinstanz
	 */
	public ITemplateEngine createForTemplate(@Nonnull String templateName)
	{
		ITemplateEngine engine = createTemplateEngine(MASTERTEMPLATE);
		setTemplate(engine, templateName);

		return engine;
	}
}
