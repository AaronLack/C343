// C343 Fall 2020
// A simple implementation for graphs with adjacency lists
// Lab 12 starter file

import java.util.*;
import java.util.function.DoubleToIntFunction;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;

    // every visited node:
    private Vector<Boolean> visited;

    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

    //This is the adjacency list data structure for this lab.
    private int[] dist;


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
        adjWeight = new Vector<LinkedList<Integer>>();
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
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }

    // add a vertex:
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
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
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1,  tmp2);
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


    // new for Problem Set 11:
    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }

    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return Integer.MAX_VALUE;
        }
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

    public void prims(String startingNode) {
        //Initialization, very similar to Dijstras in PS11
        dist = new int[totalNodes];
        int s = getNode(startingNode);
        for (int i = 0; i < totalNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        //print the dist array, for testing purposes
        System.out.println("Initial dist array: ");
        for (int i = 0; i < totalNodes; i++) {
            System.out.print(dist[i] + " ");
        }

        System.out.println(); //For better formatting
        System.out.println(); //For better formatting

        //A PriorityQueue data structure for Prims
        PriorityQueue<Integer> S = new PriorityQueue<>();

        //Adds all nodes to the Priority Queue
        for (int i = 0; i < totalNodes; i++) {
            S.add(i);
        }

        int cost = 0; //Cost variable to keep track of weights between edges
        int k = 0; //This is an iterator variable to keep track of edge relaxation steps
        while (!S.isEmpty()) { //Main loop: while the priority queue isn't empty
            int u = S.poll(); //Method takes the top element in priority queue
            S.remove(u); //removes u from the priority queue
            int v = minVertex(); //Call minVertex and set visited methods, from Dikjstra's algorithm.
            setVisited(v); //Calls setVisited

            //A similar enhanced for loop that I used in PS11. Allows for edge relaxation in Prims to happen.
            for(int neighbor: adjList.elementAt(v)) {
                primRelax(neighbor,v);
            }

            //prints the dist array after the relaxation step, for testing
            System.out.println("After the edge relaxation step " + k + " the dist array (MST) is: ");
            for (int j = 0; j < totalNodes; j++) {
                System.out.print(dist[j] + " ");
            }

            System.out.println(); //For better formatting

            cost = cost + dist[u]; //Update the cost
            System.out.println("Total Cost: " + cost); //Displays the total cost of all nodes in MST

            k++; //update k, to show the edge relaxation steps.
            System.out.println();
        }
    }

    //Helper method that gets weight of edges form u to v. Different from Dijstra's since no W is given.
    public void primRelax(int u, int v) {
        if(getWeight(u,v) < dist[v]) {
            dist[v] = getWeight(u, v);
        }
    }

    //MinVertex Method from PS11
    //Helper method that returns the closest unvisited vertex, used in Dijkstra1 algorithm, from Shaffer Book
    public int minVertex() {
        int vertex = 0;
        //initialize v to one unvisited vertex:
        for (int i = 0; i < totalNodes; i++) {
            if (!ifVisited(i)) {
                vertex = i;
                break;
            }
        }
        //look for the closest unvisited vertex:
        for (int i = 0; i < totalNodes; i++) {
            if ((!ifVisited(i)) && (dist[i] < dist[vertex])) {
                vertex = i;
            }
        }
        return vertex;
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

    //I used the same examples from PS11, except for the fact that these are undirected graphs.
    public static void main(String argv[]) {
        AdjGraph graph1 = new AdjGraph(false);
        String[] nodes1 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        graph1.setVertices(nodes1);
        int weight = 1;
        //Set the edges
        graph1.setEdge("A", "B", weight);
        graph1.setEdge("A", "C", weight);
        graph1.setEdge("B", "D", weight);
        graph1.setEdge("C", "E", weight);
        graph1.setEdge("D", "E", weight);
        graph1.setEdge("D", "F", weight);
        graph1.setEdge("D", "G", weight);
        graph1.setEdge("E", "H", weight);
        graph1.setEdge("G", "F", weight);
        graph1.setEdge("H", "G", weight);
        graph1.setEdge("H", "I", weight);
        graph1.setEdge("H", "J", weight);
        System.out.println("Graph 1");
        graph1.prims("A");

        System.out.println();

        //second example: g2
        AdjGraph graph2 = new AdjGraph(false);
        String[] nodes2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        graph2.setVertices(nodes2);
        //set the edges
        graph2.setEdge("a", "b", 3);
        graph2.setEdge("a", "d", 4);
        graph2.setEdge("a", "f", 10);
        graph2.setEdge("b", "d", 2);
        graph2.setEdge("c", "i", 6);
        graph2.setEdge("c", "j", 8);
        graph2.setEdge("d", "g", 3);
        graph2.setEdge("g", "e", 5);
        graph2.setEdge("g", "f", 1);
        graph2.setEdge("g", "h", 3);
        graph2.setEdge("h", "c", 7);
        graph2.setEdge("h", "e", 1);
        graph2.setEdge("i", "j", 4);

        System.out.println("Graph2");
        graph2.prims("a");

        System.out.println();

        //This is the Shaffer example in figure 11.14, with some added nodes to fufill the requirements
        AdjGraph graph3 = new AdjGraph(false);
        String[] nodes3 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        graph3.setVertices(nodes3);
        //set the edges
        graph3.setEdge("a", "b", 3);
        graph3.setEdge("a", "c", 5);
        graph3.setEdge("b", "e", 6);
        graph3.setEdge("b", "f", 1);
        graph3.setEdge("c", "d", 2);
        graph3.setEdge("d", "e", 3);
        graph3.setEdge("e", "g", 4);
        graph3.setEdge("g", "h", 3);
        graph3.setEdge("g", "i", 2);
        graph3.setEdge("i", "j", 2);
        //Display the graph
        System.out.println("Graph 3");
        graph3.prims("a");
    }
} // end of class AdjGraph