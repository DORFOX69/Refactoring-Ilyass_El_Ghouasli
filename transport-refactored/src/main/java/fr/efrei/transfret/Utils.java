package fr.efrei.transfret;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    private static final String FORMAT_DATE_PATTERN = "dd/MM/yyyy";
    private static final double CENTIMES_PAR_EURO = 100.0;
    public static final int DISTANCE_INCONNUE = -1;
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern(FORMAT_DATE_PATTERN);

    public static double arrondir(double valeur) {
        return Math.round(valeur * CENTIMES_PAR_EURO) / CENTIMES_PAR_EURO;
    }

    public static String formaterDate(Date d) {
        if (d == null) {
            return "";
        }
        return d.toInstant().atZone(ZoneId.systemDefault()).format(FORMAT_DATE);
    }

    public static String formaterPrix(double prix) {
        return String.format("%.2f €", prix);
    }

    public static boolean emailValide(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // retourne la distance en km entre deux villes, -1 si inconnue
    public static int getDistance(String villeA, String villeB) {
        String a = villeA.toUpperCase();
        String b = villeB.toUpperCase();
        if ((a.equals("PARIS") && b.equals("LYON")) || (a.equals("LYON") && b.equals("PARIS"))) {
            return 465;
        } else if ((a.equals("PARIS") && b.equals("MARSEILLE")) || (a.equals("MARSEILLE") && b.equals("PARIS"))) {
            return 775;
        } else if ((a.equals("PARIS") && b.equals("LILLE")) || (a.equals("LILLE") && b.equals("PARIS"))) {
            return 225;
        } else if ((a.equals("PARIS") && b.equals("BORDEAUX")) || (a.equals("BORDEAUX") && b.equals("PARIS"))) {
            return 585;
        } else if ((a.equals("LYON") && b.equals("MARSEILLE")) || (a.equals("MARSEILLE") && b.equals("LYON"))) {
            return 315;
        } else if ((a.equals("LE HAVRE") && b.equals("NEW YORK")) || (a.equals("NEW YORK") && b.equals("LE HAVRE"))) {
            return 5850;
        } else if ((a.equals("MARSEILLE") && b.equals("ALGER")) || (a.equals("ALGER") && b.equals("MARSEILLE"))) {
            return 760;
        } else if ((a.equals("PARIS") && b.equals("NEW YORK")) || (a.equals("NEW YORK") && b.equals("PARIS"))) {
            return 5840;
        }
        return DISTANCE_INCONNUE;
    }
}
