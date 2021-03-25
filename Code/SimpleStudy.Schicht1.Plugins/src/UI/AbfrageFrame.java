package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controller.MainController;
import Models.Abfrage;
import Models.Frage;

public class AbfrageFrame extends JFrame implements ActionListener
{
	ArrayList<JRadioButton> radioButtonListe = new ArrayList<>();
	JPanel pnlFragen;
	JPanel pnlAntworten;
	JPanel pnlButtons;

	JLabel lblFragentext;

	ButtonGroup buttongroup = new ButtonGroup();

	JButton btnAuswerten;
	JButton btnAbrechen;
	JButton btnUeberspringen;
	Abfrage currentAbfrage;
	int beantworteteFragen = 0;

	public AbfrageFrame(Abfrage abfrage)
	{
		currentAbfrage = abfrage;
		setTitle("Abfrage");
		this.setSize(1600,
				900);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());

		pnlFragen = new JPanel();
		lblFragentext = new JLabel();
		pnlFragen.add(lblFragentext);

		pnlAntworten = new JPanel();
		pnlButtons = new JPanel();
		btnAuswerten = new JButton("Auswerten");
		btnUeberspringen = new JButton("Ueberspringen");
		btnAbrechen = new JButton("Abbrechen");
		btnAuswerten.addActionListener(this);
		btnUeberspringen.addActionListener(this);
		btnAbrechen.addActionListener(this);

		pnlButtons.add(btnAuswerten);
		pnlButtons.add(btnUeberspringen);
		pnlButtons.add(btnAbrechen);

		// Layouting
		this.add(pnlFragen,
				BorderLayout.NORTH);
		this.add(pnlAntworten,
				BorderLayout.CENTER);
		this.add(pnlButtons,
				BorderLayout.EAST);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		neueFrage(abfrage.getFragen()
				.get(beantworteteFragen));
	}

	public void neueFrage(Frage frage)
	{
		lblFragentext.setText(frage.getText());
		radioButtonListe = new ArrayList<>();
		pnlAntworten.setLayout(new GridLayout(frage.getAntworten()
				.size(), 2));

		for (int i = 0; i < frage.getAntworten()
				.size(); i++)
		{

			radioButtonListe.add(new JRadioButton());
			buttongroup.add(radioButtonListe.get(i));
			pnlAntworten.add(radioButtonListe.get(i));
			pnlAntworten.add(new JLabel(frage.getAntworten()
					.get(i)
					.getText()));
		}
		radioButtonListe.get(0)
				.setSelected(true);
		this.repaint();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAuswerten)
		{

			int antwortId = 0;
			for (int i = 0; i < radioButtonListe.size(); i++)
				if (radioButtonListe.get(i)
						.isSelected())
				{
					antwortId = i;
					break;
				}

			final Frage frage = currentAbfrage.getFragen()
					.get(beantworteteFragen);
			boolean richtig = false;
			if (frage.getAntworten()
					.get(antwortId)
					.isCorrect())
				richtig = true;

			currentAbfrage.getErgebnis()
					.add(frage.getId(),
							richtig);
			beantworteteFragen++;
			if (beantworteteFragen >= currentAbfrage.getFragen()
					.size())
			{
				MainController.abfrageAuswerten(currentAbfrage);

				int richtige = 0;
				int falsche = 0;

				for (var ergebnis : currentAbfrage.getErgebnis()
						.getErgebnis()
						.entrySet())
				{
					if (ergebnis.getValue())
					{
						richtige++;
					}
					else
					{
						falsche++;
					}
				}

				String testergebnis = "Das Ergebnis deiner heutigen Abfrage lautet: \n\r Richtige Antworten: " + richtige
						+ "\r\n Falsche Antworten: " + falsche;
				JOptionPane.showMessageDialog(null,
						testergebnis);

				dispose();
				return;
			}
			neueFrage(currentAbfrage.getFragen()
					.get(beantworteteFragen));
		}

	}
}
