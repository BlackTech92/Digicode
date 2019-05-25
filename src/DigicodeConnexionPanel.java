import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DigicodeConnexionPanel extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTextField identifiantJtextField;
	private JButton btnNewButton = new JButton("Connexion");
	private String pseudo;
	private String mdp;
	private JPasswordField passwordField;
	private AffichageSalle afficheSalle;
	private AffichageSalleAdmin afficheAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigicodeConnexionPanel frame = new DigicodeConnexionPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DigicodeConnexionPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		identifiantJtextField = new JTextField();
		identifiantJtextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Connexion à mon espace Digicode");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblIdentifiant = new JLabel("Identifiant");

		JLabel lblNewLabel_1 = new JLabel("Mot de passe ");

		passwordField = new JPasswordField();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pseudo = identifiantJtextField.getText(); 
				mdp = passwordField.getText(); 

				ConnexionDigicode c = new ConnexionDigicode(); 
				ResultSet r2 = c.executeRequete("SELECT COUNT(*) FROM user WHERE pseudo LIKE '" + pseudo + "'"); 
																													
																													
																													
				try {
					while (r2.next()) 
					{
						if (r2.getInt(1) < 1) // 
						{
							JOptionPane.showMessageDialog(null, "Aucun utilisateur trouvé !"); 
						}
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				ResultSet r = c.executeRequete("SELECT * FROM user WHERE mdp LIKE '" + mdp + "'"); 
																									

				try {
					while (r.next()) 
					{
						if (mdp.equals(r.getString(5))) { 

							User user = new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
									r.getString(5), r.getInt(6));
							JOptionPane.showMessageDialog(null, "Vous êtes bien connecté !");

							setVisible(false); 

							if (user.getAdmin() == 1) {
								afficheAdmin = new AffichageSalleAdmin(user); 
																				
																				
								afficheAdmin.setVisible(true); 
							} else {
								afficheSalle = new AffichageSalle(user); 
																			
								afficheSalle.setVisible(true); 
							}
						} else {
							JOptionPane.showMessageDialog(null, "Mot de passe incorrect !");
						}

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(121)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
														.addComponent(lblIdentifiant).addGap(17))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblNewLabel_1)
														.addPreferredGap(ComponentPlacement.RELATED)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGap(25)
														.addComponent(btnNewButton))
												.addGroup(gl_contentPane.createSequentialGroup().addGap(33)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(passwordField)
																.addComponent(identifiantJtextField))))
										.addGap(221))
								.addGroup(gl_contentPane
										.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
												310, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(141, Short.MAX_VALUE)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(25)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(77)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(identifiantJtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdentifiant))
				.addGap(66)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE).addComponent(btnNewButton)
				.addGap(63)));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
