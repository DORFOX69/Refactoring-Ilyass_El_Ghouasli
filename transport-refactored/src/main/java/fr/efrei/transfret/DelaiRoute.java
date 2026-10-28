package fr.efrei.transfret;

public class DelaiRoute implements StrategieDelai {

    @Override
    public int calculer(int distanceKm, boolean urgent) {
        double heuresParJour = urgent ? HEURES_URGENT_PAR_JOUR : HEURES_PAR_JOUR;
        return (int) Math.ceil(distanceKm / VITESSE_KMH / heuresParJour);
    }

    private static final double VITESSE_KMH = 70;
    private static final double HEURES_PAR_JOUR = 9;
    private static final double HEURES_URGENT_PAR_JOUR = 18;
}
