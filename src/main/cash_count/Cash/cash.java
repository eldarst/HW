package cash_count.cash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

public class Cash {
    private long sum;
    private HashSet<Integer> nominals;
    private HashSet<Vector<Integer>> succeedLinearComb = new HashSet<>();
    private HashSet<HashSet<Integer>> nominalComb = new HashSet<>();
    private HashSet<HashMap<Integer, Integer>> linearComb = new HashSet<>();

    public Cash(Long c, final HashSet<Integer> nominal1) {
        this.sum = c;
        this.nominals = nominal1;
    }


    public final void printAllComb() {
        System.out.println("Количество комбинаций: " + linearComb.size());
        for (HashMap<Integer, Integer> lin : linearComb) {
            boolean t = true;
            for (Map.Entry<Integer, Integer> entry : lin.entrySet()) {
                if(t){
                    System.out.print(entry.getKey() + "*" + entry.getValue());
                    t = false;
                }
                else {
                    System.out.print(" + " + entry.getKey() + "*" + entry.getValue());
                }
            }
            System.out.println("=" + sum);
        }
    }

    //Добавляем новую комбинацию номиналов
    public void addNominalComb(final HashSet<Integer> comb) {
        nominalComb.add(comb);
    }

    //Купюры для размена
    public HashSet<Integer> getAllNominals() {
        return nominals;
    }

    public long getSum() {
        return sum;
    }

    //Проверяем была ли добавлена комбинация в список удовлетворяющих номиналов
    public boolean checkNominalComb(final HashSet<Integer> comb) {
        if (nominalComb.contains(comb)) {
            return true;
        } else {
            return false;
        }
    }

    //Добавляем линейные комбинацию номиналов: [a1 * x1], [a1 *x2, a2 * x3] ...
    // x1,x2,x3... коэффициенты
    public void addLinearComb(final HashMap<Integer, Integer> HashMap1) {
        linearComb.add(HashMap1);
    }

    // Проверяем была ли добавлена комбинация в спискок удовлетворяющих лин.комб.
    public boolean checkLinearComb(HashMap<Integer, Integer> HashMap1) {
        if (linearComb.contains(HashMap1)) {
            return true;
        } else {
            return false;
        }
    }

}
