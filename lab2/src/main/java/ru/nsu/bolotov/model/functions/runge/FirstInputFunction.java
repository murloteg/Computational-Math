package ru.nsu.bolotov.model.functions.runge;

import ru.nsu.bolotov.model.functions.InputFunction;

public class FirstInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return Math.pow(point, 5) - 0.1 * point * point - 3;
    }
}
