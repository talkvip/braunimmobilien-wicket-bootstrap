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
package org.apache.cocoon.sample.controller;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.cocoon.configuration.Settings;
import org.apache.cocoon.rest.controller.annotation.RESTController;
import org.apache.cocoon.rest.controller.annotation.RequestParameter;
import org.apache.cocoon.rest.controller.annotation.SitemapParameter;
import org.apache.cocoon.rest.controller.method.Get;
import org.apache.cocoon.rest.controller.response.RestResponse;
import org.apache.cocoon.rest.controller.response.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angstatus;
import java.util.ArrayList;
import java.util.List;
@RESTController
public class DemoRESTController implements Get {
  @Autowired
    private AngebotManager angebotManager;
    @SitemapParameter
    private String id;

    @SitemapParameter
    private String name;

    @RequestParameter
    private String reqparam;

    @Autowired
    private Settings settings;

    @Override
    public RestResponse doGet() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
    
      
      
        Angebot angebot=angebotManager.get("RH996");
       System.err.println("hhhhhhhhhhhhhhhhhhhhhhhhhh"+angebot);

        data.put("id", this.id);
        data.put("name", this.name);
        data.put("reqparam", this.reqparam);
       Iterator it= angebot.getAngobjzuords().iterator();
       while(it.hasNext()){
       Angobjzuord angobjzuord=(Angobjzuord)it.next();
       angobjzuord.getObjekte().setObjperszuords(null);
       angobjzuord.getObjekte().setAngobjzuords(null);
        angobjzuord.getObjekte().setNachweise(null);
         angobjzuord.getObjekte().setScouts(null);
         angobjzuord.getObjekte().getStrasse();
         angobjzuord.getObjekte().getStrasse().getOrt();
       angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
         angobjzuord.getObjekte().getStrasse().setObjekte(null);
         angobjzuord.getObjekte().getStrasse().setPersonen(null);
    angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);
       }
        
        angebot.setNachweise(null);
        angebot.setNachweise1(null);
        angebot.setNachweise2(null);
     data.put("angebot", angebot);
      data.put("testProperty", this.settings.getProperty("testProperty"));
System.err.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB richtige Sitemap");
        return new URLResponse("servlet:/controller/screen", data);
//return new URLResponse("servlet:/controllerbraun/xml/angebot", data);
    }
 

       

}
