package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffectationVehiculeTest {

    @Test
    void les_vehicules_compatibles_se_selectionnent_par_polymorphisme() {
        AffectationVehicule affectation = new AffectationVehicule(new Flotte());

        assertEquals("AB-123-CD", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.ROUTE).getImmatriculation());
        assertEquals("EF-456-GH", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.DANGEREUX)), ModeTransport.ROUTE).getImmatriculation());
        assertEquals("IJ-789-KL", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.REFRIGERE)), ModeTransport.ROUTE).getImmatriculation());
        assertEquals("TR-0001", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.RAIL).getImmatriculation());
        assertEquals("CMA-CGM-01", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.REFRIGERE)), ModeTransport.MER).getImmatriculation());
        assertEquals("F-GTXA", affectation.trouverVehicule(
                List.of(colis(TypeMarchandise.STANDARD)), ModeTransport.AIR).getImmatriculation());
    }

    private static Colis colis(TypeMarchandise type) {
        return new Colis("Test", 10, 50, 40, 30, type, 100);
    }
}
