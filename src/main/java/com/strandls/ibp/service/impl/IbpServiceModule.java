/**
 * 
 */
package com.strandls.ibp.service.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.ibp.service.ActivityService;
import com.strandls.ibp.service.ObservationIbpService;
import com.strandls.ibp.service.RecommendationService;
import com.strandls.ibp.service.UserGroupService;

/**
 * @author Abhishek Rudra
 *
 */
public class IbpServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ObservationIbpService.class).to(ObservationIbpServiceImpl.class).in(Scopes.SINGLETON);
		bind(RecommendationService.class).to(RecommendationServiceImpl.class).in(Scopes.SINGLETON);
		bind(ActivityService.class).to(ActivityServiceImpl.class).in(Scopes.SINGLETON);
		bind(UserGroupService.class).to(UserGroupServiceImpl.class).in(Scopes.SINGLETON);
	}

}
