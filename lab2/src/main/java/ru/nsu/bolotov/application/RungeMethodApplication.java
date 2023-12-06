package ru.nsu.bolotov.application;

import ru.nsu.bolotov.model.ApproximationOrders;
import ru.nsu.bolotov.model.InputParameters;
import ru.nsu.bolotov.model.IntegrationLimits;
import ru.nsu.bolotov.model.functions.InputFunction;
import ru.nsu.bolotov.model.functions.runge.FirstInputFunction;
import ru.nsu.bolotov.model.functions.runge.SecondInputFunction;
import ru.nsu.bolotov.util.UtilConsts;

import java.util.ArrayList;

public class RungeMethodApplication {
    public static void main(String[] args) {
        InputFunction inputFunction1 = new FirstInputFunction();
        IntegrationLimits integrationLimits1 = new IntegrationLimits(2, 5);
        InputParameters inputParameters1 = new InputParameters(inputFunction1, integrationLimits1, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction2 = new SecondInputFunction();
        IntegrationLimits integrationLimits2 = new IntegrationLimits(-1 * Math.PI, Math.PI / 3);
        InputParameters inputParameters2 = new InputParameters(inputFunction2, integrationLimits2, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        System.out.println("N = " + UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);
        ApproximationOrders firstFunctionOrders = calculateApproximationOrder(inputParameters1);
        System.out.println(inputFunction1);
        System.out.println(firstFunctionOrders);

        ApproximationOrders secondFunctionOrders = calculateApproximationOrder(inputParameters2);
        System.out.println(inputFunction2);
        System.out.println(secondFunctionOrders);
    }

    public static ApproximationOrders calculateApproximationOrder(InputParameters inputParameters) {
        InputFunction inputFunction = inputParameters.inputFunction();
        IntegrationLimits integrationLimits = inputParameters.integrationLimits();
        int numberOfSegments = inputParameters.numberOfSegments();
        InputParameters halfInputParameters = new InputParameters(inputFunction, integrationLimits, 2 * numberOfSegments);
        InputParameters quarterInputParameters = new InputParameters(inputFunction, integrationLimits, 4 * numberOfSegments);
        ApproximationOrders approximationOrders = new ApproximationOrders(new ArrayList<>());

        // Left rectangle's method
        double fullIterationIntegralValueLeftRectangles = GeneralApplication.leftRectanglesMethod(inputParameters);
        double halfIterationIntegralValueLeftRectangles = GeneralApplication.leftRectanglesMethod(halfInputParameters);
        double quarterIterationIntegralValueLeftRectangles = GeneralApplication.leftRectanglesMethod(quarterInputParameters);
        double leftRectanglesOrder = log2(Math.abs(
                        (fullIterationIntegralValueLeftRectangles - halfIterationIntegralValueLeftRectangles) / (halfIterationIntegralValueLeftRectangles - quarterIterationIntegralValueLeftRectangles)
                )
        );
        approximationOrders.orders().add(leftRectanglesOrder);

        // Trapezoid method
        double fullIterationIntegralValueTrapezoidMethod = GeneralApplication.trapezoidMethod(inputParameters);
        double halfIterationIntegralValueTrapezoidMethod = GeneralApplication.trapezoidMethod(halfInputParameters);
        double quarterIterationIntegralValueTrapezoidMethod = GeneralApplication.trapezoidMethod(quarterInputParameters);
        double trapezoidMethodOrder = log2(Math.abs(
                        (fullIterationIntegralValueTrapezoidMethod - halfIterationIntegralValueTrapezoidMethod) / (halfIterationIntegralValueTrapezoidMethod - quarterIterationIntegralValueTrapezoidMethod)
                )
        );
        approximationOrders.orders().add(trapezoidMethodOrder);

        // Simpson's method
        double fullIterationIntegralValueSimpsonMethod = GeneralApplication.simpsonMethod(inputParameters);
        double halfIterationIntegralValueSimpsonMethod = GeneralApplication.simpsonMethod(halfInputParameters);
        double quarterIterationIntegralValueSimpsonMethod = GeneralApplication.simpsonMethod(quarterInputParameters);
        double simpsonMethodOrder = log2(Math.abs(
                        (fullIterationIntegralValueSimpsonMethod - halfIterationIntegralValueSimpsonMethod) / (halfIterationIntegralValueSimpsonMethod - quarterIterationIntegralValueSimpsonMethod)
                )
        );
        approximationOrders.orders().add(simpsonMethodOrder);
        return approximationOrders;
    }

    private static double log2(double value) {
        return Math.log(value) / Math.log(2);
    }
}
