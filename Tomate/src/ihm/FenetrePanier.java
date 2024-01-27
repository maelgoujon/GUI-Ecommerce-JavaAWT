package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modele.GenerationArticles;
import modele.Panier;
import modele.Tomate;
import modele.Tomates;

public class FenetrePanier extends JFrame {

	private JPanel contentPane;

	private JTextField txtSousTotal;
	private JTextField txtTotal;
	private JTable tableProduit;
	private JTextField txtForfait;
	private DefaultTableModel modeleTable;

	private float montantSansFraisDePort;
	private float montantTotal;

	private Panier monPanier;
	private FenetreProduits fenetreAppel;

	/**
	 * Create the frame.
	 */
	public FenetrePanier(JFrame parent) {

		this.initialiserAttributs(parent);
		this.centerDialog();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		this.contentPane.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblImagePanier = new JLabel("");
		lblImagePanier.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblImagePanier);

		JLabel lblVotrePanier = new JLabel("Votre panier");
		lblVotrePanier.setForeground(new Color(0, 128, 0));
		lblVotrePanier.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		panelTitle.add(lblVotrePanier);

		JPanel panelProduitPrix = new JPanel();
		this.contentPane.add(panelProduitPrix, BorderLayout.CENTER);
		panelProduitPrix.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneProduits = new JScrollPane(this.tableProduit);
		panelProduitPrix.add(scrollPaneProduits, BorderLayout.CENTER);

		this.modeleTable = new DefaultTableModel(
				new Object[] { "Image", "Produit", "Prix unitaire", "Quantité", "Total" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0 && column != 1 && column != 2 && column != 4;
			}

		};
				
		construireControleurModelTableModification();
		this.tableProduit = new JTable() {
			@Override
			public Class<?> getColumnClass(int colonne) {
				return this.getValueAt(0, colonne).getClass();
			}
		};
		this.tableProduit.setModel(this.modeleTable);
		this.tableProduit.setEnabled(true);
		this.tableProduit.setRowHeight(40);
		this.tableProduit.getTableHeader().setReorderingAllowed(false);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(this.modeleTable);
		sorter.setComparator(0, String.CASE_INSENSITIVE_ORDER);
		this.tableProduit.setRowSorter(sorter);

		scrollPaneProduits.setViewportView(this.tableProduit);

		JPanel panelPrix = new JPanel();
		panelProduitPrix.add(panelPrix, BorderLayout.SOUTH);
		panelPrix.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelMontant = new JPanel();
		panelMontant.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelPrix.add(panelMontant);
		panelMontant.setLayout(new GridLayout(0, 2, 5, 0));

		JPanel panelLblTotal = new JPanel();
		panelMontant.add(panelLblTotal);
		panelLblTotal.setLayout(new GridLayout(0, 1, 0, 5));

		JLabel lblSousTotal = new JLabel("Sous-Total :");
		lblSousTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLblTotal.add(lblSousTotal);

		JLabel lblExpédition = new JLabel("Expédition (forfait) :");
		lblExpédition.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLblTotal.add(lblExpédition);

		JLabel lblTotal = new JLabel("TOTAL :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setForeground(new Color(0, 128, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelLblTotal.add(lblTotal);

		JPanel panelTxtTotal = new JPanel();
		panelMontant.add(panelTxtTotal);
		panelTxtTotal.setLayout(new GridLayout(0, 1, 0, 5));

		this.txtSousTotal = new JTextField(this.monPanier.getMontantTotal() - Panier.FORFAIT + " \u20ac TTC");
		this.txtSousTotal.setFont(new Font("Tahoma", Font.BOLD, 10));
		this.txtSousTotal.setBackground(new Color(255, 255, 204));
		this.txtSousTotal.setEditable(false);
		panelTxtTotal.add(this.txtSousTotal);
		this.txtSousTotal.setColumns(10);

		this.txtForfait = new JTextField(Panier.FORFAIT + " \u20ac TTC");
		this.txtForfait.setFont(new Font("Tahoma", Font.BOLD, 10));
		this.txtForfait.setBackground(new Color(255, 255, 204));
		this.txtForfait.setEditable(false);
		panelTxtTotal.add(this.txtForfait);
		this.txtForfait.setColumns(10);

		this.txtTotal = new JTextField(this.montantTotal + " \u20ac TTC");
		this.txtTotal.setBackground(new Color(204, 255, 204));
		this.txtTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.txtTotal.setForeground(new Color(0, 128, 0));
		this.txtTotal.setEditable(false);
		panelTxtTotal.add(this.txtTotal);
		this.txtTotal.setColumns(10);

		JPanel panelButtons = new JPanel();
		this.contentPane.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnValider = new JButton("Valider le panier");
		construireControleurBoutonValider(btnValider);
		panelButtons.add(btnValider);

		JButton btnVider = new JButton("Vider le panier");
		construireControleurBoutonVider(btnVider);
		panelButtons.add(btnVider);

		JButton btnContinuer = new JButton("Continuer les achats");
		construireControleurBoutonContinuer(btnContinuer);
		panelButtons.add(btnContinuer);
		this.actualiserPanier();
	}

	private void construireControleurModelTableModification() {
		this.modeleTable.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int row = e.getFirstRow();
					int column = e.getColumn();
					DefaultTableModel model = (DefaultTableModel) e.getSource();
					Object data = model.getValueAt(row, column);
					Object tomateObjet = model.getValueAt(row, 1);
					int nouvelleQuantite = (int) data;
					String tomateString = tomateObjet.toString();
					Tomate tomateEncours = GenerationArticles.générationDeBaseDesTomates().getTomate(tomateString);
					if (nouvelleQuantite < 0) {
						FenetrePanier.this.actualiserPanier();
					} else if (nouvelleQuantite == 0) {
						FenetrePanier.this.monPanier.retirerTomate(tomateEncours);
						FenetrePanier.this.actualiserPanier();
					} else {
						FenetrePanier.this.monPanier.modifierQuantité(tomateEncours, nouvelleQuantite);
						FenetrePanier.this.actualiserPanier();
					}
				}
			}
		});
	}

	private void construireControleurBoutonValider(JButton btnValider) {
		btnValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FenetrePanier.this.monPanier.estPanierVide()) {
					JOptionPane.showMessageDialog(null, "Panier vide !");
				} else {
					FenetreCoordonnees fFacture = new FenetreCoordonnees(FenetrePanier.this.monPanier);
					fFacture.setVisible(true);
					Application.centerFrameOnScreen(fFacture);
					FenetrePanier.this.dispose();
				}
			}
		});
	}

	private void construireControleurBoutonContinuer(JButton btnContinuer) {
		btnContinuer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetrePanier.this.dispose();
			}
		});
	}

	private void construireControleurBoutonVider(JButton btnVider) {
		btnVider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!FenetrePanier.this.monPanier.estPanierVide()) {
					int response = JOptionPane.showConfirmDialog(null, "Souhaitez-vous vider votre panier ?", "Vider le panier",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {
				    	FenetrePanier.this.monPanier.viderPanier();
						FenetrePanier.this.actualiserPanier();
				    }
				} else {
					JOptionPane.showMessageDialog(null, "Panier vide !");
				}
			}
		});
	}

	private void initialiserAttributs(JFrame parent) {
		this.fenetreAppel = (FenetreProduits) parent;
		this.monPanier = this.fenetreAppel.getPanier();
		this.montantSansFraisDePort = this.monPanier.getMontantSansFraisDePorts();
		this.montantTotal = this.monPanier.getMontantTotal();
	}

	private void actualiserModeleTable() {
		this.modeleTable.setRowCount(0);
		List<Tomate> listeProduit = this.monPanier.getListeProduit();
		List<Integer> listeQuantite = this.monPanier.getListeQuantité();
		List<Float> listeTotal = this.monPanier.getListeTotal();
		String designation;
		ImageIcon image;
		int quantite;
		float prix;
		float total;

		for (int i = 0; i < listeProduit.size(); i++) {
			// Atribution des valeurs aux variables
			designation = listeProduit.get(i).getDésignation();
			image = new ImageIcon(".\\src\\pictures\\format40\\" + listeProduit.get(i).getNomImage() + ".jpg");
			quantite = listeQuantite.get(i);
			prix = listeProduit.get(i).getPrixTTC();
			total = listeTotal.get(i);
			// Ajout d'une ligne produit dans le panier
			this.modeleTable.addRow(new Object[] { image, designation, prix, quantite, total });
		}
	}

	private void actualiserPanier() {
		this.actualiserModeleTable();
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String montantSansFraisDePort;
		String montantTotal;
		if (this.monPanier.estPanierVide()) {
			montantSansFraisDePort = decimalFormat.format(0.0F);
			montantTotal = decimalFormat.format(0.0F);
		} else {
			montantSansFraisDePort = decimalFormat.format(this.monPanier.getMontantSansFraisDePorts());
			montantTotal = decimalFormat.format(this.monPanier.getMontantTotal());
		}
		this.txtSousTotal.setText(montantSansFraisDePort + " \u20ac TTC");
		this.txtTotal.setText(montantTotal + " \u20ac TTC");
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