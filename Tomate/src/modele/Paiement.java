package modele;

public enum Paiement {
	CB("Paiement par carte bancaire"), PAYPAL("Paiement via Paypal"), CHEQUE("Paiement par cheque"),
	NONE("Pas de moyen de paiement");

	private final String dénomination;

	private Paiement(String dénomination) {
		this.dénomination = dénomination;
	}

	public String getDénomination() {
		return this.dénomination;
	}

	public static Paiement getMoyenPaiement(String dénomination) {
		for (Paiement paiement : Paiement.values()) {
			if (paiement.getDénomination().equals(dénomination)) {
				return paiement;
			}
		}
		return null;
	}
}