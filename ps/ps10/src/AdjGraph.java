// C343 Fall 2020
//
// a simple implementation for graphs with adjacency lists

import java.util.ArrayList;
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

    // Problem Set 10 TODO:
    // write your componentsAndSizes() method here.
    public void componenetsAndSizes() {
        int visitedCount = 0; //Int variable for amount of nodes visited
        int componentsCount = 0; //Int variable for amount of components
        for(int i = 0; i < nodeList.size(); i++) { //Looping through nodeList
            if(!ifVisited(i)) { //Checks if the node isn't visited
                DFS(i); //Calls DFS
                int count = 0; //checks how many nodes are visited after DFS is run.
                for(int j = 0; j < nodeList.size(); j++) { //Looping through nodeList
                    if(ifVisited(j)) { //Checks if the node has been visited
                        count++; //variable that updates increments the visited notes total
                    }
                }
                int result = count - visitedCount; //subtract visited count from count
                visitedCount = count; //Setting the visitedCount to the count.
                //The +1 is for to componentsCount starts it at 1 instead of 0, for clarity. Print statement:
                System.out.println("component " + (componentsCount+1) + " contains " + result  + " nodes");
                componentsCount++; //increment componentsCount
            }
        }
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

    // Problem Set 10 TODO:

    // --- write a main() method here ---

    // Provide 3 different graph examples,
    //   with at least 10 nodes for each different graph,
    //   following these steps 1) ... 4) for each graph:

    // 1) instantiate a new graph,
    // 2) assign 2 vertices and edges to the graph,
    // 3) then display 2 the graph's content (use display())
    // 4) finally call your componentsAndSizes() method to provide
    //    output results as from Problem Set 10 instructions

    public static void main(String[] args) {
        //Graph 1, you need 3
        AdjGraph graph = new AdjGraph(true);
        System.out.println("Graph 1");
        //Creating 10 verticies
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        //Creating at least 4 edges between verticies
        graph.setEdge("A", "B", 1);
        graph.setEdge("B", "C", 1);
        graph.setEdge("C", "J", 1);
        graph.setEdge("J", "A", 1);
        //Calling display and componentAndSizes() methods
        graph.display();
        graph.componenetsAndSizes();

        System.out.println();

        AdjGraph graph2 = new AdjGraph(true);
        System.out.println("Graph 2");
        //Creating 10 verticies
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addVertex("F");
        graph2.addVertex("G");
        graph2.addVertex("H");
        graph2.addVertex("I");
        graph2.addVertex("J");
        //Creating at least 4 edges between verticies
        graph2.setEdge("A", "B", 1);
        graph2.setEdge("B", "C", 1);
        graph2.setEdge("D", "E", 1);
        graph2.setEdge("E", "F", 1);
        graph2.setEdge("F", "G", 1);
        graph2.setEdge("H", "I", 1);
        graph2.setEdge("I", "J", 1);
        graph2.setEdge("J", "A", 1);
        //Calling display and componentAndSizes() methods
        graph2.display();
        graph2.componenetsAndSizes();

        System.out.println();

        AdjGraph graph3 = new AdjGraph(true);
        System.out.println("Graph 3");
        //Creating 10 verticies
        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addVertex("C");
        graph3.addVertex("D");
        graph3.addVertex("E");
        graph3.addVertex("F");
        graph3.addVertex("G");
        graph3.addVertex("H");
        graph3.addVertex("I");
        graph3.addVertex("J");
        //Creating at least 4 edges between verticies
        graph3.setEdge("A", "B", 1);
        graph3.setEdge("A", "C", 1);
        graph3.setEdge("A", "D", 1);
        graph3.setEdge("A", "E", 1);
        graph3.setEdge("A", "F", 1);
        graph3.setEdge("A", "G", 1);
        graph3.setEdge("A", "H", 1);
        graph3.setEdge("A", "I", 1);
        graph3.setEdge("A", "J", 1);
        //Calling display and componentAndSizes() methods
        graph3.display();
        graph3.componenetsAndSizes();
    }

} // end of class AdjGraph