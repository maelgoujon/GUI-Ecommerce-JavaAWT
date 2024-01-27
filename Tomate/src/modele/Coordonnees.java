package modele;

public class Coordonnees {

	private String nom;
	private String prenom;
	private String adresse;
	private String compltAdresse;
	private String codePostal;
	private String ville;
	private String tel;
	private String mail;

	public Coordonnees(String nom, String prenom, String adresse, String compltAdresse, String cp, String ville,
			String tel, String mail) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.compltAdresse = compltAdresse;
		this.codePostal = cp;
		this.ville = ville;
		this.tel = tel;
		this.mail = mail;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public String getCompltAdresse() {
		return this.compltAdresse;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	public String getTel() {
		return this.tel;
	}

	public String getMail() {
		return this.mail;
	}

	@Override
	public String toString() {
		return "### INFORMATIONS CLIENT ###\n\nIdentité : " + this.getNom() + " " + this.getPrenom() + "\nAdresse : "
				+ this.getAdresse() + "\nComplement d'adresse : " + this.getCompltAdresse() + "\nCode Postal : "
				+ this.getCodePostal() + "\nVille : " + this.getVille() + "\n tel : " + this.getTel() + "\nMail : "
				+ this.getMail();
	}
}