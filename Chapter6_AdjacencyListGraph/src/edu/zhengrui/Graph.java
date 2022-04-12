package edu.zhengrui;

import java.util.HashMap;
import java.util.Locale;

public class Graph {
    public HashMap<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<>();
    }

    public void addNode(String nodeName){
        nodeName = nodeName.toLowerCase();
        if (nodes.containsKey(nodeName)){
            return;
        }
        Node node = new Node(nodeName);
        nodes.put(nodeName, node);
    }

    public void addEdge(String from, String to){
        from = from.toLowerCase();
        to = to.toLowerCase();
        if (!nodes.containsKey(from) || !nodes.containsKey(to)){
            return;
        }

        Edge edge = new Edge(from, to);
        Node fromNode = nodes.get(from);

        fromNode.neighbors.add(edge);
    }
}
