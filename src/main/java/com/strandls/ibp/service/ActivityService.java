/**
 * 
 */
package com.strandls.ibp.service;

import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.ActivityLoggingData;
import com.strandls.activity.pojo.ActivityResult;
import com.strandls.activity.pojo.CommentLoggingData;

/**
 * @author Abhishek Rudra
 *
 */
public interface ActivityService {

	public ActivityResult getActivityIBP(String objectType, String objectId, String offset, String limit);

	public Activity addActivity(ActivityLoggingData loggingData);

	public Activity addComment(CommentLoggingData loggingData);

	public String migrateAcitvity();
}
