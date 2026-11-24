package at.spengergasse.pos3.testingdesign.recipebook;

/// Guard clauses: each method returns its argument if it is valid and throws
/// an IllegalArgumentException (message contains the parameter name) otherwise.
public final class Require {

    private Require() {
    }

    public static <T> T notNull(T value, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public static String notBlank(String value, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public static int positive(int value, String name) {
        throw new UnsupportedOperationException("TODO");
    }
}
