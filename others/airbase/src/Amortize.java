import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Takes an amount as input along with start and end dates to prepare an array of amortized amounts
 * spread as evenly as possible in the provided dates
 *
 * @author Rohit Aggarwal
 */
public class Amortize {

    /**
     * Whether the provided date is first of the month or not
     *
     * @param calendar Date in Calendar representation
     * @return boolean
     */
    private boolean isFirstDay(Calendar calendar) {
        return calendar.get(Calendar.DATE) == 1;
    }

    /**
     * Whether the provided date is last of the month or not
     *
     * @param calendar Date in Calendar representation
     * @return boolean
     */
    private boolean isLastDay(Calendar calendar) {
        return calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Ratio of the number of days covered towards the end of the month
     *
     * @param calendar Date in calendar representation
     * @return double
     */
    private double getEndDaysRatio(Calendar calendar) {

        int actualDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return (actualDays - calendar.get(Calendar.DATE) + 1) / (actualDays * 1.0);
    }

    /**
     * Ratio of the number of days covered towards the beginning of the month
     *
     * @param calendar Date in Calendar representation
     * @return double
     */
    private double getBeginDaysRatio(Calendar calendar) {

        int actualDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return calendar.get(Calendar.DATE) / (actualDays * 1.0);
    }

    /**
     * Finds if there is a difference in the amount before amortization and total amount from amortization list
     * If such a difference exists, evenly distributes remaining amount throughout the amortized list
     *
     * @param totalAmount Expected total amount before amortization
     * @param amortizedList Amortized list of amounts
     */
    private void balanceOut(int totalAmount, List<Integer> amortizedList) {

        // Subtract each amount in amortization list from total amount
        for (int amt : amortizedList) {
            totalAmount -= amt;
        }

        // If final amount reaches 0, that means there is no balance remaining
        if (totalAmount == 0) {
            return;
        }

        int size = amortizedList.size();
        int jump = size / Math.abs(totalAmount);
        int adjustment = totalAmount < 0 ? -1 : 1;
        int index = 0;

        // Loop until there is no balance remaining, distributing amount evenly across amortization list
        while (totalAmount != 0) {
            index = (index + jump) % size;
            amortizedList.set(index, amortizedList.get(index) + adjustment);
            totalAmount -= adjustment;
        }
    }

    /**
     * Takes an amount as input along with start and end dates to prepare an array of amortized amounts
     * spread as evenly as possible in the provided dates
     *
     * @param amount Amount which needs to be amortized
     * @param startDate Starting date for period of amortization
     * @param endDate Ending date for period of amortization
     * @return List
     */
    public List<Integer> amortize(int amount, Date startDate, Date endDate) {

        List<Integer> amortizedList = new ArrayList<>();

        // Convert start and end date to Calendar format
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int startMonth = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
        int endMonth = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);

        // Find if first / last months are partial. Calculates number of months in difference
        boolean isFirstMonthPartial = !isFirstDay(startCalendar);
        boolean isLastMonthPartial = !isLastDay(endCalendar);
        double monthsDiff = (endMonth - startMonth) + 1;
        double firstMonthRatio = 0.0, lastMonthRatio = 0.0;

        // If first month is partial, calculates the ratio of number of days towards the end of the month
        // with respect to total number of days of the month
        if (isFirstMonthPartial) {
            firstMonthRatio = getEndDaysRatio(startCalendar);
            monthsDiff += (firstMonthRatio - 1);
        }

        // If last month is partial, calculates the ratio of number of days towards the beginning of the month
        // with respect to total number of days of the month
        if (isLastMonthPartial) {
            lastMonthRatio = getBeginDaysRatio(endCalendar);
            monthsDiff += (lastMonthRatio - 1);
        }

        // Find the amortized amount per month
        double amortizedAmt = amount / monthsDiff;

        // If first month is partial, calculates first month's actual amortized amount as per days ratio
        if (isFirstMonthPartial) {
            amortizedList.add((int) Math.round(amortizedAmt * firstMonthRatio));
            startMonth++;
        }

        // Fill in all full months with calculated amortized amount
        while (startMonth < endMonth) {
            amortizedList.add((int) Math.round(amortizedAmt));
            startMonth++;
        }

        // If last month is partial, calculates last month's actual amortized amount as per days ratio
        // Otherwise, fill in full amortized amount for a full last month
        if (isLastMonthPartial) {
            amortizedList.add((int) Math.round(amortizedAmt * lastMonthRatio));
        } else {
            amortizedList.add((int) Math.round(amortizedAmt));
        }

        // Balance out any remaining amount and return the final amortized list
        balanceOut(amount, amortizedList);
        return amortizedList;
    }
}
