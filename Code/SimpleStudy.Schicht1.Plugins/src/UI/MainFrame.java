package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import de.dhbwka.swe.utils.gui.ButtonComponent;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;

public class MainFrame extends JFrame
{

	// Attribute
	private JLabel lbl_titel;

	private JPanel pnl_menu;
	private JPanel pnl_kopf;
	private JPanel pnl_oben;
	private JPanel pnl_listen;
	private JPanel pnl_fuss;

	private JMenuBar menu;
	private JMenu datei;
	private JMenu suche;
	private JMenu ueber;
	private JMenu beenden;

	private JScrollPane scr_liste;

	private SimpleListComponent slc_hochschule;
	private SimpleListComponent slc_dozenten;
	private SimpleListComponent slc_lernfachListe;
	private SimpleListComponent slc_kapitelListe;
	private SimpleListComponent slc_fragenListe;

	public MainFrame()
	{

		this.setTitle("Simple Study");
		Dimension screen = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setSize(screen);
		this.setLayout(new BorderLayout());
		pnl_kopf = new JPanel();
		pnl_oben = new JPanel(new BorderLayout());

		// Kopfzeile
		lbl_titel = new JLabel("Simple Study");
		pnl_kopf.add(lbl_titel);
		pnl_oben.add(pnl_kopf,
				BorderLayout.PAGE_START);

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

		pnl_menu.add(menu);

		pnl_oben.add(pnl_menu,
				BorderLayout.LINE_START);

		// Auswahllisten
		pnl_listen = new JPanel();
		pnl_listen.setLayout(new GridLayout(1, 5));

		slc_hochschule = SimpleListComponent.builder("Hochschulen")
				.title("Hochschulen")
				.font(new Font("Arial", Font.PLAIN, 15))
				.build();
		slc_dozenten = SimpleListComponent.builder("Dozenten")
				.title("Dozenten")
				.font(new Font("Arial", Font.PLAIN, 15))
				.build();
		slc_lernfachListe = SimpleListComponent.builder("Lernfächer")
				.title("Lernfächer")
				.font(new Font("Arial", Font.PLAIN, 15))
				.build();
		slc_kapitelListe = SimpleListComponent.builder("Kapitel")
				.title("Kapitel")
				.font(new Font("Arial", Font.PLAIN, 15))
				.build();
		slc_fragenListe = SimpleListComponent.builder("Fragen")
				.title("Fragen")
				.font(new Font("Arial", Font.PLAIN, 15))
				.build();

		pnl_listen.add(slc_hochschule);
		pnl_listen.add(slc_dozenten);
		pnl_listen.add(slc_lernfachListe);
		pnl_listen.add(slc_kapitelListe);
		pnl_listen.add(slc_fragenListe);

		// Buttons für die Auswahllisten

		// Hochschule

		ButtonElement[] bte_hoch = new ButtonElement[]
			{ ButtonElement.builder("BTN_Neu")
					.buttonText("Neu")
					.type(ButtonElement.Type.BUTTON)
					.build(),
					ButtonElement.builder("BTN_Bearbeiten")
							.buttonText("Bearbeiten")
							.type(ButtonElement.Type.BUTTON)
							.build(),
					ButtonElement.builder("BTN_Löschen")
							.buttonText("Löschen")
							.type(ButtonElement.Type.BUTTON)
							.build() };

		scr_liste = new JScrollPane(pnl_listen, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Dozenten

		ButtonElement[] bte_doz = new ButtonElement[]
			{ ButtonElement.builder("BTN_Neu")
					.buttonText("Neu")
					.type(ButtonElement.Type.BUTTON)
					.build(),
					ButtonElement.builder("BTN_Bearbeiten")
							.buttonText("Bearbeiten")
							.type(ButtonElement.Type.BUTTON)
							.build(),
					ButtonElement.builder("BTN_Löschen")
							.buttonText("Löschen")
							.type(ButtonElement.Type.BUTTON)
							.build() };

		scr_liste = new JScrollPane(pnl_listen, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Lernfächer

		ButtonElement[] bte_lf = new ButtonElement[]
			{ ButtonElement.builder("BTN_Neu")
					.buttonText("Neu")
					.type(ButtonElement.Type.BUTTON)
					.build(),
					ButtonElement.builder("BTN_Bearbeiten")
							.buttonText("Bearbeiten")
							.type(ButtonElement.Type.BUTTON)
							.build(),
					ButtonElement.builder("BTN_Löschen")
							.buttonText("Löschen")
							.type(ButtonElement.Type.BUTTON)
							.build() };

		scr_liste = new JScrollPane(pnl_listen, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Kapitel
		ButtonElement[] bte_kap = new ButtonElement[]
			{ ButtonElement.builder("BTN_Neu")
					.buttonText("Neu")
					.type(ButtonElement.Type.BUTTON)
					.build(),
					ButtonElement.builder("BTN_Bearbeiten")
							.buttonText("Bearbeiten")
							.type(ButtonElement.Type.BUTTON)
							.build(),
					ButtonElement.builder("BTN_Löschen")
							.buttonText("Löschen")
							.type(ButtonElement.Type.BUTTON)
							.build() };

		scr_liste = new JScrollPane(pnl_listen, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Fragen
		ButtonElement[] bte_frag = new ButtonElement[]
			{ ButtonElement.builder("BTN_Neu")
					.buttonText("Neu")
					.type(ButtonElement.Type.BUTTON)
					.build(),
					ButtonElement.builder("BTN_Bearbeiten")
							.buttonText("Bearbeiten")
							.type(ButtonElement.Type.BUTTON)
							.build(),
					ButtonElement.builder("BTN_Löschen")
							.buttonText("Löschen")
							.type(ButtonElement.Type.BUTTON)
							.build() };

		scr_liste = new JScrollPane(pnl_listen, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// ButtonComponent
		ButtonComponent btncomp_hoch = ButtonComponent.builder("Hochschule")
				.buttonElements(bte_hoch)
				.embeddedComponent(slc_hochschule)
				.position(ButtonComponent.Position.SOUTH)
				.build();

		ButtonComponent btncomp_doz = ButtonComponent.builder("Hochschule")
				.buttonElements(bte_doz)
				.embeddedComponent(slc_dozenten)
				.position(ButtonComponent.Position.SOUTH)
				.build();

		ButtonComponent btncomp_lf = ButtonComponent.builder("Lernfach")
				.buttonElements(bte_lf)
				.embeddedComponent(slc_lernfachListe)
				.position(ButtonComponent.Position.SOUTH)
				.build();

		ButtonComponent btncomp_kap = ButtonComponent.builder("Lernfach")
				.buttonElements(bte_kap)
				.embeddedComponent(slc_kapitelListe)
				.position(ButtonComponent.Position.SOUTH)
				.build();

		ButtonComponent btncomp_frag = ButtonComponent.builder("Lernfach")
				.buttonElements(bte_frag)
				.embeddedComponent(slc_fragenListe)
				.position(ButtonComponent.Position.SOUTH)
				.build();

		pnl_listen.add(btncomp_hoch);
		pnl_listen.add(btncomp_doz);
		pnl_listen.add(btncomp_lf);
		pnl_listen.add(btncomp_kap);
		pnl_listen.add(btncomp_frag);

		// Fusszeile
		pnl_fuss = new JPanel();

		// Layouting
		this.add(pnl_oben,
				BorderLayout.NORTH);
		this.add(pnl_listen,
				BorderLayout.CENTER);
		this.add(pnl_fuss,
				BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void listenfüllen()
	{

	}

}
