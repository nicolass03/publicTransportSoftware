package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
		pSearch.initComboBox(main.getStations());
		
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
	}
	
	public static void main(String[] args) {
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
	
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
}
