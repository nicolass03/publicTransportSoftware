package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelSearch extends JPanel implements ActionListener{

	public static final String SEARCH = "s";
	
	private JLabel lbl1;
	private JLabel lbl2;
	
	private JComboBox origin;
	private JComboBox target;
	
	private JButton jbSearch;
	
	private MainWindow mw;
	
	public PanelSearch(MainWindow main) {
		
		mw = main;
		setBorder(BorderFactory.createTitledBorder("Buscar"));
		
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(250, 170));
		setBackground(Color.WHITE);

		jbSearch = new JButton("Buscar");
		jbSearch.setActionCommand(SEARCH);
		jbSearch.addActionListener(this);
		jbSearch.setBackground(Color.WHITE);

	}
	
	public void initComboBox(List s) {
		lbl1 = new JLabel("Por favor seleccione el punto de partida.");
		lbl2 = new JLabel("Por favor seleccione el punto de llegada.");
		
		origin = new JComboBox(s.toArray());
		origin.setPreferredSize(new Dimension(230,25));
		origin.setBackground(Color.WHITE);

		target = new JComboBox(s.toArray());
		target.setPreferredSize(new Dimension(230,25));
		target.setBackground(Color.WHITE);

		add(lbl1);
		add(origin);
		add(lbl2);
		add(target);
		add(jbSearch);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getActionCommand().equals(SEARCH)) {
			if(origin.getSelectedItem() != null && target.getSelectedItem() != null) {
				mw.searchPath(origin.getSelectedItem(), target.getSelectedItem());				
			}
			else {
				JOptionPane.showMessageDialog(this, "Error buscando la ruta", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
