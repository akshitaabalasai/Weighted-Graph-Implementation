package program6;

import java.util.LinkedList;

public class WeightedGraph {
	
	static class Edge {
		int source;
		int destination;
		int weight;
		
public Edge(int source, int destination, int weight) {
	this.source = source;
	this.destination = destination;
	this.weight = weight;
}

}
	
static class Graph {
	int vertices;
	LinkedList<Edge> [] adjacencylist;

	Graph(int vertices) {
		this.vertices = vertices;
		adjacencylist = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i <vertices ; i++) {
			adjacencylist[i] = new LinkedList<>();
		}
	}
	
public void addEdge(int source, int destination, int weight) {
	Edge edge = new Edge(source, destination, weight);
	adjacencylist[source].addFirst(edge); //for directed graph
}

public void printGraph(){
	for (int i = 0; i <vertices ; i++) {
		LinkedList<Edge> list = adjacencylist[i];
			for (int j = 0; j <list.size() ; j++) {
				System.out.println("vertex-" + i + " is connected to " + list.get(j).destination + " with weight " + list.get(j).weight);
			}
	}
	}


public void printAdjacencyMatrix()
{
	
	int [][] adjMatrix = new int [vertices][vertices];
	
	//need to fill in adjMatrix first
	
	for (int a = 0; a < vertices; a++)
	{
		
		LinkedList <Edge> newList = adjacencylist[a];
		
		
		for (int b = 0; b < newList.size(); b++)
			
		{
			Edge edge = newList.get(b);
			adjMatrix[a][edge.destination] = edge.weight; //puts weight in that takes from start to destination
		}
		
	}
	
	
	//need to print array
	
	System.out.println("The Adjacency Matrix for the weighted graph is:");
	System.out.println();

	
	System.out.print(" ");
	
	for(int z = 0; z < vertices; z++)
	{
		System.out.print(" " + z);
	}
	
	System.out.println();
    for (int i = 0; i < vertices; i++) 
    {
    	System.out.print(i + " ");
        for (int j = 0; j < vertices; j++) 
        {
        	
            System.out.print(adjMatrix[i][j] + " ");
        }
        
        System.out.println();
    }
    
    
     
    
}


public void printAdjacencyList()
{
	
	System.out.println("The Adjacency List for the weighted graph is: ");
	System.out.println();
	
	for (int i = 0; i < vertices; i++) 
	{
		
		System.out.print("Vertex " + i);
		
		LinkedList<Edge> list = adjacencylist[i];
		
		if (list.isEmpty())
		{
			System.out.print(" -> null");
		}
		
			for (int j = 0; j <list.size(); j++) {
				
				Edge edge = list.get(j);
				System.out.print(" -> " + edge.destination + " (weight: " + edge.weight + ") ");
				
				
			}
			System.out.println();
	}
}

public void printShortestPaths()
{ //floyd warshall method
	System.out.println("The Shortest Path from each node to every other node (except itself) follow:");
	int a[][]= new int[vertices][vertices];
	
	for (int i = 0; i < vertices; i++)
	{
		for (int j = 0; j < vertices; j++)
		{
			a[i][j] = 2147483647; //set to default max value - Integer.MAX_VALUE was not working so I initialized it manually to max value Java can take
		}
	}
	
	for (int i = 0; i <vertices; i++)
	{
		for(Edge edge: adjacencylist[i])
		{
			a[i][edge.destination] = edge.weight;
		}
	}
	
	for (int k = 0; k < vertices; k++)
	{
		for(int i = 0; i < vertices; i++)
		{
			for (int j = 0; j < vertices; j++)
			{
				if (a[i][k]!= 2147483647 && a[k][j]!=2147483647 && a[i][j] > (a[i][k] + a[k][j]))
				{
					a[i][j] = (a[i][k] + a[k][j]); //smallest path is the statement on the right
				}
					
			}
		}
	}
	
	for (int i = 0; i < vertices; i++)
	{
		for (int j = 0; j < vertices; j++)
			
			if(i!=j) //to ensure it doesn't calculate path to itself
		{
			if (a[i][j] == 2147483647)
			{
				System.out.println("Shortest path from " + i + " to " + j + ": No Path");
			}
			
			else
			{
				System.out.println("Shortest path from " + i + " to " + j + ": " + a[i][j]);
			}
		}
	}
}


}



public static void main(String[] args) {
int vertices = 6;
Graph graph = new Graph(vertices);
graph.addEdge(0, 1, 4); //(source, dest, weight)
graph.addEdge(0, 2, 3);
graph.addEdge(1, 3, 2);
graph.addEdge(1, 2, 5);
graph.addEdge(2, 3, 7);
graph.addEdge(3, 4, 2);
graph.addEdge(4, 0, 4);
graph.addEdge(4, 1, 4);
graph.addEdge(4, 5, 6);
graph.printGraph(); // TODO can keep or remove this - either way
System.out.println();
graph.printAdjacencyMatrix(); // TODO add method to print adjacency matrix for debugging
System.out.println();
graph.printAdjacencyList(); // TODO add method to print adjacency list for debugging
System.out.println();

graph.printShortestPaths(); // TODO add method to print shortest paths for debugging
}
}