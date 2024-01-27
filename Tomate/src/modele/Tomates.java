package modele;

import java.util.LinkedList;
import java.util.List;

public class Tomates {

	public static final String CONSEILS_DE_CULTURE_TITRE = "Conseils de culture\r\n" + "Semis : mars-avril\r\n\n"
			+ "Repiquage : après les gelées\r\n" + "Récolte : juillet à septembre, voire octobre\n\n";

	public static final String CULTIVEZ = new String("Cultivez vos tomates\n");

	public static final String TIPS = new String("Astuces: \n");

	public static final String EMPLACEMENT_TITRE = new String("Emplacement :\n");
	public static final String EMPLACEMENT_CONTENU = new String("Les tomates sont frileuses !\r\n" + "\r\n"
			+ "Exposition : Les tomates sont originaires d’Amérique du Sud…et leurs gènes s’en souviennent !\r\n"
			+ "Réservez-leur l’endroit le mieux ensoleillé de votre jardin.\r\n\n");

	public static final String SEMIS_TITRE = new String("Semis :\n");
	public static final String SEMIS_CONTENU = new String(
			"Démarrez vos semis en petite terrine dès mars / avril (15/20° nuit et jour) dans du terreau à semis, "
					+ "couvrez vos graines de 0,5 cm, tassez doucement et maintenez humide. "
					+ "Repiquez vos semis lorsqu’ils font 5 cm, dans des godets avec du terreau 1/3 de fumier ou du compost , enterrez jusqu’au première feuilles.\r\n\\n");

	public static final String PLANT_TITRE = new String("Plantation:\n");
	public static final String PLANT_CONTENU = new String(
			"Après les Saints de glaces, plantez vos pieds de tomates dès qu’ils auront atteint 15 cm, "
					+ "enterrez-les jusqu’aux premières feuilles en pleine terre dans un trou avec du fumier, du compost ou quelques feuilles d’ortie si besoin, "
					+ "installez vos tuteurs espacés de 70 cm.\r\n\n");

	public static final String ARROSAGE_TITRE = new String("Arrosage:\n");
	public static final String ARROSAGE_CONTENU = new String(
			"Arrosez abondamment les 3 premiers jours, arrêtez les 15 jours suivants puis arrosez régulièrement.\r\n\n");

	public static final String BASILIC_TITRE = new String("Basilic :\n");
	public static final String BASILIC_CONTENU = new String(
			"Plantez du basilic entre vos pieds de tomates, ils s’entraident l’un l’autre…\n\n");

	public static final String PAILLE_TITRE = new String("Paille :\n");
	public static final String PAILLE_CONTENU = new String(
			"Pensez à pailler ! ainsi vous garderez beaucoup plus facilement la terre humide et espacerez les désherbages.\r\n\n");

	public static final String MALADIES_TITRE = new String("Maladies :\n");
	public static final String MALADIES_CONTENU = new String(
			"Maladies pouvant toucher les tomates: mildiou (ne pas arroser les feuilles et supprimer celles qui touchent le sol).\r\n\n");

	private List<Tomate> lesTomates;

	public Tomates() {
		this.lesTomates = new LinkedList<Tomate>();
	}

	public void addTomates(List<Tomate> tomates) {
		this.lesTomates.addAll(tomates);
	}

	public List<Tomate> getLesTomates() {
		return this.lesTomates;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("Liste des graines de tomates : \n\n");
		for (Tomate graine : this.lesTomates) {
			res.append(graine.toString() + '\n');
		}
		return res.toString();
	}

	public List<Tomate> tomatesDeType(TypeTomate typeTomate) {
		return this.tomatesDeTypeAvecListe(typeTomate, this.lesTomates);
	}

	private List<Tomate> tomatesDeTypeAvecListe(TypeTomate typeTomate, List<Tomate> lesTomates) {
		List<Tomate> tomates = new LinkedList<>();
		for (Tomate tomate : lesTomates) {
			if (tomate.getTypeGraine() == typeTomate) {
				tomates.add(tomate);
			}
		}
		return tomates;
	}

	public List<Tomate> tomatesDeCouleur(Couleur couleur) {
		return this.tomatesDeCouleurAvecListe(couleur, this.lesTomates);
	}

	private List<Tomate> tomatesDeCouleurAvecListe(Couleur couleur, List<Tomate> lesTomates) {
		List<Tomate> tomates = new LinkedList<>();
		for (Tomate tomate : lesTomates) {
			if (tomate.getCouleur().equals(couleur)) {
				tomates.add(tomate);
			}
		}
		return tomates;
	}

	public List<Tomate> tomatesDetypeDeCouleur(TypeTomate typeTomate, Couleur couleur) {
		List<Tomate> tomates = new LinkedList<>();
		for (Tomate tomate : this.lesTomates) {
			if (tomate.getCouleur().equals(couleur) && tomate.getTypeGraine().equals(typeTomate)) {
				tomates.add(tomate);
			}
		}
		return tomates;
	}

	public Tomate getTomate(String designation) {
		for (Tomate graine : this.lesTomates) {
			if (graine.getDésignation().equals(designation))
				return graine;
		}
		return null;
	}

}