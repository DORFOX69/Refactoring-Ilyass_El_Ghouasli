package fr.efrei.transfret;

import java.util.Map;

public class AssemblagePrix {

    private final Map<ModeTransport, TarifTransport> tarifs;
    private final Map<TypeClient, PolitiqueRemise> remises;

    public AssemblagePrix(Map<ModeTransport, TarifTransport> tarifs, Map<TypeClient, PolitiqueRemise> remises) {
        this.tarifs = Map.copyOf(tarifs);
        this.remises = Map.copyOf(remises);
    }

    public ComposantPrix assembler() {
        ComposantPrix prix = new TarifDeBase(tarifs);
        prix = new SupplementFragile(prix);
        prix = new SupplementDangereux(prix);
        prix = new SupplementRefrigere(prix);
        prix = new SupplementHorsGabarit(prix);
        prix = new SupplementUrgence(prix);
        prix = new RemisePrix(prix, remises);
        return new Assurance(prix);
    }
}
