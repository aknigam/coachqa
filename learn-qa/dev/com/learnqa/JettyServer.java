package com.learnqa;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Tutorial available at http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
 * @author anigam
 *
 */

public class JettyServer {

	
	public static void main(String[] args) throws Exception {

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
}
