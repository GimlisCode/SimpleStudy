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
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Dozent;
import Models.Frage;
import Models.Hochschule;
import Models.Kapitel;
import Models.Lernfach;
import Renderer.PrettyHashMap;

public class NeuFrame extends JFrame implements ActionListener, UiBeobachter
{
	private JLabel lbl_model;
	private JLabel lbl_1;
	private JLabel lbl_2;
	private JLabel lbl_3;
	private JLabel lbl_4;

	private JTextField tf_1;
	private JTextField tf_2;
	private JTextField tf_3;

	private JList lst_1;
	private JList lst_2;

	private JComboBox cb_1;

	private JPanel pnl_kopf;
	private JPanel pnl_mitte;
	private JPanel pnl_fuss;

	private JButton btn_speichern;
	private JButton btn_abbrechen;

	private String model;

	private HashMap<String, String> modelAttribute;

	public NeuFrame(String model, HashMap<String, String> hashMap)
	{
		this.model = model;
		this.modelAttribute = hashMap;
		this.setLayout(new BorderLayout());
		this.setTitle("Neu");

		pnl_kopf = new JPanel();
		lbl_model = new JLabel(model);
		pnl_kopf.add(lbl_model);

		var labellist = new ArrayList<>();
		var textfeld = new ArrayList<>();

		for (var entrySet : hashMap.entrySet())
		{
			labellist.add(new JLabel(entrySet.getKey()));
		}

		// Hochschule
		if (model == Hochschule.class.getSimpleName())
		{

			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Dozent");

			tf_1 = new JTextField(modelAttribute.get(Hochschule.nameText));

			lst_1 = new JList(MainController.getDozenten()
					.toArray());
			// lst_1.setSelectedIndex(0);
			// lst_1.setSelectedIndex(modelAttribute.get(Hochschule.dozentenText));

			pnl_mitte = new JPanel(new GridLayout(2, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(lst_1);

		}

		// Dozent

		else if (model == Dozent.class.getSimpleName())
		{
			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Kurs");

			tf_1 = new JTextField(modelAttribute.get(Dozent.nameText));

			lst_1 = new JList(MainController.getLernfaecher()
					.toArray());

			pnl_mitte = new JPanel(new GridLayout(2, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(lst_1);

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

			lst_1 = new JList(MainController.getKapitel()
					.toArray());

			pnl_mitte = new JPanel(new GridLayout(4, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(tf_2);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(tf_3);
			pnl_mitte.add(lbl_4);
			pnl_mitte.add(lst_1);

		}

		// Kapitel

		else if (model == Kapitel.class.getSimpleName())
		{
			lbl_1 = new JLabel("Name");
			lbl_2 = new JLabel("Nr.");
			lbl_3 = new JLabel("Fragen");

			tf_1 = new JTextField(modelAttribute.get(Kapitel.nameText));
			tf_2 = new JTextField(modelAttribute.get(Kapitel.nrText));

			lst_1 = new JList(MainController.getFragen()
					.toArray());

			pnl_mitte = new JPanel(new GridLayout(3, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(tf_2);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(lst_1);
		}

		// Fragen

		else if (model == Frage.class.getSimpleName())
		{

			lbl_1 = new JLabel("Text");
			lbl_2 = new JLabel("Fragentyp");
			lbl_3 = new JLabel("Antworten");

			tf_1 = new JTextField(modelAttribute.get(Frage.textText));
			Integer[] typen =
				{ 1, 2, 3 };
			lst_1 = new JList<Integer>(typen);
			lst_2 = new JList(MainController.getAntworten()
					.toArray());

			pnl_mitte = new JPanel(new GridLayout(3, 2));
			pnl_mitte.add(lbl_1);
			pnl_mitte.add(tf_1);
			pnl_mitte.add(lbl_2);
			pnl_mitte.add(lst_1);
			pnl_mitte.add(lbl_3);
			pnl_mitte.add(lst_2);

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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btn_speichern)
		{
			if (model == Hochschule.class.getSimpleName())
			{
				String dozenten = new String();
				modelAttribute.replace(Hochschule.nameText,
						tf_1.getText());
				for (Object doz : lst_1.getSelectedValuesList())
				{
					dozenten += ((PrettyHashMap) doz).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				}

				modelAttribute.replace(Hochschule.dozentenText,
						dozenten);
				MainController.createHochschule(modelAttribute);
			}

			else if (model == Dozent.class.getSimpleName())
			{
				String kurse = new String();
				modelAttribute.replace(Dozent.nameText,
						tf_1.getText());
				for (Object kurs : lst_1.getSelectedValuesList())
				{
					kurse += ((PrettyHashMap) kurs).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				}

				modelAttribute.replace(Dozent.kurseText,
						kurse);
				MainController.createDozent(modelAttribute);

			}

			else if (model == Lernfach.class.getSimpleName())
			{
				String kapitels = new String();
				modelAttribute.replace(Lernfach.nameText,
						tf_1.getText());
				for (Object kapitel : lst_1.getSelectedValuesList())
				{
					kapitels += ((PrettyHashMap) kapitel).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				}
				modelAttribute.replace(Lernfach.kapitelText,
						kapitels);
				MainController.createLernfach(modelAttribute);
			}

			else if (model == Kapitel.class.getSimpleName())
			{
				String fragen = new String();
				modelAttribute.replace(Kapitel.nameText,
						tf_1.getText());
				for (Object frage : lst_1.getSelectedValuesList())
				{
					fragen += ((PrettyHashMap) frage).getNormalHashMap()
							.get(Models.Entity.idText) + ";";
				}
				modelAttribute.replace(Kapitel.fragenText,
						fragen);
				MainController.createKapitel(modelAttribute);
			}

			else if (model == Frage.class.getSimpleName())
			{
				String antworten = new String();
				modelAttribute.replace(Frage.textText,
						tf_1.getText());
				modelAttribute.replace(Frage.typText,
						lst_1.getSelectedValue()
								.toString());
				for (Object antwort : lst_2.getSelectedValuesList())
				{
					antworten += ((PrettyHashMap) antwort).getNormalHashMap()
							.get(Models.Entity.idText) + ";";

				}
				modelAttribute.replace(Frage.antwortenText,
						antworten);
				MainController.createFrage(modelAttribute);

			}

			this.dispose();
		}

		else if (e.getSource() == btn_abbrechen)
		{
			this.dispose();
		}

	}

	@Override
	public void aktualisiere()
	{
		// TODO Auto-generated method stub

	}

}
