package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class FourthInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return Math.log(3 * point);
    }
}
