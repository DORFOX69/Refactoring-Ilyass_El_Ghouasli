package fr.efrei.transfret;

public class TarifRoute implements TarifTransport {

    @Override
    public double calculer(int distanceKm, double poidsKg, double volumeM3) {
        return distanceKm * PRIX_PAR_KM + poidsKg * PRIX_PAR_KG;
    }

    private static final double PRIX_PAR_KM = 1.20;
    private static final double PRIX_PAR_KG = 0.10;
}
