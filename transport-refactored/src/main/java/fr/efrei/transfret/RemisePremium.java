package fr.efrei.transfret;

public class RemisePremium implements PolitiqueRemise {

    @Override
    public double appliquer(double prix) {
        return prix * COEFFICIENT_PRIX;
    }

    private static final double COEFFICIENT_PRIX = 0.80;
}
