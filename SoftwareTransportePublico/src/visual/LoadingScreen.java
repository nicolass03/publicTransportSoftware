package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class LoadingScreen extends JDialog{

	private JLabel message;
	private JLabel loadingIcon;
	
	public LoadingScreen() {
		setLayout(new FlowLayout());
		setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(201,220));
		message = new JLabel("Iniciando aplicación");
		loadingIcon = new JLabel(new ImageIcon("./resources/loading.gif"));
		add(message);
		add(loadingIcon);
		pack();
		center();

	}
	
	public void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
