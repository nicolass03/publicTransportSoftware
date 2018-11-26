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
	public void testAddVertex2() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		graph.addVertex(15,3);
		graph.addVertex(11,4);
		graph.addVertex(13,5);
		graph.addVertex(80,6);
		assertTrue(graph.get(0) == 25);
		assertTrue(graph.get(1) == 30);
		assertTrue(graph.get(2) == 14);
		assertTrue(graph.get(3) == 15);
		assertTrue(graph.get(4) == 11);
		assertTrue(graph.get(5) == 13);
		assertTrue(graph.get(6) == 80);
	}
	
	@Test
	public void testAddVertex3() {
		setup();
		for (int i = 0; i < 20; i++) {
			graph.addVertex((int)(Math.random()*30)+1,i);
		}
		assertTrue(graph.size() == 20);
	}
	
	
	@Test
	public void testAddEdge() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addEdge(25, 30, 12);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		
	}
	
	@Test
	public void testAddEdge2() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(40, 2);
		graph.addEdge(25, 40, 70);
		graph.addEdge(25, 30, 12);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.get(30) == 12);
		assertTrue(epa.get(40) == 70);
	
	}
	
	@Test
	public void testAddEdge3() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(40, 2);
		graph.addVertex(48, 3);
		graph.addEdge(25, 40, 70);
		graph.addEdge(25, 30, 12);
		graph.addEdge(25, 48, 80);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.get(30) == 12);
		assertTrue(epa.get(40) == 70);
		assertTrue(epa.get(48) == 80);
	
	}
	
	@Test
	public void testRemoveNode() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		graph.removeNode(25);
		assertTrue(graph.get(0) == 30);
	}
	
	@Test
	public void testRemoveNode2() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		graph.removeNode(25);
		graph.removeNode(30);
		assertTrue(graph.get(0) == 14);
	}
	
	@Test
	public void testRemoveNode3() {
		setup();
		int num = (int) (Math.random()* 40) +1;
		int num1 = (int) (Math.random()* 40) +1;
		int num2 = (int) (Math.random()* 40) +1;
		int num3 = (int) (Math.random()* 40) +1;
		int num4 = (int) (Math.random()* 40) +1;
		graph.addVertex(num,0);
		graph.addVertex(num1,1);
		graph.addVertex(num2,2);
		graph.addVertex(num3,3);
		graph.addVertex(num4,4);
		graph.removeNode(num);
		assertTrue(graph.get(0) == num1);	
	}
	
	@Test 
	public void testRemoveEdge() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		graph.addEdge(25, 30, 25);
		graph.addEdge(30, 14, 47);
		graph.addEdge(14, 25, 25);
		graph.removeEdge(25, 30);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.isEmpty());
	}
	
	@Test 
	public void testRemoveEdge1() {
		setup();
		graph.addVertex(25,0);
		graph.addVertex(30,1);
		graph.addVertex(14,2);
		graph.addVertex(15,3);
		graph.addEdge(25, 30, 25);
		graph.addEdge(30, 14, 47);
		graph.addEdge(14, 25, 25);
		graph.addEdge(25, 12, 70);
		graph.addEdge(25, 15, 98);
		graph.removeEdge(25, 30);
		graph.removeEdge(25, 12);
		graph.removeEdge(25, 15);
		HashMap<Integer, Integer> epa = graph.getAdjacents(25);
		assertTrue(epa.isEmpty());	
	}
	
	@Test 
	public void testRemoveEdge2() {
		setup();
		int num = (int) (Math.random()* 40) +1;
		int num1 = (int) (Math.random()* 40) +1;
		int num2 = (int) (Math.random()* 40) +1;
		int num3 = (int) (Math.random()* 40) +1;
		int num4 = (int) (Math.random()* 40) +1;
		graph.addVertex(num,0);
		graph.addVertex(num1,1);
		graph.addVertex(num2,2);
		graph.addVertex(num3,3);
		graph.addVertex(num4,4);
		graph.addEdge(num2, num1, 25);
		graph.addEdge(num2, num3, 47);
		graph.addEdge(num2, num4, 25);
		graph.removeEdge(num2, num1);
		graph.removeEdge(num2, num3);
		graph.removeEdge(num2, num4);
		HashMap<Integer, Integer> epa = graph.getAdjacents(num2);
		assertTrue(epa.isEmpty());
	}
	
	@Test
	public void testDijkstra() {
		setup();
		graph.addVertex(1,0);
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
