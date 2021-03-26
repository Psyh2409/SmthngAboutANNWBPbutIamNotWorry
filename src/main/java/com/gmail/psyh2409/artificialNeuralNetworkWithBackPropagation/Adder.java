package com.gmail.psyh2409.artificialNeuralNetworkWithBackPropagation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Adder {
    private ArrayList<Double> doubles;
    private double result;

    public Double summer(Double... ins){
        return Arrays.stream(ins).reduce(0.0, (a, b) -> a + b);
    }
}
