package fr.efrei.transfret;

public class TarifFluvial implements TarifTransport {

    @Override
    public double calculer(int distanceKm, double poidsKg, double volumeM3) {
        return Math.max(distanceKm * PRIX_PAR_KM + poidsKg * PRIX_PAR_KG, PRIX_MINIMUM);
    }

    private static final double PRIX_PAR_KM = 0.40;
    private static final double PRIX_PAR_KG = 0.02;
    private static final double PRIX_MINIMUM = 300;
}
