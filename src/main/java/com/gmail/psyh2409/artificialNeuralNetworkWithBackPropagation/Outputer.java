package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Outputer implements Passer{
    private Adder adder = new Adder();
    private double out;

    private double activationSigmoid(double in) {
        return 1/(1+Math.pow(Math.E, (in*(-1))));
    }

    @Override
    public double forwardLifeCircle() {
        return 0;
    }

    @Override
    public double backLifeCircle() {
        return 0;
    }
}
