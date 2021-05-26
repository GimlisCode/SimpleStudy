package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Dozent;
import Models.Entity;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Renderer.PrettyHashMap;

public class NeuFrame extends JFrame implements ActionListener, UiBeobachter
{
	private final JLabel lblModel;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;

	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;

	private JList lst1;
	private JList lst2;

	private JScrollPane sp1;
	private JScrollPane sp2;

	private JComboBox cb1;

	private final JPanel pnlKopf;
	private JPanel pnlMitte;
	private final JPanel pnlFuss;

	private final JButton btnSpeichern;
	private final JButton btnAbbrechen;

	private final String model;

	private final HashMap<String, String> modelAttribute;

	public NeuFrame(String model, HashMap<String, String> hashMap)
	{
		this.model = model;
		modelAttribute = hashMap;
		setLayout(new BorderLayout());
		setTitle("Neu");

		pnlKopf = new JPanel();
		lblModel = new JLabel(model);
		pnlKopf.add(lblModel);

		// Hochschule
		if (model == Hochschule.class.getSimpleName())
		{

			lbl1 = new JLabel("Name");
			lbl2 = new JLabel("Dozent");

			tf1 = new JTextField(modelAttribute.get(Hochschule.nameText));

			final ArrayList<PrettyHashMap> dozenten = MainController.getInstance()
					.getDozenten();
			lst1 = new JList(dozenten.toArray());
			String[] selektierteDozenten;
			final ArrayList<Integer> selektierteEintraege = new ArrayList<>();
			if (modelAttribute.get(Hochschule.dozentenText) != null)
			{
				selektierteDozenten = modelAttribute.get(Hochschule.dozentenText)
						.split(";");
				for (int i = 0; i < selektierteDozenten.length; i++)
					for (int j = 0; j < dozenten.size(); j++)
						if (dozenten.get(j)
								.getNormalHashMap()
								.get(Entity.idText)
								.equals(selektierteDozenten[i]))
							selektierteEintraege.add(j);
				lst1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst1);
			pnlMitte = new JPanel(new GridLayout(2, 2));
			pnlMitte.add(lbl1);
			pnlMitte.add(tf1);
			pnlMitte.add(lbl2);
			pnlMitte.add(sp1);

		}

		// Dozent

		else if (model == Dozent.class.getSimpleName())

		{
			lbl1 = new JLabel("Name");
			lbl2 = new JLabel("Kurs");

			tf1 = new JTextField(modelAttribute.get(Dozent.nameText));

			lst1 = new JList(MainController.getInstance()
					.getLernfaecher()
					.toArray());

			final ArrayList<PrettyHashMap> faecher = MainController.getInstance()
					.getLernfaecher();
			lst1 = new JList(faecher.toArray());
			String[] selektierteFaecher;
			final ArrayList<Integer> selektierteEintraege = new ArrayList<>();
			if (modelAttribute.get(Dozent.kurseText) != null)
			{
				selektierteFaecher = modelAttribute.get(Dozent.kurseText)
						.split(";");
				for (int i = 0; i < selektierteFaecher.length; i++)
					for (int j = 0; j < faecher.size(); j++)
						if (faecher.get(j)
								.getNormalHashMap()
								.get(Entity.idText)
								.equals(selektierteFaecher[i]))
							selektierteEintraege.add(j);
				lst1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst1);
			pnlMitte = new JPanel(new GridLayout(2, 2));
			pnlMitte.add(lbl1);
			pnlMitte.add(tf1);
			pnlMitte.add(lbl2);
			pnlMitte.add(sp1);

		}

		// Lernfaecher

		else if (model == Lernfach.class.getSimpleName())
		{
			lbl1 = new JLabel("Name");
			lbl2 = new JLabel("Semester");
			lbl3 = new JLabel("Credits");
			lbl4 = new JLabel("Kapitel");

			tf1 = new JTextField(modelAttribute.get(Lernfach.nameText));
			tf2 = new JTextField(modelAttribute.get(Lernfach.creditsText));
			tf3 = new JTextField(modelAttribute.get(Lernfach.kapitelText));

			lst1 = new JList(MainController.getInstance()
					.getKapitel()
					.toArray());

			final ArrayList<PrettyHashMap> kapitel = MainController.getInstance()
					.getKapitel();
			lst1 = new JList(kapitel.toArray());
			String[] selektierteKapitel;
			final ArrayList<Integer> selektierteEintraege = new ArrayList<>();
			if (modelAttribute.get(Lernfach.kapitelText) != null)
			{
				selektierteKapitel = modelAttribute.get(Lernfach.kapitelText)
						.split(";");
				for (int i = 0; i < selektierteKapitel.length; i++)
					for (int j = 0; j < kapitel.size(); j++)
						if (kapitel.get(j)
								.getNormalHashMap()
								.get(Entity.idText)
								.equals(selektierteKapitel[i]))
							selektierteEintraege.add(j);
				lst1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst1);
			pnlMitte = new JPanel(new GridLayout(4, 2));
			pnlMitte.add(lbl1);
			pnlMitte.add(tf1);
			pnlMitte.add(lbl2);
			pnlMitte.add(tf2);
			pnlMitte.add(lbl3);
			pnlMitte.add(tf3);
			pnlMitte.add(lbl4);
			pnlMitte.add(sp1);

		}

		// Kapitel

		else if (model == Kapitel.class.getSimpleName())
		{
			lbl1 = new JLabel("Name");
			lbl2 = new JLabel("Nr.");
			lbl3 = new JLabel("Fragen");

			tf1 = new JTextField(modelAttribute.get(Kapitel.nameText));
			tf2 = new JTextField(modelAttribute.get(Kapitel.nrText));

			lst1 = new JList(MainController.getInstance()
					.getFragen()
					.toArray());

			final ArrayList<PrettyHashMap> fragen = MainController.getInstance()
					.getFragen();
			lst1 = new JList(fragen.toArray());
			String[] selektierteFragen;
			final ArrayList<Integer> selektierteEintraege = new ArrayList<>();
			if (modelAttribute.get(Kapitel.fragenText) != null)
			{
				selektierteFragen = modelAttribute.get(Kapitel.fragenText)
						.split(";");
				for (int i = 0; i < selektierteFragen.length; i++)
					for (int j = 0; j < fragen.size(); j++)
						if (fragen.get(j)
								.getNormalHashMap()
								.get(Entity.idText)
								.equals(selektierteFragen[i]))
							selektierteEintraege.add(j);
				lst1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst1);

			pnlMitte = new JPanel(new GridLayout(3, 2));
			pnlMitte.add(lbl1);
			pnlMitte.add(tf1);
			pnlMitte.add(lbl2);
			pnlMitte.add(tf2);
			pnlMitte.add(lbl3);
			pnlMitte.add(sp1);
		}

		// Fragen

		else if (model == Frage.class.getSimpleName())
		{

			lbl1 = new JLabel("Text");
			lbl2 = new JLabel("Fragentyp");
			lbl3 = new JLabel("Antworten");

			tf1 = new JTextField(modelAttribute.get(Frage.textText));

			final Integer[] typVarianten =
				{ 1, 2, 3 };
			lst1 = new JList<Integer>(typVarianten);
			lst2 = new JList(MainController.getInstance()
					.getAntworten()
					.toArray());

			final ArrayList<PrettyHashMap> antworten = MainController.getInstance()
					.getAntworten();
			lst2 = new JList(antworten.toArray());
			String[] selektierteAntworten;
			final ArrayList<Integer> selektierteEintraege = new ArrayList<>();
			if (modelAttribute.get(Frage.antwortenText) != null)
			{
				selektierteAntworten = modelAttribute.get(Frage.antwortenText)
						.split(";");
				for (int i = 0; i < selektierteAntworten.length; i++)
					for (int j = 0; j < antworten.size(); j++)
						if (antworten.get(j)
								.getNormalHashMap()
								.get(Entity.idText)
								.equals(selektierteAntworten[i]))
							selektierteEintraege.add(j);
				lst2.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst1);
			sp2 = new JScrollPane(lst2);
			pnlMitte = new JPanel(new GridLayout(3, 2));
			pnlMitte.add(lbl1);
			pnlMitte.add(tf1);
			pnlMitte.add(lbl2);
			pnlMitte.add(sp1);
			pnlMitte.add(lbl3);
			pnlMitte.add(sp2);

		}

		this.add(pnlKopf,
				BorderLayout.NORTH);
		this.add(pnlMitte,
				BorderLayout.CENTER);

		pnlFuss = new JPanel(new GridLayout(1, 1));
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(this);
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(this);
		pnlFuss.add(btnSpeichern);
		pnlFuss.add(btnAbbrechen);

		this.add(pnlFuss,
				BorderLayout.SOUTH);

		this.setSize(new Dimension(400, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSpeichern)
		{
			if (model == Hochschule.class.getSimpleName())
			{
				if (lst1.getSelectedValuesList()
						.isEmpty() || tf1.getText() == "")
					JOptionPane.showMessageDialog(this,
							"Bitte füllen Sie alle Felder aus!");
				else
				{
					String dozenten = new String();
					modelAttribute.replace(Hochschule.nameText,
							tf1.getText());
					for (final Object doz : lst1.getSelectedValuesList())
						dozenten += ((PrettyHashMap) doz).getNormalHashMap()
								.get(Models.Entity.idText) + ";";

					modelAttribute.replace(Hochschule.dozentenText,
							dozenten);
					MainController.getInstance()
							.createHochschule(modelAttribute);
					dispose();
				}

			}

			else if (model == Dozent.class.getSimpleName())
			{
				String kurse = new String();
				modelAttribute.replace(Dozent.nameText,
						tf1.getText());
				for (final Object kurs : lst1.getSelectedValuesList())
					kurse += ((PrettyHashMap) kurs).getNormalHashMap()
							.get(Models.Entity.idText) + ";";

				modelAttribute.replace(Dozent.kurseText,
						kurse);
				MainController.getInstance()
						.createDozent(modelAttribute);
				dispose();

			}

			else if (model == Lernfach.class.getSimpleName())
			{
				String kapitels = new String();
				modelAttribute.replace(Lernfach.nameText,
						tf1.getText());
				for (final Object kapitel : lst1.getSelectedValuesList())
					kapitels += ((PrettyHashMap) kapitel).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				modelAttribute.replace(Lernfach.kapitelText,
						kapitels);
				MainController.getInstance()
						.createLernfach(modelAttribute);
				dispose();
			}

			else if (model == Kapitel.class.getSimpleName())
			{
				String fragen = new String();
				modelAttribute.replace(Kapitel.nameText,
						tf1.getText());
				for (final Object frage : lst1.getSelectedValuesList())
					fragen += ((PrettyHashMap) frage).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				modelAttribute.replace(Kapitel.fragenText,
						fragen);
				MainController.getInstance()
						.createKapitel(modelAttribute);
				dispose();
			}

			else if (model == Frage.class.getSimpleName())
			{
				String antworten = new String();
				modelAttribute.replace(Frage.textText,
						tf1.getText());
				modelAttribute.replace(Frage.typText,
						lst1.getSelectedValue()
								.toString());
				for (final Object antwort : lst2.getSelectedValuesList())
					antworten += ((PrettyHashMap) antwort).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				modelAttribute.replace(Frage.antwortenText,
						antworten);
				MainController.getInstance()
						.createFrage(modelAttribute);
				dispose();

			}

		}

		else if (e.getSource() == btnAbbrechen)
		{
			final int ret = JOptionPane.showConfirmDialog(this,
					"Durch das Abbrechen des Vorgangs, werden die Änderungen nicht gespeichert. Sicher?");
			if (ret == JOptionPane.YES_OPTION)
				dispose();
		}

	}

	@Override
	public void aktualisiere()
	{

	}

}
