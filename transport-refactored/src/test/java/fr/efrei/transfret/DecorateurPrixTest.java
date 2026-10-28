package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecorateurPrixTest {

    @Test
    void les_supplements_sont_sequentiels_et_assurance_suit_la_remise() {
        List<Colis> colis = List.of(
                new Colis("Fragile", 10, 50, 40, 30, TypeMarchandise.FRAGILE, 100),
                new Colis("Dangereux", 0.01, 50, 40, 30, TypeMarchandise.DANGEREUX, 100),
                new Colis("Frais", 0.01, 50, 40, 30, TypeMarchandise.REFRIGERE, 100));
        CalculateurPrix calculateur = new CalculateurPrix(
                Map.of(ModeTransport.ROUTE, new TarifRoute()),
                Map.of(TypeClient.ENTREPRISE, new RemiseEntreprise()));

        assertEquals(308.05, calculateur.calculerPrixHT(
                colis, 100, ModeTransport.ROUTE, true, true, TypeClient.ENTREPRISE), 0.001);
    }
}
