package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserAnlegenFrame extends JFrame implements ActionListener

{

	JPanel pn_eingaben = new JPanel();
	JPanel pn_button = new JPanel();
	JLabel lbl_benutzer;
	JTextField tf_benutzer;
	JButton btn_anlegen;

	public UserAnlegenFrame()
	{

		this.setLayout(new BorderLayout());

		pn_eingaben.setLayout(new GridLayout(1, 1));
		tf_benutzer = new JTextField();
		lbl_benutzer = new JLabel("Name des Studenten:");
		pn_eingaben.add(lbl_benutzer);
		pn_eingaben.add(tf_benutzer);
		this.add(pn_eingaben,
				BorderLayout.CENTER);

		btn_anlegen = new JButton("Anlegen");
		btn_anlegen.addActionListener(this);
		pn_button.add(btn_anlegen);
		this.add(pn_button,
				BorderLayout.SOUTH);

		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		final var benutzer = tf_benutzer.getText();

	}

}
