package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import graph.Graph;

public class TestGraph {
	
	private Graph<Integer> graph;
	
	private void setup() {
		graph = new Graph<Integer>();
	}
	

	@Test
	public void testAddVertex() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		assertTrue(graph.get(0) == 25);
		assertTrue(graph.get(1) == 30);
		assertTrue(graph.get(2) == 14);
	}
	
	@Test
	public void testAddEdge() {
		setup();
		graph.addVertex(25, 0);
		graph.addVertex(30, 1);
		graph.addEdge(25, 30, 12);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.get(30) == 12);
		setup();	
	}
	
	@Test
	public void testRemoveNode() {
		setup();
		graph.addVertex(25, 0);
		graph.addVertex(30, 1);
		graph.addVertex(14, 2);
		graph.removeNode(25);
		assertTrue(graph.get(0) == 30);
		
	}
	
	@Test 
	public void testRemoveEdge() {
		setup();
		graph.addVertex(25, 0);
		graph.addVertex(30, 1);
		graph.addVertex(14, 2);
		graph.addEdge(25, 30, 25);
		graph.addEdge(30, 14, 47);
		graph.addEdge(14, 25, 25);
		graph.removeEdge(25, 30);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.isEmpty());
		
	}
	
	@Test
	public void testDijkstra() {
		setup();
		graph.addVertex(1, 0);
		graph.addVertex(2,1);
		graph.addVertex(3,2);
		graph.addVertex(4,3);
		graph.addVertex(5,4);
		graph.addVertex(6,5);
		graph.addEdge(1, 2, 7);
		graph.addEdge(2, 1, 7);
		graph.addEdge(1, 3, 9);
		graph.addEdge(3, 1, 9);
		graph.addEdge(1, 6, 14);
		graph.addEdge(6, 1, 14);
		graph.addEdge(2, 3, 10);
		graph.addEdge(3, 2, 10);
		graph.addEdge(6, 3, 2);
		graph.addEdge(3, 6, 2);
		graph.addEdge(3, 4, 11);
		graph.addEdge(4, 3, 11);
		graph.addEdge(2, 4, 15);
		graph.addEdge(4, 2, 15);
		graph.addEdge(4, 5, 6);
		graph.addEdge(5, 4, 6);
		graph.addEdge(5, 6, 9);
		graph.addEdge(6, 5, 9);
		int[] camino = graph.dijkstra(1);
		int[] esperado = {0, 7, 9, 20, 26, 11};
		assertArrayEquals(esperado, camino);
		
		
	}
	
	

}
