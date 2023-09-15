package ru.nsu.bolotov.model;

import java.util.Optional;

public record QuadraticEquationSolutionRepresentation(Optional<Double> firstRoot, Optional<Double> secondRoot,
                                                      double discriminant) {
}
