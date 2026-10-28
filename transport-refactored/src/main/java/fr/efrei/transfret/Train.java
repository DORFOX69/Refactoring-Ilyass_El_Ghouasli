package fr.efrei.transfret;

import java.util.List;

public class Train extends Vehicule {

    public Train(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.RAIL;
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        System.out.println("Le train " + immatriculation + " circule sur " + distanceKm + " km de voies ferrées");
    }
}
