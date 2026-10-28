package fr.efrei.transfret;

public class RemiseEntreprise implements PolitiqueRemise {

    @Override
    public double appliquer(double prix) {
        return prix * (prix > SEUIL_REMISE_SUPERIEURE ? REMISE_SUPERIEURE : REMISE_STANDARD);
    }

    private static final double SEUIL_REMISE_SUPERIEURE = 5000;
    private static final double REMISE_SUPERIEURE = 0.85;
    private static final double REMISE_STANDARD = 0.90;
}
