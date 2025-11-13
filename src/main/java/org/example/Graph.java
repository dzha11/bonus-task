package org.example;

import java.util.*;

public class Graph {
    public static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public String toString() { return "(" + u + " - " + v + ", w=" + w + ")"; }
    }

    int V;
    List<Edge> edges = new ArrayList<>();

    public Graph(int V) { this.V = V; }

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }
}