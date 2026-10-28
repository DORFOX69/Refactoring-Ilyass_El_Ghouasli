package fr.efrei.transfret;

import java.util.List;

public class AffectationVehicule {

    private final Flotte flotte;

    public AffectationVehicule(Flotte flotte) {
        this.flotte = flotte;
    }

    public Vehicule trouverVehicule(List<Colis> colis, ModeTransport mode) {
        return flotte.vehicules().stream()
                .filter(vehicule -> vehicule.peutPrendre(mode, colis))
                .findFirst()
                .orElse(null);
    }
}
