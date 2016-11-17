/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Result;
import ninja.Results;
import ninja.servlet.util.Request;
import ninja.session.FlashScope;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.inject.Singleton;


@Singleton
public class ApplicationController {

    public Result index() {

        return Results.html();

    }
    
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }
    
    public Result login() {
    	return Results.html();
    	
    }
    
    public Result logar(@Request HttpServletRequest request, FlashScope flashScope) {
    	Map<String, Object> mapa = new HashMap<>();
    	mapa.put("login", request.getParameter("login"));
    	mapa.put("senha", request.getParameter("senha"));

    	flashScope.clearCurrentFlashCookieData();
    	if (request.getParameter("login") == null || request.getParameter("login").equals("")) {
    		flashScope.error("Campo Login obrigatório!");
    		return Results.html().template("views/ApplicationController/login.ftl.html").render(mapa);
    	} else {
    		flashScope.success("Login com sucesso!");
    		return Results.html().template("views/ApplicationController/bemvindo.ftl.html").render(mapa);
    	}
    }
    
    public static class SimplePojo {

        public String content;
        
    }
}
