package com.jxlg.app.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.ipc.netty.http.server.HttpServer;

@Configuration
public class HttpServerConfig {
    @Autowired
    private Environment environment;

    /**
     * 这将使用应用程序属性中定义的端口创建一个 netty HttpServer
     */
    @Bean
    public HttpServer httpServer(RouterFunction<?> routerFunction){
        HttpHandler httpHandler= RouterFunctions.toHttpHandler(routerFunction);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create("localhost", Integer.valueOf(environment.getProperty("server.port")));
        server.newHandler(adapter);
        return server;
    }


//    public void startTomcatServer(RouterFunction<?> routerFunction) throws LifecycleException {
//        HttpHandler httpHandler= RouterFunctions.toHttpHandler(routerFunction);
//
//        Tomcat tomcatServer = new Tomcat();
//        tomcatServer.setHostname(HOST);
//        tomcatServer.setPort(PORT);
//        Context rootContext = tomcatServer.addContext("", System.getProperty("java.io.tmpdir"));
//        ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(httpHandler);
//        Tomcat.addServlet(rootContext, "httpHandlerServlet", servlet);
//        rootContext.addServletMapping("/", "httpHandlerServlet");
//        tomcatServer.start();
//    }


}
