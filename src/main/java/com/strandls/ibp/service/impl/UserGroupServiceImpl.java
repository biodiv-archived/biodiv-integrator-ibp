/**
 * 
 */
package com.strandls.ibp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.ibp.service.UserGroupService;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.UserGroupIbp;

/**
 * @author Abhishek Rudra
 *
 */
public class UserGroupServiceImpl implements UserGroupService {

	private final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);

	@Inject
	private UserGroupSerivceApi userGroupService;

	@Override
	public List<UserGroupIbp> getAllUserGroup() {
		try {
			List<UserGroupIbp> result = userGroupService.getAllUserGroup();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
