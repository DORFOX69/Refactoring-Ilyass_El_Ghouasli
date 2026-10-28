package fr.efrei.transfret;

import java.util.Map;

public class TarifDeBase implements ComposantPrix {

    private final Map<ModeTransport, TarifTransport> tarifs;

    public TarifDeBase(Map<ModeTransport, TarifTransport> tarifs) {
        this.tarifs = Map.copyOf(tarifs);
    }

    @Override
    public double calculer(DemandePrix demande) {
        TarifTransport tarif = tarifs.get(demande.mode());
        if (tarif == null) {
            throw new IllegalArgumentException("Aucun tarif pour le mode : " + demande.mode());
        }
        return tarif.calculer(demande.distanceKm(), demande.poidsTotal(), demande.volumeTotal());
    }
}
