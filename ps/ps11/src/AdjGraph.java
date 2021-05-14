// C343 Fall 2020
//
// a simple implementation for graphs with adjacency lists

// Problem Set 11 starter file

import java.util.*;

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

    public AdjGraph() {
        init();
    }

    private int[] dist = new int[totalNodes]; //This is the adjacency list data structure for this lab.


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

    // Problem Set 11 TODO:
    // write your methods here.
    //I am using page 391 from the Shaffer book as a reference

    //relax method:
    //dist[u] starts as the length of some path from s to u.
    //dist[v] starts as the length of some path from s to v
    //if the edge from u to v gives a shorter path from s to v through u, then we update dist[v] and previous[v].
    //u and v are some nodes, and w is the weights.
    public void relax(int u, int v, int w) {
        if(dist[v] > (dist[u] + w)) {
            dist[v] = dist[u] + w;
        }
    }

    //Dijkstra's main algorithm
    public void dijkstra1(String startingNode) {
        dist = new int[totalNodes];
        int s = getNode(startingNode);

        //initialize this dist array
        for (int i = 0; i < totalNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        //print the dist array, for testing purposes
        System.out.println("Initial dist array: ");
        for (int i = 0; i < totalNodes; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();

        //call clearWalk to make sure vertices are not marked
        clearWalk();

        //dijkstra's main part:
        for(int i = 0; i < totalNodes; i++) {
            //v smallest unknown distance vertex
            int v = minVertex();
            //visit v
            setVisited(v);
            if (dist[v] == Integer.MAX_VALUE) {
                System.out.println("Vertex unreachable");
                return; //End the method
            }

            //relaxation step, for all neighbors of vertex v
            for(int neighbor: adjList.elementAt(v)) {
                relax(v, neighbor, getWeight(v, neighbor));
            }

            //prints the dist array after the relaxation step, for testing
            System.out.println("After the edge relaxation step " + i + " the dist array is: ");
            for (int j = 0; j < totalNodes; j++) {
                System.out.print(dist[j] + " " );
            }
            System.out.println();
        }
    }

    //A helper method that returns the closest unvisited vertex, used in Dijkstra1 algorithm.
    //From Shaffer Book
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

    //Task C: TopSort
    public void topologicalSortWithQueue(Graph g) {
        LinkedList<Integer> result = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        dist = new int[totalNodes];
        for(int i = 0; i < dist.length; i++) {
            LinkedList<Integer> neighbors = this.getNeighbors(i);
            for(int j: neighbors) {
                dist[j]++; //This is how many vertices point ot each neighbor
            }
        }

        for(int i = 0; i < dist.length; i++) {
            if(dist[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0; //Counter variable
        while(!queue.isEmpty()) {
            int begin = queue.remove();
            count++;
            System.out.println(this.nodeList.elementAt(begin)); //Prints the TopSort Order
            result.add(begin);
            LinkedList<Integer> neighbor = this.getNeighbors(begin);
            for(int i: neighbor) {
                dist[i]--; //Removes a node that pints to its neighbors
                //Print statement to show the process of TopSort
                //System.out.println(Arrays.toString(dist));
                if(dist[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if(count == 0) {
            System.out.println("Vertex not found");
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

    // Problem Set 11 TODO:
    // write your new main() method here:
    // for Problem Set 11 Task B & C:
    // provide 3 different examples, using the two different versions of Dijkstra's algorithm
    // with at least 10 nodes for each different graph

    public static void main(String argv[]) {
        AdjGraph graph1 = new AdjGraph(true);
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
        //display the graph
        System.out.println("Graph 1");
        graph1.display();
        //Dijkstra1 algorithm
        graph1.dijkstra1("A");
        System.out.println();
        System.out.println("TopSort");
        //Top Sort
        graph1.topologicalSortWithQueue(graph1);

        System.out.println();

        //second example: graph2
        AdjGraph graph2 = new AdjGraph(true);
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
        //Display the graph
        System.out.println("Graph 2");
        graph2.display();
        //Dijkstra1 algorithm
        graph2.dijkstra1("a");
        System.out.println();
        System.out.println("TopSort");
        //TopSort Algorithm
        graph2.topologicalSortWithQueue(graph2);

        System.out.println();

        //This is the Shaffer example in figure 11.14, with some added nodes to fufill the requirements
        AdjGraph graph3 = new AdjGraph(true);
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
        graph3.display();
        //Dijkstra1 algorithm
        graph3.dijkstra1("a");
        System.out.println();
        System.out.println("TopSort");
        //TopSort Algorithm
        graph3.topologicalSortWithQueue(graph3);

        //For testing purposes only for problem set 12, this is not a modification to ps11!!!!!
        System.out.println();
        AdjGraph graph4 = new AdjGraph(true);
        String[] nodes4 = {"a", "b", "c", "d", "e"};
        graph4.setVertices(nodes4);
        graph4.setEdge("a", "b", 5);
        graph4.setEdge("a", "d", 22);
        graph4.setEdge("b", "d", 15);
        graph4.setEdge("c", "a", 3);
        graph4.setEdge("c", "b", 20);
        graph4.setEdge("c", "e", 13);
        graph4.setEdge("e", "d", 21);
        graph4.dijkstra1("c");

        System.out.println();
        AdjGraph graph5 = new AdjGraph(false);
        String[] nodes5 = {"1", "2", "3", "4", "5", "6"};
        graph5.setVertices(nodes5);
        graph5.setEdge("1", "2", 10);
        graph5.setEdge("1", "4", 20);
        graph5.setEdge("1", "6", 2);
        graph5.setEdge("2", "3", 3);
        graph5.setEdge("2", "4", 5);
        graph5.setEdge("3", "5", 15);
        graph5.setEdge("4", "5", 11);
        graph5.setEdge("4", "6", 10);
        graph5.setEdge("5", "6", 3);
        graph5.dijkstra1("3");


    }//end main();
} // end of class AdjGraph