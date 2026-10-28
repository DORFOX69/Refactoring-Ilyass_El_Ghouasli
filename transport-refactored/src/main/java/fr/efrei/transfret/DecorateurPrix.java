package fr.efrei.transfret;

public abstract class DecorateurPrix implements ComposantPrix {

    protected final ComposantPrix composant;

    protected DecorateurPrix(ComposantPrix composant) {
        this.composant = composant;
    }
}
