package fr.efrei.transfret;

public class SupplementHorsGabarit extends DecorateurPrix {

    public SupplementHorsGabarit(ComposantPrix composant) {
        super(composant);
    }

    @Override
    public double calculer(DemandePrix demande) {
        double prix = composant.calculer(demande);
        double coefficient = demande.colis().stream().anyMatch(Colis::estHorsGabarit)
                ? demande.mode().coefficientHorsGabarit()
                : COEFFICIENT_NEUTRE;
        return prix * coefficient;
    }

    private static final double COEFFICIENT_NEUTRE = 1.0;
}
