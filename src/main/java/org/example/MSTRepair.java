package org.example;

import java.util.*;

// брат, тут восстановление MST после удаления ребра
public class MSTRepair {

    public static void repairMST(Graph g, List<Graph.Edge> mstEdges, Graph.Edge removedEdge) {
        System.out.println("Удаляем ребро: " + removedEdge);
        mstEdges.remove(removedEdge);

        Set<Integer> compA = new HashSet<>();
        dfs(mstEdges, removedEdge.u, compA);

        Set<Integer> compB = new HashSet<>();
        for (int i = 0; i < g.V; i++) {
            if (!compA.contains(i)) compB.add(i);
        }

        System.out.println("Компонента A: " + compA);
        System.out.println("Компонента B: " + compB);

        Graph.Edge replacement = null;
        int bestWeight = Integer.MAX_VALUE;
        for (Graph.Edge e : g.edges) {
            if ((compA.contains(e.u) && compB.contains(e.v)) ||
                    (compA.contains(e.v) && compB.contains(e.u))) {
                if (e.w < bestWeight) {
                    bestWeight = e.w;
                    replacement = e;
                }
            }
        }

        if (replacement != null) {
            System.out.println("Найдено новое ребро для соединения: " + replacement);
            mstEdges.add(replacement);
        } else {
            System.out.println("Не найдено подходящее ребро для восстановления");
        }

        System.out.println("Обновлённый MST:");
        int total = 0;
        for (Graph.Edge e : mstEdges) {
            System.out.println("  " + e);
            total += e.w;
        }
        System.out.println("Общий вес нового MST: " + total);
    }

    private static void dfs(List<Graph.Edge> mstEdges, int node, Set<Integer> visited) {
        visited.add(node);
        for (Graph.Edge e : mstEdges) {
            if (e.u == node && !visited.contains(e.v)) dfs(mstEdges, e.v, visited);
            if (e.v == node && !visited.contains(e.u)) dfs(mstEdges, e.u, visited);
        }
    }
}