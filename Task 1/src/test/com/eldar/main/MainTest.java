package com.eldar.main;

import com.eldar.cash_count.cash.Cash;
import com.eldar.cash_count.main.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;


public class MainTest {
    @Test
    public void testSumInputWithString() {
        String sumLine = "one";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithEmptyString() {
        String sumLine = "";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithNegative() {
        String sumLine = "-19";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithZero() {
        String sumLine = "-19";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputNominalsWithEmptyString() {
        String nominalLine = "";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(nominalLine);
        });
    }

    @Test
    public void testInputNominalsWithNegative() {
        String nominalLine = "11 2 -1 3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(nominalLine);
        });
    }

    @Test
    public void testInputNominalsWithZero() {
        String nominalLine = "11 0 -1 3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(nominalLine);
        });
    }

    @Test
    public void testMain1() {
        long sum = 5;
        HashSet<Long> nominals = new HashSet<>();
        // nominals = [1, 2]
        for (long i = 1; i < 3; ++i) {
            nominals.add(i);
        }
        Cash cash = new Cash(sum, nominals);
        HashSet<Long> emptyLinearComb = new HashSet<>();
        Main.findNominalComb(cash, nominals.size(), emptyLinearComb);
        int quantityOfLinearComb = cash.getLinearComb().size();
        Assertions.assertEquals(3, quantityOfLinearComb);
        HashSet<HashMap<Long, Integer>> expectedLinearComb = new HashSet<>();
        HashMap<Long, Integer> linearCombination1 = new HashMap<>();
        linearCombination1.put((long) 1, 5);
        expectedLinearComb.add(linearCombination1);
        HashMap<Long, Integer> linearCombination2 = new HashMap<>();
        linearCombination2.put((long) 1, 1);
        linearCombination2.put((long) 2, 2);
        expectedLinearComb.add(linearCombination2);
        HashMap<Long, Integer> linearCombination3 = new HashMap<>();
        linearCombination3.put((long) 1, 3);
        linearCombination3.put((long) 2, 1);
        expectedLinearComb.add(linearCombination3);
        Assertions.assertEquals(expectedLinearComb, cash.getLinearComb());

    }

    @Test
    public void testMain2() {
        long sum = 1000000;
        HashSet<Long> nominals = new HashSet<>();
        nominals.add((long) 200000);
        nominals.add((long) 100000);
        Cash cash = new Cash(sum, nominals);
        HashSet<Long> emptyLinearComb = new HashSet<>();
        Main.findNominalComb(cash, nominals.size(), emptyLinearComb);
        int quantityOfLinearComb = cash.getLinearComb().size();
        Assertions.assertEquals(6, quantityOfLinearComb);
        HashSet<HashMap<Long, Integer>> expectedLinearComb = new HashSet<>();
        HashMap<Long, Integer> linearCombination1 = new HashMap<>();
        linearCombination1.put((long) 100000, 10);
        expectedLinearComb.add(linearCombination1);
        HashMap<Long, Integer> linearCombination2 = new HashMap<>();
        linearCombination2.put((long) 100000, 2);
        linearCombination2.put((long) 200000, 4);
        expectedLinearComb.add(linearCombination2);
        HashMap<Long, Integer> linearCombination3 = new HashMap<>();
        linearCombination3.put((long) 100000, 6);
        linearCombination3.put((long) 200000, 2);
        expectedLinearComb.add(linearCombination3);
        HashMap<Long, Integer> linearCombination4 = new HashMap<>();
        linearCombination4.put((long) 100000, 8);
        linearCombination4.put((long) 200000, 1);
        expectedLinearComb.add(linearCombination4);
        HashMap<Long, Integer> linearCombination5 = new HashMap<>();
        linearCombination5.put((long) 100000, 4);
        linearCombination5.put((long) 200000, 3);
        expectedLinearComb.add(linearCombination5);
        HashMap<Long, Integer> linearCombination6 = new HashMap<>();
        linearCombination6.put((long) 200000, 5);
        expectedLinearComb.add(linearCombination6);
        Assertions.assertEquals(expectedLinearComb, cash.getLinearComb());

    }
}
