package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Dozent;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Modifier.DozentenFabrik;
import Modifier.FragenFabrik;
import Modifier.HochschulFabrik;
import Modifier.KapitelFabrik;
import Modifier.LernfachFabrik;
import Renderer.PrettyHashMap;

public class MainFrame extends JFrame implements ActionListener, UiBeobachter
{

	// Attribute
	private JLabel lblTitel;
	private JLabel lblHochschule;
	private JLabel lblDozent;
	private JLabel lblLernfach;
	private JLabel lblKapitel;
	private JLabel lblFragen;

	private JPanel pnlMenu;
	private JPanel pnlKopf;
	private JPanel pnlOben;
	private JPanel pnlListen;
	private JPanel pnlListenlbl;
	private JPanel pnlListenbtn;
	private JPanel pnlHochBtn;
	private JPanel pnlDozBtn;
	private JPanel pnlLernBtn;
	private JPanel pnlKapBtn;
	private JPanel pnlFragBtn;
	private JPanel pnlMitte;
	private JPanel pnlFuss;

	private final JMenuBar menu;
	private final JMenu datei;
	private final JMenu suche;
	private final JMenu ueber;
	private final JMenu beenden;

	private JScrollPane scrListe;

	private JList hochschulListe;
	private JList dozentenListe;
	private JList lernfachListe;
	private JList kapitelListe;
	private JList fragenListe;

	private JButton btnHochNeu;
	private JButton btnHochBear;
	private JButton btnHochDel;

	private JButton btnDozNeu;
	private JButton btnDozBear;
	private JButton btnDozDel;

	private JButton btnLernNeu;
	private JButton btnLernBear;
	private JButton btnLernDel;

	private JButton btnKapNeu;
	private JButton btnKapBear;
	private JButton btnKapDel;

	private JButton btnFragNeu;
	private JButton btnFragBear;
	private JButton btnFragDel;

	public MainFrame()
	{
		MainController.getInstance()
				.registriere(this);
		setTitle("Simple Study");
		final Dimension screen = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setSize(screen);
		setLayout(new BorderLayout());
		pnlKopf = new JPanel();
		pnlOben = new JPanel(new BorderLayout());

		// Kopfzeile
		lblTitel = new JLabel("Simple Study");
		pnlKopf.add(lblTitel);
		pnlOben.add(pnlKopf,
				BorderLayout.PAGE_START);

		// Men�leiste
		pnlMenu = new JPanel();
		menu = new JMenuBar();
		datei = new JMenu("Datei");
		suche = new JMenu("Suche");
		ueber = new JMenu("Ueber");
		beenden = new JMenu("Beenden");
		beenden.addActionListener(this);

		menu.add(datei);
		menu.add(datei);
		menu.add(ueber);
		menu.add(beenden);

		pnlMenu.add(menu);

		pnlOben.add(pnlMenu,
				BorderLayout.LINE_START);

		// Auswahllisten
		pnlMitte = new JPanel();
		pnlMitte.setLayout(new BorderLayout());
		pnlListen = new JPanel();
		pnlListen.setLayout(new GridLayout(1, 5));
		pnlListenlbl = new JPanel();
		pnlListenlbl.setLayout(new GridLayout(1, 5));
		pnlListenbtn = new JPanel();
		pnlListenbtn.setLayout(new GridLayout(1, 5));

		pnlHochBtn = new JPanel();
		pnlDozBtn = new JPanel();
		pnlLernBtn = new JPanel();
		pnlKapBtn = new JPanel();
		pnlFragBtn = new JPanel();

		lblHochschule = new JLabel(Hochschule.class.getSimpleName());
		lblDozent = new JLabel(Dozent.class.getSimpleName());
		lblLernfach = new JLabel(Lernfach.class.getSimpleName());
		lblKapitel = new JLabel(Kapitel.class.getSimpleName());
		lblFragen = new JLabel(Frage.class.getSimpleName());

		pnlListenlbl.add(lblHochschule);
		pnlListenlbl.add(lblDozent);
		pnlListenlbl.add(lblLernfach);
		pnlListenlbl.add(lblKapitel);
		pnlListenlbl.add(lblFragen);

		hochschulListe = new JList<>(MainController.getHochschulen()
				.toArray());

		dozentenListe = new JList<>(MainController.getDozenten()
				.toArray());

		lernfachListe = new JList<>(MainController.getLernfaecher()
				.toArray());

		kapitelListe = new JList<>(MainController.getKapitel()
				.toArray());

		fragenListe = new JList<>(MainController.getFragen()
				.toArray());

		pnlListen.add(hochschulListe);
		pnlListen.add(dozentenListe);
		pnlListen.add(lernfachListe);
		pnlListen.add(kapitelListe);
		pnlListen.add(fragenListe);

		// Buttons fuer die Auswahllisten

		// Hochschule

		btnHochNeu = new JButton("Neu");
		btnHochNeu.addActionListener(this);
		btnHochBear = new JButton("Bearbeiten");
		btnHochBear.addActionListener(this);
		btnHochDel = new JButton("Loeschen");
		btnHochDel.addActionListener(this);

		pnlHochBtn.add(btnHochNeu);
		pnlHochBtn.add(btnHochBear);
		pnlHochBtn.add(btnHochDel);

		// Dozenten

		btnDozNeu = new JButton("Neu");
		btnDozNeu.addActionListener(this);
		btnDozBear = new JButton("Bearbeiten");
		btnDozBear.addActionListener(this);
		btnDozDel = new JButton("Loeschen");

		pnlDozBtn.add(btnDozNeu);
		pnlDozBtn.add(btnDozBear);
		pnlDozBtn.add(btnDozDel);

		// Lernfaecher

		btnLernNeu = new JButton("Neu");
		btnLernNeu.addActionListener(this);
		btnLernBear = new JButton("Bearbeiten");
		btnLernBear.addActionListener(this);
		btnLernDel = new JButton("Loeschen");

		pnlLernBtn.add(btnLernNeu);
		pnlLernBtn.add(btnLernBear);
		pnlLernBtn.add(btnLernDel);

		// Kapitel

		btnKapNeu = new JButton("Neu");
		btnKapNeu.addActionListener(this);
		btnKapBear = new JButton("Bearbeiten");
		btnKapBear.addActionListener(this);
		btnKapDel = new JButton("Loeschen");

		pnlKapBtn.add(btnKapNeu);
		pnlKapBtn.add(btnKapBear);
		pnlKapBtn.add(btnKapDel);

		// Fragen

		btnFragNeu = new JButton("Neu");
		btnFragNeu.addActionListener(this);
		btnFragBear = new JButton("Bearbeiten");
		btnFragBear.addActionListener(this);
		btnFragDel = new JButton("Loeschen");

		pnlFragBtn.add(btnFragNeu);
		pnlFragBtn.add(btnFragBear);
		pnlFragBtn.add(btnFragDel);

		// ButtonComponent

		pnlListenbtn.add(pnlHochBtn);
		pnlListenbtn.add(pnlDozBtn);
		pnlListenbtn.add(pnlLernBtn);
		pnlListenbtn.add(pnlKapBtn);
		pnlListenbtn.add(pnlFragBtn);

		// Fusszeile
		pnlFuss = new JPanel();

		// Layouting
		pnlMitte.add(pnlListenlbl,
				BorderLayout.NORTH);
		pnlMitte.add(pnlListen,
				BorderLayout.CENTER);
		pnlMitte.add(pnlListenbtn,
				BorderLayout.SOUTH);
		this.add(pnlOben,
				BorderLayout.NORTH);
		this.add(pnlMitte,
				BorderLayout.CENTER);
		this.add(pnlFuss,
				BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == beenden)
		{
			this.dispose();
		}

		else if (e.getSource() == btnHochNeu)
		{
			NeuFrame nf = new NeuFrame(Hochschule.class.getSimpleName(), HochschulFabrik.getHochschulAttribute());
		}

		else if (e.getSource() == btnHochBear)
		{
			NeuFrame nf = new NeuFrame(Hochschule.class.getSimpleName(),
					((PrettyHashMap) hochschulListe.getSelectedValue()).getNormalHashMap());
		}

		else if (e.getSource() == btnDozNeu)
		{
			NeuFrame nf = new NeuFrame(Dozent.class.getSimpleName(), DozentenFabrik.getDozentAttribute());
		}

		else if (e.getSource() == btnDozBear)
		{
			NeuFrame nf = new NeuFrame(Dozent.class.getSimpleName(),
					((PrettyHashMap) dozentenListe.getSelectedValue()).getNormalHashMap());
		}

		else if (e.getSource() == btnLernNeu)
		{
			NeuFrame nf = new NeuFrame(Lernfach.class.getSimpleName(), LernfachFabrik.getLernfachAttribute());
		}

		else if (e.getSource() == btnLernBear)
		{
			NeuFrame nf = new NeuFrame(Lernfach.class.getSimpleName(),
					((PrettyHashMap) lernfachListe.getSelectedValue()).getNormalHashMap());
		}

		else if (e.getSource() == btnKapNeu)
		{
			NeuFrame nf = new NeuFrame(Kapitel.class.getSimpleName(), KapitelFabrik.getKaptielAttribute());
		}

		else if (e.getSource() == btnKapBear)
		{
			NeuFrame nf = new NeuFrame(Kapitel.class.getSimpleName(),
					((PrettyHashMap) kapitelListe.getSelectedValue()).getNormalHashMap());
		}

		else if (e.getSource() == btnFragNeu)
		{
			NeuFrame nf = new NeuFrame(Frage.class.getSimpleName(), FragenFabrik.getFragenAttribute());
		}

		else if (e.getSource() == btnFragBear)
		{
			NeuFrame nf = new NeuFrame(Frage.class.getSimpleName(),
					((PrettyHashMap) fragenListe.getSelectedValue()).getNormalHashMap());
		}

	}

	@Override
	public void aktualisiere()
	{

		hochschulListe.setListData(MainController.getHochschulen()
				.toArray());

		dozentenListe.setListData(MainController.getDozenten()
				.toArray());

		lernfachListe.setListData(MainController.getLernfaecher()
				.toArray());

		kapitelListe.setListData(MainController.getKapitel()
				.toArray());

		fragenListe.setListData(MainController.getFragen()
				.toArray());

	}

}
