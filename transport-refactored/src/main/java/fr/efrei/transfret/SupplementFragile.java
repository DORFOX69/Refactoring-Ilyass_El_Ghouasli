package fr.efrei.transfret;

public class SupplementFragile extends DecorateurPrix {

    public SupplementFragile(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        return demande.contient(TypeMarchandise.FRAGILE) ? prix * COEFFICIENT : prix;
    }

    private static final double COEFFICIENT = 1.15;
}
