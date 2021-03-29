package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class Neuroneus implements Passer {

    private List<Double> ins;
    private double sum;
    private double mistake;
    private double out;
    private List<Synapse> outs;

    public Neuroneus() {
        ins = new ArrayList<>();
        outs = new ArrayList<>();
    }

    private double summer(List<Double> ins) {
        sum = ins.stream().reduce(0.0, Double::sum);
        return sum;
    }

    private double activationSigmoid(double in) {
        double flatness = 1;
        out = 1 / (1 + Math.pow(Math.E, (in * (-1 * flatness))));
        return out;
    }

    public double forwardLifeCircle() {
        return activationSigmoid(summer(ins));

    }

    @Override
    public double backLifeCircle() {
        double result = 0;
        for (Synapse s: outs) {
            result += s.getWeightedOutMistake();
        }
        mistake = reActivationSigmoid(result);
        return mistake;
    }

    public double reActivationSigmoid(double mistake) {
        return (mistake+0.1) * (1 - mistake-0.1);
    }

    public void studying () {
        double jump = 0.1;
        for (Synapse s: outs) {
            double newJustice = s.getJustice() + mistake * reActivationSigmoid(
                    s.getWeightedOutMistake()) * out * jump;
            s.setJustice(newJustice);        }
    }
}
