package visual;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOptions extends JPanel implements ActionListener{
	
	public static final String ADD = "a";
	public static final String DELETE = "d";
	public static final String NEW_SEARCH = "ns";
	public static final String CHANGE_GRAPH = "cg";
	
	private JButton add;
	private JButton delete;
	private JButton newSearch;
	private JButton changeGraph;
	
	private MainWindow mw;
	
	public PanelOptions(MainWindow main) {
		
		mw = main;
		
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);

		add = new JButton("Agregar estacion");
		add.addActionListener(this);
		add.setActionCommand(ADD);
		add.setBackground(Color.WHITE);

		delete = new JButton("Eliminar estacion");
		delete.addActionListener(this);
		delete.setActionCommand(DELETE);
		delete.setBackground(Color.WHITE);

		newSearch = new JButton("Nueva busqueda");
		newSearch.addActionListener(this);
		newSearch.setActionCommand(NEW_SEARCH);
		newSearch.setBackground(Color.WHITE);

		changeGraph = new JButton("Cambiar tipo de grafo");
		changeGraph.addActionListener(this);
		changeGraph.setActionCommand(CHANGE_GRAPH);
		changeGraph.setBackground(Color.WHITE);

		
		add(newSearch);
		add(add);
		add(delete);
		add(changeGraph);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		
	}
}
