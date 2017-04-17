package org.ideaexchange.ms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.graph.User;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.services.orc.log.LogLevel;
import com.microsoft.services.orc.resolvers.JavaDependencyResolver;

public abstract class OrganizationOperations {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationOperations.class);
	
	public static ListenableFuture<List<User>> getOrganizationUsers(String userAccessToken)
	{
		logger.info("Getting users in organization...");
		
		JavaDependencyResolver resolver = new JavaDependencyResolver(userAccessToken);
        resolver.getLogger().setEnabled(true);
        resolver.getLogger().setLogLevel(LogLevel.VERBOSE);
        
        GraphServiceClient client = new GraphServiceClient(GraphUtil.GRAPH_ENDPOINT_ORGANIZATION, resolver);
        
        return client.getUsers().read();
	}
}