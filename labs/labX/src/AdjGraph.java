// C343 Fall 2020
// A simple implementation for graphs with adjacency lists
// Lab X starter file

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int[] dist = new int[totalNodes];

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

    public int getWeight2(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return 0;
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


    //A starting node?
    public void longestPath(String startingNode) {
        //initialize this dist array
        dist = new int[totalNodes];
        int s = getNode(startingNode);
        for (int i = 0; i < totalNodes; i++) {
            dist[i] = -1; //setting it to -1 since this is smaller than 0
        }
        dist[s] = 0;

        clearWalk(); //Call clearwalk to make sure none of the nodes have been visited yet.
        int longestPath = 0; //Keep track of a variable for the longest path
        //Main loop, through all nodes. find longest path.
        for(int i = 0; i < totalNodes; i++) {
            //Compare two nodes, and find longest path.
            int v = maxVertex();
            setVisited(v);
            //If not connected (0), say unreachable
            if (dist[v] == 0) {
                System.out.println("Vertex unreachable");
            }
            //reverse edge relaxation method: calling getWeight2 instead of getWeight
            for(int neighbor: adjList.elementAt(v)) {
                relax(v, neighbor, getWeight2(v, neighbor));
            }

            //Dist array after edge relaxation
            System.out.println("After the edge relaxation step " + i + " the dist array is: ");
            for (int j = 0; j < totalNodes; j++) {
                System.out.print(dist[j] + " " );
            }

            //Finding the max element in the dist array, and setting that as the longestPath
            for(int k = 0; k < dist.length; k++) {
                if(dist[k] > longestPath) {
                    longestPath = dist[k];
                }
            }
            System.out.println();
        }
        //Printing the longestPath
        System.out.println("Longest Path: " + longestPath);
    }

    //A backwards edge relaxation with inequality flipped
    public void relax(int u, int v, int w) {
        if((dist[u] + w) > dist[v]) {
            dist[v] = dist[u] + w;
        }
    }

    //A backwards method of minVertex from previous labs
    public int maxVertex() {
        int vertex = 0;
        //initialize v to one unvisited vertex:
        for (int i = 0; i < totalNodes; i++) {
            if (!ifVisited(i)) {
                vertex = i;
                break;
            }
        }
        //look for the farthest unvisited vertex: backwards from max vertex
        for (int i = 0; i < totalNodes; i++) {
            if ((!ifVisited(i)) && (dist[i] > dist[vertex])) {
                vertex = i;
            }
        }
        return vertex;
    }

    public static void main(String argv[]) {
        //File reading stuff:
        File locations = new File("/Users/aaronlack/Desktop/Classes/FA20/C343/C343alack/labs/labX/src" +
                "/locations.txt");

        //Try and catch for scanners files or fileNotFound Error
        Scanner sc = null;
        try {
            sc = new Scanner(locations);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        String result = "";
        //How to convert the string values to integer values
        while(sc.hasNextLine()) {
            result += sc.nextLine() + " ";
        }
        System.out.println(result);
        //Current file output: 7 6 1 6 13 E 6 3 9 E 3 5 7 S 4 1 3 N 2 4 20 W 4 7 2 S

        AdjGraph graph = new AdjGraph(true);

//        String vertices = sc.next();//number of verticies
//        for(int i = 0; i < 7; i++) {
//            graph.addVertex(vertices);
//        }

        String[] nodes1 = {"1", "2", "3", "4", "5", "6","7"};
        graph.setVertices(nodes1);
        graph.setEdge("1", "6", 13);
        graph.setEdge("6", "3", 9);
        graph.setEdge("3", "5", 7);
        graph.setEdge("4", "1", 3);
        graph.setEdge("2", "4", 20);
        graph.setEdge("4", "7", 2);
        graph.longestPath("2");
    }

} // end of class AdjGraph