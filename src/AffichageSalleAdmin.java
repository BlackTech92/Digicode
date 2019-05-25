import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class AffichageSalleAdmin extends JFrame {

	private JPanel contentPane;
	private ModifSalle modifSalle;
	private Salle salle;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { AffichageSalleAdmin frame = new
	 * AffichageSalleAdmin(null); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }/*
	 * 
	 * /** Create the frame.
	 * 
	 * @param user
	 */
	public AffichageSalleAdmin(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Détails des salles [Mode Admin]");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JComboBox comboBox = new JComboBox();

		JLabel lblNewLabel_1 = new JLabel("Selectionner une salle");

		ConnexionDigicode c = new ConnexionDigicode(); 
		ArrayList<Salle> listeSalle = new ArrayList<Salle>(); 

		ResultSet r = c.executeRequete("select * from salle");
		try {
			while (r.next()) {
				Salle salle = new Salle(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getString(5),
						r.getString(6), r.getInt(7), r.getInt(8));
				listeSalle.add(salle); 
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		
		for (Salle salle1 : listeSalle) {
			comboBox.addItem(salle1);
		}

		JButton btnNewButton = new JButton("Afficher détails ");

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Salle s = (Salle) comboBox.getSelectedItem();

				ArrayList<Salle> listeSalle = new ArrayList<Salle>();

				ConnexionDigicode c = new ConnexionDigicode();
				ResultSet r = c.executeRequete("SELECT * FROM salle WHERE idSalle LIKE " + s.getIdSalle());

				try {
					while (r.next()) {
						Salle sal = new Salle(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getString(5),
								r.getString(6), r.getInt(7), r.getInt(8));
						listeSalle.add(sal);
						if (e.getSource() == btnNewButton) {
							String resultSalle = "Détails de la salle n°" + s.getIdSalle() + "\n" + "Type Salle : "
									+ s.getTypeSalle() + "\n" + "Code : " + s.getCode() + "\n" + "Etage : "
									+ s.getEtage() + "\n" + "Adresse : " + s.getAdresse() + "\n" + "Ville : "
									+ s.getVille() + "\n" + "Code Postale : " + s.getCodePostale() + "\n"
									+ "Capacité Max : " + s.getCapaciteMax() + "\n";

							JOptionPane.showMessageDialog(null, resultSalle);
						}
						
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton btnNewButton_1 = new JButton("Modifier");

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Salle s = (Salle) comboBox.getSelectedItem();

				ArrayList<Salle> listeSalle = new ArrayList<Salle>();

				ConnexionDigicode c = new ConnexionDigicode();
				ResultSet r = c.executeRequete("SELECT * FROM salle WHERE idSalle LIKE " + s.getIdSalle());

				try {
					while (r.next()) {
						Salle salle = new Salle(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4),
								r.getString(5), r.getString(6), r.getInt(7), r.getInt(8));
						listeSalle.add(salle);
						if (e.getSource() == btnNewButton_1) {
							modifSalle = new ModifSalle(salle); 
																
							modifSalle.setVisible(true); 
						}

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(104)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_1)
								.addComponent(btnNewButton_1))
						.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(129))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(141)
						.addComponent(lblNewLabel).addContainerGap(144, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(32)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addGap(42)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(btnNewButton_1))
						.addGap(108)));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}
