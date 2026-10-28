package fr.efrei.transfret;

import java.util.Map;

public class RemisePrix extends DecorateurPrix {

    private final Map<TypeClient, PolitiqueRemise> remises;

    public RemisePrix(ComposantPrix composant, Map<TypeClient, PolitiqueRemise> remises) {
        super(composant);
        this.remises = Map.copyOf(remises);
    }

    @Override
    public double calculer(DemandePrix demande) {
        PolitiqueRemise remise = remises.get(demande.typeClient());
        if (remise == null) {
            throw new IllegalArgumentException("Aucune remise pour le type de client : " + demande.typeClient());
        }
        return remise.appliquer(composant.calculer(demande));
    }
}
