package org.example;

import java.util.*;

public class KruskalMST {
    public List<Graph.Edge> buildMST(Graph g) {
        List<Graph.Edge> result = new ArrayList<>();
        g.edges.sort(Comparator.comparingInt(e -> e.w));
        DisjointSet ds = new DisjointSet(g.V);

        for (Graph.Edge e : g.edges) {
            if (ds.union(e.u, e.v)) result.add(e);
        }
        return result;
    }

    public int totalWeight(List<Graph.Edge> mst) {
        return mst.stream().mapToInt(e -> e.w).sum();
    }
}