package model;

/**
 * A model class representing the walk-in candidtes for an interview process
 * 
 * @author priyambadam
 *
 */
public class WalkInCandidate extends Candidate{
	

	public WalkInCandidate(String candidateName) {
		super(candidateName, CandidateType.WalkIn);
	}
}
