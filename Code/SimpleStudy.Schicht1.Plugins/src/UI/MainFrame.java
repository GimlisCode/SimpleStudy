package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{

	// Attribute
	private JLabel lbl_titel;

	private JPanel pnl_menu;
	private JPanel oben;
	private JPanel mitte;
	private JPanel links;
	private JPanel rechts;
	private JPanel unten;

	private JMenuBar menu;
	private JMenu datei;
	private JMenu suche;
	private JMenu ueber;
	private JMenu beenden;

	public MainFrame()
	{

		this.setTitle("Simple Study");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen);
		this.setLayout(new BorderLayout());
		oben = new JPanel(new BorderLayout());

		// Kopfzeile
		lbl_titel = new JLabel("Simple Study");
		oben.add(lbl_titel, BorderLayout.NORTH);

		// Menüleiste
		pnl_menu = new JPanel();
		menu = new JMenuBar();
		datei = new JMenu("Datei");
		suche = new JMenu("Suche");
		ueber = new JMenu("Über");
		beenden = new JMenu("Beenden");

		menu.add(datei);
		menu.add(datei);
		menu.add(ueber);
		menu.add(beenden);

		oben.add(pnl_menu, BorderLayout.WEST);

		// Layouting
		this.add(oben, BorderLayout.NORTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

}
