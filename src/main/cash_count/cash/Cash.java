package cash_count.cash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cash {
    private long sum;
    private HashSet<Long> allNominals;
    private HashSet<HashSet<Long>> nominalComb = new HashSet<>();
    private HashSet<HashMap<Long, Integer>> linearCombOfNominals = new HashSet<>();

    public Cash(final Long allSum, final HashSet<Long> nominals) {
        this.sum = allSum;
        this.allNominals = nominals;
    }


    public final void printAllComb() {
        System.out.println("Количество комбинаций: " + linearCombOfNominals.size());
        for (HashMap<Long, Integer> lin : linearCombOfNominals) {
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
        return linearCombOfNominals;
    }

    //Добавляем новую комбинацию номиналов
    public void addNominalComb(final HashSet<Long> currentNominalCombination) {
        nominalComb.add(currentNominalCombination);
    }

    //Купюры для размена
    public HashSet<Long> getAllNominals() {
        return allNominals;
    }

    public long getSum() {
        return sum;
    }

    //Проверяем была ли добавлена комбинация в список удовлетворяющих номиналов
    public boolean checkNominalComb(final HashSet<Long> currentNominalCombination) {
        if(!nominalComb.isEmpty()) {
            return false;
        }
        else if (nominalComb.contains(currentNominalCombination)) {
            return true;
        } else {
            return false;
        }
    }

    //Добавляем линейные комбинацию номиналов: [a1 * x1], [a1 *x2, a2 * x3] ...
    // x1,x2,x3... коэффициенты
    public void addLinearComb(final HashMap<Long, Integer> currentLinearCombination) {
        linearCombOfNominals.add(currentLinearCombination);
    }

    // Проверяем была ли добавлена комбинация в спискок удовлетворяющих лин.комб.
    public boolean checkLinearComb(HashMap<Long, Integer> currentLinearCombination) {
        if (linearCombOfNominals.contains(currentLinearCombination)) {
            return true;
        } else {
            return false;
        }
    }

}
