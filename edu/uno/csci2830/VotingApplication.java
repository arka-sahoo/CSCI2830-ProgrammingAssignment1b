package edu.uno.csci2830;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        return id.matches("\\d{5}");
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
        }

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        VotingApplication Application = new VotingApplication();

        while (true) {
            System.out.print("\nEnter your 5-digit ID: ");
            String id = scanner.nextLine();

            if (id.equalsIgnoreCase("exit")) {
                break;
            }

            if (!Application.isvalidID(id)) {
                System.out.println("invalid ID. It must be a 5-digit ID.");
                continue;
            }

            if (Application.hasAlreadyVoted(id)) {
                System.out.println("Already voted");
                continue;
            }

            System.out.println("1. Vote for " + Application.getCandidate1name());
            System.out.println("2. Vote for " + Application.getCandidate2name());
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (Application.vote(id, userChoice)) {
                System.out.println("Voted Successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        Application.displayVoteResults();
        scanner.close();
    }
}