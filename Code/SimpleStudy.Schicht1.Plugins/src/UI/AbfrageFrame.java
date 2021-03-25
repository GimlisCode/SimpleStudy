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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Models.Abfrage;
import Models.Frage;

public class AbfrageFrame extends JFrame implements ActionListener
{
	ArrayList<Frage> fragen;

	JPanel pnlFragen;
	JPanel pnlAntworten;
	JPanel pnlButtons;

	JLabel lblFragentext;

	ButtonGroup buttongroup;

	JButton btnAuswerten;
	JButton btnAbrechen;
	JButton btnUeberspringen;

	public AbfrageFrame(Abfrage abfrage)
	{

		this.fragen = abfrage.getFragen();

		this.setTitle("Abfrage");
		this.setSize(1600,
				900);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		pnlFragen = new JPanel();
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

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void neueFrage(Frage frage)
	{
		lblFragentext.setText(frage.getText());
		ArrayList<JRadioButton> radioButtonListe = new ArrayList<>();
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

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
