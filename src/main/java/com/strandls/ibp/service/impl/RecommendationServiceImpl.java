/**
 * 
 */
package com.strandls.ibp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.ibp.service.RecommendationService;
import com.strandls.observation.controller.RecommendationServicesApi;
import com.strandls.observation.pojo.RecoData;
import com.strandls.observation.pojo.RecoIbp;
import com.strandls.observation.pojo.RecoSet;
import com.strandls.observation.pojo.RecoShow;

/**
 * @author Abhishek Rudra
 *
 */
public class RecommendationServiceImpl implements RecommendationService {

	private final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

	@Inject
	private RecommendationServicesApi recoService;

	@Override
	public RecoIbp fetchRecoVote(String recoVoteId) {
		try {
			RecoIbp result = recoService.getRecoVote(recoVoteId);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public RecoShow createRecoVote(String observationId, RecoData recoData) {
		try {
			RecoShow result = recoService.createRecoVote(observationId, recoData);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public RecoShow removeRecoVote(String observationId, RecoSet recoSet) {
		try {
			RecoShow result = recoService.removeRecoVote(observationId, recoSet);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public RecoShow agreeRecoVote(String observationId, RecoSet recoSet) {
		try {
			RecoShow result = recoService.agree(observationId, recoSet);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public RecoShow validateRecoVote(String observationId, RecoSet recoSet) {
		try {
			RecoShow result = recoService.validateReco(observationId, recoSet);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public RecoShow unlockRecoVote(String observationId, RecoSet recoSet) {
		try {
			RecoShow result = recoService.unlockReco(observationId, recoSet);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Long> updateCanonical() {
		try {
			List<Long> result = recoService.getCanonicalUpdated();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
