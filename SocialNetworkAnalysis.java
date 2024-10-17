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

            socialNetwork.readUsersFromFile("users.txt");
            socialNetwork.readFollowersFromFile("followers.txt");

            // Print the graph
            // socialNetwork.printGraph();

            // // Perform BFS to find shortest path
            // socialNetwork.shortestPathBFS("1", "5");

            // Perform DFS
            // System.out.print("DFS starting from Alice: ");
            // socialNetwork.depthFirstSearch("1");
            // System.out.println();

            // Find mutual friends
            // socialNetwork.findMutualFriends("1", "5");

            // // Friend recommendations
            // socialNetwork.recommendFriends("4");

            // Calculate PageRank
            // Map<String, Double> pageRankScores = socialNetwork.pageRank(10, 0.85);
            // System.out.println("PageRank Scores:");
            // for (Map.Entry<String, Double> entry : pageRankScores.entrySet()) {
            // System.out.println(entry.getKey() + ": " + entry.getValue());
            // }

            // Calculate degree centrality
            // Map<String, Integer> degreeCentrality =
            // socialNetwork.calculateDegreeCentrality();
            // System.out.println("Degree Centrality: " + degreeCentrality);

            // Calculate closeness centrality for Alice
            // double closeness = socialNetwork.calculateClosenessCentrality("1");
            // System.out.println("Closeness Centrality of Alice: " + closeness);

            // Calculate betweenness centrality
            // Map<String, Double> betweennessCentrality =
            // socialNetwork.calculateBetweennessCentrality();
            // System.out.println("Betweenness Centrality: " + betweennessCentrality);

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

    }
}