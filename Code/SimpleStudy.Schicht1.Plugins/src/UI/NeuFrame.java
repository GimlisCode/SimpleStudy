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
	private final JLabel lbl_model;
	private JLabel lbl_1;
	private JLabel lbl_2;
	private JLabel lbl_3;
	private JLabel lbl_4;

	private JTextField tf_1;
	private JTextField tf_2;
	private JTextField tf_3;

	private JList lst_1;
	private JList lst_2;

	private JScrollPane sp1;
	private JScrollPane sp2;

	private JComboBox cb_1;

	private final JPanel pnl_kopf;
	private JPanel pnl_mitte;
	private final JPanel pnl_fuss;

	private final JButton btn_speichern;
	private final JButton btn_abbrechen;

	private final String model;

	private final HashMap<String, String> modelAttribute;

	public NeuFrame(String model, HashMap<String, String> hashMap)
	{
		this.model = model;
		modelAttribute = hashMap;
		setLayout(new BorderLayout());
		setTitle("Neu");

		pnl_kopf = new JPanel();
		lbl_model = new JLabel(model);
		pnl_kopf.add(lbl_model);

		// Hochschule
		if (model == Hochschule.class.getSimpleName())
		{

			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Dozent");

			tf_1 = new JTextField(modelAttribute.get(Hochschule.nameText));

			final ArrayList<PrettyHashMap> dozenten = MainController.getInstance()
					.getDozenten();
			lst_1 = new JList(dozenten.toArray());
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
				lst_1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst_1);
			pnl_mitte = new JPanel(new GridLayout(2, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(sp1);

		}

		// Dozent

		else if (model == Dozent.class.getSimpleName())

		{
			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Kurs");

			tf_1 = new JTextField(modelAttribute.get(Dozent.nameText));

			lst_1 = new JList(MainController.getInstance()
					.getLernfaecher()
					.toArray());

			final ArrayList<PrettyHashMap> faecher = MainController.getInstance()
					.getLernfaecher();
			lst_1 = new JList(faecher.toArray());
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
				lst_1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst_1);
			pnl_mitte = new JPanel(new GridLayout(2, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(sp1);

		}

		// Lernfaecher

		else if (model == Lernfach.class.getSimpleName())
		{
			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Semester");
			lbl_3 = new JLabel("Credits");
			lbl_4 = new JLabel("Kapitel");

			tf_1 = new JTextField(modelAttribute.get(Lernfach.nameText));
			tf_2 = new JTextField(modelAttribute.get(Lernfach.creditsText));
			tf_3 = new JTextField(modelAttribute.get(Lernfach.kapitelText));

			lst_1 = new JList(MainController.getInstance()
					.getKapitel()
					.toArray());

			final ArrayList<PrettyHashMap> kapitel = MainController.getInstance()
					.getKapitel();
			lst_1 = new JList(kapitel.toArray());
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
				lst_1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst_1);
			pnl_mitte = new JPanel(new GridLayout(4, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(tf_2);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(tf_3);
			pnl_mitte.add(lbl_4);
			pnl_mitte.add(sp1);

		}

		// Kapitel

		else if (model == Kapitel.class.getSimpleName())
		{
			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Nr.");
			lbl_3 = new JLabel("Fragen");

			tf_1 = new JTextField(modelAttribute.get(Kapitel.nameText));
			tf_2 = new JTextField(modelAttribute.get(Kapitel.nrText));

			lst_1 = new JList(MainController.getInstance()
					.getFragen()
					.toArray());

			final ArrayList<PrettyHashMap> fragen = MainController.getInstance()
					.getFragen();
			lst_1 = new JList(fragen.toArray());
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
				lst_1.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst_1);

			pnl_mitte = new JPanel(new GridLayout(3, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(tf_2);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(sp1);
		}

		// Fragen

		else if (model == Frage.class.getSimpleName())
		{

			lbl_1 = new JLabel("Text");
			lbl_2 = new JLabel("Fragentyp");
			lbl_3 = new JLabel("Antworten");

			tf_1 = new JTextField(modelAttribute.get(Frage.textText));

			final Integer[] typVarianten =
				{ 1, 2, 3 };
			lst_1 = new JList<Integer>(typVarianten);
			lst_2 = new JList(MainController.getInstance()
					.getAntworten()
					.toArray());

			final ArrayList<PrettyHashMap> antworten = MainController.getInstance()
					.getAntworten();
			lst_2 = new JList(antworten.toArray());
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
				lst_2.setSelectedIndices(selektierteEintraege.stream()
						.mapToInt(i -> i)
						.toArray());
			}

			sp1 = new JScrollPane(lst_1);
			sp2 = new JScrollPane(lst_2);
			pnl_mitte = new JPanel(new GridLayout(3, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(sp1);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(sp2);

		}

		this.add(pnl_kopf,
				BorderLayout.NORTH);
		this.add(pnl_mitte,
				BorderLayout.CENTER);

		pnl_fuss = new JPanel(new GridLayout(1, 1));
		btn_speichern = new JButton("Speichern");
		btn_speichern.addActionListener(this);
		btn_abbrechen = new JButton("Abbrechen");
		btn_abbrechen.addActionListener(this);
		pnl_fuss.add(btn_speichern);
		pnl_fuss.add(btn_abbrechen);

		this.add(pnl_fuss,
				BorderLayout.SOUTH);

		this.setSize(new Dimension(400, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btn_speichern)
		{
			if (model == Hochschule.class.getSimpleName())
			{
				if (lst_1.getSelectedValuesList()
						.isEmpty() || tf_1.getText() == "")
					JOptionPane.showMessageDialog(this,
							"Bitte füllen Sie alle Felder aus!");
				else
				{
					String dozenten = new String();
					modelAttribute.replace(Hochschule.nameText,
							tf_1.getText());
					for (final Object doz : lst_1.getSelectedValuesList())
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
						tf_1.getText());
				for (final Object kurs : lst_1.getSelectedValuesList())
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
						tf_1.getText());
				for (final Object kapitel : lst_1.getSelectedValuesList())
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
						tf_1.getText());
				for (final Object frage : lst_1.getSelectedValuesList())
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
						tf_1.getText());
				modelAttribute.replace(Frage.typText,
						lst_1.getSelectedValue()
								.toString());
				for (final Object antwort : lst_2.getSelectedValuesList())
					antworten += ((PrettyHashMap) antwort).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				modelAttribute.replace(Frage.antwortenText,
						antworten);
				MainController.getInstance()
						.createFrage(modelAttribute);
				dispose();

			}

		}

		else if (e.getSource() == btn_abbrechen)
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
		// TODO Auto-generated method stub

	}

}
