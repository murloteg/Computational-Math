package ru.nsu.bolotov.model;

import java.util.List;

public record ApproximationOrders(List<Double> orders) {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Approximation orders:\n");
        stringBuilder.append("1) Left Rectangle's method: ").append(orders.get(0)).append("\n");
        stringBuilder.append("2) Trapezoid method: ").append(orders.get(1)).append("\n");
        stringBuilder.append("3) Simpson's method: ").append(orders.get(2)).append("\n");
        return stringBuilder.toString();
    }
}
