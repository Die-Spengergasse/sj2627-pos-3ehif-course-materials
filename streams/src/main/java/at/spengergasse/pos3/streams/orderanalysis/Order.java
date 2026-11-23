package at.spengergasse.pos3.streams.orderanalysis;

import java.util.List;

public record Order(String customer, List<String> items, int totalCents) {
}
