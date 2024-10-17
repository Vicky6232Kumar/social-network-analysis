package Social_Networking_Analysis; // use the package name where you clone the project

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SocialNetworkGraph {

    // Maps user IDs to names
    private Map<String, String> users = new HashMap<>();
    // Map to store adjacency list of the graph
    private Map<String, List<String>> graph = new HashMap<>();

    // Add a new user (node) to the graph
    public void addUser(String userId, String name) {
        users.put(userId, name);
        graph.putIfAbsent(userId, new ArrayList<>());
    }

    // Add a follower relationship (directed edge) between two users
    public void addFollower(String followerId, String followedId) {
        graph.get(followerId).add(followedId);
    }

    // Print the social network graph
    public void printGraph() {
        for (String userId : graph.keySet()) {
            System.out.println(users.get(userId) + " (id:" + userId + ") is following: ");
            for (String followedId : graph.get(userId)) {
                System.out.println("  -> " + users.get(followedId) + " (id:" + followedId + ")");
            }
        }
    }

    // BFS Algorithm to find the shortest path between two users
    public void shortestPathBFS(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String user = queue.poll();

            // If we reached the destination, print the path
            if (user.equals(end)) {
                printPath(previous, start, end);
                return;
            }

            // Visit all neighbors
            for (String neighbor : graph.get(user)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    previous.put(neighbor, user);
                }
            }
        }

        System.out.println("No path exists between " + start + " and " + end);
    }

    // Utility method to print the path from BFS
    private void printPath(Map<String, String> previous, String start, String end) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.addFirst(users.get(at));
        }
        System.out.println("Shortest path from " + users.get(start) + " to " + users.get(end) + ": " + path);
    }

    // DFS Algorithm for traversing the social network
    public void depthFirstSearch(String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited);
    }

    private void dfsHelper(String user, Set<String> visited) {
        visited.add(user);
        System.out.print(users.get(user) + " ");

        for (String neighbor : graph.get(user)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    // Implementing a simplified PageRank algorithm
    public Map<String, Double> pageRank(int iterations, double dampingFactor) {
        Map<String, Double> ranks = new HashMap<>();
        int n = graph.size();
    
        // Initialize ranks for each user (equal distribution initially)
        for (String user : graph.keySet()) {
            ranks.put(user, 1.0 / n);
        }
    
        // Iteratively calculate PageRank
        for (int i = 0; i < iterations; i++) {
            Map<String, Double> newRanks = new HashMap<>();
            for (String user : graph.keySet()) {
                double newRank = (1 - dampingFactor) / n;  // Base rank (random jump)
    
                // Sum the rank contributions of all users that follow 'user'
                for (String neighbor : graph.keySet()) {
                    if (graph.get(neighbor).contains(user)) {  // If neighbor links to 'user'
                        newRank += dampingFactor * (ranks.get(neighbor) / graph.get(neighbor).size());
                    }
                }
                newRanks.put(user, newRank);
            }
            ranks = newRanks;
        }
    
        // Convert user IDs to names for output
        Map<String, Double> ranksWithNames = new HashMap<>();
        for (String userId : ranks.keySet()) {
            ranksWithNames.put(users.get(userId), ranks.get(userId));
        }
    
        return ranksWithNames;
    }
    
    // Find mutual friends between two users
    public void findMutualFriends(String user1, String user2) {
        List<String> friendsOfUser1 = graph.get(user1);
        List<String> friendsOfUser2 = graph.get(user2);
    
        // Check if users exist in the graph
        if (friendsOfUser1 == null || friendsOfUser2 == null) {
            System.out.println("One or both users do not exist.");
            return;
        }
    
        // Check if either user has no friends
        if (friendsOfUser1.isEmpty() || friendsOfUser2.isEmpty()) {
            System.out.println("One or both users have no friends.");
            return;
        }
    
        // Find mutual friends
        Set<String> mutualFriends = new HashSet<>(friendsOfUser1);
        mutualFriends.retainAll(friendsOfUser2);
    
        // Output result
        System.out.print("Mutual friends of " + users.get(user1) + " and " + users.get(user2) + ": ");
        if (mutualFriends.isEmpty()) {
            System.out.println("None");
        } else {
            for (String friend : mutualFriends) {
                System.out.print(users.get(friend) + " ");
            }
            System.out.println();
        }
    }
    
    // Friend recommendation system (common neighbors approach)
    public void recommendFriends(String user) {
        if (!graph.containsKey(user)) {
            System.out.println(users.get(user) + " does not exist in the network.");
            return;
        }

        Set<String> recommendedFriends = new HashSet<>();

        // Get friends of friends
        for (String friend : graph.get(user)) {
            for (String friendOfFriend : graph.get(friend)) {
                if (!friendOfFriend.equals(user) && !graph.get(user).contains(friendOfFriend)) {
                    recommendedFriends.add(friendOfFriend);
                }
            }
        }

        System.out.print("Friend recommendations for " + users.get(user) + ": ");
        if (recommendedFriends.isEmpty()) {
            System.out.println("None");
        } else {
            for (String recommended : recommendedFriends) {
                System.out.print(users.get(recommended) + " ");
            }
            System.out.println();
        }
    }

      // Calculate degree centrality (influence based on number of connections)
    public Map<String, Integer> calculateDegreeCentrality() {
        Map<String, Integer> degreeCentrality = new HashMap<>();

        for (String user : graph.keySet()) {
            degreeCentrality.put(users.get(user), graph.get(user).size());
        }

        return degreeCentrality;
    }

    // Calculate closeness centrality (average shortest path length to others)
    public double calculateClosenessCentrality(String user) {
        double totalDistance = 0;
        int reachableNodes = 0;
        for (String target : graph.keySet()) {
            if (!user.equals(target)) {
                int distance = bfsDistance(user, target);
                if (distance > 0) {
                    totalDistance += distance;
                    reachableNodes++;
                }
            }
        }

        return reachableNodes > 0 ? (double) reachableNodes / totalDistance : 0;
    }

    // Helper method to calculate BFS distance between two users (returns distance)
    private int bfsDistance(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        queue.add(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String user = queue.poll();
            if (user.equals(end)) {
                return distances.get(user);
            }
            for (String neighbor : graph.get(user)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    distances.put(neighbor, distances.get(user) + 1);
                }
            }
        }
        return -1; // No path found
    }

    // Calculate betweenness centrality (number of times a node appears on shortest
    // paths)
    public Map<String, Double> calculateBetweennessCentrality() {
        Map<String, Double> betweenness = new HashMap<>();
        for (String user : graph.keySet()) {
            betweenness.put(user, 0.0);
        }

        for (String source : graph.keySet()) {
            for (String target : graph.keySet()) {
                if (!source.equals(target)) {
                    List<List<String>> allShortestPaths = findAllShortestPaths(source, target);
                    for (List<String> path : allShortestPaths) {
                        for (String node : path) {
                            if (!node.equals(source) && !node.equals(target)) {
                                betweenness.put(node, betweenness.get(node) + 1.0 / allShortestPaths.size());
                            }
                        }
                    }
                }
            }
        }

        // Convert user IDs to names for betweenness centrality display
        Map<String, Double> betweennessWithNames = new HashMap<>();
        for (String userId : betweenness.keySet()) {
            betweennessWithNames.put(users.get(userId), betweenness.get(userId));
        }
        return betweennessWithNames;
    }

    // Helper method to find all shortest paths between two users (returns all
    // paths)
    private List<List<String>> findAllShortestPaths(String start, String end) {
        List<List<String>> paths = new ArrayList<>();
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Arrays.asList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastNode = path.get(path.size() - 1);

            if (lastNode.equals(end)) {
                paths.add(new ArrayList<>(path));
            }

            for (String neighbor : graph.get(lastNode)) {
                if (!visited.contains(neighbor)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return paths;
    }

    // Read user data from a file
    public void readUsersFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            addUser(parts[0], parts[1]);
        }
        reader.close();
    }

    // Read follower data from a file
    public void readFollowersFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            addFollower(parts[0], parts[1]);
        }
        reader.close();
    }
}
