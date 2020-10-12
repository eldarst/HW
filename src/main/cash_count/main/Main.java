package cash_count.main;

import cash_count.cash.Cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму и нажмите enter");
        String sumLine = br.readLine();
        long sum = Long.parseLong(sumLine);
        if (sum < 0) {
            throw new IllegalArgumentException("Value is negative");
        }
        else if(sum == 0){
            throw new IllegalArgumentException("Value equals to  0");
        }
        System.out.println("Введите купюры через пробел");
        String nominalsLine = br.readLine();
        String[] nominalStrings = nominalsLine.split(" ");
        HashSet<Long> nominals;
        nominals = new HashSet<>();
        for (String nominalString : nominalStrings) {
            long i = Long.parseLong(nominalString);

            if (i < 0 || i > sum) {
                throw new IllegalArgumentException("Invalid argument");
            }
            nominals.add(i);
        }
        System.out.println("Сумма для размена: " + sum);
        System.out.println("Купюры для размена: ");
        System.out.println(nominals);
        Cash cash = new Cash(sum, nominals);
        HashSet<Long> emptyHashSet = new HashSet<>();
        findNominalComb(cash, nominals.size(), emptyHashSet);
        cash.printAllComb();
    }


    public static void findLinearComb(Cash cash, HashSet<Long> nominals, HashMap<Long, Integer> linearComb) {
        int n = nominals.size();
        HashSet<Long> currentNominals = (HashSet<Long>) nominals.clone();
        long firstNominal;
        List<Long> list = new ArrayList<>(nominals);
        firstNominal = list.get(0);
        long k = cash.getSum() / firstNominal;
        currentNominals.remove(firstNominal);
        long sum = cash.getSum();
        if (n > 0) {
            for (int i = 1; i <= k; ++i) {
                long currentSum = 0;
                for (Map.Entry<Long, Integer> entry : linearComb.entrySet()) {
                    currentSum = currentSum + entry.getKey() * entry.getValue();
                }
                HashMap<Long, Integer> Tmp = (HashMap<Long, Integer>) linearComb.clone();
                currentSum = firstNominal * i + currentSum;
                Tmp.put(firstNominal, i);
                if (currentSum == sum && !cash.checkLinearComb(Tmp)) {
                    cash.addLinearComb(Tmp);
                } else if (currentSum > sum) {
                    break;
                } else if (!currentNominals.isEmpty()) {
                    findLinearComb(cash, currentNominals, Tmp);
                }
            }
        }
    }

    public static void findNominalComb(Cash cash, int reverseRecursionStep, final HashSet<Long> bills) {
        HashSet<Long> allNominals;
        allNominals = cash.getAllNominals();
        HashSet<Long> oldNominals;
        oldNominals = bills;
        for (Long s : allNominals) {
            if (reverseRecursionStep > 0) {
                HashSet<Long> currentNominals;
                currentNominals = (HashSet<Long>) oldNominals.clone();
                currentNominals.add(s);
                if (!cash.checkNominalComb(currentNominals)) {
                    cash.addNominalComb(currentNominals);
                    int nextRecursionStep = reverseRecursionStep - 1;
                    findNominalComb(cash, nextRecursionStep, currentNominals);
                    HashMap<Long, Integer> emptyHashMap = new HashMap<>();
                    findLinearComb(cash, currentNominals, emptyHashMap);
                }
            }
        }
    }

}
