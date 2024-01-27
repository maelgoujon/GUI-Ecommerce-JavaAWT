package modele;

public class TestPanier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Panier monpanier = new Panier();
		Tomate maTomate = GenerationArticles.GenerationTomates().get(10);
		System.out.println(maTomate.getDésignation());
		monpanier.addTomate(maTomate, 20);
		System.out.println(monpanier.toString());
		monpanier.modifierQuantité(maTomate, 10);
		System.out.println(monpanier);

	}

}
