package fr.efrei.transfret;

import java.util.List;

public class Peniche extends Vehicule {

    public Peniche() {
        super(IMMATRICULATION, CAPACITE_MAX_KG);
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.FLUVIAL && colis.stream().noneMatch(Colis::estRefrigere);
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        System.out.println("La péniche " + immatriculation + " navigue sur " + distanceKm + " km de voies fluviales");
    }

    private static final String IMMATRICULATION = "PEN-0001";
    private static final double CAPACITE_MAX_KG = 2_500_000;
}
