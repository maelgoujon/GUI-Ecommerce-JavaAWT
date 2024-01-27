package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modele.Coordonnees;
import modele.Facture;
import modele.Panier;

public class FenetreFacture extends JFrame implements Printable {

	private JPanel contentPane;
	private Facture facture;
	
	/**
	 * Create the frame.
	 */
	public FenetreFacture(Facture facture) {

		this.facture = facture;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 756, 582);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		this.contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		panelTop.add(panelTitle, BorderLayout.NORTH);

		JLabel lbl_votre_facture = new JLabel("Votre Facture ");
		lbl_votre_facture.setForeground(new Color(0, 128, 0));
		lbl_votre_facture.setFont(new Font("Segoe Print", Font.BOLD, 25));
		panelTitle.add(lbl_votre_facture);

		JLabel lbl_logo = new JLabel("");
		lbl_logo.setIcon(new ImageIcon("/ihm/logo_tomate.png"));
		panelTitle.add(lbl_logo);

		JPanel panelMerci = new JPanel();
		panelMerci.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 128, 0)));
		panelMerci.setBackground(new Color(255, 255, 255));
		FlowLayout fl_panelMerci = (FlowLayout) panelMerci.getLayout();
		fl_panelMerci.setAlignment(FlowLayout.LEFT);
		panelTop.add(panelMerci, BorderLayout.SOUTH);

		JLabel lbl_merci = new JLabel("Merci de votre visite !");
		lbl_merci.setBorder(new EmptyBorder(0, 20, 0, 0));
		lbl_merci.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lbl_merci.setForeground(new Color(0, 153, 0));
		panelMerci.add(lbl_merci);

		JPanel panelButtons = new JPanel();
		FlowLayout fl_panelButtons = (FlowLayout) panelButtons.getLayout();
		fl_panelButtons.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(panelButtons, BorderLayout.SOUTH);

		JButton btn_imprimer = new JButton("Imprimer");
		this.construireImprimer(btn_imprimer);
		panelButtons.add(btn_imprimer);

		JButton btn_quitter = new JButton("Quitter");
		this.construireQuitter(btn_quitter);
		panelButtons.add(btn_quitter);

		JScrollPane scrollPane = new JScrollPane();

		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea(this.facture.toString());
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new EmptyBorder(25,25,25,25));
		scrollPane.setViewportView(textArea);
	}

	public void construireImprimer(JButton btn_imprimer) {
		btn_imprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FenetreFacture.this.printFactureToPDF();

			}
		});
	}

	public void construireQuitter(JButton btn_quitter) {
		btn_quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	// Print to pdf
	public void printFactureToPDF() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                System.out.println("Annulation d'imprimerie");
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }
        
        double xLeft = pageFormat.getImageableX();
		double yTop = pageFormat.getImageableY();
		double width = pageFormat.getImageableWidth();
		double height = pageFormat.getImageableHeight();

		// Convert 2.1 cm to points (1 inch = 72 points)
		double xCoordinate = 2.1 * 72 / 2.54; // 2.1 cm in points
		double yCoordinate = 2.1 * 72 / 2.54; // 2.1 cm in points

		// Create a Graphics2D object for printing
		Graphics2D g2d = (Graphics2D) graphics;

		// Translate to the specified coordinates
		g2d.translate(xLeft + xCoordinate, yTop + yCoordinate);
		
		// Draw the "Hello world" text
		Font font = new Font("Ariel", Font.PLAIN, 12);
		g2d.setFont(font);
		
		String[] strArr = this.facture.toString().split("\n");
		
		for (String str : strArr) {
			g2d.drawString(str, 0, 0);
			FontMetrics fm = g2d.getFontMetrics();
			g2d.translate(0, fm.getHeight());
		}

        return Printable.PAGE_EXISTS;
    }

}