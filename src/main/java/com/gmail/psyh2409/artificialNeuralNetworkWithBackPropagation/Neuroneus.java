package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation.ArtificialNeuralNetwork.wLayer;

@Data
@EqualsAndHashCode
public class Neuroneus implements Passer {

    private List<Double> ins;
    private double sum;
    private double mistake;
    private double out;
//    private List<Synapse> outs;
    private List<Integer[]> outs;

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
        for (Integer[] arr: this.getOuts()) {
            result += wLayer[arr[0]][arr[1]].getWeightedOutMistake();
        }
        mistake = reActivationSigmoid(result);
        return mistake;
    }

    public double reActivationSigmoid(double mistake) {
        return (mistake+0.1) * (1 - mistake-0.1);
    }

    public void studying () {
        double jump = 0.1;
        double result = 0;
        for (Integer[] arr: this.getOuts()) {
            double newJustice = wLayer[arr[0]][arr[1]].getJustice() + mistake * reActivationSigmoid(
                    wLayer[arr[0]][arr[1]].getWeightedOutMistake()) * out * jump;
            wLayer[arr[0]][arr[1]].setJustice(newJustice);        }
    }
}
