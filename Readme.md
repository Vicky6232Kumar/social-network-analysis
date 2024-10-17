# Social Network Analysis Using Graph Algorithms

## Project Overview

This project is a **Social Network Analysis** tool that models a social network as a graph, where **nodes** represent users and **edges** represent relationships (follower or friendship connections). The project demonstrates various graph algorithms such as **Mutual Friends Finder**, **Friend Recommendation System**, **Degree Centrality**, **Closeness Centrality**, **Betweenness Centrality**, and **PageRank** to provide insights into user connectivity and influence within the network.

Users and their follower relationships are read from external files (`users.txt` and `followers.txt`), and the network is analyzed using various graph algorithms.

---

## Features and Real-Life Use Cases

### 1. **Graph Representation from Files**

- Users are represented as **nodes** and follower relationships as **directed edges**.
- The program reads from two files (`users.txt` and `followers.txt`) to dynamically build the graph.
- **Use Case**: This structure can model real-world social media platforms (like Twitter and Instagram) where users follow other users.

### 2. **Mutual Friends Finder**

- This function identifies the mutual friends between two users by comparing their friend lists (followers).
- **Use Case**: Similar to Facebook’s feature that shows mutual friends when viewing another user’s profile, this algorithm identifies shared connections between two people.

**Example**: Alice and Bob both follow Charlie, so Charlie is their mutual friend.

### 3. **Friend Recommendation System**

- This function recommends friends for a user by analyzing the network of their current friends and suggesting friends-of-friends (i.e., common neighbors).
- **Use Case**: Similar to LinkedIn’s "People you may know" feature, this recommendation system suggests new connections based on shared friends.

**Example**: Alice follows Bob and Bob follows Charlie. The system might recommend that Alice follow Charlie.

### 4. **Degree Centrality**

- Degree centrality measures the influence of a user based on the number of connections (followers) they have. Users with more followers have higher degree centrality.
- **Use Case**: This measure can be used to identify influencers in the network, similar to how the number of followers is important on platforms like Instagram or Twitter.

**Example**: If Alice has 100 followers and Bob has 50, Alice’s degree centrality is higher.

### 5. **Closeness Centrality**

- Closeness centrality measures how close a user is to all other users in the network based on the shortest paths between them. Users who can reach others more quickly have higher closeness centrality.
- **Use Case**: This metric can identify users who are well-positioned to quickly spread information across a network, which is useful in viral marketing campaigns.

**Example**: If Alice is connected to many others directly or through short paths, her closeness centrality is high.

### 6. **Betweenness Centrality**

- Betweenness centrality measures how often a user lies on the shortest paths between other users. Users with high betweenness centrality act as bridges between different parts of the network.
- **Use Case**: This measure identifies key users who connect different groups, useful in social networks or business collaborations.

**Example**: Bob connects Alice and Charlie, so Bob has higher betweenness centrality.

### 7. **PageRank Algorithm**

- This function implements the **PageRank** algorithm to rank users based on their importance in the network. It considers not just the number of connections but also the quality of connections (i.e., being followed by influential users).
- **Use Case**: PageRank is the core algorithm used by Google to rank web pages and can similarly rank users based on their influence in a social network.

**Example**: If Alice is followed by many important users, her PageRank score will be high.

---

## Technologies Used

- **Java**: The entire project is implemented in Java using core data structures and algorithms.
- **Graph Data Structures**: The project uses adjacency lists to efficiently represent the social network.
- **File I/O**: The project reads user and follower data from text files to dynamically create the graph.

---

## How to Run

### Prerequisites

- **Java 8** or above must be installed on your system.
- A code editor like **IntelliJ IDEA** or **VS Code** (with Java support).

### Steps to Run the Project

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Vicky6232Kumar/social-network-analysis.git
   ```

2. **Prepare Data Files**:

   - Create `users.txt` and `followers.txt` in the same directory as the Java code.

3. **Compile the Project**:

- Open a terminal or command line in the project directory and run:
  ```bash
  javac SocialNetworkAnalysis.java
  ```

4. **Run the Project**:

- After compiling, run the main class:
  ```bash
  java SocialNetworkAnalysis
  ```

---
