package io.quarkiverse.jcacheredis;

import java.util.UUID;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    /*
     * Using cacheName as CacheKey
     */
    @CachePut(cacheName = "ping-response")
    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse ping() {
        return new MyResponse("pong id=" + UUID.randomUUID().toString());
    }

    /*
     * Using the value of name arg as CacheKey
     */
    @CachePut(cacheName = "term-response")
    @GET
    @Path("/term")
    @Produces(MediaType.APPLICATION_JSON)
    public Found query(@QueryParam("name") @CacheKey String name) {

        var found = new Found();
        found.setTerm(name + " [" + UUID.randomUUID().toString() + "]");

        return found;
    }

    /*
     * Using the values of name and sort args as CacheKey
     */
    @CachePut(cacheName = "sort-response")
    @GET
    @Path("/sort")
    @Produces(MediaType.APPLICATION_JSON)
    public Found query(@QueryParam("name") @CacheKey String name, @QueryParam("sort") @CacheKey String sort) {

        var found = new Found();
        found.setTerm("name=" + name + ", sort=" + sort + " [" + UUID.randomUUID() + "]");

        return found;
    }
}
