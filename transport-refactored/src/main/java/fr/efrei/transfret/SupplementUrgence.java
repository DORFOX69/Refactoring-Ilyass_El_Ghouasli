package fr.efrei.transfret;

public class SupplementUrgence extends DecorateurPrix {

    public SupplementUrgence(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        return demande.urgent() ? prix * COEFFICIENT : prix;
    }

    private static final double COEFFICIENT = 1.50;
}
