package visual;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class PanelResultPath extends JPanel{

	public PanelResultPath(MainWindow mainWindow) {
		setPreferredSize(new Dimension(200,425));
		setBorder(BorderFactory.createTitledBorder("Resultado"));
		setBackground(Color.WHITE);
	}
	
	public void addMiniPanelResult(String[] d) {
		JPanel aux = new JPanel(new GridLayout(2,1));
		aux.setPreferredSize(new Dimension(200,30));
		aux.setBackground(Color.WHITE);
		JLabel l1 = new JLabel(d[0]);
		JLabel l2 = new JLabel(d[1]);
		aux.add(l1);
		aux.add(l2);
		add(aux);
	}
}
