package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import modele.Coordonnees;
import modele.Facture;
import modele.GenerationArticles;
import modele.Panier;
import modele.Tomates;

public class FenetreCoordonnees extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_adresse;
	private JTextField textField_CP;
	private JTextField textField_ville;
	private JTextField textField_tel;
	private JTextField textField_complt_adresse;
	private final ButtonGroup moyenDePaiement = new ButtonGroup();
	private final ButtonGroup newsletter = new ButtonGroup();
	private JTextField textField_mail;

	private JRadioButton rdbtn_CB;
	private JRadioButton rdbtn_Paypal;
	private JRadioButton rdbtn_Cheque;
	JRadioButton rdbtn_newsletter_OUI;
	JRadioButton rdbtn_newsletter_NON;

	private Coordonnees coordonnees;
	private Panier monPanier;
	private String paiement;
	private boolean abonnement;
	private Facture facture;
	
	/**
	 * Create the frame.
	 */
	public FenetreCoordonnees(Panier panier) {

		this.monPanier = panier;
		this.paiement = "NONE";

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 754, 574);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		this.contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lbl_titre = new JLabel("Vos coordonnées");
		lbl_titre.setFont(new Font("Segoe Print", Font.BOLD, 25));
		lbl_titre.setForeground(new Color(0, 128, 0));
		panelTitle.add(lbl_titre);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Eclipse\\S1_01_v2\\src\\ihm\\logo.png"));
		panelTitle.add(lblNewLabel_1);

		/// Panel 1 créé

		JPanel panelInfos = new JPanel();
		this.contentPane.add(panelInfos, BorderLayout.CENTER);
		panelInfos.setLayout(new BorderLayout(15, 0));

		JPanel panelTextFields = new JPanel();
		panelTextFields.setBorder(new EmptyBorder(0, 0, 0, 15));
		panelInfos.add(panelTextFields, BorderLayout.CENTER);
		panelTextFields.setLayout(new GridLayout(8, 0, 0, 10));

		JPanel panelLabels = new JPanel();
		panelInfos.add(panelLabels, BorderLayout.WEST);
		panelLabels.setLayout(new GridLayout(8, 1, 0, 10));

		JLabel lbl_nom = new JLabel("Nom :");
		lbl_nom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_nom.setFont(new Font("Open Sans", Font.BOLD, 12));
		panelLabels.add(lbl_nom);

		JLabel lbl_prenom = new JLabel("Prénom :");
		lbl_prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_prenom.setFont(new Font("Open Sans", Font.BOLD, 12));
		panelLabels.add(lbl_prenom);

		this.textField_nom = new JTextField();
		GridBagConstraints gbc_textField_nom = new GridBagConstraints();
		gbc_textField_nom.fill = GridBagConstraints.BOTH;
		gbc_textField_nom.insets = new Insets(0, 0, 5, 0);
		gbc_textField_nom.gridx = 1;
		gbc_textField_nom.gridy = 0;
		panelTextFields.add(this.textField_nom, gbc_textField_nom);
		this.textField_nom.setColumns(10);

		this.textField_prenom = new JTextField();
		GridBagConstraints gbc_textField_prenom = new GridBagConstraints();
		gbc_textField_prenom.fill = GridBagConstraints.BOTH;
		gbc_textField_prenom.insets = new Insets(0, 0, 5, 0);
		gbc_textField_prenom.gridx = 1;
		gbc_textField_prenom.gridy = 1;
		panelTextFields.add(this.textField_prenom, gbc_textField_prenom);
		this.textField_prenom.setColumns(10);

		JLabel lbl_adresse = new JLabel("Adresse :");
		lbl_adresse.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_adresse.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_adresse = new GridBagConstraints();
		gbc_lbl_adresse.fill = GridBagConstraints.BOTH;
		gbc_lbl_adresse.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_adresse.gridx = 0;
		gbc_lbl_adresse.gridy = 2;
		panelLabels.add(lbl_adresse, gbc_lbl_adresse);

		this.textField_adresse = new JTextField();
		GridBagConstraints gbc_textField_adresse = new GridBagConstraints();
		gbc_textField_adresse.fill = GridBagConstraints.BOTH;
		gbc_textField_adresse.insets = new Insets(0, 0, 5, 0);
		gbc_textField_adresse.gridx = 1;
		gbc_textField_adresse.gridy = 2;
		panelTextFields.add(this.textField_adresse, gbc_textField_adresse);
		this.textField_adresse.setColumns(10);

		JLabel lbl_complt_adresse = new JLabel("Complément d'adresse :");
		lbl_complt_adresse.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_complt_adresse.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_complt_adresse = new GridBagConstraints();
		gbc_lbl_complt_adresse.fill = GridBagConstraints.BOTH;
		gbc_lbl_complt_adresse.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_complt_adresse.gridx = 0;
		gbc_lbl_complt_adresse.gridy = 3;
		panelLabels.add(lbl_complt_adresse, gbc_lbl_complt_adresse);

		this.textField_complt_adresse = new JTextField();
		GridBagConstraints gbc_textField_complt_adresse = new GridBagConstraints();
		gbc_textField_complt_adresse.fill = GridBagConstraints.BOTH;
		gbc_textField_complt_adresse.insets = new Insets(0, 0, 5, 0);
		gbc_textField_complt_adresse.gridx = 1;
		gbc_textField_complt_adresse.gridy = 3;
		panelTextFields.add(this.textField_complt_adresse, gbc_textField_complt_adresse);
		this.textField_complt_adresse.setColumns(10);

		JLabel lbl_CP = new JLabel("Code postal :");
		lbl_CP.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_CP.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_CP = new GridBagConstraints();
		gbc_lbl_CP.fill = GridBagConstraints.BOTH;
		gbc_lbl_CP.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_CP.gridx = 0;
		gbc_lbl_CP.gridy = 4;
		panelLabels.add(lbl_CP, gbc_lbl_CP);

		this.textField_CP = new JTextField();
		GridBagConstraints gbc_textField_CP = new GridBagConstraints();
		gbc_textField_CP.fill = GridBagConstraints.BOTH;
		gbc_textField_CP.insets = new Insets(0, 0, 5, 0);
		gbc_textField_CP.gridx = 1;
		gbc_textField_CP.gridy = 4;
		panelTextFields.add(this.textField_CP, gbc_textField_CP);
		this.textField_CP.setColumns(10);

		JLabel lbl_ville = new JLabel("Ville :");
		lbl_ville.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_ville.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_ville = new GridBagConstraints();
		gbc_lbl_ville.fill = GridBagConstraints.BOTH;
		gbc_lbl_ville.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ville.gridx = 0;
		gbc_lbl_ville.gridy = 5;
		panelLabels.add(lbl_ville, gbc_lbl_ville);

		this.textField_ville = new JTextField();
		GridBagConstraints gbc_textField_ville = new GridBagConstraints();
		gbc_textField_ville.fill = GridBagConstraints.BOTH;
		gbc_textField_ville.insets = new Insets(0, 0, 5, 0);
		gbc_textField_ville.gridx = 1;
		gbc_textField_ville.gridy = 5;
		panelTextFields.add(this.textField_ville, gbc_textField_ville);
		this.textField_ville.setColumns(10);

		JLabel lbl_tel = new JLabel("Téléphone :");
		lbl_tel.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_tel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_tel = new GridBagConstraints();
		gbc_lbl_tel.fill = GridBagConstraints.BOTH;
		gbc_lbl_tel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_tel.gridx = 0;
		gbc_lbl_tel.gridy = 6;
		panelLabels.add(lbl_tel, gbc_lbl_tel);

		this.textField_tel = new JTextField();
		GridBagConstraints gbc_textField_tel = new GridBagConstraints();
		gbc_textField_tel.fill = GridBagConstraints.BOTH;
		gbc_textField_tel.insets = new Insets(0, 0, 5, 0);
		gbc_textField_tel.gridx = 1;
		gbc_textField_tel.gridy = 6;
		panelTextFields.add(this.textField_tel, gbc_textField_tel);
		this.textField_tel.setColumns(10);

		JLabel lbl_mail = new JLabel("Mail :");
		lbl_mail.setFont(new Font("Open Sans", Font.BOLD, 12));
		lbl_mail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl_mail = new GridBagConstraints();
		gbc_lbl_mail.anchor = GridBagConstraints.EAST;
		gbc_lbl_mail.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_mail.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_mail.gridx = 0;
		gbc_lbl_mail.gridy = 7;
		panelLabels.add(lbl_mail, gbc_lbl_mail);

		this.textField_mail = new JTextField();
		GridBagConstraints gbc_textField_mail = new GridBagConstraints();
		gbc_textField_mail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_mail.gridx = 1;
		gbc_textField_mail.gridy = 7;
		panelTextFields.add(this.textField_mail, gbc_textField_mail);
		this.textField_mail.setColumns(10);

		JPanel panelBottom = new JPanel();
		this.contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelAutres = new JPanel();
		panelBottom.add(panelAutres);
		panelAutres.setLayout(new BorderLayout(0, 0));

		JPanel panelRadio = new JPanel();
		panelAutres.add(panelRadio, BorderLayout.CENTER);
		panelRadio.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_moyensDePaiement = new JPanel();
		panel_moyensDePaiement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_moyensDePaiement.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 128, 0)),

				"Moyen de paiement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panelRadio.add(panel_moyensDePaiement);

		this.rdbtn_CB = new JRadioButton("Carte de crédit");
		this.construireControleurRadioCB();
		this.rdbtn_CB.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_moyensDePaiement.add(this.rdbtn_CB);

		this.rdbtn_Paypal = new JRadioButton("Paypal");
		this.construireControleurRadioPaypal();
		this.rdbtn_Paypal.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_moyensDePaiement.add(this.rdbtn_Paypal);

		this.rdbtn_Cheque = new JRadioButton("Paiement par chèque");
		this.construireControleurRadioCheque();
		this.rdbtn_Cheque.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_moyensDePaiement.add(this.rdbtn_Cheque);

		JPanel panel_newsletter = new JPanel();
		panel_newsletter.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 128, 0)),

				"Abonnement \u00E0 notre newsletter", TitledBorder.LEADING, TitledBorder.TOP, null,

				new Color(0, 128, 0)));
		panelRadio.add(panel_newsletter);

		this.rdbtn_newsletter_OUI = new JRadioButton("Oui");
		this.rdbtn_newsletter_OUI.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_newsletter.add(this.rdbtn_newsletter_OUI);

		this.rdbtn_newsletter_NON = new JRadioButton("Non");
		this.rdbtn_newsletter_NON.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_newsletter.add(this.rdbtn_newsletter_NON);

		JPanel panelButtons = new JPanel();
		FlowLayout fl_panelButtons = (FlowLayout) panelButtons.getLayout();
		fl_panelButtons.setVgap(0);
		fl_panelButtons.setHgap(0);
		fl_panelButtons.setAlignment(FlowLayout.RIGHT);
		panelButtons.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelAutres.add(panelButtons, BorderLayout.SOUTH);

		JButton btn_OK = new JButton("Valider");
		construireControleurBoutonOK(btn_OK);
		panelButtons.add(btn_OK);

		JButton btn_annuler = new JButton("Annuler");
		btn_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.dispose();
			}
		});
		panelButtons.add(btn_annuler);

	}

	private void construireControleurBoutonOK(JButton btn_OK) {
		btn_OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean champ = FenetreCoordonnees.this.sontTousLesChampsRemplis();
				if (champ && FenetreCoordonnees.this.formatVerifier()) {
					String nom = FenetreCoordonnees.this.textField_nom.getText();
					String prenom = FenetreCoordonnees.this.textField_prenom.getText();
					String adresse = FenetreCoordonnees.this.textField_adresse.getText();
					String complement = FenetreCoordonnees.this.textField_complt_adresse.getText();
					String cp = FenetreCoordonnees.this.textField_CP.getText();
					String ville = FenetreCoordonnees.this.textField_ville.getText();
					String tel = FenetreCoordonnees.this.textField_tel.getText();
					String mail = FenetreCoordonnees.this.textField_mail.getText();
					String paiement = FenetreCoordonnees.this.paiement;
					boolean abonnement = FenetreCoordonnees.this.abonnement;
					FenetreCoordonnees.this.coordonnees = new Coordonnees(nom, prenom, adresse, complement, cp, ville,
							tel, mail);
					FenetreCoordonnees.this.facture = new Facture(FenetreCoordonnees.this.monPanier,
							FenetreCoordonnees.this.coordonnees, abonnement);
					FenetreCoordonnees.this.facture.setPaiement(paiement);
					FenetreFacture fFacture = new FenetreFacture(FenetreCoordonnees.this.facture);
					Application.centerFrameOnScreen(fFacture);
					fFacture.setVisible(true);
					FenetreCoordonnees.this.dispose();
				} else if (!champ) {
					JOptionPane.showMessageDialog(null, "Tous les éléments ne sont pas renseignés");
				}
			}
		});
	}

	private void construireControleurRadioCB() {
		this.rdbtn_CB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.actualiserRadioPaiement(FenetreCoordonnees.this.rdbtn_CB);
				FenetreCoordonnees.this.paiement = "CB";
			}
		});
	}

	private void construireControleurRadioPaypal() {
		this.rdbtn_Paypal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.actualiserRadioPaiement(FenetreCoordonnees.this.rdbtn_Paypal);
				FenetreCoordonnees.this.paiement = "PAYPAL";
			}
		});
	}

	private void construireControleurRadioCheque() {
		this.rdbtn_Cheque.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.actualiserRadioPaiement(FenetreCoordonnees.this.rdbtn_Cheque);
				FenetreCoordonnees.this.paiement = "CHEQUE";
			}
		});
	}

	private void construireControleurRadioOUI() {
		this.rdbtn_newsletter_OUI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.actualiserRadioAbonnement(FenetreCoordonnees.this.rdbtn_newsletter_OUI);
				FenetreCoordonnees.this.abonnement = true;
			}
		});
	}

	private void construireControleurRadioNON() {
		this.rdbtn_newsletter_NON.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees.this.actualiserRadioAbonnement(FenetreCoordonnees.this.rdbtn_newsletter_NON);
				FenetreCoordonnees.this.abonnement = false;
			}
		});
	}

	public void enregistrerEtFermer(JButton btn_OK) {
	}

	public void fermerLaFenetre(JButton btn_annuler) {
	}

	private void actualiserRadioPaiement(JRadioButton radioClique) {
		this.rdbtn_CB.setSelected(false);
		this.rdbtn_Cheque.setSelected(false);
		this.rdbtn_Paypal.setSelected(false);
		radioClique.setSelected(true);
	}

	private void actualiserRadioAbonnement(JRadioButton radioClique) {
		this.rdbtn_newsletter_NON.setSelected(false);
		this.rdbtn_newsletter_OUI.setSelected(false);
		radioClique.setSelected(true);
	}

	private boolean estPaiementChoisi() {
		return this.rdbtn_CB.isSelected() || this.rdbtn_Cheque.isSelected() || this.rdbtn_Paypal.isSelected();
	}

	private boolean estAbonnementChoisi() {
		return this.rdbtn_newsletter_NON.isSelected() || this.rdbtn_newsletter_OUI.isSelected();
	}

	private boolean sontLesChampsRemplis() {
		boolean nomRenseigne = !(this.textField_nom.getText().isEmpty());
		boolean prenomRenseigne = !(this.textField_prenom.getText().isEmpty());
		boolean adresseRenseigne = !(this.textField_adresse.getText().isEmpty());
		boolean cpRenseigne = !(this.textField_CP.getText().isEmpty());
		boolean villeRenseigne = !(this.textField_ville.getText().isEmpty());
		boolean telRenseigne = !(this.textField_tel.getText().isEmpty());
		boolean mailRenseigne = !(this.textField_mail.getText().isEmpty());
		return (nomRenseigne && prenomRenseigne && adresseRenseigne && cpRenseigne && cpRenseigne
				&& villeRenseigne && telRenseigne && mailRenseigne);
	}

	private boolean sontTousLesChampsRemplis() {
		return this.sontLesChampsRemplis() && this.estAbonnementChoisi() && this.estPaiementChoisi();
	}
	
	private boolean formatVerifier() {
		String cp = this.textField_CP.getText();
		String tel = this.textField_tel.getText();
		String mail = this.textField_mail.getText();
		boolean res = true;
		if (cp.length()!=5 && !cp.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "Le code postal doit comporter 5 nombres.");
			res = false;
		} else if (!tel.matches("(0|\\+33|0033)[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(null, "Format du numéro de téléphone invalide, veuillez saisir au format +33 x xx xx xx xx");
			res = false;
		} else if (!mail.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
			JOptionPane.showMessageDialog(null, "Format d'adresse e-mail invalide, veuillez saisir au format xxx@xxx");
			res = false;
		}
		return res;
		
	}

}






