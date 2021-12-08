package io.quarkiverse.jcacheredis;

import java.util.UUID;

import javax.cache.annotation.CachePut;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @CachePut(cacheName = "ping-response")
    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse ping() {
        return new MyResponse("pong id=" + UUID.randomUUID().toString());
    }
}
