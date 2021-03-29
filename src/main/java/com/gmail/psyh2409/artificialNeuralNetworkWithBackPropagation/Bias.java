package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Bias extends Neuroneus implements Passer {
    private final double out = 1;

    @Override
    public double forwardLifeCircle() {
        return 1;
    }

    @Override
    public double backLifeCircle() {
        double result = 0;
        for (Synapse s: this.getOuts()) {
            result += s.getWeightedOutMistake();
        }
        this.setMistake(reActivationSigmoid(result));
        return getMistake()*0;
    }
}
