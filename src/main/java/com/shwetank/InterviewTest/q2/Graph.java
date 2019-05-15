package com.shwetank.InterviewTest.q2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {

    private int vertexInGraph;
    private LinkedList[] adjacentListNodes;

    Graph(int vertexInGraph) {
        this.vertexInGraph = vertexInGraph;
        adjacentListNodes = new LinkedList[vertexInGraph];

        for (int i = 0; i < vertexInGraph; i++) {
            adjacentListNodes[i] = new LinkedList<AdjacentNode>();
        }
    }

    public Graph() {
    }

    Graph newGraph(int vertexes){
        return new Graph(vertexes);
    }

    void createEdges(int parentNode, int childNode, int weight) {
        AdjacentNode node = new AdjacentNode(childNode, vertexInGraph);
        //creating edges between source and target node
        adjacentListNodes[parentNode].add(node);
    }

    void topologicalSort(int eachVertex, Boolean visited[], Stack stack) {
        visited[eachVertex] = true;
        Iterator<AdjacentNode> iterator = adjacentListNodes[eachVertex].iterator();
        while (iterator.hasNext()) {
            AdjacentNode node = iterator.next();
            if (!visited[node.getVertex()])
                topologicalSort(node.getVertex(), visited, stack);
        }
        stack.push(eachVertex);
    }

    void shortestPath(int fromSourceNode) {
        Stack stack = new Stack();
        int maxValue = Integer.MAX_VALUE;
        int distanceOfNodes[] = new int[vertexInGraph];
        Boolean visited[] = new Boolean[vertexInGraph];

        //denoting all the vertices as not visited
        for (int i = 0; i < vertexInGraph; i++) {
            visited[i] = false;
        }

        // sorting all the vertices
        for (int i = 0; i < vertexInGraph; i++) {
                topologicalSort(i, visited, stack);
        }

        //distance to all vertices as max and distance to source zero
        for (int i = 0; i < vertexInGraph; i++)
            distanceOfNodes[i] = maxValue;
        distanceOfNodes[fromSourceNode] = 0;

        while (stack.empty() == false) {

            //get new vertex from the topological order
            int newInteger = (int) stack.pop();

            Iterator<AdjacentNode> iterator;
            if (distanceOfNodes[newInteger] != maxValue) {
                iterator = adjacentListNodes[newInteger].iterator();
                while (iterator.hasNext()) {
                    AdjacentNode adjacentListNode = iterator.next();
                    if (distanceOfNodes[adjacentListNode.getVertex()] > distanceOfNodes[newInteger] + adjacentListNode.getWeight())
                        distanceOfNodes[adjacentListNode.getVertex()] = distanceOfNodes[newInteger] + adjacentListNode.getWeight();
                }
            }
        }

        for (int i = 0; i < vertexInGraph; i++) {
            if (distanceOfNodes[i] == maxValue)
                System.out.println("MaxValue ");
            else
                System.out.println(distanceOfNodes[i] + " ");
        }
    }



}
