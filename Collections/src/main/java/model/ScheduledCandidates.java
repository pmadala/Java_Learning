package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A model class representing the Scheduled candidtes for an interview process
 * 
 * @author priyambadam
 *
 */
public class ScheduledCandidates extends Candidate {

	private LocalDateTime arrivalTime;

	private LocalDateTime thresholdTime;

	public ScheduledCandidates(String candidateName, LocalDateTime arrivalTime) {
		super(candidateName, CandidateType.Scheduled);
		this.arrivalTime = arrivalTime;
		setPriorityBasedOnArrivalTime();
	}

	private void setPriorityBasedOnArrivalTime() {
		LocalDate currentDate = LocalDate.now();
		LocalTime thresholdTimeDecided = LocalTime.of(10, 0, 0);
		thresholdTime = LocalDateTime.of(currentDate, thresholdTimeDecided);
		if (arrivalTime.isAfter(thresholdTime)) {
			this.setPriority(CandidateType.WalkIn);
		}
	}
	/**
	 * Schedule candidates needs to sorted based on the arrival time as well. 
	 * Candidates needs will have priority in interview queue based on their order of arrival 
	 * 
	 */
	@Override
	public int compareTo(Candidate candidateObj) {
		int crossPriority = super.compareTo(candidateObj);
		int arrivalPriority = 0;
		if(candidateObj instanceof ScheduledCandidates) {
			ScheduledCandidates scheduledCandidates = (ScheduledCandidates) candidateObj;
			arrivalPriority = this.arrivalTime.compareTo(scheduledCandidates.arrivalTime);
		}
		
		if (crossPriority == 1 && arrivalPriority == 1) {
			return 1;
		} else if (crossPriority == 0 && arrivalPriority == 0) { 
			return 0;
		} else {
			return -1;
		}
	}
}
