package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame
{

	private JPanel oben = new JPanel();
	private JPanel intro = new JPanel();
	private JPanel unten = new JPanel();

	private JLabel lbl_intro;
	private JLabel lbl_benutzer;

	private JTextField tf_benutzer;

	private JButton login;

	public LoginFrame()
	{

		this.setLayout(new BorderLayout());

		lbl_intro = new JLabel("Willkommen bei SimpleStudy! \n Bitte geben Sie ihre ID ein.");
		intro.add(lbl_intro);

		oben.setLayout(new GridLayout(1, 1));

		lbl_benutzer = new JLabel("Benutzer-ID");
		tf_benutzer = new JTextField();

		oben.add(lbl_benutzer);
		oben.add(tf_benutzer);

		login = new JButton("Login");
		unten.add(login);

		this.add(intro, BorderLayout.NORTH);
		this.add(oben, BorderLayout.CENTER);
		this.add(unten, BorderLayout.SOUTH);

		this.setSize(350, 200);
		this.setVisible(true);

	}

	public String getBenutzer()
	{
		return tf_benutzer.getText();
	}

}
