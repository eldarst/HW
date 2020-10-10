import cash_count.main.Main;
import cash_count.cash.Cash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;


public class MainTest {
    @Test
    public void TestMain() {
        long sum = 5;
        HashSet<Long> nominals = new HashSet<>();
        // nominals = [1, 2]
        for (long i = 1; i < 3; ++i) {
            nominals.add(i);
        }
        Cash cash = new Cash(sum, nominals);
        HashSet<Long> empty = new HashSet<>();
        Main.findNominalComb(cash, nominals.size(), empty);
        int result = cash.getLinearComb().size();
        Assertions.assertEquals(3, result);
        HashSet<HashMap<Long, Integer>> expectedLinearComb = new HashSet<>();
        HashMap<Long, Integer> linearCombination1 = new HashMap<>();
        linearCombination1.put((long)1, 5);
        expectedLinearComb.add(linearCombination1);
        HashMap<Long, Integer> linearCombination2 = new HashMap<>();
        linearCombination2.put((long)1,1);
        linearCombination2.put((long)2,2);
        expectedLinearComb.add(linearCombination2);
        HashMap<Long, Integer> linearCombination3 = new HashMap<>();
        linearCombination3.put((long)1,3);
        linearCombination3.put((long)2,1);
        expectedLinearComb.add(linearCombination3);
        Assertions.assertEquals(expectedLinearComb, cash.getLinearComb());
    }
}
