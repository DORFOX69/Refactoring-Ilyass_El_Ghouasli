package fr.efrei.transfret;

import java.util.List;

public class Camion extends Vehicule {

    protected boolean adr; // certification matières dangereuses

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax);
        this.adr = adr;
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.ROUTE
                && colis.stream().noneMatch(Colis::estRefrigere)
                && (adr || colis.stream().noneMatch(Colis::estDangereux));
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        System.out.println("Le camion " + immatriculation + " roule sur " + distanceKm + " km");
    }
}
