package fr.efrei.transfret;

import java.util.List;

public class CamionFrigorifique extends Camion {

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.ROUTE && colis.stream().allMatch(Colis::estRefrigere);
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        super.partir(distanceKm, colis);
        System.out.println("Réfrigération activée à " + TEMPERATURE_CIBLE + "°C sur " + immatriculation);
    }

    private static final double TEMPERATURE_CIBLE = 4;
}
