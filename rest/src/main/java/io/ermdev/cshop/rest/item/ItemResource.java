package io.ermdev.cshop.rest.item;

import io.ermdev.cshop.commons.Error;
import io.ermdev.cshop.data.entity.Item;
import io.ermdev.cshop.data.service.ItemService;
import io.ermdev.cshop.exception.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("items")
public class ItemResource {

    private ItemService itemService;

    @Autowired
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    @Path("{itemId}")
    public Response getById(@PathParam("itemId") long itemId) {
        try {
            Item item = itemService.findById(itemId);
            return Response.status(Response.Status.OK).entity(item).build();
        } catch (EntityException e) {
            Error error = new Error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    public Response getAll() {
        try {
            List<Item> items = itemService.findAll();
            return Response.status(Response.Status.OK).entity(items).build();
        } catch (EntityException e) {
            Error error = new Error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @POST
    public Response add(Item item) {
        try {
            item = itemService.save(item);
            return Response.status(Response.Status.CREATED).entity(item).build();
        } catch (EntityException e) {
            Error error = new Error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @PUT
    @Path("{itemId}")
    public Response update(@PathParam("itemId") Long itemId, Item item) {
        try {
            item.setId(itemId);
            item = itemService.save(item);
            return Response.status(Response.Status.OK).entity(item).build();
        } catch (EntityException e) {
            Error error = new Error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Path("{itemId}")
    public Response delete(@PathParam("itemId") Long itemId) {
        try {
            Item item = itemService.delete(itemId);
            return Response.status(Response.Status.OK).entity(item).build();
        } catch (EntityException e) {
            Error error = new Error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
}
