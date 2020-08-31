import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Test Amortize with given set of input test cases
 *
 * @author Rohit Aggarwal
 */
public class TestAmortize {

    public static void main(String[] args) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TestAmortize testAmortize = new TestAmortize();
        Amortize amortize = new Amortize();
        int i = 1;

        for (TestInput input : testAmortize.testParameters()) {
            // Loop through all test cases for amortization

            System.out.printf("\nTest %d\nInput: %s\n", i++, input);
            try {

                // Run Amortize on provided test input, and receive corresponding output
                List<Integer> amortizedList = amortize.amortize(input.amount, simpleDateFormat.parse(input.startDate), simpleDateFormat.parse(input.endDate));

                // Calculate total amount from amortized list
                int amount = 0;
                for (int amt : amortizedList) {
                    amount += amt;
                }

                // Mark the test as failed if any of the prerequisite condition is not matched. Otherwise, as success
                System.out.printf("Output: %s\n", amortizedList);
                if (amortizedList.size() != input.getMonths()) {
                    System.out.println("Result: Failed [Number of amortized amounts don't match input months]");
                } else if (amount != input.amount) {
                    System.out.println("Result: Failed [Total amount after amortization didn't match input for test case]");
                } else {
                    System.out.println("Result: Success");
                }

            } catch (ParseException exception) {
                // If any exception is raised in a test case, log the exception and continue
                System.out.format("Exception: %s", exception.getMessage());
            }
        }
    }

    /**
     * Returns a list of individual test cases for validating Amortize
     *
     * @return List
     */
    private ArrayList<TestInput> testParameters() {

        return new ArrayList<>(Arrays.asList(
            new TestInput(120000, "2019-04-01", "2020-03-31"),
            new TestInput(100000, "2019-05-01", "2020-02-29"),
            new TestInput(115000, "2019-04-16", "2020-03-31"),
            new TestInput(114840, "2019-04-01", "2020-03-15"),
            new TestInput(115160, "2019-04-01", "2020-03-16"),
            new TestInput(109840, "2019-04-16", "2020-03-15"),
            new TestInput(110160, "2019-04-16", "2020-03-16"),
            new TestInput(100000, "2019-04-17", "2020-01-07")
        ));
    }

    /**
     * Represents a single test case input
     *
     * @author Rohit Aggarwal
     */
    class TestInput {

        int amount;
        String startDate;
        String endDate;

        private int months;

        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        TestInput(int amount, String startDate, String endDate) {

            this.amount = amount;
            this.startDate = startDate;
            this.endDate = endDate;

            try {

                // Parses the provided start and end dates and calculates difference in months
                Date start = dateFormat.parse(startDate);
                Date end = dateFormat.parse(endDate);
                months = (end.getYear() - start.getYear()) * 12 + (end.getMonth() - start.getMonth()) + 1;

            } catch (ParseException exception) {
                // Don't do anything and simply absorb the error
            }
        }

        @Override
        public String toString() {
            return "[" + this.amount + ", " + this.startDate + ", " + this.endDate + "]";
        }

        /**
         * Returns the calculated difference in months between start and end dates
         *
         * @return integer
         */
        public int getMonths() {
            return this.months;
        }
    }
}
