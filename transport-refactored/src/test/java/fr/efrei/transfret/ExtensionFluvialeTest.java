package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtensionFluvialeTest {

    @Test
    void le_mode_fluvial_a_son_tarif_delai_trajet_et_vehicule() {
        assertEquals(300, new TarifFluvial().calculer(365, 100, 0), 0.001);
        assertEquals(420, new TarifFluvial().calculer(1000, 1000, 0), 0.001);
        assertEquals(3, estimateurFluvial().calculer(365, ModeTransport.FLUVIAL, false));
        assertEquals(365, Utils.getDistance("Paris", "Le Havre"));
        assertEquals(365, Utils.getDistance("Le Havre", "Paris"));
        assertEquals("PEN-0001", new AffectationVehicule(new Flotte())
                .trouverVehicule(List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.FLUVIAL)
                .getImmatriculation());
    }

    @Test
    void le_fluvial_refuse_le_refrigere_et_l_urgence() {
        ValidateurExpedition validateur = new ValidateurExpedition();
        Client client = new Client("Test", "test@example.fr", "0600000000", "Adresse",
                TypeClient.PARTICULIER, null);

        assertTrue(validateur.valider(client, List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.FLUVIAL, false));
        assertFalse(validateur.valider(client, List.of(colis(TypeMarchandise.REFRIGERE)), ModeTransport.FLUVIAL, false));
        assertFalse(validateur.valider(client, List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.FLUVIAL, true));
    }

    @Test
    void le_decorateur_hors_gabarit_ajoute_dix_pourcent() {
        CalculateurPrix calculateur = new CalculateurPrix(
                Map.of(ModeTransport.FLUVIAL, new TarifFluvial()),
                Map.of(TypeClient.PARTICULIER, new RemiseParticulier()));
        Colis colisHorsGabarit = new Colis("Long colis", 10, 201, 40, 30, TypeMarchandise.STANDARD, 100);

        assertEquals(440.22, calculateur.calculerPrixHT(
                List.of(colisHorsGabarit), 1000, ModeTransport.FLUVIAL, false, false, TypeClient.PARTICULIER), 0.001);
    }

    private static EstimateurDelai estimateurFluvial() {
        return new EstimateurDelai(Map.of(ModeTransport.FLUVIAL, new DelaiFluvial()));
    }

    private static Colis colis(TypeMarchandise type) {
        return new Colis("Test", 10, 50, 40, 30, type, 100);
    }
}
