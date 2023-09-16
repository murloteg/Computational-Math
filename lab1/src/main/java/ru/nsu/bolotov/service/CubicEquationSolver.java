package ru.nsu.bolotov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.bolotov.model.*;
import ru.nsu.bolotov.utils.UtilConsts;

public final class CubicEquationSolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CubicEquationSolver.class);

    public static void solveEquation(InputDataRepresentation inputData) {
        CubicEquationParameters cubicEquationParameters = inputData.cubicEquationParameters();
        LOGGER.trace("[INPUT: x^3 + ({} * x^2) + ({} * x) + {}]", cubicEquationParameters.parameterA(),
                cubicEquationParameters.parameterB(), cubicEquationParameters.parameterC());
        QuadraticEquationParameters quadraticEquationParameters = new QuadraticEquationParameters(
                3,
                2 * cubicEquationParameters.parameterA(),
                cubicEquationParameters.parameterB()
        );
        QuadraticEquationSolutionRepresentation quadraticEquationSolution = QuadraticEquationSolver.solveEquation(quadraticEquationParameters);
        if (quadraticEquationSolution.discriminant() <= 0) {
            findRootsNegativeDiscriminantCase(inputData);
        } else {
            findRootsPositiveDiscriminantCase(inputData, quadraticEquationSolution);
        }
    }

    private static void findRootsPositiveDiscriminantCase(
            InputDataRepresentation inputData,
            QuadraticEquationSolutionRepresentation quadraticEquationSolution
    ) {
        CubicEquationParameters cubicEquationParameters = inputData.cubicEquationParameters();
        double epsilon = inputData.calculationErrorParameters().epsilon();
        double minRoot = quadraticEquationSolution.firstRoot().orElseThrow(IllegalArgumentException::new);
        double maxRoot = quadraticEquationSolution.secondRoot().orElseThrow(IllegalArgumentException::new);
        if (getFunctionValue(cubicEquationParameters, minRoot) > epsilon &&
                getFunctionValue(cubicEquationParameters, maxRoot) > epsilon) {
            SegmentRepresentation segment = findRootLocationOnTheLeftSegment(inputData, minRoot);
            findRootByDichotomyMethod(inputData, segment);
        } else if (getFunctionValue(cubicEquationParameters, minRoot) < (-1) * epsilon &&
                getFunctionValue(cubicEquationParameters, maxRoot) < (-1) * epsilon) {
            SegmentRepresentation segment = findRootLocationOnTheRightSegment(inputData, maxRoot);
            findRootByDichotomyMethod(inputData, segment);
        } else if (getFunctionValue(cubicEquationParameters, minRoot) > epsilon &&
                Math.abs(getFunctionValue(cubicEquationParameters, maxRoot)) < epsilon) {
            LOGGER.info("The root was found! [Root: {}; Multiplicity: {}]; [Function value: |f(x)| = {}]",
                    maxRoot, 2, getFunctionValue(cubicEquationParameters, maxRoot));
            SegmentRepresentation segment = findRootLocationOnTheLeftSegment(inputData, minRoot);
            findRootByDichotomyMethod(inputData, segment);
        } else if (Math.abs(getFunctionValue(cubicEquationParameters, minRoot)) < epsilon &&
                getFunctionValue(cubicEquationParameters, maxRoot) < (-1) * epsilon) {
            LOGGER.info("The root was found! [Root: {}; Multiplicity: {}]; [Function value: |f(x)| = {}]",
                    minRoot, 2, getFunctionValue(cubicEquationParameters, minRoot));
            SegmentRepresentation segment = findRootLocationOnTheRightSegment(inputData, maxRoot);
            findRootByDichotomyMethod(inputData, segment);
        } else if (getFunctionValue(cubicEquationParameters, minRoot) > epsilon &&
                getFunctionValue(cubicEquationParameters, maxRoot) < (-1) * epsilon) {
            SegmentRepresentation firstSegment = findRootLocationOnTheLeftSegment(inputData, minRoot);
            SegmentRepresentation secondSegment = new SegmentRepresentation(minRoot, maxRoot);
            SegmentRepresentation thirdSegment = findRootLocationOnTheRightSegment(inputData, maxRoot);

            findRootByDichotomyMethod(inputData, firstSegment);
            findRootByDichotomyMethod(inputData, secondSegment);
            findRootByDichotomyMethod(inputData, thirdSegment);
        } else if (Math.abs(getFunctionValue(cubicEquationParameters, minRoot)) < epsilon &&
                Math.abs(getFunctionValue(cubicEquationParameters, maxRoot)) < epsilon) {
            LOGGER.info("The root was found! [Root: {}]", (minRoot + maxRoot) / 2);
        }
    }

    private static void findRootsNegativeDiscriminantCase(InputDataRepresentation inputData) {
        CubicEquationParameters cubicEquationParameters = inputData.cubicEquationParameters();
        double epsilon = inputData.calculationErrorParameters().epsilon();
        if (Math.abs(getFunctionValue(cubicEquationParameters, 0)) < epsilon) {
            LOGGER.info("The root was found! [Root: {}; Multiplicity: {}]", 0, 3);
        } else if (getFunctionValue(cubicEquationParameters, 0) < (-1) * epsilon) {
            SegmentRepresentation segment = findRootLocationOnTheRightSegment(inputData, 0);
            findRootByDichotomyMethod(inputData, segment);
        } else if (getFunctionValue(cubicEquationParameters, 0) > epsilon) {
            SegmentRepresentation segment = findRootLocationOnTheLeftSegment(inputData, 0);
            findRootByDichotomyMethod(inputData, segment);
        }
    }

    private static SegmentRepresentation findRootLocationOnTheRightSegment(InputDataRepresentation inputData, double point) {
        int stepCounter = 1;
        double delta = inputData.calculationErrorParameters().delta();
        while (true) {
            if (getFunctionValue(inputData.cubicEquationParameters(), point + stepCounter * delta) > 0) {
                return new SegmentRepresentation(
                        point + (stepCounter - 1) * delta,
                        point + stepCounter * delta
                );
            }
            ++stepCounter;
        }
    }

    private static SegmentRepresentation findRootLocationOnTheLeftSegment(InputDataRepresentation inputData, double point) {
        int stepCounter = 1;
        double delta = inputData.calculationErrorParameters().delta();
        while (true) {
            if (getFunctionValue(inputData.cubicEquationParameters(), point - stepCounter * delta) < 0) {
                return new SegmentRepresentation(
                        point - stepCounter * delta,
                        point - (stepCounter - 1) * delta
                );
            }
            ++stepCounter;
        }
    }

    private static void findRootByDichotomyMethod(InputDataRepresentation inputData, SegmentRepresentation segment) {
        CubicEquationParameters cubicEquationParameters = inputData.cubicEquationParameters();
        double epsilon = inputData.calculationErrorParameters().epsilon();
        double leftBorder = segment.getLeftBorder();
        double rightBorder = segment.getRightBorder();
        boolean isCaseWhenLeftExtremumUnderAxis = getFunctionValue(cubicEquationParameters, leftBorder) < 0;
        while (true) {
            double middle = (leftBorder + rightBorder) / 2;
            double functionValue = getFunctionValue(cubicEquationParameters, middle);
            if (Math.abs(functionValue) < epsilon) {
                LOGGER.info("The root was found! [Root: {}]; [Function value: |f(x)| = {}]", middle, functionValue);
                break;
            } else if (functionValue > epsilon) {
                leftBorder = isCaseWhenLeftExtremumUnderAxis ? leftBorder : middle;
                rightBorder = isCaseWhenLeftExtremumUnderAxis ? middle : rightBorder;
            } else if (functionValue < (-1) * epsilon) {
                leftBorder = isCaseWhenLeftExtremumUnderAxis ? middle : leftBorder;
                rightBorder = isCaseWhenLeftExtremumUnderAxis ? rightBorder : middle;
            }
        }
    }

    private static double getFunctionValue(CubicEquationParameters cubicEquationParameters, double point) {
        return (point * point * point) + (cubicEquationParameters.parameterA() * point * point) +
                (cubicEquationParameters.parameterB() * point) + cubicEquationParameters.parameterC();
    }

    private CubicEquationSolver() {
        throw new IllegalStateException(UtilConsts.StringConsts.INSTANTIATION_MESSAGE);
    }
}
