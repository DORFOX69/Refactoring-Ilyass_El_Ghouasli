package fr.efrei.transfret;

public class DelaiAir implements StrategieDelai {

    @Override
    public int calculer(int distanceKm, boolean urgent) {
        return distanceKm > SEUIL_LONG_COURRIER && !urgent ? DELAI_LONG_COURRIER : DELAI_STANDARD;
    }

    private static final int SEUIL_LONG_COURRIER = 3000;
    private static final int DELAI_LONG_COURRIER = 2;
    private static final int DELAI_STANDARD = 1;
}
