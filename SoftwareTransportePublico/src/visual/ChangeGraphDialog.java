package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ChangeGraphDialog extends JDialog implements ActionListener{

	public static final String SELECT = "s";
	
	private JLabel lbl;
	private JComboBox<String> cBox;
	private JButton accept;
	
	private MainWindow mw;
	
	public ChangeGraphDialog(MainWindow m) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new FlowLayout());
		mw = m;
		lbl = new JLabel("Seleccione el tipo de grafo a usar");
		String[] data = {"Matriz","Lista"};
		cBox = new JComboBox<String>(data);
		accept = new JButton("Aceptar");
		accept.addActionListener(this);
		accept.setActionCommand(SELECT);
		
		add(lbl);
		add(cBox);
		add(accept);
		pack();
		center();

	}

	public void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getActionCommand().equals(SELECT)) {
			mw.changeTypeOfGraph(cBox.getSelectedItem());
			this.setVisible(false);
			this.dispose();
		}
	}
}
