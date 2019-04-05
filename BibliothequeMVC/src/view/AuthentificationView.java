package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.AuthentificationCtl;

public class AuthentificationView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4158838399418846121L;
	private JTextField textFieldIdentifiant;
	private JPasswordField passwordFieldMDP;
	private JTextField txtInformations;

	public AuthentificationView() {
		initAuthentificationView();
	}

	private void initAuthentificationView() {
		setLayout(null);

		textFieldIdentifiant = new JTextField();
		textFieldIdentifiant.setBounds(316, 159, 183, 23);
		this.add(textFieldIdentifiant);

		JButton connectionCmd = new JButton("Connection");
		connectionCmd.addActionListener(new BtnListener());
		connectionCmd.setBounds(255, 344, 108, 53);
		this.add(connectionCmd);

		passwordFieldMDP = new JPasswordField();
		passwordFieldMDP.setBounds(316, 226, 183, 23);
		this.add(passwordFieldMDP);

		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(177, 163, 88, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mot de Passe");
		lblNewLabel_1.setBounds(177, 230, 88, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("BIBLIOTHEQUE DES MARMOUSOTS");
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 24));
		lblNewLabel_2.setBounds(127, 35, 448, 79);
		add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);

		this.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		txtInformations = new JTextField();
		txtInformations.setBounds(0, 596, 769, 20);
		add(txtInformations);
		txtInformations.setEditable(false);
		txtInformations.setColumns(10);
		
		setVisible(true);
	}

	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			try {

				if (!(textFieldIdentifiant.getText().equals("")) && !(passwordFieldMDP.getPassword().equals(""))) {
					AuthentificationCtl.connection("biblio");

					AuthentificationCtl.entreeUtilisateur(Integer.parseInt(textFieldIdentifiant.getText()));

					if (AuthentificationCtl.checkPwd(passwordFieldMDP.getPassword())) {
						txtInformations.setText("Utilisateur connecté");
						HomeView.btnEmprunter.setEnabled(AuthentificationCtl.getToken());
						HomeView.btnRetour.setEnabled(AuthentificationCtl.getToken());
					} else {
						JOptionPane.showMessageDialog(null, "Wrong password or username", "login fail", 2);
					}
				} else if (textFieldIdentifiant.getText().equals("") || (passwordFieldMDP.getPassword().equals(""))) {
					txtInformations.setText("Identifiant emprunteur non renseigné");
				}

			} catch (NumberFormatException | SQLException e1) {

				JOptionPane.showMessageDialog(null, e1.getMessage(), "login fail", 2);
			}

		}
	}
}
