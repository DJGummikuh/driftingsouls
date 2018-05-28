package net.driftingsouls.ds2.server.modules;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import net.driftingsouls.ds2.interfaces.annotations.controllers.Action;
import net.driftingsouls.ds2.interfaces.annotations.controllers.KeinLoginNotwendig;
import net.driftingsouls.ds2.interfaces.annotations.pipeline.Module;
import net.driftingsouls.ds2.server.entities.NewsEntry;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.ActionType;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.Controller;
import net.driftingsouls.ds2.server.framework.pipeline.controllers.EmptyHeaderFooterOutputHandler;

/**
 * Zeigt die News der letzten Zeit als RSS Feed an.
 * 
 * @author Sebastian Gift
 */
@KeinLoginNotwendig
@Module(name="news")
public class NewsController extends Controller
{
	private Logger log = Logger.getLogger(NewsController.class);

	/**
	 * Gibt den News RSS Feed aus.
	 */
	@Action(value = ActionType.DEFAULT, outputHandler = EmptyHeaderFooterOutputHandler.class)
	public void defaultAction() throws IOException
	{
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Drifting-Souls News");
		feed.setLink("http://ds.drifting-souls.net");
		feed.setDescription("Drifting-Souls Newsfeed");

		Session db = getDB();
		List<SyndEntry> entries = new ArrayList<>();
		List<NewsEntry> allNews = Common.cast(db.createQuery("from NewsEntry").list());
		for (NewsEntry news : allNews)
		{
			SyndEntry entry = new SyndEntryImpl();
			entry.setTitle(news.getTitle());
			entry.setPublishedDate(new Date(news.getDate() * 1000));
			entry.setLink("./ds?module=newsdetail&action=default&newsid=" + news.getId());

			SyndContent description = new SyndContentImpl();
			description.setType("text/plain");
			description.setValue(news.getShortDescription());
			entry.setDescription(description);

			entries.add(entry);
		}
		feed.setEntries(entries);

		SyndFeedOutput result = new SyndFeedOutput();
		Writer writer = getContext().getResponse().getWriter();

		try
		{
			result.output(feed, writer);
		}
		catch (FeedException e)
		{
			log.error("Could not write out rss feed due to errors", e);
		}
	}
}
