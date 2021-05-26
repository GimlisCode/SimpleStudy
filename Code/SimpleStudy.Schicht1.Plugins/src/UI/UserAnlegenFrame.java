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

	JPanel pnlEingaben = new JPanel();
	JPanel pnlButton = new JPanel();
	JLabel lblBenutzer;
	JTextField tfBenutzer;
	JButton btnAnlegen;

	public UserAnlegenFrame()
	{
		setTitle("Neuer Student*in");

		setLayout(new BorderLayout());

		pnlEingaben.setLayout(new GridLayout(1, 1));
		tfBenutzer = new JTextField();
		lblBenutzer = new JLabel("Name des Studierenden:");
		pnlEingaben.add(lblBenutzer);
		pnlEingaben.add(tfBenutzer);
		this.add(pnlEingaben,
				BorderLayout.CENTER);

		btnAnlegen = new JButton("Anlegen");
		btnAnlegen.addActionListener(this);
		pnlButton.add(btnAnlegen);
		this.add(pnlButton,
				BorderLayout.SOUTH);

		setVisible(true);
		this.setSize(300,
				150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (tfBenutzer.getText()
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
			final var benutzer = tfBenutzer.getText();

			final HashMap<String, String> student = new HashMap<>();
			student.put(Student.nameText,
					benutzer);
			MainController.getInstance()
					.updateStudent(student);
			dispose();
		}

	}

}
