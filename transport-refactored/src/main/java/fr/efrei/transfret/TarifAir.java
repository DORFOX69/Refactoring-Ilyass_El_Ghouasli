package fr.efrei.transfret;

public class TarifAir implements TarifTransport {

    @Override
    public double calculer(int distanceKm, double poidsKg, double volumeM3) {
        double poidsTaxable = Math.max(poidsKg, volumeM3 * COEFFICIENT_POIDS_VOLUMETRIQUE);
        return distanceKm * PRIX_PAR_KM + poidsTaxable * PRIX_PAR_KG;
    }

    private static final double COEFFICIENT_POIDS_VOLUMETRIQUE = 167;
    private static final double PRIX_PAR_KM = 0.60;
    private static final double PRIX_PAR_KG = 4.50;
}
