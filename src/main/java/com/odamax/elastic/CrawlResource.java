package com.odamax.elastic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("crawl")
@Stateless
@Consumes("application/json")
@Produces("application/json")
public class CrawlResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlResource.class);



    @Path("url")
    @GET
    public Response crawlUrl(@QueryParam("searchTerm") String searchTerm) {
        try {

            return Response.status(Response.Status.OK).entity("asdasdasdasdasd").build();
        }
        catch (Exception e) {
            LOGGER.error("exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("errorMessage", e.getMessage() + e.getCause()).build();
        }
    }






}
