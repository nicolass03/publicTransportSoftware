package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import graph.Graph;

public class MainClass {

		Graph<Station> stations;

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
}
