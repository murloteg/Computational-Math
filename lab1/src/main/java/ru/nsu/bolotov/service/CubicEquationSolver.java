package ru.nsu.bolotov.service;

import ru.nsu.bolotov.model.CalculationErrorParameters;
import ru.nsu.bolotov.model.InputDataRepresentation;
import ru.nsu.bolotov.model.QuadraticEquationParameters;
import ru.nsu.bolotov.model.QuadraticEquationRootsRepresentation;
import ru.nsu.bolotov.utils.UtilConsts;

public final class CubicEquationSolver {
    public static void solveEquation(InputDataRepresentation inputData) {
        QuadraticEquationParameters quadraticEquationParameters = new QuadraticEquationParameters(
                3,
                2 * inputData.cubicEquationParameters().parameterA(),
                inputData.cubicEquationParameters().parameterB()
        );
        QuadraticEquationRootsRepresentation quadraticEquationRootsRepresentation = QuadraticEquationSolver.solveEquation(quadraticEquationParameters);
        if (quadraticEquationRootsRepresentation.discriminant() <= 0) {
            findTheOnlyOneRoot(inputData.calculationErrorParameters());
        } else {
            // TODO
        }
    }

    private static void findTheOnlyOneRoot(CalculationErrorParameters calculationErrorParameters) {
        // TODO
    }

    private CubicEquationSolver() {
        throw new IllegalStateException(UtilConsts.StringConsts.INSTANTIATION_MESSAGE);
    }
}
