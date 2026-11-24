package at.spengergasse.pos3.testingdesign.recipebook;

import java.util.List;

/// # Rich Domain: Recipe
///
/// Build a tiny **rich domain model** for a recipe book: immutable records that validate themselves, a guard-clause helper, and behaviour that lives inside the domain classes.
///
/// All classes are in one package here; in a real project `Require` would live in `foundation` and the records in `domain`.
///
/// 1. **`Require`** (guard clauses). Each method returns its argument if it is valid and throws an `IllegalArgumentException` otherwise. The message must contain the parameter `name`.
///    - `notNull(T value, String name)`: `value` must not be `null`.
///    - `notBlank(String value, String name)`: not `null`, not empty and not only whitespace.
///    - `positive(int value, String name)`: must be greater than 0.
/// 2. **`Ingredient(String name, int grams)`**: the compact constructor requires a non-blank `name` and positive `grams`.
///    - `scaledBy(int factor)`: a new `Ingredient` with `grams * factor`; `factor` must be positive.
///    - `isNamed(String other)`: `true` if the name equals `other`, ignoring case.
/// 3. **`Recipe(String name, int servings, List<Ingredient> ingredients)`**: the compact constructor requires a non-blank `name`, positive `servings` and a non-null list. It stores an **unmodifiable copy** of the list, so later changes to the caller's list do not affect the recipe. The constructor `Recipe(String name, int servings)` (no ingredients) is already there.
///    - `withIngredient(Ingredient ingredient)`: a **new** `Recipe` with the ingredient appended (the original stays unchanged); `null` is rejected.
///    - `totalGrams()`: the sum of the grams of all ingredients (0 if there are none).
///    - `scaledBy(int factor)`: a new `Recipe` with `servings * factor` and every ingredient scaled by `factor`; `factor` must be positive.
///    - `contains(String ingredientName)`: `true` if any ingredient is named like that (ignoring case). Let the ingredient answer the question (*tell, don't ask*).
///
/// Run `RecipeTest`. Its tests are grouped by class (`Require`, then `Ingredient`, then `Recipe`), so you can work through them one after the other.
public record Recipe(String name, int servings, List<Ingredient> ingredients) {

    public Recipe {
        // TODO validate with Require and store an unmodifiable copy of the list
    }

    public Recipe(String name, int servings) {
        this(name, servings, List.of());
    }

    public Recipe withIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("TODO");
    }

    public int totalGrams() {
        throw new UnsupportedOperationException("TODO");
    }

    public Recipe scaledBy(int factor) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean contains(String ingredientName) {
        throw new UnsupportedOperationException("TODO");
    }
}
