package com.shwetank.InterviewTest.q2;

public class CallingClass {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph = graph.newGraph(8);

        graph.createEdges(0,1,5);
        graph.createEdges(0,2,3);
        graph.createEdges(1,3,6);
        graph.createEdges(1,2,2);
        graph.createEdges(2,4,4);
        graph.createEdges(2,5,2);
        graph.createEdges(2,3,7);
        graph.createEdges(3,4,-1);
        graph.createEdges(4,5,-2);
        graph.createEdges(5,6,2);
        graph.createEdges(5,7,4);
        graph.createEdges(4,7,-2);

        int startingNode = 3;
        System.out.println("Shortest Distance from the source "+startingNode+" are ");
        graph.shortestPath(startingNode);
    }
}
