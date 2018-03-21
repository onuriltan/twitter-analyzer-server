package com.onuriltan.twitteranalyzerserver.config.cometd;

import org.cometd.annotation.ServerAnnotationProcessor;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.server.BayeuxServerImpl;
import org.cometd.server.CometDServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

@Configuration
public class CometdConfig implements DestructionAwareBeanPostProcessor, ServletContextAware {

    private BayeuxServer bayeuxServer;
    private ServerAnnotationProcessor processor;

    @Inject
    public void setBayeuxServer(BayeuxServer bayeuxServer) {
        this.bayeuxServer = bayeuxServer;
    }

    @PostConstruct
    public void init() {
        this.processor = new ServerAnnotationProcessor(bayeuxServer);
    }

    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        processor.processDependencies(bean);
        processor.processConfigurations(bean);
        processor.processCallbacks(bean);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    public void postProcessBeforeDestruction(Object bean, String name) throws BeansException {
        processor.deprocessCallbacks(bean);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BayeuxServer bayeuxServer() {
        BayeuxServerImpl bean = new BayeuxServerImpl();
        return bean;
    }

    @Bean
    public Servlet cometD() {
        return new CometDServlet();
    }

    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute(BayeuxServer.ATTRIBUTE, bayeuxServer);
    }
}