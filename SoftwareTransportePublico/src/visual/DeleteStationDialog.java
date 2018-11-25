package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeleteStationDialog extends JDialog implements ActionListener{

	public static final String ACCEPT = "a";

	private JLabel lbl;
	private JComboBox st;

	private JButton accept;

	private MainWindow mw;

	public DeleteStationDialog(List l, MainWindow m) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mw = m;

		setLayout(new FlowLayout());

		lbl = new JLabel("Seleccione la estación a eliminar");
		st = new JComboBox(l.toArray());

		accept = new JButton("Aceptar");
		accept.addActionListener(this);
		accept.setActionCommand(ACCEPT);

		add(lbl);
		add(st);
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
		if(a.getActionCommand().equals(ACCEPT)) {
			if(st.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				mw.deleteStation(st.getSelectedItem());
				setVisible(false);
				this.dispose();				
			}
		}
	}

}
