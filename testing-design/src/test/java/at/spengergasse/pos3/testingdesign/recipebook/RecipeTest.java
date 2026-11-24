package at.spengergasse.pos3.testingdesign.recipebook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecipeTest {

    private static final Ingredient FLOUR = new Ingredient("Flour", 200);
    private static final Ingredient SUGAR = new Ingredient("Sugar", 50);

    // --- Require ---

    @Test
    void validValuesAreReturnedUnchanged() {
        assertThat(Require.notNull("x", "p")).isEqualTo("x");
        assertThat(Require.notBlank("abc", "p")).isEqualTo("abc");
        assertThat(Require.positive(7, "p")).isEqualTo(7);
    }

    @Test
    void notNullRejectsNullAndNamesTheParameter() {
        assertThatThrownBy(() -> Require.notNull(null, "ingredients"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ingredients");
    }

    @Test
    void notBlankRejectsNullEmptyAndWhitespace() {
        assertThatThrownBy(() -> Require.notBlank(null, "name"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name");
        assertThatThrownBy(() -> Require.notBlank("", "name"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Require.notBlank("   ", "name"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void positiveRejectsZeroAndNegative() {
        assertThatThrownBy(() -> Require.positive(0, "grams"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("grams");
        assertThatThrownBy(() -> Require.positive(-3, "grams"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // --- Ingredient ---

    @Test
    void validIngredientKeepsItsValues() {
        var ingredient = new Ingredient("Butter", 125);
        assertThat(ingredient.name()).isEqualTo("Butter");
        assertThat(ingredient.grams()).isEqualTo(125);
    }

    @Test
    void constructorRejectsBlankName() {
        assertThatThrownBy(() -> new Ingredient(null, 10))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ingredient(" ", 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructorRejectsNonPositiveGrams() {
        assertThatThrownBy(() -> new Ingredient("Salt", 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Ingredient("Salt", -5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void scaledByReturnsNewIngredient() {
        var scaled = FLOUR.scaledBy(3);
        assertThat(scaled).isEqualTo(new Ingredient("Flour", 600));
        assertThat(FLOUR.grams()).isEqualTo(200);
    }

    @Test
    void scaledByRejectsNonPositiveFactor() {
        assertThatThrownBy(() -> FLOUR.scaledBy(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isNamedIgnoresCase() {
        assertThat(FLOUR.isNamed("flour")).isTrue();
        assertThat(FLOUR.isNamed("FLOUR")).isTrue();
        assertThat(FLOUR.isNamed("sugar")).isFalse();
    }

    // --- Recipe ---

    @Test
    void validRecipeKeepsItsValues() {
        var recipe = new Recipe("Shortbread", 4, List.of(FLOUR, SUGAR));
        assertThat(recipe.name()).isEqualTo("Shortbread");
        assertThat(recipe.servings()).isEqualTo(4);
        assertThat(recipe.ingredients()).containsExactly(FLOUR, SUGAR);
    }

    @Test
    void constructorRejectsInvalidArguments() {
        assertThatThrownBy(() -> new Recipe("", 4, List.of()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Recipe("Cake", 0, List.of()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Recipe("Cake", 2, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shortConstructorCreatesRecipeWithoutIngredients() {
        var recipe = new Recipe("Tea", 1);
        assertThat(recipe.ingredients()).isEmpty();
        assertThat(recipe.totalGrams()).isZero();
    }

    @Test
    void recipeKeepsACopyOfTheList() {
        var source = new ArrayList<>(List.of(FLOUR));
        var recipe = new Recipe("Bread", 2, source);
        source.add(SUGAR);
        assertThat(recipe.ingredients()).containsExactly(FLOUR);
    }

    @Test
    void ingredientsListIsUnmodifiable() {
        var recipe = new Recipe("Bread", 2, new ArrayList<>(List.of(FLOUR)));
        assertThatThrownBy(() -> recipe.ingredients().add(SUGAR))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void withIngredientReturnsNewRecipeAndLeavesOriginalUnchanged() {
        var original = new Recipe("Bread", 2, List.of(FLOUR));
        var extended = original.withIngredient(SUGAR);
        assertThat(extended.ingredients()).containsExactly(FLOUR, SUGAR);
        assertThat(extended.name()).isEqualTo("Bread");
        assertThat(extended.servings()).isEqualTo(2);
        assertThat(original.ingredients()).containsExactly(FLOUR);
    }

    @Test
    void withIngredientRejectsNull() {
        assertThatThrownBy(() -> new Recipe("Bread", 2).withIngredient(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void totalGramsSumsAllIngredients() {
        assertThat(new Recipe("Shortbread", 4, List.of(FLOUR, SUGAR)).totalGrams()).isEqualTo(250);
    }

    @Test
    void scaledByScalesServingsAndIngredients() {
        var scaled = new Recipe("Shortbread", 4, List.of(FLOUR, SUGAR)).scaledBy(2);
        assertThat(scaled.servings()).isEqualTo(8);
        assertThat(scaled.ingredients())
                .containsExactly(new Ingredient("Flour", 400), new Ingredient("Sugar", 100));
    }

    @Test
    void recipeScaledByRejectsNonPositiveFactor() {
        assertThatThrownBy(() -> new Recipe("Bread", 2).scaledBy(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void containsIgnoresCase() {
        var recipe = new Recipe("Shortbread", 4, List.of(FLOUR, SUGAR));
        assertThat(recipe.contains("flour")).isTrue();
        assertThat(recipe.contains("Butter")).isFalse();
    }
}
