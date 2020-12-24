package kz.edu.nu.cs.se.hw;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import java.util.Date;

@Path("/items")
public class ListItemsService {
    
    private List<String> list = new CopyOnWriteArrayList<String>();
       
    public ListItemsService() {
    	Date date = new Date();
    	
        for (int i = 0; i < 20; i++) {
            list.add("Entry " + String.valueOf(i) + " " + "(created at: " + date + ")");
        }
        
        Collections.reverse(list);
    }
    
    @GET
    public Response getList() {
        Gson gson = new Gson();
        
        return Response.ok(gson.toJson(list)).build();
    }
    
    @GET
    @Path("clear")
    public Response clearListItems() { 	
    	list.clear();
        Gson gson = new Gson();
        
        return Response.ok(gson.toJson(list)).build();
    }
        
    @GET
    @Path("{id: [0-9]+}")
    public Response getListItem(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        
        return Response.ok(list.get(i)).build();
    }
    
    @GET
    @Path("clear/{id}")
    public Response clearListItems(@PathParam("id") String id) {
        
    	Gson gson = new Gson();
    	list.remove(Integer.parseInt(id));
        
    	return Response.ok(gson.toJson(list)).build();
    }
    
    @POST
    public Response postListItem(@FormParam("newEntry") String entry) {
    	Date date = new Date();
        list.add(0, entry + " " + "(created at: " + date + ")");

        return Response.ok().build();
    }
     
}
