package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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
	
	private AddStationDialog as;
	private DeleteStationDialog ds;
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
	
	public void initDeleteStationDialog() {
		switch(main.getGraphType()) {
		case 0:
			ds = new DeleteStationDialog(main.getStations_List(), this);
			break;
			
		case 1:
			ds = new DeleteStationDialog(main.getStations_Matrix(), this);
			break;
		}
		ds.setVisible(true);
	}
	
	public void initAddStationDialog() {
		as = new AddStationDialog(this);
		as.setVisible(true);
	}
	
	/////////////////////////////////////////////////////////////
	
	public void searchPath(Object o, Object t) {
		Station origin = (Station) o;
		Station target = (Station) t;
		Station[] path = main.getShortestPathTo(origin, target);
		putResults(path); 
	}
	
	public void putResults(Station[] path) {
		for(int i = 0; i < path.length;i++) {
			String[] d = {path[i].getName(),path[i].getAdress()};
			pResult.addMiniPanelResult(d);
		}
	}
	
	public void deleteStation(Object d) {
		Station s = (Station) d;
		main.deleteStation(s);
		pSearch.validate();
	}
	
	public void addStation(String[] d) {
		main.addStation(d);
		pSearch.validate();
	}
	
	public void changeTypeOfGraph(Object object) {
		String t = (String) object;
		switch(t) {
		case "Matriz":
			main.setGraphType(main.GRAPH_MATRIX);
			break;
			
		case "Lista":
			main.setGraphType(main.GRAPH_LIST);
			break;
		}
	}

}
