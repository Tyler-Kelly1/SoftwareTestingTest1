import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;

public class TollCalculatorTest {

    private final org.example.TollCalculator tollCalculator = new org.example.TollCalculator();

    @ParameterizedTest(name = "Case {index} => [{0}] weight={1}, isEV={2}, isCarpool={3} -> rebate={4}")
    @CsvFileSource(resources = "data/normal_test_data.csv", numLinesToSkip = 1)
    void testCalculateDiscountValid(
            String testCaseId,
            double weight,
            boolean isEV,
            boolean isCarpool,
            double expectedDiscount) {

        try {
            double actualDiscount = tollCalculator.calculateDiscount(weight, isEV, isCarpool);
            assertEquals(expectedDiscount, actualDiscount, 0.001);
        } catch (IllegalArgumentException e) {
            fail("Unexpected IllegalArgumentException thrown for valid input on test case " + testCaseId);
        }
    }

    @ParameterizedTest(name = "Exception Case {index} => [{0}] weight={1} -> throws {4}")
    @CsvFileSource(resources = "data/exception_test_data.csv", numLinesToSkip = 1)
    void testCalculateDiscountExceptions(
            String testCaseId,
            double weight,
            boolean isEV,
            boolean isCarpool,
            String expectedException) {

        if ("IllegalArgumentException".equals(expectedException)) {
            assertThrows(IllegalArgumentException.class, () ->
                    tollCalculator.calculateDiscount(weight, isEV, isCarpool)
            );
        } else {
            fail("Unhandled exception type specified in CSV: " + expectedException);
        }
    }
}