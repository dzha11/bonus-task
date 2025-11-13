package org.example;

import java.util.*;

public class ReplacementFinder {

    public static List<List<Integer>> getComponents(int V, List<Graph.Edge> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (Graph.Edge e : edges) {
            adj.get(e.u).add(e.v);
            adj.get(e.v).add(e.u);
        }

        boolean[] visited = new boolean[V];
        List<List<Integer>> comps = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, adj, visited, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    private static void dfs(int u, List<List<Integer>> adj, boolean[] vis, List<Integer> comp) {
        vis[u] = true;
        comp.add(u);
        for (int v : adj.get(u))
            if (!vis[v]) dfs(v, adj, vis, comp);
    }

    public static Graph.Edge findReplacement(Graph g, List<Integer> compA, List<Integer> compB, List<Graph.Edge> mst) {
        Set<String> mstEdges = new HashSet<>();
        for (Graph.Edge e : mst)
            mstEdges.add(e.u + "-" + e.v + "," + e.v + "-" + e.u);

        Graph.Edge best = null;
        for (Graph.Edge e : g.edges) {
            if (mstEdges.contains(e.u + "-" + e.v)) continue;
            boolean aIn = compA.contains(e.u) || compA.contains(e.v);
            boolean bIn = compB.contains(e.u) || compB.contains(e.v);
            if (aIn && bIn) {
                if (best == null || e.w < best.w) best = e;
            }
        }
        return best;
    }
}