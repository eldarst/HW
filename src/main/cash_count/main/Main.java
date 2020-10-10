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
        HashSet<Long> empty = new HashSet<>();
        findNominalComb(cash, nominals.size(), empty);
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
                long s = 0;
                for (Map.Entry<Long, Integer> entry : linearComb.entrySet()) {
                    s = s + entry.getKey() * entry.getValue();
                }
                HashMap<Long, Integer> Tmp = (HashMap<Long, Integer>) linearComb.clone();
                s = firstNominal * i + s;
                Tmp.put(firstNominal, i);
                if (s == sum && !cash.checkLinearComb(Tmp)) {
                    cash.addLinearComb(Tmp);
                } else if (s > sum) {
                    break;
                } else if (!currentNominals.isEmpty()) {
                    findLinearComb(cash, currentNominals, Tmp);
                }
            }
        }
    }

    public static void findNominalComb(Cash cash, int q, final HashSet<Long> bills) {
        HashSet<Long> allNominals;
        allNominals = cash.getAllNominals();
        HashSet<Long> nominals;
        nominals = bills;
        for (Long s : allNominals) {
            if (q > 0) {
                HashSet<Long> currentNominals;
                currentNominals = (HashSet<Long>) nominals.clone();
                currentNominals.add(s);
                if (!cash.checkNominalComb(currentNominals)) {
                    cash.addNominalComb(currentNominals);
                    int p = q - 1;
                    findNominalComb(cash, p, currentNominals);
                    HashMap<Long, Integer> map = new HashMap<>();
                    findLinearComb(cash, currentNominals, map);
                }
            }
        }
    }

}
