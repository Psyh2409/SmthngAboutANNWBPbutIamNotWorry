package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Synapse implements Passer {
    private Passer inNeuroneus;
    private double justice;
    private Passer outNeuroneus;
    private double weightedOutMistake;
    private double OutMistake;
    private double inDouble;

    public Synapse() {
        super();
        do {
            justice = Math.random();
        } while (justice == 0);
    }

    public Synapse(Passer inNeuroneus, Passer outNeuroneus) {
        super();
        do {
            justice = Math.random();
        } while (justice == 0);
        this.inNeuroneus = inNeuroneus;
        this.outNeuroneus = outNeuroneus;
    }

    public double weighting(double in) {
        return in * justice;
    }

    @Override
    public double forwardLifeCircle() {
        inDouble = inNeuroneus.forwardLifeCircle();
        double weightedOut = weighting(inDouble);
        if (outNeuroneus instanceof Neuroneus) {
            ((Neuroneus) outNeuroneus).getIns().add(weightedOut);
        }
        return weightedOut;
    }

    @Override
    public double backLifeCircle() {
        weightedOutMistake = weighting(
                !(outNeuroneus instanceof Bias)
                        ? OutMistake = ((Neuroneus) outNeuroneus).getMistake()*justice
                        : 0*justice);
        return weightedOutMistake;
    }

    @Override
    public void studying() {
        outNeuroneus.studying();
    }

}
