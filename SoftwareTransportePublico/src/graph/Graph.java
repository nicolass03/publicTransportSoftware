package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.PropertyPermission;
import java.util.Stack;

/**
 *Directed graph using list of lists.
 * @author Nicolas Sanabria
 *
 * @param <T> Type of data.
 */

public class Graph<T> {

	/**
	 * List of adjacent nodes to each one with their respective weight
	 */
	private List<HashMap<T,Integer>> adjacent;
	private List<T> vertex;
	private Map<T, Integer> indexes;


	/**
	 * Constructor for the graph.
	 */
	public Graph() {
		vertex = new ArrayList<T>();
		indexes = new HashMap<T, Integer>();	
		adjacent = new ArrayList<HashMap<T,Integer>>();
	}

	/**
	 * Adds vertex to the graph.
	 * @param n - The vertex to add.
	 */
	public void addVertex(T n, Integer index) {
		vertex.add(n);
		indexes.put(n, index);
		adjacent.add(new HashMap<T,Integer>());
	}

	/**
	 * Adds edge to between two vertex.
	 * @param n1 - source vertex of the edge.
	 * @param n2 - target vertex of the edge
	 * @param w - weight of the edge.
	 */
	public void addEdge(T n1, T n2, Integer w) {
		if(vertex.contains(n1) && vertex.contains(n2)) {
			T v2 = vertex.get(indexOf(n2));
			adjacent.get(indexOf(n1)).put(v2, w);
		}

		//		if(nodes.contains(n2)) {
		//			int index = getObjectIndex(n2);
		//			if(index > -1)
		//				adjacent.get(index).put(n2, w);
		//		}
	}

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
	public Integer indexOf(T key) {
		return indexes.get(key);
	}

	/**
	 * Removes the specified node.
	 * @param d - the node to remove.
	 */
	public void removeNode(T d) {
		vertex.remove(d);
		indexes.remove(d);
	}

	/**
	 * @return the vertex
	 */
	public List<T> getVertex() {
		return vertex;
	}

	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(List<T> vertex) {
		this.vertex = vertex;
	}

	/**
	 * Removes an edge between two nodes.
	 * @param n1 - the source node of the edge.
	 * @param n2 - the target node of the edge.
	 */
	public void removeEdge(T n1, T n2) {
		if(vertex.contains(n1)) {
			int index = indexOf(n1);
			if(adjacent.get(index).containsKey(n2)) {
				T v2 = vertex.get(indexOf(n2));
				adjacent.get(index).remove(v2);
			}
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
	public HashMap<T,Integer> getAdjacents(T source){
		return adjacent.get(indexOf(source));
	}

	/**
	 * Finds shortest path from source vertex to all nodes using Dijkstra algorithm.
	 * pre: weight of all edges > 0;
	 * @param source - Source vertex.
	 * @return - int array with weight to every node.
	 */
	public int[] dijkstra(T source) {

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
			if(!vis[index]) {
				vis[index] = true;
				HashMap<T,Integer> adj = getAdjacents(actual);

				for(T x : adj.keySet()) {
					int weight = adj.get(x);
					int srcIndex = indexOf(x);
					if(!vis[indexOf(x)]) {
						if(dist[index] + weight < dist[srcIndex]) {
							dist[srcIndex] = dist[index] + weight;
							prev[srcIndex] = index;
							q.add(x);
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

		for(int j = 0; j < vertex.size()-1; j++) {
			for(int i = 0; i < vertex.size(); i++) {
				T current = vertex.get(i);
				HashMap<T,Integer> adj = getAdjacents(current);
				for(T x : adj.keySet()) {
					int weight = adj.get(x);
					int srcIndex = indexOf(x);
					if(dist[i] != Integer.MAX_VALUE && dist[i]+weight<dist[srcIndex]) {
						dist[srcIndex] = dist[i]+weight;
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

		q.add(source);
		int srcIndex = indexOf(source);
		visited[srcIndex] = true;

		while(!q.isEmpty()) {
			T current = q.poll();
			HashMap<T,Integer> adj = getAdjacents(current);
			for(T x : adj.keySet()) {
				if(!visited[indexOf(x)]) {
					q.add(x);
					System.out.println(x);
					visited[indexOf(x)] = true;
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
			HashMap<T,Integer> adj = getAdjacents(current);
			for(T x : adj.keySet()) {
				if(!visited[indexOf(x)]) {
					s.push(x);
					System.out.println(x);
					visited[indexOf(x)] = true;
				}
			}
		}
	}

	public Graph<T> kruskal(T source) {
		
		return null;
	}
	private int[] init() {
		int[] parents = new int[vertex.size()];
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		return parents;
	}
	
	private int root(int x, int[] id) {
		while(id[x] != x) {
			id[x] = id[id[x]];
			x = id[x];
		}
		return x;
	}
	
//	private void union(int x, int y) {
//		int p = root(x);
//		int q = root
//	}
	
	public List<ArrayList> getEdges(){
		List<ArrayList> edges = new ArrayList<ArrayList>();
		for(int i = 0; i < vertex.size(); i++) {
			T current = vertex.get(i);
			ArrayList currEdges = new ArrayList();
			HashMap<T,Integer> adj = getAdjacents(current);
			for(T x : adj.keySet()) {
				Integer w = adj.get(x);
				edges.get(i).add(current);
				edges.get(i).add(x);
				edges.get(i).add(w);
			}
		}
		return edges;
	}
	
	public long prim(T source) {
		PriorityQueue<Integer[]> q = new PriorityQueue<>();
		T y;
		boolean[] vis = new boolean[vertex.size()];
		long minimumCost = 0;
		Integer[] s = {indexOf(source),0};
		q.add(s);
		Integer[] curr = null;
		while(!q.isEmpty()) {
			curr = q.poll();
			Integer index = curr[0];
			
			if(vis[index] == true) continue;
			
			minimumCost += (Integer) curr[1];
			vis[index] = true;
			
			HashMap<T,Integer> adj = getAdjacents(get(index));
			for(T x : adj.keySet()) {
				y = x;
				if(!vis[indexOf(y)]) {
					Integer[] aux = {indexOf(y), adj.get(y)};
					q.add(aux);
				}
			}
		}
		
		return minimumCost;
	}
}
