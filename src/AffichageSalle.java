import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AffichageSalle extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffichageSalle frame = new AffichageSalle(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		})
	}*/

	/**
	 * Create the frame.
	 * 
	 * @param user
	 */
	public AffichageSalle(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("D\u00E9tails des salles ");
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

		JButton btnNewButton = new JButton("Afficher d\u00E9tails ");

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
						Salle salle = new Salle(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4),
								r.getString(5), r.getString(6), r.getInt(7), r.getInt(8));
						listeSalle.add(salle);
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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(210).addComponent(lblNewLabel)
						.addContainerGap(201, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(btnNewButton))
								.addGroup(Alignment.LEADING,
										gl_contentPane.createSequentialGroup().addGap(104).addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(219)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1).addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addGap(70)))
						.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE).addComponent(btnNewButton)
						.addGap(117)));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}
