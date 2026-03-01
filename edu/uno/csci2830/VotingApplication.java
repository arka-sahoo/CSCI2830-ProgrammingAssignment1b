package edu.uno.csci2830;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VotingApplication {

    private Candidate john;
    private Candidate jane;
    private ArrayList<String> votedIDs;

    /**
     * Constructor that initializes candidates
     */
    public VotingApplication() {
        john = new Candidate("John");
        jane = new Candidate("Jane");
        votedIDs = new ArrayList<>(); // Stores IDs that have already voted
    }

    /**
     * Validates whether the ID is 5 digit or not.
     * @param id The voter ID
     * @return Returns true if valid, otherwise false
     */
    public boolean isvalidID(String id){
        return id.matches("\\d{5}");
    }

    /**
     * Checks if a voter ID has already voted.
     * @param id The voter ID
     * @return Returns true if already voted, otherwise false
     */
    public boolean hasAlreadyVoted(String id) {
        return votedIDs.contains(id);
    }

    /**
     * Records a vote for the selected candidate.
     * @param id The voter ID
     * @param choice The voting choice
     * @return Returns true if vote was successful, otherwise false
     */
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
            return false; // Invalid choice
        }

        votedIDs.add(id); // Add ID after a successful vote
        return true;
    }

    /**
     * @return Returns the name of the candidate John.
     */
    public String getCandidate1name() {
        return john.getName();
    }

    /**
     * @return Returns the name of the candidate Jane.
     */
    public String getCandidate2name() {
        return jane.getName();
    }

    /**
     * Saves the voter ID and selected candidate to the csv file.
     * @param id The voter ID
     * @param candidateName The name of the chosen candidate
     */
    private void saveToCsvFile(String id, String candidateName) {
        try (FileWriter writer = new FileWriter("votes.csv", true)) {
            writer.append(id + "," + candidateName + "\n");
        } catch (IOException e) {
            System.out.println("There was an error writing to file.");
        }
    }

    /**
     * Displays the voting results
     */
    public void displayVoteResults() {
        System.out.println("\n-----Voting Results-----");
        System.out.println("John: " + john.getVotes() + " votes");
        System.out.println("Jane: " + jane.getVotes() + " votes");
        }

    /**
     * Main method
     * @param args
     */
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        VotingApplication Application = new VotingApplication();

        // Main program loop
        while (true) {
            System.out.print("\nEnter your 5-digit ID: ");
            String id = scanner.nextLine();

            // Validate ID format
            if (id.equalsIgnoreCase("exit")) {
                break;
            }

            if (!Application.isvalidID(id)) {
                System.out.println("invalid ID. It must be a 5-digit ID.");
                continue;
            }

            // Check for duplicate voting
            if (Application.hasAlreadyVoted(id)) {
                System.out.println("Already voted");
                continue;
            }

            // Display menu
            System.out.println("1. Vote for " + Application.getCandidate1name());
            System.out.println("2. Vote for " + Application.getCandidate2name());
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            // Option for exiting program
            if (userChoice == 3) {
                break;
            }

            // Record vote and display a success message or an error message
            if (Application.vote(id, userChoice)) {
                System.out.println("Voted Successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // Display the final results after the program ends
        Application.displayVoteResults();
        scanner.close();
    }
}