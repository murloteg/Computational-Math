package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class FifthInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return (point - 8) * Math.cos(point);
    }
}
