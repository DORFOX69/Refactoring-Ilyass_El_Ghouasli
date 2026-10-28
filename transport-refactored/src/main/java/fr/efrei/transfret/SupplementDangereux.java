package fr.efrei.transfret;

public class SupplementDangereux extends DecorateurPrix {

    public SupplementDangereux(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        return demande.contient(TypeMarchandise.DANGEREUX) ? prix * COEFFICIENT : prix;
    }

    private static final double COEFFICIENT = 1.30;
}
