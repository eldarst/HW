package cash_count.main;

import cash_count.cash.Cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму и нажмите enter");
        String sumLine = br.readLine();
        Long sum = checkNumberEntering(sumLine);
        System.out.println("Введите купюры через пробел");
        String nominalsLine = br.readLine();
        HashSet<Long> allNominals = checkNominalEntering(nominalsLine, sum);
        System.out.println("Сумма для размена: " + sum);
        System.out.println("Купюры для размена: ");
        System.out.println(allNominals);
        Cash cash = new Cash(sum, allNominals);
        HashSet<Long> emptyHashSet = new HashSet<>();
        findNominalComb(cash, allNominals.size(), emptyHashSet);
        cash.printAllComb();
    }

    public static Long checkNumberEntering(String sumLine) throws IOException {
        try {
            long sum = Long.parseLong(sumLine);
            if (sum < 0) {
                throw new IllegalArgumentException("Value is negative");
            } else if (sum == 0) {
                throw new IllegalArgumentException("Value equals to 0");
            }
            return sum;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Number was expected");
        }
    }

    private static HashSet<Long> checkNominalEntering(String nominalsLine, Long sum) throws IOException {
        String[] nominalStrings = nominalsLine.split(" ");
        HashSet<Long> allNominals = new HashSet<>();
        for (String nominalString : nominalStrings) {
            checkNumberEntering(nominalString);
            long i = Long.parseLong(nominalString);
            if (i < 0 || i > sum) {
                throw new IllegalArgumentException("Invalid argument");
            }
            allNominals.add(i);
        }
        return allNominals;
    }

    //Находим линейные комбинации которые дают сумму, из комбинаций номиналов
    public static void findLinearComb(Cash cash, HashSet<Long> nominalsCombination, HashMap<Long, Integer> linearComb) {
        int n = nominalsCombination.size();
        HashSet<Long> currentNominals = (HashSet<Long>) nominalsCombination.clone();
        long firstNominal;
        List<Long> list = new ArrayList<>(nominalsCombination);
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

    //Находим комбинации номиналов: Номиналы = {1, 2, 3} -> комбинации = {1}, {2}, {3}, {1,2}, {1,3}, {1,2,3}
    public static void findNominalComb(Cash cash, int reverseRecursionStep, final HashSet<Long> nominals) {
        HashSet<Long> allNominals;
        allNominals = cash.getAllNominals();
        HashSet<Long> oldNominals;
        oldNominals = nominals;
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
