package com.egimaben.skiena.starter.australianvoting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCalculator {
	private Case caze;
	//candidate -> votes
	private Map<Integer,Integer> voteCount = new HashMap<>();
	public VoteCalculator(Case caze) {
		this.caze = caze;
	}
	protected String _winner() {
		runInitialCount();
		String winner = getWinner();
		while(winner==null)
		{
			recount();
			winner = getWinner();
		}
		return winner;
	}
	private void recount() {
		Map<Double,List<Integer>> tally = tally();
		double loserPercentage = 100;
		//find lowest percentage
		for(double percentage:tally.keySet()) {
			if(percentage<loserPercentage)
				loserPercentage = percentage;
		}
		//get losers
		List<Integer> losingCandidates = tally.get(loserPercentage);
		for(int index:losingCandidates) {
			for(Integer[] ballot:caze.ballots()) {
				//check if this ballot ranks current losing candidate as first
				if(ballot[0]==index) {
					addVote(ballot[1]);
				}
				//eliminate candidate
				eliminateCandidate(index);
			}
		}
		
	}
	private void eliminateCandidate(int index) {
		caze.eliminateCandidate(index);
		voteCount.remove(index);
	}
	private String getWinner() {
		float numBallots = caze.ballots().size();
		Map<Double,List<Integer>> counts = new HashMap<>();
	    double winnerPercentage = 0.0;
		for(int candidateIndex:voteCount.keySet()) {
			float votes = voteCount.get(candidateIndex);
			double percentage = votes/numBallots * 100;
			if(percentage>winnerPercentage)
				winnerPercentage = percentage;
			if(counts.containsKey(percentage)) {
				List<Integer> tiedCandidates = counts.get(percentage);
				tiedCandidates.add(candidateIndex);
				counts.put(percentage, tiedCandidates);
				
			}else {
				List<Integer> candidates = new ArrayList<>();
				candidates.add(candidateIndex);
				counts.put(percentage,candidates);
			}
		}
		
		if(winnerPercentage>50)
		{
			
			List<Integer> winners = counts.get(winnerPercentage);
			StringBuilder sb = new StringBuilder();
			for(int winner:winners) {
				sb.append(caze.getCandidate(winner));
				sb.append(" ");
				}
			return sb.toString();
		}
		return null;
	}
	private Map<Double,List<Integer>> tally(){
		float numBallots = caze.ballots().size();
		Map<Double,List<Integer>> tally = new HashMap<>();
		for(int candidateIndex:voteCount.keySet()) {
			float votes = voteCount.get(candidateIndex);
			double ratio = (double)(votes/numBallots);
			double percentage = ratio * 100;
			if(tally.containsKey(percentage)) {
				List<Integer> tiedCandidates = tally.get(percentage);
				tiedCandidates.add(candidateIndex);
				tally.put(percentage, tiedCandidates);
			}else {
				List<Integer> candidates = new ArrayList<>();
				candidates.add(candidateIndex);
				tally.put(percentage,candidates);
			}
		}
		return tally;
	}
	private void runInitialCount() {
		for(Integer[] ballot:caze.ballots()) {
			addVote(ballot[0]);
		}
	}
	private void addVote(int candidateIndex) {
		Integer currentCount = voteCount.get(candidateIndex);
		if(currentCount == null)
			voteCount.put(candidateIndex, 1);
		else voteCount.put(candidateIndex, currentCount+1);
	}
}
