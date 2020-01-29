/**
 * 
 */
package com.strandls.ibp.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.ibp.ApiConstants;
import com.strandls.ibp.service.UserGroupService;
import com.strandls.userGroup.pojo.UserGroupIbp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Abhishek Rudra
 *
 */

@Api("UserGroup Service Api")
@Path(ApiConstants.USERGROUP + ApiConstants.V1)
public class UserGroupController {

	@Inject
	private UserGroupService userGroupService;

	@GET
	@Path(ApiConstants.ALL)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find all the UserGroups", notes = "Returns all the UserGroups", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Unable to fetch the UserGroups", response = String.class) })

	public Response getAllUserGroup() {
		try {
			List<UserGroupIbp> result = userGroupService.getAllUserGroup();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
