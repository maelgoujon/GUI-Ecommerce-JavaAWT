package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import modele.Tomates;

public class FenetreConseil extends JFrame {

	/**
	 * Create the frame.
	 */
	public FenetreConseil() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 720, 480);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lbl_titre = new JLabel("Nos conseils");
		lbl_titre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_titre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.getContentPane().add(lbl_titre, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JTextArea textArea_conseils_titre = new JTextArea(Tomates.CONSEILS_DE_CULTURE_TITRE + Tomates.ARROSAGE_TITRE
				+ Tomates.ARROSAGE_CONTENU + Tomates.BASILIC_TITRE + Tomates.BASILIC_CONTENU + Tomates.MALADIES_TITRE
				+ Tomates.MALADIES_CONTENU + Tomates.PAILLE_TITRE + Tomates.PAILLE_CONTENU + Tomates.PLANT_TITRE
				+ Tomates.PLANT_CONTENU);
		textArea_conseils_titre.setFont(new Font("Arial", Font.PLAIN, 16));
		textArea_conseils_titre.setCaretColor(Color.WHITE);
		textArea_conseils_titre.setBorder(new EmptyBorder(50, 50, 0, 50));
		textArea_conseils_titre.setLineWrap(true);
		textArea_conseils_titre.setEditable(false);
		textArea_conseils_titre.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea_conseils_titre);

	}

}
