package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import java.util.Arrays;
import java.util.List;

public class ArtificialNeuralNetwork {
    private final static int ins = 2;
    private final static int hiddenLayer = 3;
    private final static int hLCount = 1;
    private final static int outs = 1;
    private static int currentInt;
    private final static double[] oOuts = new double[outs];
    private final static double[] goals = new double[outs];
    private final static double[] mistakes = new double[outs];

    private static void mistakeCalculator() {
        for (int i = 0; i < outs; i++) {
            mistakes[i] = goals[i] - oOuts[i];
        }
    }

    private static int[] toDigitArrayWithFixedCapacity(int someNumber) {
        int log10 = someNumber == 0 ? 0 : (int) (Math.log10(someNumber));
        int[] result = new int[ins];
        for (int f = 0, e = result.length - 1; f < log10 + 1; f++, e--) {
            result[e] = someNumber % 10;
            someNumber = someNumber / 10;
        }
        return result;
    }

    public static void goalsInitializer(){
        Arrays.fill(goals, currentInt / (Math.pow(10, ins)));
    }

    public static void main(String[] args) {

            Neuroneus[][] nLayer = nInitializer();
            Synapse[][] wLayer = wInitializer(nLayer);
        for (int i = 0; i < 10; i++) {
            lifeCircle(nLayer, wLayer, 0, 10);
        }
    }

    public static void lifeCircle(Neuroneus[][] nLayer, Synapse[][] wLayer, int from, int to) {
        for (int x = from; x < to+1; x++) {
            currentInt = x;
            goalsInitializer();

            Neuroneus[] neuroneuses = nLayer[0];
            int[] arrOfDigits = toDigitArrayWithFixedCapacity(x);
            for (int i = 0; i < neuroneuses.length; i++) {
                neuroneuses[i].getIns().add((double) arrOfDigits[i]);
                System.out.print(arrOfDigits[i] + "\t");
            }
            System.out.println();
            for (int i = 0; i < wLayer.length; i++) {
                Synapse[] synapses = wLayer[i];
                for (int j = 0; j < synapses.length; j++) {
                    Synapse synaps = synapses[j];
                    synaps.forwardLifeCircle();
                    System.out.print("|" + String.format(
                            "%.2f", synaps.getInDouble()) + "*" + String.format(
                                    "%.2f", synaps.getJustice()));
                }
                System.out.println();
            }
            System.out.print(x + " => ");
            int y = 0;
            for (Neuroneus n : nLayer[nLayer.length - 1]) {
                oOuts[y] = n.forwardLifeCircle();
                mistakeCalculator();
                n.backLifeCircle();
                n.studying();

                System.out.printf("\t" + ++y + ": %.2f \t", oOuts[y-1]);
            }
            y = 0;
             System.out.println();
            for (int i = 0; i < oOuts.length; i++) {
                System.out.println("currentInt: " + currentInt + " oOut" + (i+1) + ": " + oOuts[i] + " goal" +
                        (i+1) + ": " + goals[i] + " mistake" + (i+1) + ": " + mistakes[i]);
            }

//            for (int i = 0; i < nLayer.length; i++) { // it is something about run back propagation, but I still don't wary.
//                for (int j = 0; j < nLayer[i].length; j++) {
//                    nLayer[i][j].backLifeCircle();
//                }
//            }
        }
    }

    public static Synapse[][] wInitializer(Neuroneus[][] nLayer) {
        Synapse[][] wLayer = new Synapse[nLayer.length - 1][];
        for (int i = 0; i < nLayer.length - 1; i++) {
            int capacity = nLayer[i].length * nLayer[i + 1].length;
            wLayer[i] = new Synapse[capacity];
            int w = 0;
            for (Neuroneus before : nLayer[i]) {
                for (Neuroneus after : nLayer[i + 1]) {
                    if (!(after instanceof Bias)) {
                        wLayer[i][w] = new Synapse(before, after);
                    } else {
                        wLayer[i][w] = new UnSynapse(before, after);
                    }
                    wLayer[i][w].setOutNeuroneus(after);
                    before.getOuts().add(wLayer[i][w]);
                    w++;
                }
            }
        }
        return wLayer;
    }

    public static Neuroneus[][] nInitializer() {
        Neuroneus[][] nLayer = new Neuroneus[hLCount + 2][];
        for (int i = 0; i < hLCount + 2; i++) {
            int capacity = ins;
            if (i > 0) capacity = hiddenLayer + 1; //+ bias
            if (i == hLCount + 1) capacity = outs;
            nLayer[i] = new Neuroneus[capacity];
            for (int j = 0; j < capacity; j++) {
                if (i != 0 && i != hLCount + 1 && j == 0) {
                    nLayer[i][j] = new Bias();
                } else {
                    nLayer[i][j] = new Neuroneus();
                }
            }
        }
        return nLayer;
    }
}




