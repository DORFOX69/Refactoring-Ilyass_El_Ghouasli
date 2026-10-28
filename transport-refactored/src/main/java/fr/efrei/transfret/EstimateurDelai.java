package fr.efrei.transfret;

public class EstimateurDelai {

    public int calculer(int distance, ModeTransport mode, boolean urgent) {
        int jours;
        switch (mode) {
            case ROUTE:
                double heures = distance / VITESSE_ROUTE_KMH;
                jours = (int) Math.ceil(heures / (urgent ? HEURES_CONDUITE_URGENT_PAR_JOUR : HEURES_CONDUITE_PAR_JOUR));
                break;
            case RAIL:
                jours = (int) Math.ceil(distance / DISTANCE_RAIL_PAR_JOUR) + DELAI_RAIL_JOURS - (urgent ? 1 : 0);
                break;
            case MER:
                jours = (int) Math.ceil(distance / DISTANCE_MER_PAR_JOUR) + DELAI_MER_JOURS;
                break;
            case AIR:
                jours = distance > SEUIL_DISTANCE_AIR_LONG && !urgent ? DELAI_AIR_LONG : DELAI_AIR_STANDARD;
                break;
            default:
                throw new IllegalArgumentException("Mode inconnu : " + mode);
        }
        return Math.max(jours, DELAI_MINIMUM);
    }

    private static final double VITESSE_ROUTE_KMH = 70.0;
    private static final double HEURES_CONDUITE_PAR_JOUR = 9;
    private static final double HEURES_CONDUITE_URGENT_PAR_JOUR = 18;
    private static final double DISTANCE_RAIL_PAR_JOUR = 1440.0;
    private static final int DELAI_RAIL_JOURS = 1;
    private static final double DISTANCE_MER_PAR_JOUR = 720.0;
    private static final int DELAI_MER_JOURS = 2;
    private static final int SEUIL_DISTANCE_AIR_LONG = 3000;
    private static final int DELAI_AIR_STANDARD = 1;
    private static final int DELAI_AIR_LONG = 2;
    private static final int DELAI_MINIMUM = 1;
}
