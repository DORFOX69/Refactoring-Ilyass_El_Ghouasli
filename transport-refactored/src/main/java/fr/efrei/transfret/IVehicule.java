package fr.efrei.transfret;

import java.util.List;

/**
 * Capacités communes aux véhicules de la flotte.
 */
public interface IVehicule {
    String getImmatriculation();
    double getCapaciteMax();
    double getChargeActuelle();
    boolean estDisponible();
    void setDisponible(boolean disponible);
    void charger(Colis colis);
    void decharger(Colis colis);
    boolean peutPrendre(ModeTransport mode, List<Colis> colis);
    void partir(int distanceKm, List<Colis> colis);
}
