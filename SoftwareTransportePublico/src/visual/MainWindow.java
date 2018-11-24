package visual;

import javax.swing.JFrame;

import model.MainClass;

public class MainWindow extends JFrame{

	private MainClass main;
	
	public MainWindow() {
		
		main = new MainClass();
		setTitle("STP :: Software de Transporte Publico");
		pack();
	}
	
	public static void main(String[] args) {
	
	}

}
