package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Student;

public class StatistikFrame extends JFrame implements UiBeobachter, ActionListener
{
	Student currentUser = null;

	JPanel pnlKopf;
	JPanel pnlStatistik;
	JPanel pnlFuss;
	JLabel lblText;

	JButton btnSchliessen;

	public StatistikFrame()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		currentUser = MainController.getInstance()
				.getCurrentUser();
		setTitle("Simple Study: Statistik");
		this.setSize(1600,
				900);

		setLayout(new BorderLayout());
		pnlKopf = new JPanel();
		pnlKopf.setLayout(new BorderLayout());
		btnSchliessen = new JButton("Statistik schliessen");
		btnSchliessen.addActionListener(this);
		lblText = new JLabel("Statistik von " + currentUser.getName());
		lblText.setFont(new Font("Arial", Font.BOLD, 20));
		pnlKopf.add(lblText,
				BorderLayout.CENTER);
		pnlKopf.add(btnSchliessen,
				BorderLayout.EAST);

		pnlStatistik = new JPanel();
		pnlFuss = new JPanel();

		this.add(pnlKopf,
				BorderLayout.NORTH);
		this.add(pnlStatistik,
				BorderLayout.CENTER);
		this.add(pnlFuss,
				BorderLayout.SOUTH);
		showStatistik();

		MainController.getInstance()
				.registriere(this);

		setVisible(true);
	}

	private void showStatistik()
	{
		final var userStatistik = currentUser.getStatistik()
				.getStatistik();
		int counter = 0;
		ArrayList<JLabel> eintraege = new ArrayList<>();
		for (final var statistikEintrag : userStatistik.entrySet())
			try
			{
				final var fragenText = MainController.getFrage(statistikEintrag.getKey())
						.getText();
				counter++;
				eintraege.add(new JLabel("Frage (" + statistikEintrag.getKey() + "): " + fragenText));

				eintraege.add(new JLabel("Richtig beantwortet: " + Integer.toString(statistikEintrag.getValue()
						.getRichtig())));

				eintraege.add(new JLabel("Falsch beantwortet: " + Integer.toString(statistikEintrag.getValue()
						.getFalsch())));
			}
			catch (final Exception e)
			{

			}
		pnlStatistik.setLayout(new GridLayout(counter, 3));
		for (JLabel label : eintraege)
		{
			pnlStatistik.add(label);
		}

		this.repaint();
		revalidate();
	}

	@Override
	public void aktualisiere()
	{
		showStatistik();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		dispose();
	}
}
