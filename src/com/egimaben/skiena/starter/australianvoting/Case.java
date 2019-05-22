package com.egimaben.skiena.starter.australianvoting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Case {
	private int numCandidates = 0;
	private HashMap<Integer, String> candidates = new HashMap<>();
	private List<Integer[]> ballots = new ArrayList<>();

	protected void addCandidate(String name) {
		candidates.put(candidates.size() + 1, name);
	}
	protected String getCandidate(int index) {
		return candidates.get(index);
	}
	protected void setNumCandidates(int num) {
		numCandidates = num;
	}
	protected void addBallot(Integer[] ballot) {
		ballots.add(ballot);
	}
	protected List<Integer[]> ballots(){
		return this.ballots;
	}
	protected void eliminateCandidate(int index) {
		candidates.remove(index);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NumCandidates\n");
		sb.append(numCandidates);
		sb.append("\n");
		sb.append("candidates:\n");
		sb.append(candidates);
		sb.append("\nBallots:\n");
		for(Integer[] ballot:ballots) {
			sb.append(Arrays.asList(ballot));
		}
		return sb.toString();
		
	}
}
