package model;

import jdk.internal.jline.internal.Log;
/**
 * Model class representing the candidates for interview process 
 * @author priyambadam
 *
 */
public class Candidate implements Comparable<Candidate>{

	private String candidateName;
	private CandidateType priority;
	
	public Candidate(String candidateName, CandidateType candidateType) {
		this.candidateName = candidateName;
		this.priority = candidateType;
	}

	public int compareTo(Candidate candidateObj) {
		return this.priority.compareTo(candidateObj.getPriority());			
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public CandidateType getPriority() {
		return priority;
	}

	protected void setPriority(CandidateType priority) {
		this.priority = priority;
	}

	public String print() {
		return priority.toString() + " --> " + candidateName +"\n";
	}

}
