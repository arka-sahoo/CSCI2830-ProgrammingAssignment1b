package edu.uno.csci2830;

/**
 * The Candidate class represents a candidate in the voting application.
 * It stores the candidate's name and the number of votes received.
 */
public class Candidate {
    
    // Name of the candidate
    private String name;

    // Number of votes received
    private int votes;

    /**
     * Constructor to create a Candidate object.
     * @param name The name of the candidate
     */
    public Candidate(String name) {
        this.name = name;
        this.votes = 0; // Initial vote count is 0
    }

    /**
     * Increments the vote count by 1.
     */
    public void addAVote() {
        votes ++;
    }

    /**
     * Returns the name of the candidate.
     * @return candidate name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of votes received.
     * @return vote count
     */
    public int getVotes() {
        return votes;
    }
}