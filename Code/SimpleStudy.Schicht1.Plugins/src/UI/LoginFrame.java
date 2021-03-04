package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.MainController;

public class LoginFrame extends JFrame implements ActionListener
{

	private final JPanel oben = new JPanel();
	private final JPanel intro = new JPanel();
	private final JPanel unten = new JPanel();

	private final JLabel lbl_intro;
	private final JLabel lbl_benutzer;

	private final JComboBox cb_benutzer;

	private final JButton login;
	private final JButton neu;

	public LoginFrame()
	{
		this.setTitle("Login");

		setLayout(new BorderLayout());

		lbl_intro = new JLabel("Willkommen bei SimpleStudy! \n Bitte geben Sie ihre ID ein.");
		intro.add(lbl_intro);

		oben.setLayout(new GridLayout(1, 1));

		lbl_benutzer = new JLabel("Benutzer-ID");
		cb_benutzer = new JComboBox(MainController.getStudenten()
				.toArray());

		oben.add(lbl_benutzer);
		oben.add(cb_benutzer);

		login = new JButton("Login");
		neu = new JButton("Neu");
		login.addActionListener(this);
		neu.addActionListener(this);
		unten.add(login);
		unten.add(neu);

		this.add(intro,
				BorderLayout.NORTH);
		this.add(oben,
				BorderLayout.CENTER);
		this.add(unten,
				BorderLayout.SOUTH);

		this.setSize(350,
				200);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		final var benutzer = cb_benutzer.getSelectedItem();

		if (e.getSource()
				.equals(neu))
		{
			this.dispose();
			UserAnlegenFrame aFrame = new UserAnlegenFrame();
		}

	}

}
