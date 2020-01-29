/**
 * 
 */
package com.strandls.ibp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.ibp.service.ObservationIbpService;
import com.strandls.observation.controller.ObservationServiceApi;
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
public class ObservationIbpServiceImpl implements ObservationIbpService {

	private final Logger logger = LoggerFactory.getLogger(ObservationIbpServiceImpl.class);

	private ObservationServiceApi observationService;

	@Override
	public ShowData showObservation(String observationId) {
		try {
			ShowData observationShow = observationService.show(observationId);
			return observationShow;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ShowData createObservation(ObservationCreate observationCreate) {
		try {
			ShowData result = observationService.createObservation(observationCreate);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ObservationUpdateData getEditData(String observationId) {
		try {
			ObservationUpdateData result = observationService.getEditPageData(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ShowData updateObservation(String observationId, ObservationUpdateData updateData) {
		try {
			ShowData result = observationService.updateObservation(observationId, updateData);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String deleteObservation(String observationId) {
		try {
			String result = observationService.deleteObservation(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Long updateSpeciesGroup(String observationId, String speciesGroupId) {
		try {
			Long result = observationService.updateSGroup(observationId, speciesGroupId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Tags> updateTags(TagsMapping tagsMapping) {
		try {
			List<Tags> result = observationService.updateTags(tagsMapping);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<FactValuePair> updateObservationTraits(String observationId, String traitId, List<Long> valueList) {
		try {
			List<FactValuePair> result = observationService.updateTraits(observationId, traitId, valueList);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UserGroupIbp> updateObservationUserGroups(String observationId, List<Long> userGroupList) {
		try {
			List<UserGroupIbp> result = observationService.updateUserGroup(observationId, userGroupList);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<SpeciesGroup> getAllSpeciesGroup() {
		try {
			List<SpeciesGroup> result = observationService.getAllSpecies();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Language> getAllLanguages(Boolean isDirty) {
		try {
			List<Language> result = observationService.getLanguaes(isDirty);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Featured> featureObservation(FeaturedCreate featuredCreate) {
		try {
			List<Featured> result = observationService.createFeatured(featuredCreate);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Featured> unFeaturedObservation(String observationId, List<Long> userGroupIds) {
		try {
			List<Featured> result = observationService.unFeatured(observationId, userGroupIds);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<TraitsValue> getTraitsValue(String traitId) {
		try {
			List<TraitsValue> result = observationService.getValuesOfTraits(traitId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<TraitsValuePair> getTraitValuePair(String speciesId) {
		try {
			List<TraitsValuePair> result = observationService.getTraitList(speciesId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ObservationUserPermission getUserPermission(String observationId, String taxonList) {
		try {
			ObservationUserPermission result = observationService.getUserPermissions(observationId, taxonList);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Tags> tagsAutoComplete(String phrase) {
		try {
			List<Tags> result = observationService.getTagsSuggetion(phrase);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UserGroupIbp> getUsersGroups() {
		try {
			List<UserGroupIbp> result = observationService.getUsersGroupList();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Flag> createFlag(String observationId, FlagIbp flagIbp) {
		try {
			List<Flag> result = observationService.createFlag(observationId, flagIbp);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Flag> removeFlag(String observationId) {
		try {
			List<Flag> result = observationService.unFlag(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Follow requestFollow(String observationId) {
		try {
			Follow result = observationService.followObservation(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Follow requestUnfollow(String observationId) {
		try {
			Follow result = observationService.unfollow(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Long getObservationAuthor(String observationId) {
		try {
			Long result = observationService.getObservationAuthor(observationId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String applyUserGroupFilter(String groupIds) {
		try {
			String result = observationService.applyNewFilter(groupIds);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String applyGeoPrivacy() {
		try {
			String result = observationService.applyGeoPrivacy();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
