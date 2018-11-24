package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import graph.Graph;
import graph.Graph_matrix;

public class MainClass {

	public static final int GRAPH_LIST = 0;
	public static final int GRAPH_MATRIX = 1;

	private Graph<Station> stations;
	private Graph_matrix<Station> stations_matrix;

	public MainClass() {
		stations = new Graph<Station>();
		init();
	}

	public void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./init.txt")));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Station[] getShortestPathTo(Station o, Station t) {
		int[] disorderedPath = stations.dijkstra(o);
		int tIndex = stations.indexOf(t);
		Station[] path = new Station[disorderedPath.length];
		path[0] = t;
		path[path.length-1] = o;
		for(int i = 1; i < path.length-1; i++) {
			tIndex = disorderedPath[tIndex];
			Station current = stations.get(tIndex);
			path[i] = current;
		}
		return path;
	}
	
	public List getStations() {
		return stations.getVertex();
	}
}
