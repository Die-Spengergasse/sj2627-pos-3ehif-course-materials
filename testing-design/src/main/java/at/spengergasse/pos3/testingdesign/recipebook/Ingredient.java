package at.spengergasse.pos3.testingdesign.recipebook;

public record Ingredient(String name, int grams) {

    public Ingredient {
        // TODO validate with Require
    }

    public Ingredient scaledBy(int factor) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isNamed(String other) {
        throw new UnsupportedOperationException("TODO");
    }
}
