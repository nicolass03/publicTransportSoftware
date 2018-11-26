//package graph;
//
//public class Test {
//
//	public static void main(String[] args) {
//		Graph<Integer> g = new Graph<Integer>();
//		Integer n1 = 0;
//		Integer n2 = 8;
//		Integer n3 = 4;
//		Integer n4 = 800;
//		Integer n5 = 30;
//		Integer n6 = 1;
//		Integer n7 = 9;
//		Integer n8 = 2;
//		Integer n9 = 3;
//		
//		g.addVertex(n9);
//		g.addVertex(n8,1);
//		g.addVertex(n7,2);
//		g.addVertex(n6,3);
//		g.addVertex(n5,3);
//		g.addVertex(n4,5);
//		g.addVertex(n3,6);
//		g.addVertex(n2,7);
//		g.addVertex(n1,8);
//		
//		g.addEdge(n1, n7, 8);
//		g.addEdge(n1, n5, 7);
//		g.addEdge(n1, n4, 1);
//		g.addEdge(n1, n8, 9);
//		g.addEdge(n8, n3, 4);
//		g.addEdge(n5, n6, 6);
//		g.addEdge(n5, n2, 2);
//		g.addEdge(n8, n9, 5);
//		
////		g.addEdge(n7, n1, 8);
////		g.addEdge(n5, n1, 7);
////		g.addEdge(n4, n1, 1);
////		g.addEdge(n8, n1, 9);
////		g.addEdge(n3, n8, 4);
////		g.addEdge(n6, n5, 6);
////		g.addEdge(n2, n5, 2);
////		g.addEdge(n9, n8, 5);
//		
//		int[] bf = g.bellman_ford(n1);
//		for(int i = 0; i < bf.length; i++) {
//			System.out.println(bf[i]+",	");
//		}
//		
//		System.out.println(g.prim(n1));
//	}
//
//}
