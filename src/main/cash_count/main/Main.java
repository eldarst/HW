package cash_count.main;

import cash_count.cash.Cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму и нажмите enter");
        String sumLine = br.readLine();
        long sum = Long.parseLong(sumLine);
        System.out.println("Введите купюры через пробел");
        String nominalsLine = br.readLine();
        String[] nominalStrings = nominalsLine.split(" ");
        HashSet<Integer> nominals;
        nominals = new HashSet<>();
        for (String nominalString : nominalStrings) {
            nominals.add(Integer.parseInt(nominalString));
        }
        System.out.println("Сумма для размена: " + sum);
        System.out.println("Купюры для размена: ");
        System.out.println(nominals);
        Cash cash = new Cash(sum, nominals);
        HashSet<Integer> now = new HashSet<>();
        findNominalComb(cash, nominals.size(), now);
        cash.printAllComb();
    }

    protected static void AddValueToVec(Vector<Integer> vec, int value, int q) {
        for (int i = 0; i < q; ++i) {
            vec.add(value);
        }
    }

    public static void findLinearComb(Cash cash, HashSet<Integer> nominals, HashMap<Integer, Integer> linearComb) {
        int n = nominals.size();
        HashSet<Integer> currentNominals = (HashSet<Integer>) nominals.clone();
        int firstNominal;
        List<Integer> list = new ArrayList<>(nominals);
        firstNominal = list.get(0);
        long k = cash.getSum() / firstNominal;
        currentNominals.remove(firstNominal);
        long sum = cash.getSum();
        if (n > 0) {
            for (int i = 0; i <= k; ++i) {
                int s = 0;
                for (Map.Entry<Integer, Integer> entry : linearComb.entrySet()) {
                    s = s + entry.getKey() * entry.getValue();
                }
                HashMap<Integer, Integer> Tmp = (HashMap<Integer, Integer>) linearComb.clone();
                s = firstNominal * i + s;
                Tmp.put(firstNominal, i);
                if (s == sum && !cash.checkLinearComb(Tmp)) {
                    cash.addLinearComb(Tmp);
                    Vector<Integer> res = new Vector<>();
                    for (Map.Entry<Integer, Integer> entry : Tmp.entrySet()) {
                        AddValueToVec(res, entry.getKey(), entry.getValue());
                    }
                    Collections.sort(res);
                    cash.addSucceedComb(res);
                } else if (s > sum) {
                    break;
                } else if (!currentNominals.isEmpty()) {
                    findLinearComb(cash, currentNominals, Tmp);
                }
            }
        }
    }

    public static void findNominalComb(Cash cash, int q, final HashSet<Integer> bills) {
        HashSet<Integer> allNominals;
        allNominals = cash.getAllNominals();
        HashSet<Integer> nominals;
        nominals= bills;
        for (Integer s : allNominals) {
            if (q > 0) {
                HashSet<Integer> currentNominals;
                currentNominals = (HashSet<Integer>) nominals.clone();
                currentNominals.add(s);
                if (!cash.checkNominalComb(currentNominals)) {
                    cash.addNominalComb(currentNominals);
                    int p = q - 1;
                    findNominalComb(cash, p, currentNominals);
                    HashMap<Integer, Integer> map = new HashMap<>();
                    findLinearComb(cash, currentNominals, map);
                }
            }
        }
    }

}
