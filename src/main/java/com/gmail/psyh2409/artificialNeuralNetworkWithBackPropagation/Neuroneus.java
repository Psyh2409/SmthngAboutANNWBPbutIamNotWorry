package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;

import static com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation.ArtificialNeuralNetwork.wLayer;

@Data
@EqualsAndHashCode
public class Neuroneus implements Passer {

    private List<Double> ins;
    private double sum;
    private double mistake;
    private double out;
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
    public double backLifeCircle(double[] mistakes) {
        double result = 0;
        OptionalDouble mistakesSum = Arrays.stream(mistakes).reduce(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double left, double right) {
                return left + right;
            }
        });
        for (Integer[] arr: this.getOuts()) {
            result += wLayer[arr[0]][arr[1]].getWeightedOutMistake();
        }
        mistake = reActivationSigmoid(mistakesSum.getAsDouble());
        return mistake;
    }

    public double reActivationSigmoid(double num) {
        double a = (num+0.1);
        double b = (1 - num-0.1);
        return a*b;
    }

    public void studying () {
        double jump = 0.1;
        for (Integer[] arr: this.getOuts()) {
            double newJustice = wLayer[arr[0]][arr[1]].getJustice() + mistake * reActivationSigmoid(
                    wLayer[arr[0]][arr[1]].getWeightedOutMistake()) * out * jump;
            wLayer[arr[0]][arr[1]].setJustice(newJustice);        }
    }
}
