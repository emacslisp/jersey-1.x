/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.jersey.impl.methodparams;

import com.sun.jersey.impl.AbstractResourceTester;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.CookieParam;
import javax.ws.rs.Path;
import com.sun.jersey.impl.AbstractResourceTester;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Cookie;

/**
 *
 * @author Paul.Sandoz@Sun.Com
 */
@SuppressWarnings("unchecked")
public class CookieParamAsStringTest extends AbstractResourceTester {

    public CookieParamAsStringTest(String testName) {
        super(testName);
    }

    @Path("/")
    public static class ResourceString {
        @GET
        public String doGet(@CookieParam("arg1") String arg1, 
                @CookieParam("arg2") String arg2, @CookieParam("arg3") String arg3) {
            assertEquals("a", arg1);
            assertEquals("b", arg2);
            assertEquals("c", arg3);
            return "content";
        }
        
        @POST
        public String doPost(@CookieParam("arg1") String arg1, 
                @CookieParam("arg2") String arg2, @CookieParam("arg3") String arg3,
                String r) {
            assertEquals("a", arg1);
            assertEquals("b", arg2);
            assertEquals("c", arg3);
            assertEquals("content", r);
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringEmpty {
        @GET
        public String doGet(@CookieParam("arg1") String arg1) {
            assertEquals("", arg1);
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringAbsent {
        @GET
        public String doGet(@CookieParam("arg1") String arg1) {
            assertEquals(null, arg1);
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringList {
        @GET
        @Produces("application/stringlist")
        public String doGetString(@CookieParam("args") List<String> args) {
            assertEquals("a", args.get(0));
            return "content";
        }
        
        @GET
        @Produces("application/list")
        public String doGet(@CookieParam("args") List args) {
            assertEquals(String.class, args.get(0).getClass());
            assertEquals("a", args.get(0));
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringListEmpty {
        @GET
        @Produces("application/stringlist")
        public String doGetString(@CookieParam("args") List<String> args) {
            assertEquals(1, args.size());
            assertEquals("", args.get(0));
            return "content";
        }        
    }
        
    @Path("/")
    public static class ResourceStringNullDefault {
        @GET
        public String doGet(@CookieParam("arg1") String arg1, 
                @CookieParam("arg2") String arg2, @CookieParam("arg3") String arg3) {
            assertEquals(null, arg1);
            assertEquals(null, arg2);
            assertEquals(null, arg3);
            return "content";
        }        
    }
    
    @Path("/")
    public static class ResourceStringDefault {
        @GET
        public String doGet(
                @CookieParam("arg1") @DefaultValue("a") String arg1, 
                @CookieParam("arg2") @DefaultValue("b") String arg2, 
                @CookieParam("arg3") @DefaultValue("c") String arg3) {
            assertEquals("a", arg1);
            assertEquals("b", arg2);
            assertEquals("c", arg3);
            return "content";
        }        
    }
    
    @Path("/")
    public static class ResourceStringDefaultOverride {
        @GET
        public String doGet(
                @CookieParam("arg1") @DefaultValue("a") String arg1, 
                @CookieParam("arg2") @DefaultValue("b") String arg2, 
                @CookieParam("arg3") @DefaultValue("c") String arg3) {
            assertEquals("d", arg1);
            assertEquals("e", arg2);
            assertEquals("f", arg3);
            return "content";
        }        
    }
    
    @Path("/")
    public static class ResourceStringListEmptyDefault {
        @GET
        @Produces("application/stringlist")
        public String doGetString(
                @CookieParam("args") List<String> args) {
            assertEquals(0, args.size());
            return "content";
        }
        
        @GET
        @Produces("application/list")
        public String doGet(
                @CookieParam("args") List args) {
            assertEquals(0, args.size());
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringListDefault {
        @GET
        @Produces("application/stringlist")
        public String doGetString(
                @CookieParam("args") @DefaultValue("a") List<String> args) {
            assertEquals("a", args.get(0));
            return "content";
        }
        
        @GET
        @Produces("application/list")
        public String doGet(
                @CookieParam("args") @DefaultValue("a") List args) {
            assertEquals(String.class, args.get(0).getClass());
            assertEquals("a", args.get(0));
            return "content";
        }
    }
    
    @Path("/")
    public static class ResourceStringListDefaultOverride {
        @GET
        @Produces("application/stringlist")
        public String doGetString(
                @CookieParam("args") @DefaultValue("a") List<String> args) {
            assertEquals("b", args.get(0));
            return "content";
        }
        
        @GET
        @Produces("application/list")
        public String doGet(
                @CookieParam("args") @DefaultValue("a") List args) {
            assertEquals(String.class, args.get(0).getClass());
            assertEquals("b", args.get(0));
            return "content";
        }
    }
    
    public void testStringGet() {
        initiateWebApplication(ResourceString.class);
        
        resource("/").
            cookie(new Cookie("arg1", "a")).
            cookie(new Cookie("arg2", "b")).
            cookie(new Cookie("arg3", "c")).
            get(String.class);
    }
    
    public void testStringEmptyGet() {
        initiateWebApplication(ResourceStringEmpty.class);
        
        resource("/").
            cookie(new Cookie("arg1", "")).
            get(String.class);
    }
    
    public void testStringAbsentGet() {
        initiateWebApplication(ResourceStringAbsent.class);
        
        resource("/").
            get(String.class);
    }
    
    public void testStringPost() {
        initiateWebApplication(ResourceString.class);
        
        String s = resource("/").
            entity("content").
            cookie(new Cookie("arg1", "a")).
            cookie(new Cookie("arg2", "b")).
            cookie(new Cookie("arg3", "c")).
            post(String.class);
        
        assertEquals("content", s);
    }
    
    public void testStringListGet() {
        initiateWebApplication(ResourceStringList.class);
        
        resource("/").
            accept("application/stringlist").
            cookie(new Cookie("args", "a")).
            get(String.class);
    }
    
    public void testStringListEmptyGet() {
        initiateWebApplication(ResourceStringListEmpty.class);
        
        resource("/").
            accept("application/stringlist").
            cookie(new Cookie("args", "")).
            get(String.class);
    }
    
    public void testListGet() {
        initiateWebApplication(ResourceStringList.class);
        
        resource("/").
            accept("application/list").
            cookie(new Cookie("args", "a")).
            get(String.class);
    }
    
    public void testStringNullDefault() {
        initiateWebApplication(ResourceStringNullDefault.class);
        
        resource("/").get(String.class);
    }
    
    public void testStringDefault() {
        initiateWebApplication(ResourceStringDefault.class);
        
        resource("/").get(String.class);
    }
    
    public void testStringDefaultOverride() {
        initiateWebApplication(ResourceStringDefaultOverride.class);
        
        resource("/").
            cookie(new Cookie("arg1", "d")).
            cookie(new Cookie("arg2", "e")).
            cookie(new Cookie("arg3", "f")).
            get(String.class);
    }
    
    public void testStringListEmptyDefault() {
        initiateWebApplication(ResourceStringListEmptyDefault.class);
        
        resource("/").
            accept("application/stringlist").
            get(String.class);
    }
    
    public void testListEmptyDefault() {
        initiateWebApplication(ResourceStringListEmptyDefault.class);
        
        resource("/").
            accept("application/list").
            get(String.class);
    }
    
    public void testStringListDefault() {
        initiateWebApplication(ResourceStringListDefault.class);
        
        resource("/").
            accept("application/stringlist").
            get(String.class);
    }
    
    public void testListDefault() {
        initiateWebApplication(ResourceStringListDefault.class);
        
        resource("/").
            accept("application/list").
            get(String.class);
    }
    
    public void testListDefaultOverride() {
        initiateWebApplication(ResourceStringListDefaultOverride.class);
        
        resource("/").
            accept("application/list").
            cookie(new Cookie("args", "b")).
            get(String.class);
    }
}
