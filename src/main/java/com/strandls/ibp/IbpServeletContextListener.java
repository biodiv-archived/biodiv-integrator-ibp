/**
 * 
 */
package com.strandls.ibp;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.authentication_utility.filter.FilterModule;
import com.strandls.ibp.controller.IbpControllerModule;
import com.strandls.ibp.service.impl.IbpServiceModule;
import com.strandls.observation.controller.ObservationServiceApi;
import com.strandls.observation.controller.RecommendationServicesApi;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * @author Abhishek Rudra
 *
 */
public class IbpServeletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		Injector injector = Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {

				Map<String, String> props = new HashMap<String, String>();
				props.put("javax.ws.rs.Application", ApplicationConfig.class.getName());
				props.put("jersey.config.server.wadl.disableWadl", "true");

				bind(ObservationServiceApi.class).in(Scopes.SINGLETON);
				bind(RecommendationServicesApi.class).in(Scopes.SINGLETON);
				bind(UserGroupSerivceApi.class).in(Scopes.SINGLETON);
				bind(ActivitySerivceApi.class).in(Scopes.SINGLETON);
				serve("/api/*").with(GuiceContainer.class, props);
				filter("/*").through(SwaggerFilter.class);

			}
		}, new IbpControllerModule(), new FilterModule(), new IbpServiceModule());

		return injector;

	}
}
