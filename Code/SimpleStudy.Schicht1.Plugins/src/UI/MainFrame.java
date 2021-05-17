package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Dozent;
import Models.Entity;
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
	private JLabel lblHochschule;
	private JLabel lblDozent;
	private JLabel lblLernfach;
	private JLabel lblKapitel;
	private JLabel lblFragen;
	private JLabel lblLogo;

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
	private JPanel pnlObenBtn;

	private JScrollPane scrListe;

	private JList hochschulListe;
	private JList dozentenListe;
	private JList lernfachListe;
	private JList kapitelListe;
	private JList fragenListe;

	private ImageIcon logo;

	private JScrollPane hochScrollPane;
	private JScrollPane dozScrollPane;
	private JScrollPane lernScrollPane;
	private JScrollPane kapScrollPane;
	private JScrollPane fragScrollPane;

	private JButton btnNeuAbfrage;
	private JButton btnStatistikAnzeigen;
	private JButton btnBeenden;

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
		this.setSize(1600,
				900);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		pnlKopf = new JPanel(new BorderLayout());
		pnlOben = new JPanel(new BorderLayout());
		pnlObenBtn = new JPanel();

		// Kopfzeile
		logo = new ImageIcon("logoSimpleStudy.png");
		logo.setImage(logo.getImage()
				.getScaledInstance(400,
						100,
						Image.SCALE_AREA_AVERAGING));
		lblLogo = new JLabel(logo);
		btnNeuAbfrage = new JButton("Abfrage starten");
		btnNeuAbfrage.addActionListener(this);
		btnStatistikAnzeigen = new JButton("Statistik zeigen");
		btnStatistikAnzeigen.addActionListener(this);
		btnBeenden = new JButton("Beenden");
		btnBeenden.addActionListener(this);
		pnlObenBtn.add(btnNeuAbfrage);
		pnlObenBtn.add(btnStatistikAnzeigen);
		pnlObenBtn.add(btnBeenden);
		pnlKopf.add(lblLogo,
				BorderLayout.CENTER);
		pnlKopf.add(pnlObenBtn,
				BorderLayout.EAST);
		pnlOben.add(pnlKopf,
				BorderLayout.PAGE_START);

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

		hochschulListe = new JList<>(MainController.getInstance()
				.getHochschulen()
				.toArray());
		hochschulListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hochScrollPane = new JScrollPane(hochschulListe);

		dozentenListe = new JList<>(MainController.getInstance()
				.getDozenten()
				.toArray());
		dozentenListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dozScrollPane = new JScrollPane(dozentenListe);

		lernfachListe = new JList<>(MainController.getInstance()
				.getLernfaecher()
				.toArray());
		lernfachListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lernScrollPane = new JScrollPane(lernfachListe);

		kapitelListe = new JList<>(MainController.getInstance()
				.getKapitel()
				.toArray());
		kapitelListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		kapScrollPane = new JScrollPane(kapitelListe);

		fragenListe = new JList<>(MainController.getInstance()
				.getFragen()
				.toArray());
		fragenListe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fragScrollPane = new JScrollPane(fragenListe);

		pnlListen.add(hochScrollPane);
		pnlListen.add(dozScrollPane);
		pnlListen.add(lernScrollPane);
		pnlListen.add(kapScrollPane);
		pnlListen.add(fragScrollPane);

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
		btnDozDel.addActionListener(this);

		pnlDozBtn.add(btnDozNeu);
		pnlDozBtn.add(btnDozBear);
		pnlDozBtn.add(btnDozDel);

		// Lernfaecher

		btnLernNeu = new JButton("Neu");
		btnLernNeu.addActionListener(this);
		btnLernBear = new JButton("Bearbeiten");
		btnLernBear.addActionListener(this);
		btnLernDel = new JButton("Loeschen");
		btnLernDel.addActionListener(this);

		pnlLernBtn.add(btnLernNeu);
		pnlLernBtn.add(btnLernBear);
		pnlLernBtn.add(btnLernDel);

		// Kapitel

		btnKapNeu = new JButton("Neu");
		btnKapNeu.addActionListener(this);
		btnKapBear = new JButton("Bearbeiten");
		btnKapBear.addActionListener(this);
		btnKapDel = new JButton("Loeschen");
		btnKapDel.addActionListener(this);

		pnlKapBtn.add(btnKapNeu);
		pnlKapBtn.add(btnKapBear);
		pnlKapBtn.add(btnKapDel);

		// Fragen

		btnFragNeu = new JButton("Neu");
		btnFragNeu.addActionListener(this);
		btnFragBear = new JButton("Bearbeiten");
		btnFragBear.addActionListener(this);
		btnFragDel = new JButton("Loeschen");
		btnFragDel.addActionListener(this);

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
		if (e.getSource() == btnHochNeu)
		{
			NeuFrame nf = new NeuFrame(Hochschule.class.getSimpleName(), HochschulFabrik.getInstance()
					.getHochschulAttribute());
		}

		else if (e.getSource() == btnHochBear)
		{
			if (hochschulListe.getSelectedValue() != null)
			{
				NeuFrame nf = new NeuFrame(Hochschule.class.getSimpleName(),
						((PrettyHashMap) hochschulListe.getSelectedValue()).getNormalHashMap());
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie eine Hochschule aus!");
			}

		}

		else if (e.getSource() == btnHochDel)
		{
			if (hochschulListe.getSelectedValue() != null)
			{
				int ret = JOptionPane.showConfirmDialog(MainFrame.this,
						"Soll die gewählte Hochschule wirklich gelöscht werden?");
				if (ret == JOptionPane.YES_OPTION)
				{
					MainController.getInstance()
							.deleteHochschule(((PrettyHashMap) hochschulListe.getSelectedValue()).visible.get(Entity.idText));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie eine Hochschule aus!");
			}

		}

		else if (e.getSource() == btnDozNeu)
		{
			NeuFrame nf = new NeuFrame(Dozent.class.getSimpleName(), DozentenFabrik.getInstance()
					.getDozentAttribute());
		}

		else if (e.getSource() == btnDozBear)
		{
			if (dozentenListe.getSelectedValue() != null)
			{
				NeuFrame nf = new NeuFrame(Dozent.class.getSimpleName(),
						((PrettyHashMap) dozentenListe.getSelectedValue()).getNormalHashMap());
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie einen Dozent*in aus!");
			}

		}

		else if (e.getSource() == btnDozDel)
		{
			if (dozentenListe.getSelectedValue() != null)
			{
				int ret = JOptionPane.showConfirmDialog(MainFrame.this,
						"Soll der/die gewählte Dozent/in wirklich gelöscht werden?");
				if (ret == JOptionPane.YES_OPTION)
				{
					MainController.getInstance()
							.deleteDozent(((PrettyHashMap) dozentenListe.getSelectedValue()).visible.get(Entity.idText));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie einen Dozent*in aus!");
			}

		}

		else if (e.getSource() == btnLernNeu)
		{
			NeuFrame nf = new NeuFrame(Lernfach.class.getSimpleName(), LernfachFabrik.getInstance()
					.getLernfachAttribute());
		}

		else if (e.getSource() == btnLernBear)
		{
			if (lernfachListe.getSelectedValue() != null)
			{
				NeuFrame nf = new NeuFrame(Lernfach.class.getSimpleName(),
						((PrettyHashMap) lernfachListe.getSelectedValue()).getNormalHashMap());
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie ein Lernfach aus!");
			}

		}

		else if (e.getSource() == btnLernDel)
		{
			if (lernfachListe.getSelectedValue() != null)
			{
				int ret = JOptionPane.showConfirmDialog(MainFrame.this,
						"Soll das gewählte Lernfach wirklich gelöscht werden?");
				if (ret == JOptionPane.YES_OPTION)
				{
					MainController.getInstance()
							.deleteLernfach(((PrettyHashMap) lernfachListe.getSelectedValue()).visible.get(Entity.idText));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie ein Lernfach aus!");
			}

		}

		else if (e.getSource() == btnKapNeu)
		{
			NeuFrame nf = new NeuFrame(Kapitel.class.getSimpleName(), KapitelFabrik.getInstance()
					.getKaptielAttribute());
		}

		else if (e.getSource() == btnKapBear)
		{
			if (kapitelListe.getSelectedValue() != null)
			{
				NeuFrame nf = new NeuFrame(Kapitel.class.getSimpleName(),
						((PrettyHashMap) kapitelListe.getSelectedValue()).getNormalHashMap());
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie ein Kapitel aus!");
			}

		}

		else if (e.getSource() == btnKapDel)
		{
			if (kapitelListe.getSelectedValue() != null)
			{
				int ret = JOptionPane.showConfirmDialog(MainFrame.this,
						"Soll das gewählte Kapitel wirklich gelöscht werden?");
				if (ret == JOptionPane.YES_OPTION)
				{
					MainController.getInstance()
							.deleteKapitel(((PrettyHashMap) kapitelListe.getSelectedValue()).visible.get(Entity.idText));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie ein Kapitel aus!");
			}

		}

		else if (e.getSource() == btnFragNeu)
		{
			NeuFrame nf = new NeuFrame(Frage.class.getSimpleName(), FragenFabrik.getInstance()
					.getFragenAttribute());
		}

		else if (e.getSource() == btnFragBear)
		{
			if (fragenListe.getSelectedValue() != null)
			{
				NeuFrame nf = new NeuFrame(Frage.class.getSimpleName(),
						((PrettyHashMap) fragenListe.getSelectedValue()).getNormalHashMap());
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie eine Frage aus!");
			}

		}

		else if (e.getSource() == btnFragDel)
		{
			if (fragenListe.getSelectedValue() != null)
			{
				int ret = JOptionPane.showConfirmDialog(MainFrame.this,
						"Soll die gewählte Frage wirklich gelöscht werden?");
				if (ret == JOptionPane.YES_OPTION)
				{
					MainController.getInstance()
							.deleteFrage(((PrettyHashMap) fragenListe.getSelectedValue()).visible.get(Entity.idText));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie eine Frage aus!");
			}

		}

		else if (e.getSource() == btnNeuAbfrage)

		{

			if (fragenListe.getSelectedValuesList()
					.isEmpty())
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie mindestens eine Frage aus!");
			}
			else
			{
				ArrayList<HashMap<String, String>> fragen = new ArrayList<>();
				for (Object value : (fragenListe.getSelectedValuesList()))
				{

					fragen.add(((PrettyHashMap) value).getNormalHashMap());
				}

				AbfrageFrame af = new AbfrageFrame(MainController.getInstance()
						.createAbfrage(fragen));
			}

		}

		else if (e.getSource() == btnStatistikAnzeigen)
		{

			StatistikFrame sf = new StatistikFrame();

		}

		else if (e.getSource() == btnBeenden)
		{
			int ret = JOptionPane.showConfirmDialog(MainFrame.this,
					"Soll das Programm wirklich beendet werden?");
			if (ret == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}

		}

	}

	@Override
	public void aktualisiere()
	{

		hochschulListe.setListData(MainController.getInstance()
				.getHochschulen()
				.toArray());

		dozentenListe.setListData(MainController.getInstance()
				.getDozenten()
				.toArray());

		lernfachListe.setListData(MainController.getInstance()
				.getLernfaecher()
				.toArray());

		kapitelListe.setListData(MainController.getInstance()
				.getKapitel()
				.toArray());

		fragenListe.setListData(MainController.getInstance()
				.getFragen()
				.toArray());

	}

}
