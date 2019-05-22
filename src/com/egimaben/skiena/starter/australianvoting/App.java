package com.egimaben.skiena.starter.australianvoting;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
	private int numCases = 0;
	private List<Case> cases = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		App app = new App();
		app.readInput();
		for (Case caze : app.cases) {
			VoteCalculator vc = new VoteCalculator(caze);
			System.out.println(vc._winner());
		}

	}

	private boolean readComplete() {
		return cases.size() == numCases;
	}

	private static int getInt(String str) {
		return Integer.parseInt(str);
	}

	private static Integer[] parseBallot(String ballot, int numCandidates) {
		Integer[] parsedBallot = new Integer[numCandidates];
		String[] rawBallot = ballot.split(" ");
		for (int i = 0; i < numCandidates; i++)
			parsedBallot[i] = getInt(rawBallot[i]);
		return parsedBallot;
	}

	private void readInput() {
		Scanner sc = new Scanner(this.getClass().getResourceAsStream("input.txt"));
		numCases = Integer.parseInt(sc.nextLine());
		// skip empty line
		sc.nextLine();

		while (sc.hasNextLine()) {
			if (readComplete()) {
				break;
			}
			Case cs = new Case();
			int numCandidates = getInt(sc.nextLine());
			cs.setNumCandidates(numCandidates);
			for (int i = 0; i < numCandidates; i++) {
				cs.addCandidate(sc.nextLine());
			}
			String ballot = sc.nextLine();
			while (!ballot.isEmpty()) {
				cs.addBallot(parseBallot(ballot, numCandidates));
				try {
					ballot = sc.nextLine();
				} catch (NoSuchElementException e) {
					
					break;
				}
				;
			}
			cases.add(cs);

		}
		sc.close();
	}
}
