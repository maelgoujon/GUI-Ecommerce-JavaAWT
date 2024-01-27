package modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Facture {

	private Coordonnees coordonnees;
	private Panier panier;
	private Paiement paiement;;
	private boolean abonnement;

	public Facture(Panier panier, Coordonnees coordonnees, boolean abonnement) {
		this.coordonnees = coordonnees;
		this.paiement = Paiement.NONE;
		this.abonnement = abonnement;
		this.panier = panier;
	}

	public Coordonnees getCoordonnées() {
		return this.coordonnees;
	}

	public Panier getPanier() {
		return this.panier;
	}

	public String printMoyenPaiement() {
		return this.paiement.toString();
	}

	public boolean getAbonnement() {
		return this.abonnement;
	}

	public void setPaiement(String paiement) {
		this.paiement = Paiement.getMoyenPaiement(paiement);
	}

	public float montantFactureTotal() {
		return this.panier.getMontantTotal();
	}

	public float montantFactureSousTotal() {
		return this.panier.getMontantSansFraisDePorts();
	}

	public float montantExpéditionForfaitFrance() {
		return Panier.FORFAIT;
	}

	@Override
	public String toString() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'le' EEEE d MMMM yyyy 'à' HH'h'mm:ss",
				Locale.FRENCH);
		String formattedDateTime = dateTime.format(formatter);
		String facture = "Toulouse, " + formattedDateTime + "\n\n";
		facture += this.coordonnees.toString() + "\n\n";
		facture += this.panier.toString() + "\n\n";
		facture += "Prix de votre commande : " + this.montantFactureSousTotal() + '\u20ac' + " TTC"
				+ "\nExpedition forfait France : " + this.montantExpéditionForfaitFrance() + '\u20ac' + " TTC"
				+ "\nPrix total TTC : " + this.montantFactureTotal() + '\u20ac' + " TTC";
		return facture;
	}

}