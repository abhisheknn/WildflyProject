package com.rthings.connected.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class TestWebservice {

	@Path("/get")
	@GET
	public Response getResponsse(){
		
		return Response.ok("ok").build();
	}
}
