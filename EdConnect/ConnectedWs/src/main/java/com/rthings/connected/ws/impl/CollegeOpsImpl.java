package com.rthings.connected.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.rthings.connected.beans.CollegeAbout;
import com.rthings.connected.ws.CollegeOps;

@Path("/college")
public class CollegeOpsImpl implements CollegeOps {

	@Path("/getAllCollegesAboutInfo")
	@GET
	public Response getCollegeAboutInfo() {
		// TODO Auto-generated method stub
		List<CollegeAbout> abouts= new ArrayList<CollegeAbout>();
		
		for(int i=0;i<10;i++){
			CollegeAbout about= new CollegeAbout();
			about.setCollegeId("svpcet"+i);
		about.setDesc("chu chu chu chuchu chu chu chu chu chu chuchu chu chuchuchu chu chu chu chu chu chu chu chu chu chu chu chu chu chu chu chu "+i);
		if(i<5)
		about.setRating(i);
		else
			about.setRating(i-5);
		abouts.add(about);
		}
		Gson gson= new Gson();
		
		return Response.status(200).header("Access-Control-Allow-Origin" , "http://localhost:9000").entity(gson.toJson(abouts)).build();
		 
	}
	
}
