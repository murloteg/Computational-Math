package ru.nsu.bolotov.model.functions.general;

import ru.nsu.bolotov.model.functions.InputFunction;

public class ThirdInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return 0.2 * Math.sqrt(point);
    }
}
