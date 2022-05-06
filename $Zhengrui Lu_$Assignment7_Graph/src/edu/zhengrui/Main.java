package edu.zhengrui;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int a = 5, b = 6;
        swap(a, b);
    }
    private static void swap(int a, int b){
        int temp = a;
        a = b;
//        b = temp;
        temp = b;
    }

//
//    /*
//    133. Clone Graph
//    BFS
//    O(N), O(N)
//    the Node class is in another java file in the same package
//    */
//    public Node cloneGraph(Node node) {
//        if (node == null){
//            return node;
//        }
//
//        Queue<Node> que = new LinkedList<>();
//        HashMap<Node, Node> visited = new HashMap<>();
//        visited.put(node, new Node(node.val, new ArrayList<Node>()));
//        que.add(node);
//
//        while (!que.isEmpty()){
//            Node ori = que.poll();
//
//            for (Node neibor: ori.neighbors){
//                if (!visited.containsKey(neibor)){
//                    visited.put(neibor, new Node(neibor.val, new ArrayList<Node>()));
//                    que.add(neibor);
//                }
//
//                Node copyNeibor = visited.get(neibor);
//                visited.get(ori).neighbors.add(copyNeibor);
//            }
//        }
//
//        return visited.get(node);
//    }
//
//    /*
//    847. Shortest Path Visiting All Nodes
//    BFS
//    O(2^N * N^2), O(2^N * N)
//    */
//    public int shortestPathLength(int[][] graph) {
//        if (graph.length == 1) {
//            return 0;
//        }
//
//        int n = graph.length;
//        int endingMask = (1 << n) - 1;
//        boolean[][] seen = new boolean[n][endingMask];
//        ArrayList<int[]> queue = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            queue.add(new int[] {i, 1 << i});
//            seen[i][1 << i] = true;
//        }
//
//        int steps = 0;
//        while (!queue.isEmpty()) {
//            ArrayList<int[]> nextQueue = new ArrayList<>();
//            for (int i = 0; i < queue.size(); i++) {
//                int[] currentPair = queue.get(i);
//                int node = currentPair[0];
//                int mask = currentPair[1];
//                for (int neighbor : graph[node]) {
//                    int nextMask = mask | (1 << neighbor);
//                    if (nextMask == endingMask) {
//                        return 1 + steps;
//                    }
//
//                    if (!seen[neighbor][nextMask]) {
//                        seen[neighbor][nextMask] = true;
//                        nextQueue.add(new int[] {neighbor, nextMask});
//                    }
//                }
//            }
//            steps++;
//            queue = nextQueue;
//        }
//
//        return -1;
//    }
//
//    /*
//    2065. Maximum Path Quality of a Graph
//    create Node and Edge class to make the approach more clear
//    */
//    public class Node {
//        public int val;
//        int id;
//        int value;
//        List<Edge> edges;
//        public Node(int id, int value) {
//            this.id = id;
//            this.value = value;
//            edges = new ArrayList<>();
//        }
//    }
//
//    public class Edge {
//        int id1;
//        int id2;
//        int cost;
//        public Edge(int id1, int id2, int cost) {
//            this.id1 = id1;
//            this.id2 = id2;
//            this.cost = cost;
//        }
//    }
//
//    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
//        List<Node> nodes = new ArrayList<>();
//        for (int i = 0; i < values.length; i++) {
//            nodes.add(new Node(i, values[i]));
//        }
//
//        for (int[] edge : edges) {
//            Edge e = new Edge(edge[0], edge[1], edge[2]);
//            nodes.get(edge[0]).edges.add(e);
//            nodes.get(edge[1]).edges.add(e);
//        }
//
//        for (Node node : nodes) {
//            Collections.sort(node.edges, (e1, e2) -> {
//                return e1.cost - e2.cost;
//            });
//        }
//
//        Set<Integer> qualityAdded = new HashSet<>();
//        qualityAdded.add(0);
//        return visit(nodes, 0, qualityAdded, maxTime, nodes.get(0).value, 0);
//    }
//
//    private int visit(List<Node> nodes, int curId, Set<Integer> qualityAdded, int maxTime, int quality, int time) {
//        if (time > maxTime) {
//            return Integer.MIN_VALUE;
//        }
//
//        int maxQuality = Integer.MIN_VALUE;
//        if (curId == 0 && time <= maxTime) {
//            maxQuality = Math.max(maxQuality, quality);
//        }
//
//        for (Edge edge : nodes.get(curId).edges) {
//            if (time + edge.cost > maxTime) {
//                break;
//            }
//
//            int nbrId = edge.id1 != curId ? edge.id1 : edge.id2;
//            boolean hasAdded = qualityAdded.contains(nbrId);
//            qualityAdded.add(nbrId);
//            int newQuality = hasAdded ? quality : quality + nodes.get(nbrId).value;
//            maxQuality = Math.max(maxQuality, visit(nodes, nbrId, qualityAdded, maxTime, newQuality, time + edge.cost));
//            if (!hasAdded) {
//                qualityAdded.remove(nbrId);
//            }
//        }
//
//        return maxQuality;
//    }
}
