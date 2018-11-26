package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import graph.Graph;
import graph.Graph_matrix;

public class TestGraphMatrix {

private Graph_matrix<Integer> graph;
	
	private void setup() {
		graph = new Graph_matrix<Integer>(6);
	}
	

	@Test
	public void testAddVertex() {
		setup();
		graph.addVertex(25);
		graph.addVertex(30);
		graph.addVertex(14);
		assertTrue(graph.get(0) == 25);
		assertTrue(graph.get(1) == 30);
		assertTrue(graph.get(2) == 14);
	}
	
	@Test
	public void testAddEdge() {
		setup();
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addEdge(0, 1, 12);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 6);
		graph.addEdge(5, 4, 7);
		ArrayList<Integer> a = graph.getAdjacents(3);
		ArrayList<Integer> e = new ArrayList<>();
		e.add(2);
		e.add(5);
		assertEquals(e, a);		
	}
	
	@Test
	public void testRemoveNode() {
		setup();
		graph.addVertex(25);
		graph.addVertex(30);
		graph.addVertex(14);
		graph.removeNode(25);
		assertTrue(graph.get(0) == 30);
		
	}

	@Test 
	public void testRemoveEdge() {
		setup();
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addEdge(0, 1, 12);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 6);
		graph.addEdge(5, 4, 7);
		graph.removeEdge(0, 1);
		ArrayList<Integer> epa = graph.getAdjacents(0);
		assertTrue(epa.isEmpty());
		
	}
	
	@Test
	public void testDijkstra() {
		setup();
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);
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
