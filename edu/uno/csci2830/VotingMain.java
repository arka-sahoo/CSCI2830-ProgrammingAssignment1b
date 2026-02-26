package edu.uno.csci2830;

import java.util.Scanner;

public class VotingMain {
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
        }
    }
}
