package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modele.Couleur;
import modele.GenerationArticles;
import modele.Panier;
import modele.Tomate;
import modele.Tomates;
import modele.TypeTomate;
import java.awt.FlowLayout;

public class FenetreProduits extends JFrame {

	private JPanel contentPane;

	private DefaultTableModel modeleTable;
	private DefaultComboBoxModel modeleTypeTomates;
	private DefaultComboBoxModel modeleCouleurTomates;

	private JTable table_1;
	private JTable table_2;
	private JTable table;

	private JComboBox combo_Type_Tomates;
	private JComboBox combo_Couleur_Tomates;

	private String typeTomate;
	private String couleurTomate;
	private JTextField textField;

	private FenetreDetail detail;

	private List<Tomate> lesTomates;
	private Panier monPanier;
	private JLabel lbl_panier;

	/**
	 * Create the frame.
	 */
	public FenetreProduits() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 720);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.centerDialog();
		this.construireControleurFenetre();

		this.monPanier = new Panier();

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(new Color(255, 255, 255));
		panel_menu.setBorder(new EmptyBorder(10, 5, 0, 5));
		this.getContentPane().add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_menu_gauche = new JPanel();
		panel_menu_gauche.setBackground(new Color(255, 255, 255));
		panel_menu.add(panel_menu_gauche);
		panel_menu_gauche.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setIcon(new ImageIcon("src/pictures/logo/logo_tomate.png"));
		panel_menu_gauche.add(lblNewLabel_3, BorderLayout.WEST);

		JButton btnConseils = new JButton("Nos conseils de culture");
		btnConseils.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConseils.setOpaque(false);
		btnConseils.setContentAreaFilled(false);
		btnConseils.setBorder(new LineBorder(Color.BLACK));
		construireControleurConseils(btnConseils);
		panel_menu.add(btnConseils);

		JPanel panel_menu_droite = new JPanel();
		panel_menu_droite.setBackground(new Color(255, 255, 255));
		panel_menu.add(panel_menu_droite);
		panel_menu_droite.setLayout(new BorderLayout(0, 0));

		JPanel panel_panierBorder = new JPanel();
		panel_panierBorder.setBackground(Color.WHITE);
		panel_panierBorder.setBorder(new EmptyBorder(0, 0, 0, 30));
		panel_menu_droite.add(panel_panierBorder, BorderLayout.EAST);
		panel_panierBorder.setLayout(new BorderLayout(0, 0));

		JPanel panel_panierGrid = new JPanel();
		panel_panierGrid.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_panierGrid.setBackground(new Color(255, 255, 255));
		panel_panierBorder.add(panel_panierGrid, BorderLayout.EAST);

		JButton btnPanier = new JButton("");
		construireControleurPanier(btnPanier);
		btnPanier.setIcon(new ImageIcon("src/pictures/logo/logo_panier.png"));
		btnPanier.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPanier.setMinimumSize(new Dimension(20, 10));
		btnPanier.setMaximumSize(new Dimension(60, 10));
		btnPanier.setMargin(new Insets(20, 30, 20, 30));
		btnPanier.setOpaque(false);
		btnPanier.setContentAreaFilled(false);
		btnPanier.setBorderPainted(false);
		btnPanier.setBorder(new LineBorder(Color.BLACK));
		btnPanier.setForeground(Color.WHITE);

		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String prix = decimalFormat.format(0.0F);
		
		JPanel panel_btnPanier = new JPanel();
		panel_btnPanier.setBorder(new EmptyBorder(0, 0, 0, 30));
		panel_panierGrid.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_panierGrid.add(btnPanier);
//		panel_panierGrid.add(panel_btnPanier);
		panel_btnPanier.setOpaque(false);
		
		lbl_panier = new JLabel("0,00 € TTC");
		lbl_panier.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_panier.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_panier.setBackground(Color.WHITE);
		panel_panierGrid.add(lbl_panier);

		JPanel panel_container = new JPanel();
		panel_container.setBackground(new Color(255, 255, 255));
		this.getContentPane().add(panel_container, BorderLayout.CENTER);
		panel_container.setLayout(new BorderLayout(0, 0));

		JPanel panel_combo_container = new JPanel();
		panel_combo_container.setBackground(new Color(255, 255, 255));
		panel_combo_container.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 0), 5), "Filtre de tomates",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panel_container.add(panel_combo_container, BorderLayout.SOUTH);
		panel_combo_container.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblTypeTomate = new JLabel("Type de tomate");
		lblTypeTomate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeTomate.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_combo_container.add(lblTypeTomate);

		this.combo_Type_Tomates = new JComboBox();
		this.construireControleurTypeTomate();
		this.construireComboBoxTypeTomates();
		panel_combo_container.add(this.combo_Type_Tomates);

		JLabel lblCouleurTomate = new JLabel("Couleur de tomate");
		lblCouleurTomate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleurTomate.setFont(new Font("Open Sans", Font.BOLD, 12));
		panel_combo_container.add(lblCouleurTomate);

		this.combo_Couleur_Tomates = new JComboBox();
		this.construireControleurComboCouleur();
		this.construireComboBoxCouleurTomate();
		panel_combo_container.add(this.combo_Couleur_Tomates);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		panel_container.add(scrollPane, BorderLayout.CENTER);

		this.modeleTable = new DefaultTableModel(new Object[] { "Tomate" }, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// Rendre toutes les cellules non éditables
				return false;
			}
		};
		this.table = new JTable(this.modeleTable);
		this.table.setBackground(new Color(234, 255, 239));
		this.table.setRowHeight(30);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		this.construireControleurChoixTomate();
		this.table.setFont(new Font("Arial", Font.PLAIN, 16));
		this.table.setSelectionForeground(new Color(255, 255, 255));
		this.table.setSelectionBackground(new Color(0, 128, 0));
		this.table.setGridColor(new Color(255, 255, 255));
		this.table.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(this.table);

		JLabel lbl_titre = new JLabel("Nos graines de tomate");
		lbl_titre.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lbl_titre);
		this.actualiserTableProduits();

	}

	private void construireControleurPanier(JButton btnPanier) {
		btnPanier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetrePanier fPanier = new FenetrePanier(FenetreProduits.this);
				Application.centerFrameOnScreen(fPanier);
				fPanier.setVisible(true);
			}
		});
	}

	private void construireControleurConseils(JButton btnConseils) {
		btnConseils.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreConseil conseil = new FenetreConseil();
				Application.centerFrameOnScreen(conseil);
				conseil.setVisible(true);
			}
		});
	}

	private void construireControleurFenetre() {
		this.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				FenetreProduits.this.actualiserPrixPanier();
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}

	private void construireControleurChoixTomate() {
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = FenetreProduits.this.table.getSelectedRow();
					String tomateSelectionnee = new String(FenetreProduits.this.lesTomates.get(index).getDésignation());
					Tomate tomate = GenerationArticles.générationDeBaseDesTomates().getTomate(tomateSelectionnee);
					FenetreProduits.this.detail = new FenetreDetail(tomate, FenetreProduits.this);
					FenetreProduits.this.detail.setVisible(true);
					System.out.println(FenetreProduits.this.monPanier);
				}
			}
		});
	}

	private void construireControleurComboCouleur() {
		this.combo_Couleur_Tomates.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				FenetreProduits.this.actualiserTableProduits();
			}
		});
	}

	private void construireControleurTypeTomate() {
		this.combo_Type_Tomates.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				FenetreProduits.this.actualiserTableProduits();
			}
		});
	}

	// remplit le JTable à partir d'un tableau tableauTomates
	private void remplirTableau(List<Tomate> listeTomates) {
		this.modeleTable.setRowCount(0);
		for (Tomate t : listeTomates) {
			this.modeleTable.addRow(new Object[] { t.getDésignation() });
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void construireComboBoxTypeTomates() {
		this.modeleTypeTomates = new DefaultComboBoxModel();
		this.modeleTypeTomates.addElement("Toutes les tomates");
		for (TypeTomate type : TypeTomate.values()) {
			this.modeleTypeTomates.addElement(type.getDénomination());
		}
		this.combo_Type_Tomates.setModel(this.modeleTypeTomates);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void construireComboBoxCouleurTomate() {
		this.modeleCouleurTomates = new DefaultComboBoxModel();
		for (Couleur couleur : Couleur.values()) {
			this.modeleCouleurTomates.addElement(couleur.getDénomination());
		}
		this.combo_Couleur_Tomates.setModel(this.modeleCouleurTomates);
	}

	private void actualiserTableProduits() {
		// Generer la liste temporaire de variables
		Tomates tomates = GenerationArticles.générationDeBaseDesTomates();
		String type = this.combo_Type_Tomates.getSelectedItem().toString();
		String couleur = this.combo_Couleur_Tomates.getSelectedItem().toString();

		// Mise à jour de la liste de tomates
		if (couleur == "Multicolore" && type == "Toutes les tomates") {
			this.lesTomates = tomates.getLesTomates();
		} else if (couleur == "Multicolore") {
			this.lesTomates = tomates.tomatesDeType(TypeTomate.getTypeTomate(type));
		} else if (type == "Toutes les tomates") {
			this.lesTomates = tomates.tomatesDeCouleur(Couleur.getCouleur(couleur));
		} else {
			this.lesTomates = tomates.tomatesDetypeDeCouleur(TypeTomate.getTypeTomate(type),
					Couleur.getCouleur(couleur));
		}

		// Mise à jour du modèle de table
		this.remplirTableau(this.lesTomates);
	}

	public void setPanier(Panier panier) {
		this.monPanier = panier;
	}

	public Panier getPanier() {
		return this.monPanier;
	}

	public void actualiserPrixPanier() {
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String prix = decimalFormat.format(this.monPanier.getMontantSansFraisDePorts());
		this.lbl_panier.setText(prix + " " + '\u20ac' + " TTC");
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