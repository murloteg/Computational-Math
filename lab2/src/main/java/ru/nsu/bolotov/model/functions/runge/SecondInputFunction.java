package ru.nsu.bolotov.model.functions.runge;

import ru.nsu.bolotov.model.functions.InputFunction;

public class SecondInputFunction implements InputFunction {
    @Override
    public double getFunctionValue(double point) {
        return Math.sin(point + 4.9);
    }

    @Override
    public String toString() {
        return "f(x) = sin(x + 4.9) от -Pi до Pi/3";
    }
}
