/**
 * 
 */
package com.strandls.ibp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.ActivityLoggingData;
import com.strandls.activity.pojo.ActivityResult;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.ibp.ApiConstants;
import com.strandls.ibp.service.ActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Abhishek Rudra
 *
 */

@Api("Activity Service Ibp")
@Path(ApiConstants.ACTIVITY + ApiConstants.V1)
public class ActivityController {

	@Inject
	private ActivityService activitySerivceIbp;

	@GET
	@Path(ApiConstants.IBP + "/{objectType}/{objectId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find activity by ID for IBP", notes = "Returns activity details", response = ActivityResult.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Traits not found", response = String.class) })

	public Response getIbpActivity(@PathParam("objectType") String objectType, @PathParam("objectId") String objectId,
			@DefaultValue(value = "0") @QueryParam("offset") String offset,
			@DefaultValue(value = "10") @QueryParam("limit") String limit) {
		try {
			ActivityResult result = activitySerivceIbp.getActivityIBP(objectType, objectId, offset, limit);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path(ApiConstants.LOG)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Logs activity", notes = "Return the activity", response = Activity.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to post the data", response = String.class) })

	public Response logActivity(@Context HttpServletRequest request,
			@ApiParam(name = "activityLogging") ActivityLoggingData activityLogging) {

		try {
			Activity result = activitySerivceIbp.addActivity(activityLogging);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@POST
	@Path(ApiConstants.ADD + ApiConstants.COMMENT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Adds a comment", notes = "Return the current activity", response = Activity.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to log a comment", response = String.class) })

	public Response addComment(@Context HttpServletRequest request,
			@ApiParam(name = "commentData") CommentLoggingData commentData) {
		try {
			Activity result = activitySerivceIbp.addComment(commentData);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.MIGRATE)
	@Produces(MediaType.TEXT_PLAIN)

	@ApiOperation(value = "Migrate the Old activity data", notes = "Starts a Thread that Migrate the data", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to start the process", response = String.class) })

	public Response migrateData() {
		try {
			String result = activitySerivceIbp.migrateAcitvity();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
