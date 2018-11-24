package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMap extends JPanel {

	public static final String MAP_PATH = "./resources/mapa.png";
	
	private JLabel map;
	
	public PanelMap(MainWindow mainWindow) {
		setPreferredSize(new Dimension(400,600));
		setBackground(Color.WHITE);

		map = new JLabel();
		Image i = new ImageIcon(MAP_PATH).getImage();
		Image red = i.getScaledInstance(390, 598, Image.SCALE_SMOOTH);
		map.setIcon(new ImageIcon(red));
		add(map);
	}
}
