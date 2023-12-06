package ru.nsu.bolotov.model.functions.runge;

import ru.nsu.bolotov.model.functions.InputFunction;

public class FirstInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return Math.pow(point, 5) - 0.1 * point * point - 3;
    }

    @Override
    public String toString() {
        return "f(x) = x^5 - 0,1 * x^2 - 3 от 2 до 5";
    }
}
