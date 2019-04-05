package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.RetourCtl;
import model.BiblioException;

public class RetourView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778338146823232617L;
	private JTextField txtIdexemplaire;
	private JTextField txtInformations;

	/**
	 * Create the application.
	 */
	public RetourView() {
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

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 5, 255, 86);
		panel_2.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblIDExemplaire = new JLabel("Identifiant exemplaire");
		panel.add(lblIDExemplaire);

		txtIdexemplaire = new JTextField();
		panel.add(txtIdexemplaire);
		txtIdexemplaire.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);

		JButton btnConfirmer = new JButton("Confirmer");
		panel_1.add(btnConfirmer);
		btnConfirmer.addActionListener(new BtnListener());

		this.setVisible(true);
	}

	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			try {

				if (!(txtIdexemplaire.getText().equals(""))) {

					RetourCtl.connection("biblio");

					RetourCtl.entreeExemplaire(Integer.parseInt(txtIdexemplaire.getText()));

					RetourCtl.chercherEmpruntEnCours();

					RetourCtl.retournerEmprunt();

					txtInformations.setText("Retour effectué");

				} else if (txtIdexemplaire.getText().equals("")) {
					txtInformations.setText("Identifiant exemplaire non renseigné");
				}
				
			} catch (NumberFormatException | SQLException | BiblioException e1) {

				txtInformations.setText(e1.getMessage());
			}
		}
	}
}
