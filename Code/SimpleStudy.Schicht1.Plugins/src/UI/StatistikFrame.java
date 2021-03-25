package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.MainController;
import Models.Student;

public class StatistikFrame extends JFrame
{
	Student currentUser = null;
	JPanel statistikPanel = new JPanel();

	public StatistikFrame()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		currentUser = MainController.getCurrentUser();
		setTitle("Simple Study: Statistik von Nutzer: " + currentUser.getName());
		this.setSize(1600,
				900);

		setLayout(new BorderLayout());
		this.add(statistikPanel,
				BorderLayout.CENTER);
		showStatistik();

//		MainController.getInstance() /TODO: kann man mal noch machen, dass die Statistik aktualisiert wird
//		.registriere(this);

		setVisible(true);
	}

	private void showStatistik()
	{
		final var userStatistik = currentUser.getStatistik()
				.getStatistik();
		statistikPanel.setLayout(new GridLayout(userStatistik.size(), 1));
		for (final var statistikEintrag : userStatistik.entrySet())
			statistikPanel.add(new JLabel("FradenID: " + statistikEintrag.getKey() + "; " + statistikEintrag.getValue()
					.toString()));

		this.repaint();
		revalidate();
	}
}
