package view;

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
	private JLabel lblNewLabel;

	public HomeView() {
		
		setBackground(Color.DARK_GRAY);
		this.setTitle("Bibliothèque");
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JPanel boutonPane = new JPanel();

		btnLog = new JButton("Authentification");
		btnLog.addActionListener(new BtnListener());
		boutonPane.add(btnLog);

		btnEmprunter = new JButton("Emprunter");
		btnEmprunter.addActionListener(new BtnListener());
		boutonPane.add(btnEmprunter);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new BtnListener());
		boutonPane.add(btnRetour);

		btnConsulter = new JButton("Consulter");
		btnConsulter.addActionListener(new BtnListener());
		boutonPane.add(btnConsulter);

//		lblBibliothequeDesMarmousots = new JLabel("BIBLIOTHEQUE DES MARMOUSOTS");
//		lblBibliothequeDesMarmousots.setHorizontalAlignment(SwingConstants.CENTER);
//		lblBibliothequeDesMarmousots.setFont(new Font("Consolas", Font.BOLD, 22));
//		content.add(lblBibliothequeDesMarmousots, BorderLayout.CENTER);

		content.setLayout(cardLayout);
		content.add(AuthentificationCtl.getView(), "authentification");
		
		this.getContentPane().add(content, BorderLayout.CENTER);		
		this.getContentPane().add(boutonPane, BorderLayout.WEST);
		boutonPane.setLayout(new GridLayout(4, 1, 0, 0));

		lblNewLabel = new JLabel(new Date(System.currentTimeMillis()).toString());
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 22));

		btnEmprunter.setEnabled(AuthentificationCtl.getToken());
		btnRetour.setEnabled(AuthentificationCtl.getToken());

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

				// if (AuthentificationCtl.getToken()) {
				// btnConsulter.setEnabled(true);
				// btnEmprunter.setEnabled(true);
				// btnRetour.setEnabled(true);
				// } else {
//				content.add(AuthentificationCtl.getView(), "authentification");
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