package fr.efrei.transfret;

public enum ModeTransport {
    ROUTE(true, true, false, 1.0),
    RAIL(false, true, false, 1.0),
    MER(true, false, false, 1.0),
    AIR(false, true, true, 1.0),
    FLUVIAL(false, false, false, 1.10);

    private final boolean accepteRefrigere;
    private final boolean urgenceAutorisee;
    private final boolean matieresDangereusesInterdites;
    private final double coefficientHorsGabarit;

    ModeTransport(boolean accepteRefrigere, boolean urgenceAutorisee,
                  boolean matieresDangereusesInterdites, double coefficientHorsGabarit) {
        this.accepteRefrigere = accepteRefrigere;
        this.urgenceAutorisee = urgenceAutorisee;
        this.matieresDangereusesInterdites = matieresDangereusesInterdites;
        this.coefficientHorsGabarit = coefficientHorsGabarit;
    }

    public boolean accepteRefrigere() {
        return accepteRefrigere;
    }

    public boolean urgenceAutorisee() {
        return urgenceAutorisee;
    }

    public boolean matieresDangereusesInterdites() {
        return matieresDangereusesInterdites;
    }

    public double coefficientHorsGabarit() {
        return coefficientHorsGabarit;
    }
}
