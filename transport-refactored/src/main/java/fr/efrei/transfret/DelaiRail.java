package fr.efrei.transfret;

public class DelaiRail implements StrategieDelai {

    @Override
    public int calculer(int distanceKm, boolean urgent) {
        return (int) Math.ceil(distanceKm / DISTANCE_PAR_JOUR) + DELAI_SUPPLEMENTAIRE_JOURS
                - (urgent ? REDUCTION_URGENCE_JOURS : 0);
    }

    private static final double DISTANCE_PAR_JOUR = 1440;
    private static final int DELAI_SUPPLEMENTAIRE_JOURS = 1;
    private static final int REDUCTION_URGENCE_JOURS = 1;
}
