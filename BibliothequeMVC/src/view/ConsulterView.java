package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.ConsulterCtl;

public class ConsulterView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7991950320167323619L;

	private JTextField txtIdUtilisateur;
	private JTextField txtInformations;
	static String[] labelsUser = { "ID: ", "Nom: ", "Prenom: ", "Pseudo: ", "Categorie: " };
	static JTextField[] txtInfosUser = new JTextField[labelsUser.length];
	static String[] labelsEmprunt = { "Emprunt n° ", "id Emprunt: ", "Date d'emprunt: " };
	static JTextField[] txtInfosEmprunt = new JTextField[labelsUser.length];
	static JPanel panelLblUser, panelLblExemplaire;
	static JPanel panelTxtUser, panelTxtExemplaire;

	/**
	 * Create the application.
	 */
	public ConsulterView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		txtInformations = new JTextField();
		txtInformations.setEditable(false);
		this.add(txtInformations, BorderLayout.SOUTH);
		txtInformations.setColumns(10);

		JPanel panelConsulter = new JPanel();
		add(panelConsulter, BorderLayout.CENTER);
		panelConsulter.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelConsulter.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelSaisie = new JPanel();
		panelNorth.add(panelSaisie);
		panelSaisie.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblIdUtilisateur = new JLabel("Identifiant utilisateur");
		panelSaisie.add(lblIdUtilisateur);

		txtIdUtilisateur = new JTextField();
		panelSaisie.add(txtIdUtilisateur);
		txtIdUtilisateur.setColumns(10);

		JPanel panelBtnConf = new JPanel();
		panelNorth.add(panelBtnConf);

		JButton btnConfirmer = new JButton("Confirmer");
		panelBtnConf.add(btnConfirmer);

		JPanel panelInfo = new JPanel();
		panelConsulter.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));

		JPanel panelInfoEmprunt = new JPanel();
		panelInfo.add(panelInfoEmprunt, BoxLayout.X_AXIS);
		panelInfoEmprunt.setLayout(new BoxLayout(panelInfoEmprunt, BoxLayout.X_AXIS));
		panelLblExemplaire = new JPanel();
		panelLblExemplaire.setPreferredSize(new Dimension(0, 0));
		panelLblExemplaire.setLayout(new GridLayout(9, 1));
		panelInfoEmprunt.add(panelLblExemplaire);
		panelTxtExemplaire = new JPanel();
		panelTxtExemplaire.setPreferredSize(new Dimension(0, 0));
		panelTxtExemplaire.setLayout(new GridLayout(9, 1));
		panelInfoEmprunt.add(panelTxtExemplaire);

		JPanel panelInfoUser = new JPanel();
		panelInfo.add(panelInfoUser, BoxLayout.X_AXIS);
		panelInfoUser.setLayout(new BoxLayout(panelInfoUser, BoxLayout.X_AXIS));
		panelLblUser = new JPanel();
		panelLblUser.setPreferredSize(new Dimension(0, 0));
		panelLblUser.setLayout(new GridLayout(5, 1));
		panelInfoUser.add(panelLblUser);
		panelTxtUser = new JPanel();
		panelTxtUser.setPreferredSize(new Dimension(0, 0));
		panelTxtUser.setLayout(new GridLayout(5, 1));
		panelInfoUser.add(panelTxtUser);

		btnConfirmer.addActionListener(new BtnListener());

		this.setVisible(true);
	}

	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			try {

				if (!(txtIdUtilisateur.getText().equals(""))) {

					try {
						ConsulterCtl.connection("biblio");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					panelLblUser.removeAll();
					panelTxtUser.removeAll();

					ConsulterCtl.entreeUtilisateur(Integer.parseInt(txtIdUtilisateur.getText()));

					ConsulterCtl.addEmpruntEnCours();

					ConsulterCtl.createExemplaires();

					String[] infosUser = ConsulterCtl.getUserInfo();

					for (int i = 0; i < labelsUser.length; i++) {

						JLabel lblNom = new JLabel(labelsUser[i]);
						lblNom.setHorizontalAlignment(SwingConstants.LEFT);
						lblNom.setFont(new Font("Consolas", Font.BOLD, 14));
						panelLblUser.add(lblNom);

						txtInfosUser[i] = new JTextField();
						txtInfosUser[i].setText(infosUser[i]);
						txtInfosUser[i].setEditable(false);
						panelTxtUser.add(txtInfosUser[i]);
					}
					panelLblUser.updateUI();
					panelTxtUser.updateUI();

					panelLblExemplaire.removeAll();
					panelTxtExemplaire.removeAll();

					List<String[]> infoEmprunts = ConsulterCtl.getEmpruntInfo();

					for (String[] infoEmprunt : infoEmprunts) {

						for (int i = 0; i < labelsEmprunt.length; i++) {
							JLabel lblNom = new JLabel(labelsEmprunt[i]);
							lblNom.setHorizontalAlignment(SwingConstants.LEFT);
							lblNom.setFont(new Font("Consolas", Font.BOLD, 14));
							panelLblExemplaire.add(lblNom);

							txtInfosEmprunt[i] = new JTextField();
							txtInfosEmprunt[i].setText(infoEmprunt[i]);
							txtInfosEmprunt[i].setEditable(false);
							panelTxtExemplaire.add(txtInfosEmprunt[i]);
						}
					}
					panelLblExemplaire.updateUI();
					panelTxtExemplaire.updateUI();
				} else if (txtIdUtilisateur.getText().equals("")) {
					txtInformations.setText("Identifiant utilisateur non renseigné");
				}

			} catch (NumberFormatException | SQLException e1) {

				txtInformations.setText(e1.getMessage());
			}
		}
	}
}
