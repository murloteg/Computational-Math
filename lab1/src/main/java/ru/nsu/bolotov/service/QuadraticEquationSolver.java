package ru.nsu.bolotov.service;

import ru.nsu.bolotov.model.QuadraticEquationParameters;
import ru.nsu.bolotov.model.QuadraticEquationRootsRepresentation;
import ru.nsu.bolotov.utils.UtilConsts;

import java.util.Optional;

public final class QuadraticEquationSolver {
    public static QuadraticEquationRootsRepresentation solveEquation(QuadraticEquationParameters quadraticEquationParameters) {
        double discriminant = quadraticEquationParameters.secondParameter() * quadraticEquationParameters.secondParameter() -
                4 * quadraticEquationParameters.firstParameter() * quadraticEquationParameters.thirdParameter();
        if (discriminant <= 0) {
            return new QuadraticEquationRootsRepresentation(Optional.empty(), Optional.empty(), discriminant);
        } else {
            double discriminantSqrt = Math.sqrt(discriminant);
            double firstRoot = ((-1) * quadraticEquationParameters.secondParameter() + discriminantSqrt) / (2 * quadraticEquationParameters.firstParameter());
            double secondRoot = ((-1) * quadraticEquationParameters.secondParameter() - discriminantSqrt) / (2 * quadraticEquationParameters.firstParameter());
            return new QuadraticEquationRootsRepresentation(
                    Optional.of(Math.min(firstRoot, secondRoot)),
                    Optional.of(Math.max(firstRoot, secondRoot)),
                    discriminant
            );
        }
    }

    private QuadraticEquationSolver() {
        throw new IllegalStateException(UtilConsts.StringConsts.INSTANTIATION_MESSAGE);
    }
}
