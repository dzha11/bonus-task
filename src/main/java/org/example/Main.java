package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0,1,3);
        g.addEdge(0,2,4);
        g.addEdge(1,2,1);
        g.addEdge(1,3,2);
        g.addEdge(2,3,4);
        g.addEdge(3,4,2);
        g.addEdge(4,5,6);
        g.addEdge(2,5,5);

        KruskalMST kruskal = new KruskalMST();
        List<Graph.Edge> mst = kruskal.buildMST(g);
        int weight = kruskal.totalWeight(mst);

        System.out.println("Исходный MST:");
        mst.forEach(e -> System.out.println("  " + e));
        System.out.println("Общий вес: " + weight);

        // удаляем одно ребро
        Graph.Edge removed = mst.get(1);
        mst.remove(removed);
        System.out.println("\nУдалено ребро: " + removed);

        // компоненты
        List<List<Integer>> comps = ReplacementFinder.getComponents(g.V, mst);
        System.out.println("Компонента A: " + comps.get(0));
        System.out.println("Компонента B: " + comps.get(1));

        // находим замену
        Graph.Edge replacement = ReplacementFinder.findReplacement(g, comps.get(0), comps.get(1), mst);
        System.out.println("Добавляем новое ребро: " + replacement);
        mst.add(replacement);

        int newWeight = kruskal.totalWeight(mst);
        System.out.println("\nОбновленный MST:");
        mst.forEach(e -> System.out.println("  " + e));
        System.out.println("Общий вес: " + newWeight);
    }
}