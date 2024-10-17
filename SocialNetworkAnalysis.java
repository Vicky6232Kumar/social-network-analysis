package Social_Networking_Analysis; // use the package name where you clone the project

import java.io.*;
import java.util.*;

/**
 * SocialNetworkAnalysis
 */
public class SocialNetworkAnalysis {

    public static void main(String[] args) {
        try {
            SocialNetworkGraph socialNetwork = new SocialNetworkGraph();

            // Load users and followers from files
            socialNetwork.readUsersFromFile("users.txt");
            socialNetwork.readFollowersFromFile("followers.txt");

            // Create a scanner object for reading user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Display the menu
                System.out.println("\nChoose an option:");
                System.out.println("1. Display Graph");
                System.out.println("2. Find Shortest Path (BFS)");
                System.out.println("3. Perform DFS");
                System.out.println("4. Find Mutual Friends");
                System.out.println("5. Friend Recommendations");
                System.out.println("6. Calculate PageRank");
                System.out.println("7. Calculate Degree Centrality");
                System.out.println("8. Calculate Closeness Centrality");
                System.out.println("9. Calculate Betweenness Centrality");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                // Read user choice
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Print the graph
                        socialNetwork.printGraph();
                        break;
                    case 2:
                        // Find shortest path using BFS
                        System.out.print("Enter start user ID: ");
                        String startUser = scanner.next();
                        System.out.print("Enter end user ID: ");
                        String endUser = scanner.next();
                        socialNetwork.shortestPathBFS(startUser, endUser);
                        break;
                    case 3:
                        // Perform DFS
                        System.out.print("Enter start user ID: ");
                        String dfsStart = scanner.next();
                        System.out.print("DFS starting from " + dfsStart + ": ");
                        socialNetwork.depthFirstSearch(dfsStart);
                        System.out.println();
                        break;
                    case 4:
                        // Find mutual friends
                        System.out.print("Enter first user ID: ");
                        String user1 = scanner.next();
                        System.out.print("Enter second user ID: ");
                        String user2 = scanner.next();
                        socialNetwork.findMutualFriends(user1, user2);
                        break;
                    case 5:
                        // Friend recommendations
                        System.out.print("Enter user ID to get recommendations: ");
                        String userToRecommend = scanner.next();
                        socialNetwork.recommendFriends(userToRecommend);
                        break;
                    case 6:
                        // Calculate PageRank
                        Map<String, Double> pageRankScores = socialNetwork.pageRank(10, 0.85);
                        System.out.println("PageRank Scores:");
                        for (Map.Entry<String, Double> entry : pageRankScores.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        break;
                    case 7:
                        // Calculate degree centrality
                        Map<String, Integer> degreeCentrality = socialNetwork.calculateDegreeCentrality();
                        System.out.println("Degree Centrality: " + degreeCentrality);
                        break;
                    case 8:
                        // Calculate closeness centrality
                        System.out.print("Enter user ID to calculate closeness centrality: ");
                        String closenessUser = scanner.next();
                        double closeness = socialNetwork.calculateClosenessCentrality(closenessUser);
                        System.out.println("Closeness Centrality of " + closenessUser + ": " + closeness);
                        break;
                    case 9:
                        // Calculate betweenness centrality
                        Map<String, Double> betweennessCentrality = socialNetwork.calculateBetweennessCentrality();
                        System.out.println("Betweenness Centrality: " + betweennessCentrality);
                        break;
                    case 0:
                        // Exit the program
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

    }
}