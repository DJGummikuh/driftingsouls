package net.driftingsouls.ds2.server.modules;

import java.io.IOException;

import net.driftingsouls.ds2.interfaces.framework.templates.ITemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;

import net.driftingsouls.ds2.interfaces.annotations.controllers.Action;
import net.driftingsouls.ds2.interfaces.annotations.controllers.KeinLoginNotwendig;
import net.driftingsouls.ds2.interfaces.annotations.pipeline.Module;
import net.driftingsouls.ds2.server.entities.NewsEntry;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.interfaces.framework.pipeline.controllers.ActionType;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.Controller;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.EmptyHeaderOutputHandler;
import net.driftingsouls.ds2.server.framework.templates.TemplateViewResultFactory;

/**
 * Ein einzelner Newseintrag.
 *
 * @author Sebastian Gift
 */
@KeinLoginNotwendig
@Module(name = "newsdetail")
public class NewsDetailController extends Controller
{
	private TemplateViewResultFactory templateViewResultFactory;

	@Autowired
	public NewsDetailController(TemplateViewResultFactory templateViewResultFactory)
	{
		this.templateViewResultFactory = templateViewResultFactory;
	}

	/**
	 * Zeigt den Newseintrag an.
	 */
	@Action(value = ActionType.DEFAULT, outputHandler = EmptyHeaderOutputHandler.class)
	public ITemplateEngine defaultAction(NewsEntry newsid) throws IOException
	{
		ITemplateEngine t = templateViewResultFactory.createFor(this);

		if (newsid != null)
		{
			t.setVar("news.headline", newsid.getTitle(),
					"news.date", Common.date("d.m.Y H:i", newsid.getDate()),
					"news.text", Common._text(newsid.getNewsText()));
		}
		else
		{
			t.setVar("news.headline", "ES EXISTIERT KEIN EINTRAG MIT DIESER ID");
		}

		return t;
	}
}
