package modele;

import java.util.LinkedList;
import java.util.List;

public class Panier {

	public static float FORFAIT = 4.50F;

	private List<Tomate> listeProduit;
	private List<Integer> listeQuantité;
	private List<Float> listeTotal;

	public Panier() {
		this.listeProduit = new LinkedList<>();
		this.listeQuantité = new LinkedList<>();
		this.listeTotal = new LinkedList<>();
	}

	public float getPrixTomates(Tomate tomate) throws IllegalArgumentException {
		if (!this.listeProduit.contains(tomate)) {
			throw new IllegalArgumentException("tomate non présente dans le panier");
		}
		int index = this.listeProduit.indexOf(tomate);
		return (Math.round(this.listeTotal.get(index)*100)/100);
	}

	public float getMontantSansFraisDePorts() {
		float total = 0.0F;
		for (Float montant : this.listeTotal) {
			total += montant.floatValue();
		}
		total = Math.round(total *100) / 100;
		return total;
	}

	public float getMontantTotal() {
		float total = this.getMontantSansFraisDePorts();
		total += Panier.FORFAIT;
		total = Math.round(total *100) / 100;
		return total;
	}

	public List<Tomate> getListeProduit() {
		return this.listeProduit;
	}

	public List<Integer> getListeQuantité() {
		return this.listeQuantité;
	}

	public List<Float> getListeTotal() {
		return this.listeTotal;
	}

	public void modifierQuantité(Tomate tomate, int quantite) throws IllegalArgumentException {
		if (!this.listeProduit.contains(tomate)) {
			throw new IllegalArgumentException("Tomate indisponible dans le panier");
		}
		int index = this.listeProduit.indexOf(tomate);
		this.listeQuantité.set(index, quantite);
		float total = tomate.getPrixTTC() * quantite;
		this.listeTotal.set(index, total);
	}

	public void addTomate(Tomate tomateCourante, int quantite) {
		if (this.listeProduit.contains(tomateCourante)) {
			int index = this.listeProduit.indexOf(tomateCourante);
			int quantiteInitiale = this.listeQuantité.get(index);
			int quantiteFinale = quantiteInitiale + quantite;
			float total = tomateCourante.getPrixTTC() * quantiteFinale;
			this.listeQuantité.set(index, quantiteFinale);
			this.listeTotal.set(index, total);
		} else {
			float montant = tomateCourante.getPrixTTC() * quantite;
			this.listeProduit.add(tomateCourante);
			this.listeQuantité.add(quantite);
			this.listeTotal.add(montant);
		}
	}

	public void viderPanier() {
		if (!this.estPanierVide()) {
			this.listeProduit.clear();
			this.listeQuantité.clear();
			this.listeTotal.clear();
		}
	}

	public void retirerTomate(Tomate tomate) throws IllegalArgumentException {
		if (!this.listeProduit.contains(tomate)) {
			throw new IllegalArgumentException("Tomate indisponible dans le panier");
		}
		int index = this.listeProduit.indexOf(tomate);
		this.listeProduit.remove(index);
		this.listeQuantité.remove(index);
		this.listeTotal.remove(index);
	}

	public boolean estPanierVide() {
		return this.listeProduit.isEmpty() && this.listeQuantité.isEmpty() && this.listeTotal.isEmpty();
	}

	@Override
	public String toString() {
		int index;
		String panier = "";
		for (Tomate tomate : this.listeProduit) {
			index = this.listeProduit.indexOf(tomate);
			panier += (index + 1) + " : " + tomate.getDésignation() + ", PU : " + tomate.getPrixTTC() + '\u20ac'
					+ ", quantité commandée : " + this.listeQuantité.get(index) + ", montant total : "
					+ this.listeTotal.get(index) + '\u20ac' + "\n";
		}
		return panier;
	}

}
