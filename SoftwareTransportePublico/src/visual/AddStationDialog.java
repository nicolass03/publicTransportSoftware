package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddStationDialog extends JDialog implements ActionListener{

	public static final String ACCEPT = "a";

	private JLabel lblName;
	private JLabel lblAdress;
	private JLabel lblBuses;

	private JTextField txtName;
	private JTextField txtAdress;
	private JTextField txtBuses;

	private JButton jbAccept;

	private MainWindow mw;

	public AddStationDialog(MainWindow m) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		mw = m;

		setLayout(new FlowLayout());

		lblName = new JLabel("Nombre:");
		lblAdress = new JLabel("Dirección:");
		lblBuses = new JLabel("Buses:");

		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(100,25));
		txtAdress = new JTextField();
		txtAdress.setPreferredSize(new Dimension(100,25));
		txtBuses = new JTextField("Escriba las rutas separadas por ','");
		txtBuses.setPreferredSize(new Dimension(200,25));

		jbAccept = new JButton("Aceptar");
		jbAccept.addActionListener(this);
		jbAccept.setActionCommand(ACCEPT);

		add(lblName);
		add(txtName);
		add(lblAdress);
		add(txtAdress);
		add(lblBuses);
		add(txtBuses);

		add(jbAccept);
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
			String[] d = {txtName.getText(), txtAdress.getText(), txtBuses.getText()};
			if(txtName.getText() == null || txtName.getText().equals("") || txtAdress.getText() == null || 
					txtAdress.getText().equals("") || txtBuses.getText() == null || txtBuses.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
//				mw.addStation(d);
				setVisible(false);
				this.dispose();				
			}
		}
	}
}
