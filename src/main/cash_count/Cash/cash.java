package cash_count.cash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cash {
    private long sum;
    private HashSet<Long> allNominals;
    private HashSet<HashSet<Long>> nominalComb ;
    private HashSet<HashMap<Long, Integer>> linearComb;

    public Cash(Long c, final HashSet<Long> nominal1) {
        this.sum = c;
        this.allNominals = nominal1;
    }


    public final void printAllComb() {
        System.out.println("Количество комбинаций: " + linearComb.size());
        for (HashMap<Long, Integer> lin : linearComb) {
            boolean t = true;
            for (Map.Entry<Long, Integer> entry : lin.entrySet()) {
                if (t) {
                    System.out.print(entry.getKey() + "*" + entry.getValue());
                    t = false;
                } else {
                    System.out.print(" + " + entry.getKey() + "*" + entry.getValue());
                }
            }
            System.out.println("=" + sum);
        }
    }

    //Возвращает все добавленные линейные комбинации
    public HashSet<HashMap<Long, Integer>> getLinearComb() {
        return linearComb;
    }

    //Добавляем новую комбинацию номиналов
    public void addNominalComb(final HashSet<Long> comb) {
        nominalComb.add(comb);
    }

    //Купюры для размена
    public HashSet<Long> getAllNominals() {
        return allNominals;
    }

    public long getSum() {
        return sum;
    }

    //Проверяем была ли добавлена комбинация в список удовлетворяющих номиналов
    public boolean checkNominalComb(final HashSet<Long> comb) {
        if (nominalComb.contains(comb)) {
            return true;
        } else {
            return false;
        }
    }

    //Добавляем линейные комбинацию номиналов: [a1 * x1], [a1 *x2, a2 * x3] ...
    // x1,x2,x3... коэффициенты
    public void addLinearComb(final HashMap<Long, Integer> HashMap1) {
        linearComb.add(HashMap1);
    }

    // Проверяем была ли добавлена комбинация в спискок удовлетворяющих лин.комб.
    public boolean checkLinearComb(HashMap<Long, Integer> HashMap1) {
        if (linearComb.contains(HashMap1)) {
            return true;
        } else {
            return false;
        }
    }

}
