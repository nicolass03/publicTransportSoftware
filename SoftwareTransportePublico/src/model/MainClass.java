package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import graph.Graph;
import graph.Graph_matrix;

public class MainClass {

	public static final int GRAPH_LIST = 0;
	public static final int GRAPH_MATRIX = 1;

	private Graph<Station> stations;
	private Graph_matrix<Station> stations_matrix;
	
	private int graphType;

	public MainClass() {
		stations = new Graph<Station>();
		graphType = GRAPH_LIST;
		init();
	}

	public void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./init.txt")));
			int vertex = Integer.parseInt(br.readLine());
			stations_matrix = new Graph_matrix<Station>(vertex);
			while(vertex-->0){
				String[] datos = br.readLine().split(" ");
				Integer index = Integer.parseInt(datos[0]);
				stations.addVertex(new Station(datos[1], datos[2], datos[3].split(",")), index);
				stations_matrix.addVertex(new Station(datos[1], datos[2], datos[3].split(",")));
				
			}
			while(br.readLine() != null){
				String[] datos = br.readLine().split(" ");
				Station s1 = stations.get(Integer.parseInt(datos[0]));
				Station s2 = stations.get(Integer.parseInt(datos[1]));
				int weight = Integer.parseInt(datos[2]);
				stations.addEdge(s1,s2,weight);
				stations_matrix.addEdge(s1,s2,weight);
			}	
			br.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Station[] getShortestPathTo(Station o, Station t) {
		Station[] path = null;
		switch (graphType){
		case 0:
			int[] disorderedPath = stations.dijkstra(o);
			int tIndex = stations.indexOf(t);
			path = new Station[disorderedPath.length];
			path[0] = t;
			path[path.length-1] = o;
			for(int i = 1; i < path.length-1; i++) {
				tIndex = disorderedPath[tIndex];
				Station current = stations.get(tIndex);
				path[i] = current;
			}				
			break;
			
		case 1:
			int[] disorderedPath_ = stations_matrix.dijkstra(o);
			int tIndex_ = stations.indexOf(t);
			path = new Station[disorderedPath_.length];
			path[0] = t;
			path[path.length-1] = o;
			for(int i = 1; i < path.length-1; i++) {
				tIndex_ = disorderedPath_[tIndex_];
				Station current = stations.get(tIndex_);
				path[i] = current;
			}				
			break;
		}
		return path;
	}
	
	public List getStations_List() {
		return stations.getVertex();
	}
	
	public List getStations_Matrix() {
		return stations_matrix.vertex;
	}

	/**
	 * @return the graphType
	 */
	public int getGraphType() {
		return graphType;
	}

	/**
	 * @param graphType the graphType to set
	 */
	public void setGraphType(int graphType) {
		this.graphType = graphType;
	}
	
	public void addStation(String[] d) {
		stations.addVertex(new Station(d[0],d[1],d[2].split(",")), getStations_List().size());
		stations.addVertex(new Station(d[0],d[1],d[2].split(",")), getStations_Matrix().size());
	}
	
	public void deleteStation(Station s) {
		stations.removeNode(s);
		stations_matrix.removeNode(s);
	}
	
	
}
