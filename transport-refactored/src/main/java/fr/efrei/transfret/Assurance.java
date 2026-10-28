package fr.efrei.transfret;

public class Assurance extends DecorateurPrix {

    public Assurance(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        if (!demande.assurance()) {
            return prix;
        }
        return prix + Math.max(demande.valeurTotale() * TAUX, MONTANT_MINIMUM);
    }

    private static final double TAUX = 0.02;
    private static final double MONTANT_MINIMUM = 15;
}
