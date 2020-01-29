/**
 * 
 */
package com.strandls.ibp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.ActivityLoggingData;
import com.strandls.activity.pojo.ActivityResult;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.ibp.service.ActivityService;

/**
 * @author Abhishek Rudra
 *
 */
public class ActivityServiceImpl implements ActivityService {

	private final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Inject
	private ActivitySerivceApi activityService;

	@Override
	public ActivityResult getActivityIBP(String objectType, String objectId, String offset, String limit) {
		try {
			ActivityResult result = activityService.getIbpActivity(objectType, objectId, offset, limit);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Activity addActivity(ActivityLoggingData loggingData) {
		try {
			Activity result = activityService.logActivity(loggingData);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Activity addComment(CommentLoggingData loggingData) {
		try {
			Activity result = activityService.addComment(loggingData);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String migrateAcitvity() {
		try {
			String result = activityService.migrateData();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
