/**
 * 
 */
package com.strandls.ibp.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Abhishek Rudra
 *
 */
public class IbpControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ObservationController.class).in(Scopes.SINGLETON);
		bind(RecommendationController.class).in(Scopes.SINGLETON);
		bind(ActivityController.class).in(Scopes.SINGLETON);
		bind(UserGroupController.class).in(Scopes.SINGLETON);
	}
}
