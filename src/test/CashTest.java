import cash_count.cash.Cash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


public class CashTest {
    @Test
    public void TestCash() {
        HashSet<Long> testSet = new HashSet<>();
        testSet.add((long) 1);
        Cash cash1 = new Cash((long) 1, testSet);
        HashSet<Long> nominalSet = new HashSet<>();
        for (int i = 1; i < 20; ++i) {
            if (i % 2 == 0) {
                nominalSet.add((long) i);
            }
        }
        cash1.addNominalComb(nominalSet);
        Assertions.assertEquals(true, cash1.checkNominalComb(nominalSet));
    }
}

