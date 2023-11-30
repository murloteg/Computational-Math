package ru.nsu.bolotov.model;

import ru.nsu.bolotov.model.functions.InputFunction;

public record InputParameters(InputFunction inputFunction, IntegrationLimits integrationLimits, int numberOfSegments) {
}
