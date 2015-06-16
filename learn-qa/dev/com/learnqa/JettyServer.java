package com.learnqa;

import com.coachqa.mvc.MyWebApplicationInitializer;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.annotations.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.springframework.web.WebApplicationInitializer;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tutorial available at http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
 * @author anigam
 *
 */

public class JettyServer {


	/**
	 * Refer:
	 * 	http://stackoverflow.com/questions/13222071/spring-3-1-webapplicationinitializer-embedded-jetty-8-annotationconfiguration
	 * 	http://stackoverflow.com/questions/9526604/jetty-8-1-1-v20120215-in-eclipse-with-webapp-jsf-maven
	 *
	 */
	public static void runServer3() throws Exception {
		Server server = new Server(8090);

		WebAppContext context = new WebAppContext();
		context.setResourceBase("src/main/webapp");
        // context.setBaseResource(Resource.newClassPathResource("src/main/webapp"));
		context.setContextPath("/");

        // http://stackoverflow.com/questions/13222071/spring-3-1-webapplicationinitializer-embedded-jetty-8-annotationconfiguration
        context.setConfigurations(new Configuration[] {
                new WebXmlConfiguration(),
                new AnnotationConfiguration() {
                    @Override
                    public void preConfigure(WebAppContext context) throws Exception {
                        MultiMap<String> map = new MultiMap<String>();
                        map.add(WebApplicationInitializer.class.getName(), MyWebApplicationInitializer.class.getName());
                        context.setAttribute(CLASS_INHERITANCE_MAP, map);
                        _classInheritanceHandler = new ClassInheritanceHandler(map);
                    }
                }
        });

		context.setParentLoaderPriority(true);

		server.setHandler(context);
		server.start();
		System.out.println("Server started...");
		server.join();
	}

	public static void runJettyServer2point5(String[] args) throws Exception {

		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
		
		Server server = new Server(8090);	
		
		
		WebAppContext context = new WebAppContext();
//		context.setDescriptor("/WEB-INF/web.xml");
		context.setDescriptor("/WEB-INF");
		context.setResourceBase("src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
		server.join();
	
	}

	public static void main(String[] args) throws Exception {
		runServer3();
	}
}
