package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modele.GenerationArticles;
import modele.Panier;
import modele.Tomate;

public class FenetreDetail extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldQuantiteGraines;

	private JLabel lblImageTomate;
	private JTextArea textAreaDescription;
	private JSpinner spinnerQte;
	private JTextField textFieldPrixTotal;

	private DefaultComboBoxModel<String> modeleTomatesSimilaires;
	private JComboBox<String> comboTomatesSimilaires;

	private List<Tomate> tomatesSimilaires;
	private Tomate tomateEnCours;
	private Panier monPanier;
	private JTextField textFieldPrixSachet;
	private int quantite;
	private FenetreProduits fenetreAppel;

	/**
	 * Create the dialog.
	 */
	public FenetreDetail(Tomate tomate, JFrame parent) {
		super(parent, "Fenêtre détail", true);
		this.fenetreAppel = (FenetreProduits) parent;
		this.tomateEnCours = tomate;
		this.monPanier = this.fenetreAppel.getPanier();

		this.setBounds(100, 100, 718, 361);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		this.centerDialog();

		JPanel panelPhoto = new JPanel();
		this.contentPanel.add(panelPhoto);
		panelPhoto.setLayout(new BorderLayout(0, 0));

		JPanel panelImageDispo = new JPanel();
		panelPhoto.add(panelImageDispo);
		panelImageDispo.setLayout(new BorderLayout(10, 10));

		this.lblImageTomate = new JLabel();
		this.lblImageTomate.setHorizontalAlignment(SwingConstants.CENTER);
		panelImageDispo.add(this.lblImageTomate, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panelPhoto.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTomate = new JLabel((String) null);
		lblTomate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTomate.setForeground(new Color(0, 128, 0));
		lblTomate.setFont(new Font("Segoe Print", Font.BOLD, 14));
		panel.add(lblTomate);

		this.comboTomatesSimilaires = new JComboBox<String>();
		this.construireControleurComboTomates();
		panel.add(this.comboTomatesSimilaires);
		this.construireComboTomates();

		JPanel panelDesc = new JPanel();
		this.contentPanel.add(panelDesc);
		panelDesc.setLayout(new BorderLayout(0, 0));

		JPanel panelNbPrix = new JPanel();
		panelDesc.add(panelNbPrix, BorderLayout.SOUTH);
		panelNbPrix.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panelNombre = new JPanel();
		panelNbPrix.add(panelNombre);
		panelNombre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblGraines = new JLabel("Nombre de graines par sachet");
		panelNombre.add(lblGraines);

		this.textFieldQuantiteGraines = new JTextField();
		this.textFieldQuantiteGraines.setPreferredSize(new Dimension(30, 20));
		this.textFieldQuantiteGraines.setText("0");
		this.textFieldQuantiteGraines.setEditable(false);
		panelNombre.add(this.textFieldQuantiteGraines);

		JPanel panelPrix = new JPanel();
		panelNbPrix.add(panelPrix);
		panelPrix.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		this.spinnerQte = new JSpinner();
		this.construireControleurSpinnerQauntite();

		JLabel lblQuantite = new JLabel("Quantité :");
		panelPrix.add(lblQuantite);
		this.spinnerQte.setPreferredSize(new Dimension(50, 20));
		panelPrix.add(this.spinnerQte);

		JPanel panelPrixTotal = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPrixTotal.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelNbPrix.add(panelPrixTotal);

		JLabel lblPrix = new JLabel("Prix du sachet :");
		panelPrixTotal.add(lblPrix);

		this.textFieldPrixSachet = new JTextField();
		this.textFieldPrixSachet.setText("0.0 € TTC");
		this.textFieldPrixSachet.setPreferredSize(new Dimension(80, 30));
		this.textFieldPrixSachet.setEditable(false);
		panelPrixTotal.add(this.textFieldPrixSachet);

		JLabel lblPrixTotal = new JLabel("Prix total :");
		panelPrixTotal.add(lblPrixTotal);

		this.textFieldPrixTotal = new JTextField();
		this.textFieldPrixTotal.setText("0.0 € TTC");
		this.textFieldPrixTotal.setPreferredSize(new Dimension(80, 30));
		this.textFieldPrixTotal.setEditable(false);
		panelPrixTotal.add(this.textFieldPrixTotal);

		JScrollPane scrollPaneTextArea = new JScrollPane((Component) null);
		scrollPaneTextArea.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 128, 0)));
		panelDesc.add(scrollPaneTextArea, BorderLayout.CENTER);

		this.textAreaDescription = new JTextArea();
		this.textAreaDescription.setText((String) null);
		this.textAreaDescription.setLineWrap(true);
		this.textAreaDescription.setEditable(false);
		this.textAreaDescription.setWrapStyleWord(true);
		scrollPaneTextArea.setViewportView(this.textAreaDescription);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton ajouterButton = new JButton("Ajouter au panier");
		ajouterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		construireControleurBoutonAjouter(ajouterButton);
		buttonPane.add(ajouterButton);
		this.getRootPane().setDefaultButton(ajouterButton);

		JButton annulerButton = new JButton("Annuler");
		this.construireControleurAnnuler(annulerButton);
		buttonPane.add(annulerButton);
		this.afficherDonnees();

	}

	private void construireControleurBoutonAjouter(JButton ajouterButton) {
		ajouterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (FenetreDetail.this.quantite != 0) {
					FenetreDetail.this.monPanier.addTomate(FenetreDetail.this.tomateEnCours,
							FenetreDetail.this.quantite);
					FenetreDetail.this.fenetreAppel.setPanier(FenetreDetail.this.monPanier);
					FenetreDetail.this.fenetreAppel.actualiserPrixPanier();
					FenetreDetail.this.dispose();
					if (FenetreDetail.this.quantite == 1) {
						JOptionPane.showMessageDialog(null, "Vous avez ajouté 1 sachet de "
								+ FenetreDetail.this.tomateEnCours.getDésignation() + " au panier");
					} else {
						JOptionPane.showMessageDialog(null, "Vous avez ajouté " + FenetreDetail.this.quantite
								+ " sachets de " + FenetreDetail.this.tomateEnCours.getDésignation() + " au panier");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune quantité");
				}
			}
		});
	}

	private void construireControleurComboTomates() {
		this.comboTomatesSimilaires.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String tomate = FenetreDetail.this.comboTomatesSimilaires.getSelectedItem().toString();
					if (tomate != "Produits Similaires") {
						FenetreDetail.this.tomateEnCours = GenerationArticles.générationDeBaseDesTomates()
								.getTomate(tomate);
						FenetreDetail.this.afficherDonnees();
					}
				}
			}
		});
	}

	private void construireControleurAnnuler(JButton annulerButton) {
		annulerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreDetail.this.dispose();
			}
		});
	}

	private void construireControleurSpinnerQauntite() {
		this.spinnerQte.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int amount = (int) FenetreDetail.this.spinnerQte.getValue();
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				String prix = decimalFormat.format((FenetreDetail.this.tomateEnCours.getPrixTTC() * amount));
				FenetreDetail.this.textFieldPrixTotal.setText((prix + " " + '\u20ac' + " TTC"));
				FenetreDetail.this.quantite = amount;
			}
		});
	}

	private void construireComboTomates() {
		this.modeleTomatesSimilaires = new DefaultComboBoxModel<String>();
		List<Tomate> tomates = this.tomateEnCours.getTomatesApparentées();
		this.modeleTomatesSimilaires.addElement("Produits Similaires");
		for (Tomate tomate : tomates) {
			this.modeleTomatesSimilaires.addElement(tomate.getDésignation());
		}
		this.comboTomatesSimilaires.setModel(this.modeleTomatesSimilaires);
	}

	private void afficherDonnees() {
		this.construireComboTomates();
		this.lblImageTomate
				.setIcon(new ImageIcon(".\\src\\pictures\\format200\\" + this.tomateEnCours.getNomImage() + ".jpg"));
		this.textAreaDescription.setText(this.tomateEnCours.getDescription());
		this.textFieldPrixTotal.setText(("0,00 € TTC"));
		this.quantite = 0;
		this.textFieldPrixSachet.setText((this.tomateEnCours.getPrixTTC() + " " + '\u20ac' + " TTC"));
		this.textFieldQuantiteGraines.setText("" + this.tomateEnCours.getNombreDeGraines());
		this.spinnerQte.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0),
				Integer.valueOf(this.tomateEnCours.getNombreDeGraines()), Integer.valueOf(1)));
	}

	private void centerDialog() {
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		int dialogWidth = this.getWidth();
		int dialogHeight = this.getHeight();

		int dialogX = centerPoint.x - dialogWidth / 2;
		int dialogY = centerPoint.y - dialogHeight / 2;

		this.setLocation(dialogX, dialogY);
	}
}
