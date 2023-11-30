package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class SixthInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return 1 / (1 + Math.exp(point));
    }
}
