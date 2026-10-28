package fr.efrei.transfret;

import java.util.Random;

public class GenerateurIdentifiantExpedition {

    private final Random random;
    private int sequence;

    public GenerateurIdentifiantExpedition(Random random) {
        this.random = random;
    }

    public String generer() {
        sequence++;
        return PREFIXE + sequence + SEPARATEUR + (BORNE_MINIMUM + random.nextInt(NOMBRE_IDENTIFIANTS));
    }

    private static final String PREFIXE = "EXP-";
    private static final String SEPARATEUR = "-";
    private static final int BORNE_MINIMUM = 1000;
    private static final int NOMBRE_IDENTIFIANTS = 9000;
}
