package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategieTarifaireTest {

    @Test
    void les_tarifs_de_base_respectent_les_coefficients_et_minimums() {
        assertEquals(121.00, new TarifRoute().calculer(100, 10, 0), 0.001);
        assertEquals(150.00, new TarifRail().calculer(100, 10, 0), 0.001);
        assertEquals(500.00, new TarifMer().calculer(100, 10, 0), 0.001);
        assertEquals(811.50, new TarifAir().calculer(100, 10, 1), 0.001);
        assertEquals(510.00, new TarifAir().calculer(100, 100, 0), 0.001);
    }

    @Test
    void les_policies_de_remise_appliquent_les_regles_client() {
        assertEquals(1000.00, new RemiseParticulier().appliquer(1000), 0.001);
        assertEquals(900.00, new RemiseEntreprise().appliquer(1000), 0.001);
        assertEquals(5100.00, new RemiseEntreprise().appliquer(6000), 0.001);
        assertEquals(800.00, new RemisePremium().appliquer(1000), 0.001);
    }
}
