package chapter_2_4;

import java.util.Comparator;
import java.util.Date;

/**
 * 交易对象
 */
public class Transaction implements Comparable<Transaction>{
    private final String  who;
    private final Date when;
    private final double amount;

    public static final WhoOrder WHO_ORDER = new WhoOrder();
    public static final WhenOrder WHEN_ORDER = new WhenOrder();
    public static final HowMunchOrder HOW_MUNCH_ORDER = new HowMunchOrder();

    public Transaction(String who, Date when, double amount) {
        if (Double.isInfinite(amount) || Double.isNaN(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-10s %tF %8.2f", who, when, amount);
    }

    /**
     * 默认根据金额排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Transaction o) {
        return Double.compare(amount, o.amount);
    }

    public static class WhoOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.who.compareTo(o2.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.when.compareTo(o2.when);
        }
    }

    public static class HowMunchOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Double.compare(o1.amount, o2.amount);
        }
    }


}
