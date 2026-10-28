package fr.efrei.transfret;

import java.util.List;

public class Avion extends Vehicule {

    public Avion(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.AIR && colis.stream().noneMatch(Colis::estDangereux);
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        System.out.println("L'avion " + immatriculation + " roule sur la piste");
        System.out.println("L'avion " + immatriculation + " vole à " + ALTITUDE_CROISIERE_PIEDS + " pieds");
    }

    private static final int ALTITUDE_CROISIERE_PIEDS = 35000;
}
