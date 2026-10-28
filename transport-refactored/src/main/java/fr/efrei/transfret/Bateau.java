package fr.efrei.transfret;

import java.util.List;

public class Bateau extends Vehicule {

    private boolean conteneursRefrigeres;

    public Bateau(String immatriculation, double capaciteMax, boolean conteneursRefrigeres) {
        super(immatriculation, capaciteMax);
        this.conteneursRefrigeres = conteneursRefrigeres;
    }

    @Override
    protected boolean estCompatible(ModeTransport mode, List<Colis> colis) {
        return mode == ModeTransport.MER
                && (conteneursRefrigeres || colis.stream().noneMatch(Colis::estRefrigere));
    }

    @Override
    public void partir(int distanceKm, List<Colis> colis) {
        int milesNautiques = (int) (distanceKm / KM_PAR_MILLE_NAUTIQUE);
        System.out.println("Le navire " + immatriculation + " navigue sur " + milesNautiques + " milles nautiques");
        if (conteneursRefrigeres) {
            colis.stream().filter(Colis::estRefrigere).forEach(this::activerRefrigeration);
        }
    }

    private void activerRefrigeration(Colis colis) {
        System.out.println("Conteneur frigorifique réglé à " + TEMPERATURE_CIBLE + "°C pour " + colis.getDescription());
    }

    public boolean aConteneursRefrigeres() {
        return conteneursRefrigeres;
    }

    private static final double KM_PAR_MILLE_NAUTIQUE = 1.852;
    private static final double TEMPERATURE_CIBLE = 4;
}
