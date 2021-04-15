package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.MainController;
import Controller.UiBeobachter;
import Models.Entity;
import Renderer.PrettyHashMap;

public class LoginFrame extends JFrame implements ActionListener, UiBeobachter
{

	private final JPanel oben = new JPanel();
	private final JPanel intro = new JPanel();
	private final JPanel unten = new JPanel();

	private final JLabel lbl_intro;
	private final JLabel lbl_benutzer;

	private final JComboBox cb_benutzer;

	private final JButton login;
	private final JButton neu;
	private final JButton delete;

	public LoginFrame()
	{
		MainController.getInstance()
				.registriere(this);
		this.setTitle("Login");

		setLayout(new BorderLayout());

		lbl_intro = new JLabel("Willkommen bei SimpleStudy! \n \r Bitte wählen Sie Ihren Benutzer aus.");
		intro.add(lbl_intro);

		oben.setLayout(new GridLayout(1, 1));

		lbl_benutzer = new JLabel("Benutzer");
		cb_benutzer = new JComboBox(MainController.getInstance().getStudenten()
				.toArray());

		oben.add(lbl_benutzer);
		oben.add(cb_benutzer);

		login = new JButton("Login");
		neu = new JButton("Neu");
		delete = new JButton("Loeschen");
		login.addActionListener(this);
		neu.addActionListener(this);
		delete.addActionListener(this);
		unten.add(login);
		unten.add(neu);
		unten.add(delete);

		this.add(intro,
				BorderLayout.NORTH);
		this.add(oben,
				BorderLayout.CENTER);
		this.add(unten,
				BorderLayout.SOUTH);

		this.setSize(400,
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
			UserAnlegenFrame aFrame = new UserAnlegenFrame();
		}

		else if (e.getSource()
				.equals(login))
		{
			MainController.getInstance().setCurrentUser((PrettyHashMap) cb_benutzer.getSelectedItem());
			MainFrame mf = new MainFrame();
			this.dispose();
		}

		else if (e.getSource()
				.equals(delete))
		{
			if (cb_benutzer.getSelectedItem() == null)
			{
				JOptionPane.showMessageDialog(this,
						"Bitte wählen Sie eine*n Student*in aus!");
			}
			else
			{
				MainController.getInstance().deleteStudent(((PrettyHashMap) cb_benutzer.getSelectedItem()).visible.get(Entity.idText));
			}

		}

	}

	@Override
	public void aktualisiere()
	{
		cb_benutzer.setModel(new DefaultComboBoxModel(MainController.getInstance().getStudenten()
				.toArray()));
	}

}
