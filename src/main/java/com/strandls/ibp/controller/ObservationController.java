/**
 * 
 */
package com.strandls.ibp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.ibp.ApiConstants;
import com.strandls.ibp.service.ObservationIbpService;
import com.strandls.observation.pojo.FactValuePair;
import com.strandls.observation.pojo.Featured;
import com.strandls.observation.pojo.FeaturedCreate;
import com.strandls.observation.pojo.Flag;
import com.strandls.observation.pojo.FlagIbp;
import com.strandls.observation.pojo.Follow;
import com.strandls.observation.pojo.Language;
import com.strandls.observation.pojo.ObservationCreate;
import com.strandls.observation.pojo.ObservationUpdateData;
import com.strandls.observation.pojo.ObservationUserPermission;
import com.strandls.observation.pojo.ShowData;
import com.strandls.observation.pojo.SpeciesGroup;
import com.strandls.observation.pojo.Tags;
import com.strandls.observation.pojo.TagsMapping;
import com.strandls.observation.pojo.TraitsValue;
import com.strandls.observation.pojo.TraitsValuePair;
import com.strandls.observation.pojo.UserGroupIbp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Abhishek Rudra
 *
 */

@Api("Observation ibp")
@Path(ApiConstants.OBSERVATION + ApiConstants.V1)
public class ObservationController {

	@Inject
	private ObservationIbpService observationService;

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
		return Response.status(Status.OK).entity("ping").build();
	}

	@GET
	@Path(ApiConstants.SHOW + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find Observation by ID", notes = "Returns the complete Observation with all the specificaiton", response = ShowData.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Observation not found", response = String.class),
			@ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

	public Response observationShow(@PathParam("observationId") String observationId) {
		try {
			ShowData result = observationService.showObservation(observationId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Observation Cannot be retrived").build();
		}
	}

	@POST
	@Path(ApiConstants.CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "Create a Observation", notes = "Returns the show Page of Observation", response = ShowData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "observation Cannot be created", response = String.class) })

	public Response createObservation(@Context HttpServletRequest request,
			@ApiParam(name = "observationData") ObservationCreate observationData) {
		try {
			ShowData result = observationService.createObservation(observationData);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.EDIT + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Get the data for  Observation core part Update ", notes = "Returns the user the update page data", response = ObservationUpdateData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to edit the observation", response = String.class) })

	public Response getEditPageData(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId) {
		try {
			ObservationUpdateData result = observationService.getEditData(observationId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Update the Observation core part", notes = "Returns the user the complete show page", response = ShowData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to edit the observation", response = String.class) })

	public Response updateObservation(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId,
			@ApiParam(name = "observationUpdateData") ObservationUpdateData observationUpdate) {
		try {

			ShowData result = observationService.updateObservation(observationId, observationUpdate);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("User not allowed to edit the observation").build();
		}
	}

	@DELETE
	@Path(ApiConstants.DELETE + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@ValidateUser

	@ApiOperation(value = "Delete the Observaiton", notes = "Return the Success or Failure Message", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Observation Cannot be Deleted", response = String.class) })

	public Response deleteObservation(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId) {
		try {

			String result = observationService.deleteObservation(observationId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Observation Cannot be Deleted").build();
		}
	}

	@PUT
	@Path(ApiConstants.SPECIESGROUP + "/{observationId}/{sGroupId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "Update the Species group of the observation", notes = "Returns the updated Species group id", response = Long.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to update the Species Group", response = String.class) })

	public Response updateSGroup(@Context HttpServletRequest request, @PathParam("observationId") String observationId,
			@PathParam("sGroupId") String sGroupId) {
		try {
			Long result = observationService.updateSpeciesGroup(observationId, sGroupId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.TAGS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "update tags for the observation", notes = "Returns Tags list", response = Tags.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to update the tags", response = String.class) })

	public Response updateTags(@Context HttpServletRequest request,
			@ApiParam(name = "tagsMapping") TagsMapping tagsMapping) {
		try {
			List<Tags> result = observationService.updateTags(tagsMapping);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.TRAITS + "/{observationId}/{traitId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Update the specific Trait with values", notes = "Returns all facts", response = FactValuePair.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to Update the Traits", response = String.class) })

	public Response updateTraits(@Context HttpServletRequest request, @PathParam("observationId") String observationId,
			@PathParam("traitId") String traitId, @ApiParam(name = "valueList") List<Long> valueList) {
		try {
			List<FactValuePair> result = observationService.updateObservationTraits(observationId, traitId, valueList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.USERGROUP + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Update the UserGroup linked with a observation", notes = "Returns all the current userGroup Linked", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to updated the userGroup of Observaiton", response = String.class) })

	public Response updateUserGroup(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId,
			@ApiParam(name = "userGroupList") List<Long> userGroupList) {
		try {
			List<UserGroupIbp> result = observationService.updateObservationUserGroups(observationId, userGroupList);

			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.SPECIES + ApiConstants.ALL)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Get all the Specie Group", notes = "Returns all the Species Group", response = SpeciesGroup.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to fetch the UserGroup", response = String.class) })

	public Response getAllSpecies() {
		try {

			List<SpeciesGroup> result = observationService.getAllSpeciesGroup();
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.LANGUAGE)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find all the Languages based on IsDirty field", notes = "Returns all the Languages Details", response = Language.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Languages Not Found", response = String.class) })

	public Response getLanguages(@QueryParam("isDirty") Boolean isDirty) {
		try {
			List<Language> result = observationService.getAllLanguages(isDirty);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.FEATURED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Posting of Featured to a Group", notes = "Returns the Details of Featured", response = Featured.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Unable to Feature in a Group", response = String.class) })

	public Response createFeatured(@Context HttpServletRequest request,
			@ApiParam(name = "featuredCreate") FeaturedCreate featuredCreate) {
		try {
			List<Featured> result = observationService.featureObservation(featuredCreate);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UNFEATURED + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "UnFeatures a Object from a UserGroup", notes = "Returns the Current Featured", response = Featured.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Unable to Unfeature", response = String.class) })

	public Response unFeatured(@Context HttpServletRequest request, @PathParam("observationId") String observationId,
			@ApiParam(name = "userGroupList") List<Long> userGroupList) {
		try {
			List<Featured> result = observationService.unFeaturedObservation(observationId, userGroupList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.TRAITS + "/{traitId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Find the value of Traits", notes = "Returns the values of traits based on trait's ID", response = TraitsValue.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to get the values", response = String.class) })

	public Response getValuesOfTraits(@Context HttpServletRequest request, @PathParam("traitId") String traitId) {
		try {
			List<TraitsValue> result = observationService.getTraitsValue(traitId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.SPECIES + "/{speciesId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all Trait Values pair for Specific SpeciesId", notes = "Return the Key value pairs of Traits", response = TraitsValuePair.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Species Not Found", response = String.class) })

	public Response getTraitList(@PathParam("speciesId") String speciesId) {
		try {
			List<TraitsValuePair> result = observationService.getTraitValuePair(speciesId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.PERMISSIONS + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Find all the user Permission for current observation", notes = "Returns list of permission for validate post and feature in a group", response = ObservationUserPermission.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to fetch the permission", response = String.class) })

	public Response getUserPermissions(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId,
			@ApiParam(name = "taxonList") @QueryParam("taxonList") String taxonList) {
		try {
			ObservationUserPermission result = observationService.getUserPermission(observationId, taxonList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.TAGS + ApiConstants.AUTOCOMPLETE)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find the Sugguestion for tags", notes = "Return list of Top 10 tags matching the phrase", response = Tags.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to fetch the tags", response = String.class) })

	public Response getTagsSuggetion(@QueryParam("phrase") String phrase) {
		try {
			List<Tags> result = observationService.tagsAutoComplete(phrase);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.USERGROUP)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Find all the userGroup Associated with a user", notes = "Returns a List of UserGroup", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to get the userGroup", response = String.class) })
	public Response getUsersGroupList(@Context HttpServletRequest request) {
		try {
			List<UserGroupIbp> result = observationService.getUsersGroups();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.CREATE + ApiConstants.FLAG + "/{observationId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Flag a Observaiton", notes = "Return a list of flag to the Observaiton", response = Flag.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to flag a Observation", response = String.class),
			@ApiResponse(code = 406, message = "User has already flagged", response = String.class) })

	public Response createFlag(@Context HttpServletRequest request, @PathParam("observationId") String observationId,
			@ApiParam(name = "flagIbp") FlagIbp flagIbp) {
		try {
			List<Flag> result = observationService.createFlag(observationId, flagIbp);
			if (result.isEmpty())
				return Response.status(Status.NOT_ACCEPTABLE).entity("User Allowed Flagged").build();
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path(ApiConstants.UNFLAG + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Unflag a Observation", notes = "Return a list of flag to the Observation", response = Flag.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to unflag a Observation", response = String.class),
			@ApiResponse(code = 406, message = "User is not allowed to unflag", response = String.class) })

	public Response unFlag(@Context HttpServletRequest request, @PathParam("observaitonId") String observationId) {
		try {
			List<Flag> result = observationService.removeFlag(observationId);
			if (result == null)
				return Response.status(Status.NOT_ACCEPTABLE).entity("User not allowed to Unflag").build();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.FOLLOW + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Marks follow for a User", notes = "Returnt the follow details", response = Follow.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to mark follow", response = String.class) })

	public Response followObservation(@Context HttpServletRequest request,
			@PathParam("observationId") String observationId) {
		try {
			Follow result = observationService.requestFollow(observationId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UNFOLLOW + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Marks unfollow for a User", notes = "Returnt the unfollow details", response = Follow.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to mark unfollow", response = String.class) })

	public Response unfollow(@Context HttpServletRequest request, @PathParam("observationId") String observationId) {

		try {
			Follow result = observationService.requestUnfollow(observationId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.AUTHOR + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)

	@ApiOperation(value = "Finds the authorId of the observation", notes = "Returns the authorid of a observation", response = Long.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to fetch the authorid", response = String.class) })

	public Response getObservationAuthor(@PathParam("observationId") String observationId) {
		try {
			Long result = observationService.getObservationAuthor(observationId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Cannot find the Author").build();
		}
	}

	@POST
	@Path(ApiConstants.APPLYFILTER)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)

	@ValidateUser
	@ApiOperation(value = "Apply the new Filter Rule to the Observation Existings", notes = "Starts the process to apply the Rule", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to start the process", response = String.class) })

	public Response applyNewFilter(@Context HttpServletRequest request, @QueryParam("groupIds") String groupIds) {
		try {
			String result = observationService.applyUserGroupFilter(groupIds);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Filter cannot be started").build();
		}
	}

	@POST
	@Path(ApiConstants.APPLYGEOPRIVACY)
	@Produces(MediaType.TEXT_PLAIN)

	@ValidateUser

	@ApiOperation(value = "Bulk update the geoPrivate traits in all observation", notes = "Starts a process to update the geoPrivacy Field", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to start the process", response = String.class),
			@ApiResponse(code = 406, message = "User not allowed to perform the task", response = String.class) })

	public Response applyGeoPrivacy(@Context HttpServletRequest request) {
		try {
			String result = observationService.applyGeoPrivacy();
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Unable to Start the process").build();
		}
	}

}
