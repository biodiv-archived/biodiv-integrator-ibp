/**
 * 
 */
package com.strandls.ibp.service;

import java.util.List;

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

/**
 * @author Abhishek Rudra
 *
 */
public interface ObservationIbpService {

	public ShowData showObservation(String observationId);

	public ShowData createObservation(ObservationCreate observationCreate);

	public ObservationUpdateData getEditData(String observationId);

	public ShowData updateObservation(String observationId, ObservationUpdateData updateData);

	public String deleteObservation(String observationId);

	public Long updateSpeciesGroup(String observationId, String speciesGroupId);

	public List<Tags> updateTags(TagsMapping tagsMapping);

	public List<FactValuePair> updateObservationTraits(String observationId, String traitId, List<Long> valueList);

	public List<UserGroupIbp> updateObservationUserGroups(String observationId, List<Long> userGroupList);

	public List<SpeciesGroup> getAllSpeciesGroup();

	public List<Language> getAllLanguages(Boolean isDirty);

	public List<Featured> featureObservation(FeaturedCreate featuredCreate);

	public List<Featured> unFeaturedObservation(String observationId, List<Long> userGroupIds);

	public List<TraitsValue> getTraitsValue(String traitId);

	public List<TraitsValuePair> getTraitValuePair(String speciesId);

	public ObservationUserPermission getUserPermission(String observationId, String taxonList);

	public List<Tags> tagsAutoComplete(String phrase);

	public List<UserGroupIbp> getUsersGroups();

	public List<Flag> createFlag(String observationId, FlagIbp flagIbp);

	public List<Flag> removeFlag(String observationId, Flag flag);

	public Follow requestFollow(String observationId);

	public Follow requestUnfollow(String observationId);

	public Long getObservationAuthor(String observationId);

	public String applyUserGroupFilter(String groupIds);

	public String applyGeoPrivacy();

}
