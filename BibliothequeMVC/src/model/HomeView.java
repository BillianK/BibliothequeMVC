package model;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import control.AuthentificationCtl;
import control.ConsulterCtl;
import control.EmprunterCtl;
import control.RetourCtl;

public class HomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7837590952337370766L;
	CardLayout cardLayout = new CardLayout();
	JPanel content = new JPanel();

	static JButton btnLog;
	static JButton btnEmprunter;
	static JButton btnRetour;
	static JButton btnConsulter;
	private JLabel lblDate;

	public HomeView() {
		setBackground(Color.DARK_GRAY);
		this.setTitle("Bibliothèque");
		this.setSize(830, 593);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel boutonPane = new JPanel();

		btnEmprunter = new JButton("Emprunter");
		btnEmprunter.addActionListener(new BtnListener());

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new BtnListener());

		content.setLayout(cardLayout);

//		lblBibliothequeDesMarmousots = new JLabel("BIBLIOTHEQUE DES MARMOUSOTS");
//		lblBibliothequeDesMarmousots.setHorizontalAlignment(SwingConstants.CENTER);
//		lblBibliothequeDesMarmousots.setFont(new Font("Consolas", Font.BOLD, 22));
//		getContentPane().add(lblBibliothequeDesMarmousots, BorderLayout.CENTER);

		this.getContentPane().add(boutonPane, BorderLayout.WEST);
		boutonPane.setLayout(new GridLayout(4, 1, 0, 0));

		btnConsulter = new JButton("Consulter");
		boutonPane.add(btnConsulter);
		btnConsulter.addActionListener(new BtnListener());
		boutonPane.add(btnEmprunter);
		boutonPane.add(btnRetour);

		this.getContentPane().add(content, BorderLayout.CENTER);


		content.add(AuthentificationCtl.getView(), "authentification");
		

		btnEmprunter.setEnabled(AuthentificationCtl.getToken());
		btnRetour.setEnabled(AuthentificationCtl.getToken());

		btnLog = new JButton("Authentification");
		boutonPane.add(btnLog);

		lblDate = new JLabel(new Date(System.currentTimeMillis()).toString());
		getContentPane().add(lblDate, BorderLayout.NORTH);
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Consolas", Font.BOLD, 22));

		btnLog.addActionListener(new BtnListener());

		this.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new HomeView();
	}

	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRetour) {

				content.add(RetourCtl.getView(), "retour");
				cardLayout.show(content, "retour");

			} else if (e.getSource() == btnLog) {

				cardLayout.show(content, "authentification");

			} else if (e.getSource() == btnEmprunter) {

				content.add(EmprunterCtl.getView(), "emprunter");
				cardLayout.show(content, "emprunter");

			} else if (e.getSource() == btnConsulter) {

				content.add(ConsulterCtl.getView(), "consulter");
				cardLayout.show(content, "consulter");

			} else {

			}

		}

	}
}