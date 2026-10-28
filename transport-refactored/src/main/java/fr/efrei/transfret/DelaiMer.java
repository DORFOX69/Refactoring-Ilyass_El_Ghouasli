package fr.efrei.transfret;

public class DelaiMer implements StrategieDelai {

    @Override
    public int calculer(int distanceKm, boolean urgent) {
        return (int) Math.ceil(distanceKm / DISTANCE_PAR_JOUR) + DELAI_SUPPLEMENTAIRE_JOURS;
    }

    private static final double DISTANCE_PAR_JOUR = 720;
    private static final int DELAI_SUPPLEMENTAIRE_JOURS = 2;
}
