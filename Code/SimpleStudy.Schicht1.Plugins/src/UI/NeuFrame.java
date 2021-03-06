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
import Models.Dozent;
import Models.Hochschule;
import Models.Lernfach;
import Renderer.PrettyHashMap;

public class NeuFrame extends JFrame implements ActionListener
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

			tf_1 = new JTextField();

			lst_1 = new JList(MainController.getDozenten()
					.toArray());

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

			tf_1 = new JTextField();

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

			tf_1 = new JTextField();
			tf_2 = new JTextField();
			tf_3 = new JTextField();

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
					dozenten += ((PrettyHashMap) doz).get(Models.Entity.idText) + ";";
				}

				modelAttribute.replace(Hochschule.dozentenText,
						dozenten);
				MainController.createHochschule(modelAttribute);
			}

			if (model == Dozent.class.getSimpleName())
			{
				String kurse = new String();
				modelAttribute.replace(Dozent.nameText,
						tf_1.getText());
				for (Object kurs : lst_1.getSelectedValuesList())
				{
					kurse += ((PrettyHashMap) kurs).get(Models.Entity.idText) + ";";
				}

				modelAttribute.replace(Dozent.kurseText,
						kurse);
				MainController.createDozent(modelAttribute);

			}

			this.dispose();
		}

		else if (e.getSource() == btn_abbrechen)
		{
			this.dispose();
		}

	}

}
