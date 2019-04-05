package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.EmprunterCtl;
import model.BiblioException;

public class EmpruntView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -389166848639507232L;
	private JTextField txtIdemprunteur;
	private JTextField txtIdexemplaire;
	private JTextField txtInformations;

	/**
	 * Create the application.
	 */
	public EmpruntView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel);

		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setBounds(92, 81, 108, 23);
		btnConfirmer.addActionListener(new BtnListener());
		panel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 5, 276, 65);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 276, 30);
		panel_4.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblIDEmprunteur = new JLabel("Identifiant emprunteur");
		panel_1.add(lblIDEmprunteur);

		txtIdemprunteur = new JTextField();
		txtIdemprunteur.setColumns(10);
		panel_1.add(txtIdemprunteur);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 35, 276, 30);
		panel_4.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblIDExemplaire = new JLabel("Identifiant exemplaire");
		panel_2.add(lblIDExemplaire);

		txtIdexemplaire = new JTextField();
		panel_2.add(txtIdexemplaire);
		txtIdexemplaire.setColumns(10);
		panel.add(btnConfirmer);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		txtInformations = new JTextField();
		txtInformations.setSize(new Dimension(200, 20));
		panel_3.add(txtInformations);
		txtInformations.setEditable(false);
		txtInformations.setColumns(10);

		this.setVisible(true);
	}

	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				if (!(txtIdemprunteur.getText().equals("") || txtIdexemplaire.getText().equals(""))) {
					
					EmprunterCtl.connection("biblio");
					EmprunterCtl.conditionsEmprunt();

					EmprunterCtl.entreeUtilisateur(Integer.parseInt(txtIdemprunteur.getText()));
					EmprunterCtl.addEmpruntEnCours();

					EmprunterCtl.entreeExemplaire(Integer.parseInt(txtIdexemplaire.getText()));
					EmprunterCtl.enregistrerEmprunt();

					txtInformations.setText("Emprunt effectué");

				} else if (txtIdemprunteur.getText().equals("")) {
					
					txtInformations.setText("Identifiant emprunteur non renseigné");
					
				} else if (txtIdexemplaire.getText().equals("")) {
					
					txtInformations.setText("Identifiant exemplaire non renseigné");
					
				}
			} catch (NumberFormatException | SQLException | BiblioException e1) {

				txtInformations.setText(e1.getMessage());
			}
		}
	}
}
