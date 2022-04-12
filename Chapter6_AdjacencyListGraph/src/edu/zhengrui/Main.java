package edu.zhengrui;

public class Main {
    public static void main(String[] args) {
        Graph graph = initGraph();

        System.out.println();
    }

    public static Graph initGraph(){
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "E");
        graph.addEdge("B", "C");
        graph.addEdge("A", "B");
    }
}
