package com.laptop.laptopbag;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.laptop.bag.exception.ErrorMessage;
import com.laptop.bag.interfaces.Ioperation;
import com.laptop.bag.interfaces.LaptopOperation;
import com.laptop.bag.model.LaptopDetails;

/**
 * @author rahul.rathore
 *	
 *	24-Nov-2016
 *	
 *	@see  http://localhost:8080/laptop-bag/webapi/prompt/
 */
@Path("prompt")
public class ResourceSecureWithPrompt {
	
	private Ioperation operation = new LaptopOperation();

    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/all
     * @return javax.ws.rs.core.Response
     */
    @GET
    @Path(value="all")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getIt() {
    	List<LaptopDetails> data = operation.getAllLaptops();
    	if(!data.isEmpty())
    		return Response.status(Status.OK).entity(new GenericEntity<List<LaptopDetails>>(data){}).build();
        return Response.status(Status.NO_CONTENT).entity(new GenericEntity<ErrorMessage>(
        		new ErrorMessage(Status.NO_CONTENT, "No Content"), ErrorMessage.class)).build();
    }
    
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/find/{id}
     * @param id int
     * @return javax.ws.rs.core.Response
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    @Path("find/{id}")
    public Response getUsingId(@PathParam("id") int id) {
    	LaptopDetails data = operation.searchLaptop(id);
    	if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/add
     * @param detail LaptopDetails
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response postIt(LaptopDetails detail){
    	LaptopDetails data = operation.addLaptopDetail(detail);
    	if(data.getId() < Integer.MIN_VALUE || data.getId() > Integer.MAX_VALUE)
    		return Response.status(Status.BAD_REQUEST).entity("").build();
    	return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
    }
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/delete/{id}
     * @param id int
     * @return javax.ws.rs.core.Response
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteIt(@PathParam ("id") int id) {
		int data = operation.deleteLaptopBag(id);
		if(data != -1)
    		return Response.status(Status.OK).entity(data).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/update
     * @param detail LaptopDetails
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response putIt(LaptopDetails detail) {
		LaptopDetails data = operation.updateLaptopDetail(detail);
		if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/prompt/ping
     * @param text String
     * @return String
     */
    @GET
    @Path("ping/{message}") 
    @Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String pingAlive(@PathParam("message") String text) {
		return String.format("%1s %2s", "Hi!",text);
	}
}
