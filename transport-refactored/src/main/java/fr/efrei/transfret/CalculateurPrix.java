package fr.efrei.transfret;

import java.util.Map;

public class CalculateurPrix {

    private final ComposantPrix composant;

    public CalculateurPrix(Map<ModeTransport, TarifTransport> tarifs, Map<TypeClient, PolitiqueRemise> remises) {
        this.composant = new AssemblagePrix(tarifs, remises).assembler();
    }

    public double calculerPrixHT(DemandePrix demande) {
        return Utils.arrondir(composant.calculer(demande));
    }

    public double calculerPrixHT(java.util.List<Colis> colis, int distance, ModeTransport mode, boolean urgent,
                                 boolean assurance, TypeClient typeClient) {
        return calculerPrixHT(new DemandePrix(colis, distance, mode, urgent, assurance, typeClient));
    }
}
