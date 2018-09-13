package com.gol.wallet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("wallet")
@Stateless
@Consumes("application/json")
@Produces("application/json")
public class WalletResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletResource.class);

    @Inject
    WalletService walletService;


    @Path("createUser")
    @GET
    public Response createUser(@QueryParam("userName") String userName, @QueryParam("userId") Long userId, @QueryParam("balance") Long balance ) {
        try {
            walletService.insertData(userName, userId, balance);
            return Response.status(Response.Status.OK).entity("OK").build();
        }
        catch (Exception e) {
            LOGGER.error("exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("errorMessage"  + " " + e.getMessage()  + " " + e.getCause()).build();
        }
    }

    @Path("fetchBalance")
    @GET
    public Response fetchBalanceOfUser(@QueryParam("userId") Long userId, @QueryParam("transactionId") String transactionId ) {
        try {
            return Response.status(Response.Status.OK).entity(walletService.fetchCurrentBalanceOfUser(userId, transactionId)).build();
        }
        catch (Exception e) {
            LOGGER.error("exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("errorMessage"  + " " + e.getMessage()  + " " + e.getCause()).build();
        }
    }


    @Path("fetchTransactionId")
    @GET
    public Response fetchTransactionIdOfUser( @QueryParam("userId") Long userId ) {
        try {
            return Response.status(Response.Status.OK).entity(walletService.generateOrGetTransactionId(userId)).build();
        }
        catch (Exception e) {
            LOGGER.error("exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("errorMessage" + " " + e.getMessage()  + " " + e.getCause()).build();
        }
    }

    @Path("manipulateUserBalance")
    @GET
    public Response manipulateUserBalance( @QueryParam("userId") Long userId, @QueryParam("transactionId") String transactionId, @QueryParam("amount") Long amount  ) {
        try {
            return Response.status(Response.Status.OK).entity(walletService.manipulateUserBalance(userId, transactionId, amount)).build();
        }
        catch (Exception e) {
            LOGGER.error("exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("errorMessage"  + " " + e.getMessage()  + " " + e.getCause()).build();
        }
    }


}
