package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.MainController;

public class MainFrame extends JFrame
{

	// Attribute
	private JLabel lbl_titel;
	private JLabel lbl_hochschule;
	private JLabel lbl_dozent;
	private JLabel lbl_lernfach;
	private JLabel lbl_kapitel;
	private JLabel lbl_fragen;

	private JPanel pnl_menu;
	private JPanel pnl_kopf;
	private JPanel pnl_oben;
	private JPanel pnl_listen;
	private JPanel pnl_listenlbl;
	private JPanel pnl_listenbtn;
	private JPanel pnl_hoch_btn;
	private JPanel pnl_doz_btn;
	private JPanel pnl_lern_btn;
	private JPanel pnl_kap_btn;
	private JPanel pnl_frag_btn;
	private JPanel pnl_mitte;
	private JPanel pnl_fuss;

	private final JMenuBar menu;
	private final JMenu datei;
	private final JMenu suche;
	private final JMenu ueber;
	private final JMenu beenden;

	private JScrollPane scr_liste;

	private JList hochschulliste;
	private JList dozentenliste;
	private JList lernfachliste;
	private JList kapitelliste;
	private JList fragenliste;

	private JButton btn_hoch_neu;
	private JButton btn_hoch_bear;
	private JButton btn_hoch_del;

	private JButton btn_doz_neu;
	private JButton btn_doz_bear;
	private JButton btn_doz_del;

	private JButton btn_lern_neu;
	private JButton btn_lern_bear;
	private JButton btn_lern_del;

	private JButton btn_kap_neu;
	private JButton btn_kap_bear;
	private JButton btn_kap_del;

	private JButton btn_frag_neu;
	private JButton btn_frag_bear;
	private JButton btn_frag_del;

	public MainFrame()
	{

		setTitle("Simple Study");
		final Dimension screen = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setSize(screen);
		setLayout(new BorderLayout());
		pnl_kopf = new JPanel();
		pnl_oben = new JPanel(new BorderLayout());

		// Kopfzeile
		lbl_titel = new JLabel("Simple Study");
		pnl_kopf.add(lbl_titel);
		pnl_oben.add(pnl_kopf,
				BorderLayout.PAGE_START);

		// Men�leiste
		pnl_menu = new JPanel();
		menu = new JMenuBar();
		datei = new JMenu("Datei");
		suche = new JMenu("Suche");
		ueber = new JMenu("Ueber");
		beenden = new JMenu("Beenden");

		menu.add(datei);
		menu.add(datei);
		menu.add(ueber);
		menu.add(beenden);

		pnl_menu.add(menu);

		pnl_oben.add(pnl_menu,
				BorderLayout.LINE_START);

		// Auswahllisten
		pnl_mitte = new JPanel();
		pnl_mitte.setLayout(new BorderLayout());
		pnl_listen = new JPanel();
		pnl_listen.setLayout(new GridLayout(1, 5));
		pnl_listenlbl = new JPanel();
		pnl_listenlbl.setLayout(new GridLayout(1, 5));
		pnl_listenbtn = new JPanel();
		pnl_listenbtn.setLayout(new GridLayout(1, 5));

		pnl_hoch_btn = new JPanel();
		pnl_doz_btn = new JPanel();
		pnl_lern_btn = new JPanel();
		pnl_kap_btn = new JPanel();
		pnl_frag_btn = new JPanel();

		lbl_hochschule = new JLabel("Hochschulen");
		lbl_dozent = new JLabel("Dozenten");
		lbl_lernfach = new JLabel("Lernfaecher");
		lbl_kapitel = new JLabel("Kapitel");
		lbl_fragen = new JLabel("Fragen");

		pnl_listenlbl.add(lbl_hochschule);
		pnl_listenlbl.add(lbl_dozent);
		pnl_listenlbl.add(lbl_lernfach);
		pnl_listenlbl.add(lbl_kapitel);
		pnl_listenlbl.add(lbl_fragen);

		hochschulliste = new JList<>(MainController.getHochschulen()
				.toArray());

		dozentenliste = new JList<>(MainController.getDozenten()
				.toArray());

		lernfachliste = new JList<>(MainController.getLernfaecher()
				.toArray());

		kapitelliste = new JList<>(MainController.getKapitel()
				.toArray());

		fragenliste = new JList<>();

		pnl_listen.add(hochschulliste);
		pnl_listen.add(dozentenliste);
		pnl_listen.add(lernfachliste);
		pnl_listen.add(kapitelliste);
		pnl_listen.add(fragenliste);

		// Buttons fuer die Auswahllisten

		// Hochschule

		btn_hoch_neu = new JButton("Neu");
		btn_hoch_bear = new JButton("Bearbeiten");
		btn_hoch_del = new JButton("Loeschen");

		pnl_hoch_btn.add(btn_hoch_neu);
		pnl_hoch_btn.add(btn_hoch_bear);
		pnl_hoch_btn.add(btn_hoch_del);

		// Dozenten

		btn_doz_neu = new JButton("Neu");
		btn_doz_bear = new JButton("Bearbeiten");
		btn_doz_del = new JButton("Loeschen");

		pnl_doz_btn.add(btn_doz_neu);
		pnl_doz_btn.add(btn_doz_bear);
		pnl_doz_btn.add(btn_doz_del);

		// Lernf�cher

		btn_lern_neu = new JButton("Neu");
		btn_lern_bear = new JButton("Bearbeiten");
		btn_lern_del = new JButton("Loeschen");

		pnl_lern_btn.add(btn_lern_neu);
		pnl_lern_btn.add(btn_lern_bear);
		pnl_lern_btn.add(btn_lern_del);

		// Kapitel

		btn_kap_neu = new JButton("Neu");
		btn_kap_bear = new JButton("Bearbeiten");
		btn_kap_del = new JButton("Loeschen");

		pnl_kap_btn.add(btn_kap_neu);
		pnl_kap_btn.add(btn_kap_bear);
		pnl_kap_btn.add(btn_kap_del);

		// Fragen

		btn_frag_neu = new JButton("Neu");
		btn_frag_bear = new JButton("Bearbeiten");
		btn_frag_del = new JButton("Loeschen");

		pnl_frag_btn.add(btn_frag_neu);
		pnl_frag_btn.add(btn_frag_bear);
		pnl_frag_btn.add(btn_frag_del);

		// ButtonComponent

		pnl_listenbtn.add(pnl_hoch_btn);
		pnl_listenbtn.add(pnl_doz_btn);
		pnl_listenbtn.add(pnl_lern_btn);
		pnl_listenbtn.add(pnl_kap_btn);
		pnl_listenbtn.add(pnl_frag_btn);

		// Fusszeile
		pnl_fuss = new JPanel();

		// Layouting
		pnl_mitte.add(pnl_listenlbl,
				BorderLayout.NORTH);
		pnl_mitte.add(pnl_listen,
				BorderLayout.CENTER);
		pnl_mitte.add(pnl_listenbtn,
				BorderLayout.SOUTH);
		this.add(pnl_oben,
				BorderLayout.NORTH);
		this.add(pnl_mitte,
				BorderLayout.CENTER);
		this.add(pnl_fuss,
				BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

}
