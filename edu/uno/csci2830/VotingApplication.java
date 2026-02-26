package edu.uno.csci2830;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VotingApplication {

    private Candidate john;
    private Candidate jane;
    private ArrayList<String> votedIDs;

    public VotingApplication() {
        john = new Candidate("John");
        jane = new Candidate("Jane");
        votedIDs = new ArrayList<>();
    }

    public boolean isvalidID(String id){
        return id.matches("\\{5}");
    }

    public boolean hasAlreadyVoted(String id) {
        return votedIDs.contains(id);
    }

    public boolean vote(String id, int choice) {

        if (choice == 1) {
            john.addAVote();
            saveToCsvFile(id, john.getName());
        }
        else if (choice == 2) {
            jane.addAVote();
            saveToCsvFile(id, jane.getName());
        }
        else {
            return false;
        }

        votedIDs.add(id);
        return true;
    }

    public String getCandidate1name() {
        return john.getName();
    }

    public String getCandidate2name() {
        return jane.getName();
    }

    private void saveToCsvFile(String id, String candidateName) {
        try (FileWriter writer = new FileWriter("votes.csv", true)) {
            writer.append(id + "," + candidateName + "\n");
        } catch (IOException e) {
            System.out.println("There was an error writing to file.");
        }
    }

    public void displayVoteResults() {
        System.out.println("\n-----Voting Results-----");
        System.out.println("John: " + john.getVotes() + " votes");
        System.out.println("Jane: " + jane.getVotes() + " votes");

        if (john.getVotes() > jane.getVotes()) {
            System.out.println("Winner: John");
        }
        else if (jane.getVotes() > john.getVotes()) {
            System.out.println("Winner: Jane");
        }
        else {
            System.out.println("This is a draw!");
        }
    }
}