package ru.nsu.bolotov.application;

import ru.nsu.bolotov.model.functions.*;
import ru.nsu.bolotov.model.InputParameters;
import ru.nsu.bolotov.model.IntegrationLimits;
import ru.nsu.bolotov.model.functions.general.*;
import ru.nsu.bolotov.util.UtilConsts;

public class GeneralApplication {
    public static void main(String[] args) {
        InputFunction inputFunction1 = new FirstInputFunction();
        IntegrationLimits integrationLimits1 = new IntegrationLimits(-11, -8);
        InputParameters inputParameters1 = new InputParameters(inputFunction1, integrationLimits1, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction2 = new SecondInputFunction();
        IntegrationLimits integrationLimits2 = new IntegrationLimits(0, 1.5);
        InputParameters inputParameters2 = new InputParameters(inputFunction2, integrationLimits2, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction3 = new ThirdInputFunction();
        IntegrationLimits integrationLimits3 = new IntegrationLimits(1, 3);
        InputParameters inputParameters3 = new InputParameters(inputFunction3, integrationLimits3, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction4 = new FourthInputFunction();
        IntegrationLimits integrationLimits4 = new IntegrationLimits(0, 1);
        InputParameters inputParameters4 = new InputParameters(inputFunction4, integrationLimits4, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction5 = new FifthInputFunction();
        IntegrationLimits integrationLimits5 = new IntegrationLimits(0, 3 * Math.PI / 7);
        InputParameters inputParameters5 = new InputParameters(inputFunction5, integrationLimits5, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        InputFunction inputFunction6 = new SixthInputFunction();
        IntegrationLimits integrationLimits6 = new IntegrationLimits(1, 2);
        InputParameters inputParameters6 = new InputParameters(inputFunction6, integrationLimits6, UtilConsts.ComputationConsts.NUMBER_OF_SEGMENTS);

        System.out.println("Left rectangle's method:");
        System.out.println(leftRectanglesMethod(inputParameters1));
        System.out.println(leftRectanglesMethod(inputParameters2));
        System.out.println(leftRectanglesMethod(inputParameters3));
        System.out.println(leftRectanglesMethod(inputParameters4)); // ln(0) --> -inf
        System.out.println(leftRectanglesMethod(inputParameters5));
        System.out.println(leftRectanglesMethod(inputParameters6));
        printSeparator();

        System.out.println("Trapezoid method:");
        System.out.println(trapezoidMethod(inputParameters1));
        System.out.println(trapezoidMethod(inputParameters2));
        System.out.println(trapezoidMethod(inputParameters3));
        System.out.println(trapezoidMethod(inputParameters4)); // ln(0) --> -inf
        System.out.println(trapezoidMethod(inputParameters5));
        System.out.println(trapezoidMethod(inputParameters6));
        printSeparator();

        System.out.println("Simpson's method:");
        System.out.println(simpsonMethod(inputParameters1));
        System.out.println(simpsonMethod(inputParameters2));
        System.out.println(simpsonMethod(inputParameters3));
        System.out.println(simpsonMethod(inputParameters4)); // ln(0) --> -inf
        System.out.println(simpsonMethod(inputParameters5));
        System.out.println(simpsonMethod(inputParameters6));
        printSeparator();
    }

    /**
     * Formula: h * {sum [from i = 0 to i = (n - 1)] of f(x_i)}
     */
    public static double leftRectanglesMethod(InputParameters inputParameters) {
        InputFunction inputFunction = inputParameters.inputFunction();
        IntegrationLimits integrationLimits = inputParameters.integrationLimits();
        int numberOfSegments = inputParameters.numberOfSegments();
        double lowerLimit = integrationLimits.lowerLimit();
        double upperLimit = integrationLimits.upperLimit();
        double h = (upperLimit - lowerLimit) / numberOfSegments;

        double result = 0;
        double initialPoint = lowerLimit;
        for (int i = 0; i < numberOfSegments; ++i) {
            result += (h * inputFunction.getFunctionValue(initialPoint));
            initialPoint += h;
        }
        return result;
    }

    /**
     * Formula: (h / 2) * (f(x_0) + f(x_n)) + (h * {sum [from i = 1 to i = (n - 1)] of f(x_i)})
     */
    public static double trapezoidMethod(InputParameters inputParameters) {
        InputFunction inputFunction = inputParameters.inputFunction();
        IntegrationLimits integrationLimits = inputParameters.integrationLimits();
        int numberOfSegments = inputParameters.numberOfSegments();
        double lowerLimit = integrationLimits.lowerLimit();
        double upperLimit = integrationLimits.upperLimit();
        double h = (upperLimit - lowerLimit) / numberOfSegments;

        double result = (h / 2) * (inputFunction.getFunctionValue(lowerLimit) + inputFunction.getFunctionValue(upperLimit));
        double initialPoint = lowerLimit + h;
        for (int i = 1; i < numberOfSegments; ++i) {
            result += (h * inputFunction.getFunctionValue(initialPoint));
            initialPoint += h;
        }
        return result;
    }

    /**
     * Formula: (h / 3) * (f(x_0) + f(x_n) + {2 * sum [from i = 0 to i = (m - 1)] of (f(x_2i))} + {4 * sum [from i = 0 to i = (m - 1)] of (f(x_2i+1))})
     */
    public static double simpsonMethod(InputParameters inputParameters) {
        InputFunction inputFunction = inputParameters.inputFunction();
        IntegrationLimits integrationLimits = inputParameters.integrationLimits();
        int numberOfSegments = inputParameters.numberOfSegments();
        double lowerLimit = integrationLimits.lowerLimit();
        double upperLimit = integrationLimits.upperLimit();
        double h = (upperLimit - lowerLimit) / numberOfSegments;
        int m = numberOfSegments / 2;

        double result = (inputFunction.getFunctionValue(lowerLimit) + inputFunction.getFunctionValue(upperLimit));
        double initialPoint = lowerLimit;
        for (int i = 0; i < m; ++i) {
            result += (2 * inputFunction.getFunctionValue(initialPoint));
            initialPoint += (2 * h);
        }

        initialPoint = (lowerLimit + h);
        for (int i = 0; i < m; ++i) {
            result += (4 * inputFunction.getFunctionValue(initialPoint));
            initialPoint += (2 * h);
        }
        result *= (h / 3);
        return result;
    }

    private static void printSeparator() {
        System.out.println("\n\n");
    }
}
