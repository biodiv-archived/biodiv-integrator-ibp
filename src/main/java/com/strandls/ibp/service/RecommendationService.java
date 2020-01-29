/**
 * 
 */
package com.strandls.ibp.service;

import java.util.List;

import com.strandls.observation.pojo.RecoData;
import com.strandls.observation.pojo.RecoIbp;
import com.strandls.observation.pojo.RecoSet;
import com.strandls.observation.pojo.RecoShow;

/**
 * @author Abhishek Rudra
 *
 */
public interface RecommendationService {

	public RecoIbp fetchRecoVote(String recoVoteId);

	public RecoShow createRecoVote(String observationId, RecoData recoData);

	public RecoShow removeRecoVote(String observationId, RecoSet recoSet);

	public RecoShow agreeRecoVote(String observationId, RecoSet recoSet);

	public RecoShow validateRecoVote(String observationId, RecoSet recoSet);

	public RecoShow unlockRecoVote(String observationId, RecoSet recoSet);

	public List<Long> updateCanonical();

}
