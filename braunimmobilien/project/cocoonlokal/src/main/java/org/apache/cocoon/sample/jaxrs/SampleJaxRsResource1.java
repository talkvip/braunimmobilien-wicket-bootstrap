/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.sample.jaxrs;

import java.util.HashMap;
import java.util.Map;
import org.apache.cocoon.rest.controller.response.TextResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cocoon.configuration.Settings;
import org.apache.cocoon.rest.jaxrs.response.URLResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import braunimmobilien.model.Angebot;
import braunimmobilien.cocoon.Configuration;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.KonditionManager;
@Path("/sample")
//@Consumes({MediaType.APPLICATION_JSON})
//@Produces({MediaType.APPLICATION_JSON})
public class SampleJaxRsResource1 {
	 @Autowired
	    private KonditionManager konditionManager;
	 @Autowired
	    private AngstatusManager angstatusManager;
	 @Autowired
	    private AngebotManager angebotManager;
	    @Autowired
	    private NachweiseManager nachweiseManager;
	    @Autowired
		private Configuration configuration;
	      @Autowired
	    private KundeManager kundenManager;
	      @Autowired
	      private PersonManager personManager;
    private Settings settings;

    @GET
   @Consumes(MediaType.APPLICATION_JSON)
 //   @Produces(MediaType.APPLICATION_JSON)
    @Path("/angebote")
    public Response getsService(
            @Context UriInfo uriInfo, @Context Request request) {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("angebote",angebotManager.getAngebote());
         return URLResponseBuilder.newInstance("servlet:sample:/json/angebote", data).build();
    }

 @GET
    @Path("/angebote/{id}")
// @Consumes(MediaType.APPLICATION_JSON)
// @Produces(MediaType.APPLICATION_JSON)
    public Response getService(@PathParam("id") String id, @Context UriInfo uriInfo, @Context Request request) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("angebot", angebotManager.get(id));
         return URLResponseBuilder.newInstance("servlet:sample:/json/angebot", data).build();
    }
 @DELETE
 @Path("/angebote/{id}")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
 public Response deleteService(@PathParam("id") String id, @QueryParam("req-param") String reqParam,
         @Context UriInfo uriInfo, @Context Request request) {
	 System.err.println("delete "+id);
     Map<String, Object> data = new HashMap<String, Object>();
     data.put("angebot", "Exposee "+id+" deleted");
    try{ angebotManager.remove(id);}
    catch(Exception ex){
    	  data = new HashMap<String, Object>();
    	     data.put("angebot", "Error deleting Exposee "+id+": "+ex);
    }
    
 	System.err.println("delete "+id);
      return URLResponseBuilder.newInstance("servlet:sample:/json/angebotdelete", data).build();
 }


    @GET
    @Path("/sax-pipeline/unauthorized")
    public Response saxPipelineUnauthorized() {
        return URLResponseBuilder.newInstance("servlet:sample:/sax-pipeline/unauthorized").build();
    }
    
    @POST
    @Path("/angebote")
 //  @Consumes({MediaType.APPLICATION_JSON})
 //   @Produces({MediaType.APPLICATION_JSON})
    public Response insertService(Angebot angebot) {
    	angebot.setAnglagebeschreibung("ein neuer Fall");
    	angebot.setAngstatus(angstatusManager.get(new Long(1)));
    	angebot.setKondition(konditionManager.get("kon"));
    	angebot.setAnganz(new Integer(1));
    	angebotManager.save(angebot);
    	System.err.println("insert "+angebot);
    
    	 Map<String, Object> data = new HashMap<String, Object>();
    	
         data.put("angebot",angebot);
        return URLResponseBuilder.newInstance("servlet:sample:/json/angebot", data).build();
}
        

    @PUT
    @Path("/angebote")
 //  @Consumes({MediaType.APPLICATION_JSON})
 //   @Produces({MediaType.APPLICATION_JSON})
    public Response updateService(Angebot angebot) {
    	System.err.println("update "+angebot);
    	Angebot angebot1=angebotManager.get(angebot.getId());
    	angebot1.setAngkurzbeschreibung(angebot.getAngkurzbeschreibung());
    
    	angebotManager.save(angebot1);
    	
    
    	 Map<String, Object> data = new HashMap<String, Object>();
    	
         data.put("angebot",angebot1);
        return URLResponseBuilder.newInstance("servlet:sample:/json/angebot", data).build();
}

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
