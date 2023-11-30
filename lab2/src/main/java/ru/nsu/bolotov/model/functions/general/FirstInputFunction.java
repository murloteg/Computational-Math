package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class FirstInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return point + 1.7;
    }
}
