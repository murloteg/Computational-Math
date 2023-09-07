package ru.nsu.bolotov.model;

import java.util.Optional;

public record QuadraticEquationRootsRepresentation(Optional<Double> firstRoot, Optional<Double> secondRoot,
                                                   double discriminant) {
}
