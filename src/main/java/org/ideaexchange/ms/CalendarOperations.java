package org.ideaexchange.ms;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.services.orc.log.LogLevel;
import com.microsoft.services.orc.resolvers.JavaDependencyResolver;
import com.microsoft.services.outlook.Event;
import com.microsoft.services.outlook.fetchers.OutlookClient;

public abstract class CalendarOperations {

	private static final Logger logger = LoggerFactory.getLogger(CalendarOperations.class);
	
	public static ListenableFuture<List<Event>> getUserEvents(String userAccessToken)
	{
		logger.info("Getting calendar for user");
		
		JavaDependencyResolver resolver = new JavaDependencyResolver(userAccessToken);
        resolver.getLogger().setEnabled(true);
        resolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        
        OutlookClient client = new OutlookClient(GraphUtil.GRAPH_ENDPOINT_ME, resolver);
        ListenableFuture<String> payload = client.getMe()
    		.getCalendarView()
        	.addParameter("startdatetime", (new DateTime()).minusWeeks(1))
        	.addParameter("enddatetime", (new DateTime()).plusWeeks(1))
        	.top(10)
        	.select("Subject")
        	.readRaw();
        logger.info("");
        return GraphUtil.transformToEntityListListenableFuture(payload, Event.class, Event[].class, resolver);
	}
}