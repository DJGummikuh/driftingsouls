package net.driftingsouls.ds2.server.framework.pipeline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import net.driftingsouls.ds2.interfaces.annotations.ViewModel;
import net.driftingsouls.ds2.interfaces.framework.pipeline.controllers.OutputHandler;
import net.driftingsouls.ds2.interfaces.framework.ContextMap;

/**
 * Ausgabeklasse fuer AJAX-Antworten.
 */
public class AjaxOutputHandler extends OutputHandler
{
	@ViewModel
	public static class ErrorResult
	{
		public static class MessageViewModel
		{
			public String type;
		}

		public static class ErrorViewModel
		{
			public String description;
			public String url;
		}

		public MessageViewModel message;
		public List<ErrorViewModel> errors = new ArrayList<>();
	}

	@Override
	public void printHeader() {}
	@Override
	public void printFooter() {}
	@Override
	public void printErrorList() throws IOException
	{
		ErrorResult result = new ErrorResult();
		for( net.driftingsouls.ds2.server.framework.pipeline.Error error : ContextMap.getContext().getErrorList() )
		{
			ErrorResult.ErrorViewModel errorObj = new ErrorResult.ErrorViewModel();
			errorObj.description = error.getDescription();
			errorObj.url = error.getUrl();
			result.errors.add(errorObj);
		}

		result.message = new ErrorResult.MessageViewModel();
		result.message.type = "errorlist";

		ContextMap.getContext().getResponse().getWriter().write(new Gson().toJson(result));
	}
}
