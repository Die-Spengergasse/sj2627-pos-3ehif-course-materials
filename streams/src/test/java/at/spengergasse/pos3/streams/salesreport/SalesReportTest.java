package at.spengergasse.pos3.streams.salesreport;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SalesReportTest {

    private final Sale s1 = new Sale(1, "North", "Tea", 300);
    private final Sale s2 = new Sale(2, "South", "Coffee", 500);
    private final Sale s3 = new Sale(3, "North", "Coffee", 500);
    private final Sale s4 = new Sale(4, "North", "Cake", 100);
    private final List<Sale> sales = List.of(s1, s2, s3, s4);

    @Test
    void productListJoinsInBrackets() {
        assertThat(SalesReport.productList(sales)).isEqualTo("[Tea, Coffee, Coffee, Cake]");
        assertThat(SalesReport.productList(List.of(s1))).isEqualTo("[Tea]");
    }

    @Test
    void productListOfNoSalesIsEmptyBrackets() {
        assertThat(SalesReport.productList(List.of())).isEqualTo("[]");
    }

    @Test
    void salesPerRegionCounts() {
        assertThat(SalesReport.salesPerRegion(sales)).isEqualTo(Map.of("North", 3L, "South", 1L));
    }

    @Test
    void salesPerRegionOfNothingIsEmpty() {
        assertThat(SalesReport.salesPerRegion(List.of())).isEmpty();
    }

    @Test
    void averagePerRegionIsADouble() {
        assertThat(SalesReport.averagePerRegion(sales)).isEqualTo(Map.of("North", 300.0, "South", 500.0));
        assertThat(SalesReport.averagePerRegion(List.of(s1, s4, new Sale(5, "North", "Tea", 101))))
                .containsEntry("North", 167.0);
    }

    @Test
    void productsPerRegionKeepsTheOriginalOrder() {
        assertThat(SalesReport.productsPerRegion(sales)).isEqualTo(Map.of(
                "North", List.of("Tea", "Coffee", "Cake"),
                "South", List.of("Coffee")));
    }

    @Test
    void splitByAmountUsesGreaterOrEqual() {
        Map<Boolean, List<Sale>> split = SalesReport.splitByAmount(sales, 300);
        assertThat(split.get(true)).containsExactly(s1, s2, s3);
        assertThat(split.get(false)).containsExactly(s4);
    }

    @Test
    void splitByAmountAlwaysHasBothKeys() {
        Map<Boolean, List<Sale>> split = SalesReport.splitByAmount(sales, 10_000);
        assertThat(split).containsOnlyKeys(true, false);
        assertThat(split.get(true)).isEmpty();
        assertThat(split.get(false)).hasSize(4);
    }

    @Test
    void productByIdMapsIdsToNames() {
        assertThat(SalesReport.productById(sales)).isEqualTo(Map.of(1, "Tea", 2, "Coffee", 3, "Coffee", 4, "Cake"));
    }

    @Test
    void productByIdRejectsDuplicateIds() {
        assertThatThrownBy(() -> SalesReport.productById(List.of(s1, new Sale(1, "East", "Milk", 90))))
                .isInstanceOf(IllegalStateException.class);
    }
}
