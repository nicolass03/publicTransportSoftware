package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.MainClass;
import model.Station;

public class MainWindow extends JFrame{

	public static final String ICON_PATH = "./resources/LogoMIOCali.png";
	
	private PanelMap pMap;
	private PanelResultPath pResult;
	private PanelSearch pSearch;
	private PanelOptions pOptions;
	
//	private AddStationDialog as;
	private BFSTravelDialog bfs;
	private static LoadingScreen ls;
	private ChangeGraphDialog cg;
	
	private MainClass main;
	
	public MainWindow() {
		
		setTitle("STP :: Software de Transporte Publico");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(ICON_PATH).getImage());
		setLayout(new BorderLayout());
		setResizable(false);
		setPreferredSize(new Dimension(690,680));
		
		main = new MainClass();
		
		pMap = new PanelMap(this);
		pOptions = new PanelOptions(this);
		pResult = new PanelResultPath(this);
		pSearch = new PanelSearch(this);
		pSearch.initComboBox(main.getStations_List());
		
		JScrollPane sp = new JScrollPane(pResult);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		JPanel aux = new JPanel(new FlowLayout());
		aux.setBackground(Color.WHITE);

		aux.setPreferredSize(new Dimension(250,650));
		aux.add(pSearch, BorderLayout.WEST);
		aux.add(sp, BorderLayout.WEST);
		add(pMap, BorderLayout.CENTER);
		add(aux, BorderLayout.WEST);
		add(pOptions, BorderLayout.SOUTH);
		pack();
		center();
	}
	
	public void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public static void main(String[] args) {
		ls = new LoadingScreen();
		ls.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ls.setVisible(false);
		ls.dispose();
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
	
	////////// INITIALIZING METHODS FOR ALL DIALOGS //////////////
	
	public void initChangeGraphDialog() {
		cg = new ChangeGraphDialog(this);
		cg.setVisible(true);
	}
	
	public void initBFSDialog(Object object) {
		ArrayList<Station> s = main.bfs((Station) object);
		bfs = new BFSTravelDialog(s);
		bfs.setVisible(true);
	}
	
	public void initSelectBFSDialog() {
		SelectSourceDialog s = null; 
		switch(main.getGraphType()) {
		case 0:
			s = new SelectSourceDialog(main.getStations_List(), this);
			break;
			
		case 1:
			s = new SelectSourceDialog(main.getStations_Matrix(), this);
			break;
		}
		s.setVisible(true);
	}
	
//	public void initAddStationDialog() {
//		as = new AddStationDialog(this);
//		as.setVisible(true);
//	}
	
	/////////////////////////////////////////////////////////////
	
	public void searchPath(Object o, Object t) {
		Station origin = (Station) o;
		Station target = (Station) t;
		Station[] path = main.getShortestPathTo(origin, target);
		putResults(path); 
	}
	
	public void putResults(Station[] path) {
		for(int i = path.length-1; i >= 0;i--) {
			if(path[i] != null) {
				String[] d = {path[i].getName(),path[i].getAdress()};
				pResult.addMiniPanelResult(d);
				pResult.revalidate();
				pResult.repaint();				
			}
		}
	}
//	
//	public void addStation(String[] d) {
//		main.addStation(d);
//		pSearch.revalidate();
//	}
	
	public void changeTypeOfGraph(Object object) {
		String t = (String) object;
		switch(t) {
		case "Matriz":
			main.setGraphType(main.GRAPH_MATRIX);
			reloadPanelSearch(1);
			break;
			
		case "Lista":
			main.setGraphType(main.GRAPH_LIST);
			reloadPanelSearch(0);
			break;
		}
	}

	public void clearResultsPanel() {
		pResult.removeAll();
		pResult.validate();
		pResult.revalidate();
		pResult.repaint();
	}
	
	public void reloadPanelSearch(int type) {
		pSearch.removeAll();
		switch(type) {
		case 0:
			pSearch.initComboBox(main.getStations_List());
			break;
			
		case 1:
			pSearch.initComboBox(main.getStations_Matrix());
		}
		pSearch.validate();
		pSearch.revalidate();
		pSearch.repaint();
	}
}

