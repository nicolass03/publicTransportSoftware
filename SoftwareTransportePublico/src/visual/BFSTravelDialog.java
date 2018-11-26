package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import model.Station;

public class BFSTravelDialog extends JDialog{

	public BFSTravelDialog(List<Station> d) {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(180,600));
		JScrollPane js = new JScrollPane();
		js.setPreferredSize(new Dimension(160,600));
		add(js);
		for(int i = 0; i < d.size(); i++) {
			JLabel lbl = new JLabel(d.get(i).getName());
			lbl.setPreferredSize(new Dimension(200,60));
			js.add(lbl);
		}
		revalidate();
		validate();
		repaint();
		pack();
		center();
	}
	
	public void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
