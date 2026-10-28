package fr.efrei.transfret;

import java.util.Map;
import java.util.Objects;

public class EstimateurDelai {

    private final Map<ModeTransport, StrategieDelai> strategies;

    public EstimateurDelai(Map<ModeTransport, StrategieDelai> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public int calculer(int distanceKm, ModeTransport mode, boolean urgent) {
        StrategieDelai strategie = Objects.requireNonNull(
                strategies.get(mode), "Aucune stratégie de délai pour le mode : " + mode);
        return Math.max(strategie.calculer(distanceKm, urgent), DELAI_MINIMUM);
    }

    private static final int DELAI_MINIMUM = 1;
}
