// C343 Fall 2020
// Aaron Lack alack Lab 11
// a simple implementation for graphs with adjacency lists

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // every visited node:
    private Vector<Boolean> visited;

    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;


    //A data structure for the adjacency matrix in Floyds Algorithm
    private int[][] matrix;

    public AdjGraph() {
        init();
    }

    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }

    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNodes = totalEdges = 0;
        digraph = false;
    }

    // set vertices:
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }

    // add a vertex:
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNodes ++;
    }

    public int getNode(String node) {
        for (int i = 0; i < nodeList.size(); i ++) {
            if (nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices:
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if (adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdges ++;
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if ((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2:
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well:
            if (digraph == false) {
                setEdge(getNode(v2), getNode(v1), weight);
            }
        }
    }

    // keep track whether a vertex has been visited or not,
    //    for graph traversal purposes:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }


    // clean up before traversing the graph:
    public void clearWalk() {
        nodeEnum.clear();
        for (int i = 0; i < nodeList.size(); i ++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i ++) {
            if (ifVisited(i) == false) {
                if (method.equals("BFS")) {
                    BFS(i);      // i is the start node
                } else if (method.equals("DFS")) {
                    DFS(i); // i is the start node
                } else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for (int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if (ifVisited(v1) == false) {
                DFS(v1);
            }
        }
    }

    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while (toVisit.size() > 0) {
            int v = toVisit.remove(0);   // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for (int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if ( (ifVisited(v1) == false) && (toVisit.contains(v1) == false) ) {
                    toVisit.add(v1);
                }
            }
        }
    }

    public void display() {
        System.out.println("total nodes: " + totalNodes);
        System.out.println("total edges: " + totalEdges);
    }

    public void displayEnum() {
        for (int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    /*
    TODO:
     Implement a method called floydNoWeights() that computes the length (in this case it's equivalent
      to the number of edges in the path) of the shortest path for every pair of nodes in the graph, and
       displays the results in a format that's easy to read.
     In the initialization step: if there is an edge from u to v, set the number of edges between these two
      nodes to 1; otherwise, set the counter to a very large number (e.g., Integer.MAX_VALUE).
     */

    //Floyds Algorithm Method
    //I put 9999999 instead of Integer.MAX_VALUE because Integer.MAX_VAlUE was giving me a lot of values
    // that did not make sense.
    public void floydNoWeights(){
        //instantiating the adjacency matrix
        matrix = new int[totalNodes][totalNodes];

        //Looping through the matrix and setting the number of edges to the max value 9999999
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                NumberOfEdges(i, j, 9999999);
            }
        }

        //Given two nodes, if there is a connection between the same node, set it to 0 because not possible
        for(int i = 0; i < totalNodes; i++){
            NumberOfEdges(i, i, 0);
        }

        //Given two nodes, if there is a connection, set the number of edges to 1
        for(int i = 0; i < adjList.size(); i++){
            for(int j: adjList.get(i)){
                NumberOfEdges(i,j,1);
            }
        }

        //Floyds algorithm main step.
        for(int k = 0; k < totalNodes; k++){
            for(int i = 0; i < totalNodes; i++){
                for(int j = 0; j < totalNodes; j++){
                    //Only gets executed if the the matrix[i][k] and matrix[k][j] are not the max value
                    if(matrix[i][k] != 9999999 && matrix[k][j] != 9999999) {
                        if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                        }
                    }
                }
            }
        }
        //Calling displayConnections
        displayConnections();
    }

    //NumberOfEdges method to be called in the floyds algorithm,
    // sets the matrix of [i][j] to the number of edges
    public void NumberOfEdges(int u, int v, int edges) {
        matrix[u][v] = edges;
    }

    //displayConnections is a helper method used for Floyds algorithm to get a better visualization of all
    // of the connections in a given graph
    //Arrays.toString(i) gives me the adjacency list
    public void displayConnections() {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 9999999) {
                    System.out.println("No Connections");
                }
                else {
                    System.out.println("Number of Connections: " + Integer.toString(matrix[i][j]));
                }
            }
        }
    }

    //Create 3 Graphs with at least 5 vertices and 7 edges
    //Number of connections is A-A, A-B, A-C, A-D, A-E, B-A,... etc.
    public static void main(String[] args) {
        AdjGraph graph = new AdjGraph(true);
        System.out.println("Graph 1");
        //Creating 5 vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        //Creating at least 7 edges between vertices, mine says 4 edges...
        graph.setEdge("A", "C", 1);
        graph.setEdge("B", "C", 1);
        graph.setEdge("C", "E", 1);
        graph.setEdge("D", "A", 1);
        graph.setEdge("E", "A", 1);
        graph.setEdge("B", "D", 1);
        graph.setEdge("D", "E", 1);
        //Calling display method
        graph.display();
        //Call Floyd's Algorithm
        graph.floydNoWeights();

        System.out.println();

        AdjGraph graph2 = new AdjGraph(true);
        System.out.println("Graph 2");
        //Creating 5 vertices
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        //Creating at least 7 edges between vertices, mine says 4 edges...
        graph2.setEdge("A", "B", 1);
        graph2.setEdge("B", "C", 1);
        graph2.setEdge("C", "D", 1);
        graph2.setEdge("D", "E", 1);
        graph2.setEdge("E", "A", 1);
        graph2.setEdge("A", "C", 1);
        graph2.setEdge("B", "E", 1);
        //Calling display method
        graph2.display();
        //Call Floyd's Algorithm
        graph2.floydNoWeights();

        System.out.println();

        AdjGraph graph3 = new AdjGraph(true);
        System.out.println("Graph 3");
        //Creating 5 vertices
        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addVertex("C");
        graph3.addVertex("D");
        graph3.addVertex("E");
        //Creating at least 7 edges between vertices, mine says 4 edges...
        graph3.setEdge("A", "B", 1);
        graph3.setEdge("A", "C", 1);
        graph3.setEdge("A", "D", 1);
        graph3.setEdge("A", "E", 1);
        graph3.setEdge("B", "C", 1);
        graph3.setEdge("B", "D", 1);
        graph3.setEdge("B", "E", 1);
        //Calling display method
        graph3.display();
        //Call Floyd's Algorithm
        graph3.floydNoWeights();

    }

} // end of class AdjGraph