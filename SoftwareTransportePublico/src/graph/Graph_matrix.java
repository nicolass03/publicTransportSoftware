package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Non directed graph using adjacency matrix
 * @author Nicolas Sanabria.
 *
 * @param <T> Type of data.
 */
public class Graph_matrix<T> {

	public int[][] adjMatrix;
	public int numberOfVertex;
	public List<T> vertex;

	/**
	 * 
	 * @param numberOfVertex - amount of vertex in the graph.
	 */
	public Graph_matrix(int numberOfVertex) {
		vertex = new ArrayList<T>();
		adjMatrix = new int[numberOfVertex][numberOfVertex];
		this.numberOfVertex = numberOfVertex;
	}

	/**
	 * Adds vertex to the graph.
	 * @param n - The vertex to add.
	 */
	public void addVertex(T n) {
		if(vertex.size() < numberOfVertex) {
			vertex.add(n);
		}
	}

	/**
	 * Adds edge to between two vertex.
	 * @param n1 - source vertex of the edge.
	 * @param n2 - target vertex of the edge
	 * @param w - weight of the edge.
	 */
	public void addEdge(T n1, T n2, Integer w) {
		if(vertex.contains(n1) && vertex.contains(n2)) {
			int index = indexOf(n1);
			int index2 = indexOf(n2);
			adjMatrix[index][index2] = w;
//			adjMatrix[index2][index] = w;
		}
	}

	//		if(nodes.contains(n2)) {
	//			int index = getObjectIndex(n2);
	//			if(index > -1)
	//				adjacent.get(index).put(n2, w);
	//		}

	/**
	 * Returns object/vertex on the specified index.
	 * @param index - the index of the vertex/object.
	 * @return - the object/vertex found. Null if doesn't exist.
	 */
	public T get(int index) {
		return vertex.get(index);
	}

	/**
	 * Returns the number of vertex on the graph.
	 * @return - Integer that specifies the size of the graph.
	 */
	public int size() {
		return vertex.size();
	}

	/**
	 * Returns the index of specified object.
	 * @param key - the object wich index is asked for.
	 * @return - Integer that specifies the index of the object.
	 */
	private Integer indexOf(T key) {
		return vertex.indexOf(key);
	}

	/**
	 * Removes the specified node.
	 * @param d - the node to remove.
	 */
	public void removeNode(T d) {
		int index = indexOf(d);
		for(int i = 0; i < adjMatrix.length;i++) {
			adjMatrix[index][i] = 0;
//			adjMatrix[i][index] = 0;
		}
		vertex.remove(d);
	}

	/**
	 * Removes an edge between two nodes.
	 * @param n1 - the source node of the edge.
	 * @param n2 - the target node of the edge.
	 */
	public void removeEdge(T n1, T n2) {
		if(vertex.contains(n1) && vertex.contains(n2)) {
			int index = indexOf(n1);
			int index2 = indexOf(n2);
			adjMatrix[index][index2] = 0;
			adjMatrix[index2][index] = 0;
		}

		//		if(nodes.contains(n2)) {
		//			int index = getObjectIndex(n2);
		//			if(index > -1 && adjacent.get(index).containsKey(n1))
		//				adjacent.get(index).remove(n1);
		//		}
	}

	/**
	 * Returns the adjacents HashMap with target vertex as K and weight as V.
	 * @param source - Vertex wich adjacents we have to find.
	 * @return - the HashMap of adjacent vertex of the node.
	 */
	public ArrayList<T> getAdjacents(T source){
		ArrayList<T> adj = new ArrayList<T>();
		if(vertex.contains(source)) {
			int index = indexOf(source);
			for(int i = 0; i < adjMatrix[index].length; i++) {
				if(adjMatrix[index][i] > 0) {
					T a = vertex.get(i);
					adj.add(a);
				}
			}
		}
		return adj;
	}

	/**
	 * Finds shortest path from source vertex to all nodes using Dijkstra algorithm.
	 * pre: weight of all edges > 0;
	 * @param source - Source vertex.
	 * @return - int array with weight to every node.
	 */
	public int[] dijkstra(T source) {

		//		INT NVERTEX = NODES.SIZE();

		int[] dist = new int[vertex.size()];
		boolean[] vis = new boolean[vertex.size()];
		int[] prev = new int[vertex.size()];

		dist[indexOf(source)] = 0;
		for(int i = 0; i < dist.length; i++) {
			if(i != indexOf(source)) {
				dist[i] = Integer.MAX_VALUE;				
			}
		}

		PriorityQueue<T> q = new PriorityQueue<T>();
		q.add(source);


		while(!q.isEmpty()) {
			T actual = q.poll();
			int index = indexOf(actual);
			if(vis[index]) continue;

			vis[index] = true;

			for(int i = 0; i < adjMatrix[index].length; i++) {
				if(adjMatrix[index][i] != 0) {
					int weight = adjMatrix[index][i];
					if(!vis[i]) {
						if(dist[index] + weight < dist[i]) {
							dist[i] = dist[index] + weight;
							prev[i] = index;
							q.add(vertex.get(i));
						}
					}
				}
			}
		}
		return prev;
	}

	/**
	 * Finds shortest path from source vertex to all nodes using Bellman Ford algorithm.
	 * @param source - Source vertex.
	 * @return - int array with weight to every node.
	 */
	public int[] bellman_ford(T source) {

		int[] dist = new int[vertex.size()];
		int index = indexOf(source);
		dist[index] = 0;

		for(int i = 0; i < dist.length; i++) {
			if(i != index) {
				dist[i] = Integer.MAX_VALUE;				
			}
		}


		for(int j = 0; j < adjMatrix[index].length-1; j++) {
			for(int i = 0; i < adjMatrix[index].length; i++) {
				if(adjMatrix[index][i] != 0) {
					int weight = adjMatrix[index][i];
					if(dist[index] + weight < dist[i]) {
						dist[i] = dist[index] + weight;
					}
				}
			}
			
		}
		return dist;
	}



	/**
	 * Breadth First Search algorithm from source vertex.
	 * @param source - Source vertex.
	 */
	public void bfs(T source) {
		PriorityQueue<T> q = new PriorityQueue<T>();
		boolean[] visited = new boolean[vertex.size()];
		visited[indexOf(source)] = true;

		q.add(source);

		while(!q.isEmpty()) {
			T current = q.poll();
			int index = indexOf(current);
			for(int i = 0; i < adjMatrix[index].length; i++) {
				if(adjMatrix[index][i] != 0) {
					if(!visited[i]) {
						q.add(vertex.get(i));
						System.out.println(i);
						visited[i] = true;
					}					
				}
			}
		}
	}

	/**
	 * Depth First Search algorithm from source vertex.
	 * @param source - Source vertex.
	 */
	public void dfs(T source) {
		Stack<T> s = new Stack<T>();
		boolean[] visited = new boolean[vertex.size()];
		s.push(source);
		visited[indexOf(source)] = true;

		while(!s.isEmpty()) {
			T current = s.pop();
			int index = indexOf(current);
			for(int i = 0; i < adjMatrix[index].length; i++) {
				if(adjMatrix[index][i] != 0) {
					if(!visited[i]) {
						s.push(vertex.get(i));
						System.out.println(i);
						visited[i] = true;
					}					
				}
			}
		}
	}

	public Graph<T> kurskal(T source){
		return null;
	}
}
