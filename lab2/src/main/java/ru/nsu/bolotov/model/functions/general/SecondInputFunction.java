package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class SecondInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return 4 * Math.pow(point + 1, 3);
    }
}
