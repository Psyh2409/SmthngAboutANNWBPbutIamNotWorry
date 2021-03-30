package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation.ArtificialNeuralNetwork.wLayer;

@Data
@EqualsAndHashCode(callSuper = false)
public class Bias extends Neuroneus implements Passer {
    private final double out = 1;

    @Override
    public double forwardLifeCircle() {
        return 1;
    }

    @Override
    public double backLifeCircle(double [] mistakes) {
        double result = 0;
        for (Integer[] arr: this.getOuts()) {
            result += wLayer[arr[0]][arr[1]].getWeightedOutMistake();
        }
        this.setMistake(reActivationSigmoid(result));
        return getMistake()*0;
    }
}
