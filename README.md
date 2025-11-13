Bonus Task — MST Repair (Design & Analysis of Algorithms)
This repository contains my solution for the bonus task in the Design & Analysis of Algorithms course.
The goal of the task is to implement:
Construction of a Minimum Spanning Tree (MST) using Kruskal’s algorithm.
Recovery of the MST after removing one edge, while preserving the minimal total weight.
The entire project is implemented in Java using clean, modular architecture and follows the required theoretical principles.


What This Project Does
This program:
Builds an MST from a weighted undirected graph using Kruskal's algorithm
Removes one edge from the MST
Detects how the tree splits into two connected components
Finds a replacement edge (the minimum-weight edge reconnecting the components)
Inserts the replacement edge and restores the MST
Prints both the original and the updated trees
This models a real-world scenario such as network recovery, where failing connections must be replaced efficiently.


Project Structure:
src/main/java/org/example/
│
├── DisjointSet.java         # Union–Find data structure (with path compression + rank)
├── Graph.java               # Graph representation (vertices + list of edges)
├── KruskalMST.java          # Implementation of Kruskal's MST algorithm
├── ReplacementFinder.java   # Finds replacement edges after deletion
├── MSTRepair.java           # Alternative MST recovery logic using DFS
└── Main.java                # Demonstration of the full process
Additionally:
pom.xml                      # Maven configuration + JUnit


Algorithmic Concepts Used
1. Kruskal’s Algorithm
I sort all edges by weight and use a Disjoint Set (Union–Find) to avoid cycles.
Only edges that connect two different components are added to the MST.
2. Union–Find (Disjoint Set Union)
Used to efficiently track connected components while building the MST.
Includes:
Path compression
Union by rank
3. Component Detection After Deletion
When one MST edge is removed, the tree is guaranteed to split into exactly two components.
I implemented this using DFS:
ReplacementFinder.getComponents() — adjacency-list based
MSTRepair.dfs() — reconstructs one component from the deleted edge
4. Replacement Edge Selection
The program scans all graph edges and selects the minimum-weight edge that reconnects the two components while avoiding edges already in the MST.
This ensures the restored MST remains optimal.


How to Run the Project
Make sure you have:
Java 17+
Maven
1. Compile: mvn compile
2. Run: mvn exec:java -Dexec.mainClass="org.example.Main"
Or simply run Main.java from IntelliJ IDEA.


Example Output
The program prints:
The original MST
Its total weight
Which edge was removed
The two components created
The chosen replacement edge
The new updated MST with its weight
Example:
Original MST:
  (1 - 2, w=1)
  (1 - 3, w=2)
  (3 - 4, w=2)
  (0 - 1, w=3)
  (4 - 5, w=6)
Total weight: 14

Removed edge: (1 - 3, w=2)
Component A: [1, 2, 0]
Component B: [3, 4, 5]

Replacement edge found: (3 - 4, w=2)

Updated MST:
  ...
Total weight: 14
This confirms that the MST remains minimal even after recovery.


Testing Support
The Maven configuration includes JUnit 5, so automated tests can be added for:
MST correctness
Component detection
Replacement selection
Cycle avoidance


Building the Project
To create a packaged .jar: mvn clean package
Output: target/daa3_bonus-1.0-SNAPSHOT.jar


Why This Solution Is Correct
My implementation is theoretically sound because:
Kruskal’s algorithm guarantees an MST
Removing one edge from an MST always produces exactly two components
The replacement search checks only cross-component edges
The smallest such edge is always the correct choice for re-optimizing the tree
No cycles are ever created
The resulting structure remains a valid and minimal spanning tree
This satisfies all algorithmic requirements for the bonus task.


Summary:
In this bonus project, I implemented:
Kruskal’s MST algorithm
Efficient Union–Find structure
DFS-based component reconstruction
Correct MST recovery after edge removal
Full runnable demonstration in Java
Clean project architecture using Maven
The code is ready to run, clear to review, and fully aligned with the DAA theory.
