package ru.nsu.bolotov.parser;

import org.apache.commons.cli.*;
import ru.nsu.bolotov.exception.FailedParsingCmdLineException;
import ru.nsu.bolotov.model.CalculationErrorParameters;
import ru.nsu.bolotov.model.CubicEquationParameters;
import ru.nsu.bolotov.model.InputDataRepresentation;
import ru.nsu.bolotov.utils.UtilConsts;

public final class InputParser {
    public static InputDataRepresentation parseCommandLine(String[] args) {
        Options options = getOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException exception) {
            throw new FailedParsingCmdLineException(exception.getMessage());
        }
        return new InputDataRepresentation(
                new CubicEquationParameters(
                        Double.parseDouble(commandLine.getOptionValue("a")),
                        Double.parseDouble(commandLine.getOptionValue("b")),
                        Double.parseDouble(commandLine.getOptionValue("c"))
                ),
                new CalculationErrorParameters(
                        Double.parseDouble(commandLine.getOptionValue("e")),
                        Double.parseDouble(commandLine.getOptionValue("d"))
                )
        );
    }

    private static Options getOptions() {
        Option parameterAOption = new Option("a", "parameter_a", true, "This option presents parameter A");
        parameterAOption.setRequired(true);
        Option parameterBOption = new Option("b", "parameter_b", true, "This option presents parameter B");
        parameterBOption.setRequired(true);
        Option parameterCOption = new Option("c", "parameter_c", true, "This option presents parameter C");
        parameterCOption.setRequired(true);
        Option epsilonOption = new Option("e", "epsilon", true, "This option presents epsilon");
        epsilonOption.setRequired(true);
        Option deltaOption = new Option("d", "delta", true, "This option presents delta");
        deltaOption.setRequired(true);

        Options options = new Options();
        options.addOption(parameterAOption);
        options.addOption(parameterBOption);
        options.addOption(parameterCOption);
        options.addOption(epsilonOption);
        options.addOption(deltaOption);
        return options;
    }

    private InputParser() {
        throw new IllegalStateException(UtilConsts.StringConsts.INSTANTIATION_MESSAGE);
    }
}
