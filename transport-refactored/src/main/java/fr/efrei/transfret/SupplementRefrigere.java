package fr.efrei.transfret;

public class SupplementRefrigere extends DecorateurPrix {

    public SupplementRefrigere(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        return demande.contient(TypeMarchandise.REFRIGERE) ? prix * COEFFICIENT : prix;
    }

    private static final double COEFFICIENT = 1.20;
}
