package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnSynapse extends Synapse implements Passer {
    private final double justice = 0;

    public UnSynapse(Neuroneus before, Neuroneus after) {
        super(before, after);
    }


    public double weighting(double in) {
        return in * justice * 0;
    }

    @Override
    public double forwardLifeCircle() {
        return weighting(0);
    }
}
