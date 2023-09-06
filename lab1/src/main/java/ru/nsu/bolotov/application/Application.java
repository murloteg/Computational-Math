package ru.nsu.bolotov.application;

import ru.nsu.bolotov.parser.InputParser;
import ru.nsu.bolotov.service.CubicEquationSolver;

public class Application {
    public static void main(String[] args) {
        CubicEquationSolver.solveEquation(InputParser.parseCommandLine(args));
    }
}
