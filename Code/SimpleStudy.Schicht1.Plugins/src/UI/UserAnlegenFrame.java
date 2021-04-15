package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.MainController;
import Models.Student;

public class UserAnlegenFrame extends JFrame implements ActionListener

{

	JPanel pn_eingaben = new JPanel();
	JPanel pn_button = new JPanel();
	JLabel lbl_benutzer;
	JTextField tf_benutzer;
	JButton btn_anlegen;

	public UserAnlegenFrame()
	{
		setTitle("Neuer Student*in");

		setLayout(new BorderLayout());

		pn_eingaben.setLayout(new GridLayout(1, 1));
		tf_benutzer = new JTextField();
		lbl_benutzer = new JLabel("Name des Studierenden:");
		pn_eingaben.add(lbl_benutzer);
		pn_eingaben.add(tf_benutzer);
		this.add(pn_eingaben,
				BorderLayout.CENTER);

		btn_anlegen = new JButton("Anlegen");
		btn_anlegen.addActionListener(this);
		pn_button.add(btn_anlegen);
		this.add(pn_button,
				BorderLayout.SOUTH);

		setVisible(true);
		this.setSize(300,
				150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (tf_benutzer.getText()
				.isEmpty())
		{
			final JOptionPane fehlermeldung = new JOptionPane();
			fehlermeldung.showMessageDialog(null,
					"Sie müssen einen Namen eingeben",
					"Fehler",
					JOptionPane.WARNING_MESSAGE);
		}

		else
		{
			final var benutzer = tf_benutzer.getText();

			final HashMap<String, String> student = new HashMap<>();
			student.put(Student.nameText,
					benutzer);
			MainController.getInstance()
					.updateStudent(student);
			dispose();
		}

	}

}
