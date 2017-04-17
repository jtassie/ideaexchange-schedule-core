package org.ideaexchange.ms;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.microsoft.services.orc.core.DependencyResolver;
import com.microsoft.services.orc.log.LogLevel;

public abstract class GraphUtil {

	public final static String GRAPH_ENDPOINT_ME = "https://graph.microsoft.com/v1.0";
	public final static String GRAPH_ENDPOINT_ORGANIZATION = "https://graph.microsoft.com/v1.0/myOrganization";
	
	/**
     * Add list result callback.
     *
     * @param future the future
     */
    public static <TEntity> ListenableFuture<List<TEntity>> transformToEntityListListenableFuture(
            ListenableFuture<String> future,
            final Class<TEntity> clazz,
            final Class<TEntity[]> clazzList,
            final DependencyResolver resolver) {

        return Futures.transform(future, 
    		new AsyncFunction<String, List<TEntity>>() {
	            public ListenableFuture<List<TEntity>> apply(String payload) throws Exception {
	                SettableFuture<List<TEntity>> result = SettableFuture.create();
	                List<TEntity> list;
	                try {
	                    resolver.getLogger().log("Entity collection Deserialization Started", LogLevel.VERBOSE);
	                    	                    
	                    JsonElement root = new JsonParser().parse(payload);
	                    JsonObject k = root.getAsJsonObject();
	                    JsonElement p = k.get("value");
	                    String internalPayload = p.toString();
	                    
	                    GsonBuilder builder = new GsonBuilder();
	                    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
	                    Gson gson = builder.create();
	                    
	                    Type listType = new TypeToken<ArrayList<TEntity>>(){}.getType();
	                    list = Arrays.asList(gson.fromJson(p.getAsJsonArray(), clazzList));
	                    resolver.getLogger().log("Entity collection Deserialization Finished", LogLevel.VERBOSE);
	
	                    result.set(list);
	                } catch (Throwable t) {
	                    result.setException(t);
	                }
	
	                return result;
	            }
        	}
        );
    }
}