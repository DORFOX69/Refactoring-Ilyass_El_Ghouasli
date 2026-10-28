package fr.efrei.transfret;

public class TarifRail implements TarifTransport {

    @Override
    public double calculer(int distanceKm, double poidsKg, double volumeM3) {
        return Math.max(distanceKm * PRIX_PAR_KM + poidsKg * PRIX_PAR_KG, PRIX_MINIMUM);
    }

    private static final double PRIX_PAR_KM = 0.80;
    private static final double PRIX_PAR_KG = 0.07;
    private static final double PRIX_MINIMUM = 150;
}
