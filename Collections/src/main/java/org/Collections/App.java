package org.Collections;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.Candidate;
import model.ScheduledCandidates;
import model.WalkInCandidate;

public class App 
{
    public static void main( String[] args )
    {
    	InterviewQueue queue = InterviewQueue.getInstance();
    	LocalDate currentDate = LocalDate.now();
        
    	LocalTime candidateTime1 =  LocalTime.of(8, 10, 0);
        LocalDateTime arrivalTime1 = LocalDateTime.of(currentDate, candidateTime1);
        Candidate candidate1 = new ScheduledCandidates("John", arrivalTime1);
        queue.offer(candidate1);
        
        LocalTime candidateTime3 =  LocalTime.of(9, 5, 0);
        LocalDateTime arrivalTime3 = LocalDateTime.of(currentDate, candidateTime3);
        Candidate candidate3 = new ScheduledCandidates("James", arrivalTime3);
        queue.offer(candidate3);
        
        LocalTime candidateTime2 =  LocalTime.of(8, 5, 0);
        LocalDateTime arrivalTime2 = LocalDateTime.of(currentDate, candidateTime2);
        Candidate candidate2 = new ScheduledCandidates("Mike", arrivalTime2);
        queue.offer(candidate2);
        
        LocalTime candidateTime4 =  LocalTime.of(10, 20, 0);
        LocalDateTime arrivalTime4 = LocalDateTime.of(currentDate, candidateTime4);
        Candidate candidate4 = new ScheduledCandidates("Caroline", arrivalTime4);
        queue.offer(candidate4);
        
        queue.offer(new WalkInCandidate("Harry"));
        queue.offer(new WalkInCandidate("Lily"));
        queue.offer(new WalkInCandidate("Nethane"));
        queue.offer(new WalkInCandidate("Eugene"));
        queue.offer(new WalkInCandidate("Brian"));
        queue.offer(new WalkInCandidate("Marien"));
        
        queue.printInterviewCandidateSequence();
    }
}
