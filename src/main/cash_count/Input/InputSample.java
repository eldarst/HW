package cash_count.Input;

import cash_count.Cash.cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class InputSample {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму и нажмите enter");
        String sumLine = br.readLine();
        long sum = Long.parseLong(sumLine);
        System.out.println("Введите купюры через пробел");
        String nominalsLine = br.readLine();
        String[] nominalStrings = nominalsLine.split(" ");
        int n = nominalStrings.length;
        HashSet<Integer> nominals;
        nominals = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            nominals.add(Integer.parseInt(nominalStrings[i]));
        }
        System.out.println("Сумма для размена: " + sum);
        System.out.println("Купюры для размена: ");
        System.out.println(nominals);
        cash Cash = new cash(sum, nominals);
        HashSet<Integer> now = new HashSet<>();
        CheckAll(Cash, nominals.size(), now);
        Cash.PrintComb();
    }
    protected static void AddValueToVec(Vector<Integer> vec, int value, int q){
        for(int i = 0; i < q; ++i)
        {
            vec.add(value);
        }
    }
    public static void Check(cash Cash, HashSet<Integer> bills, HashMap<Integer, Integer> map)
    {
        //System.out.println("Check");
        int n = bills.size();
        HashSet<Integer> CurrentBillls = (HashSet<Integer>) bills.clone();
        int tmp;
        List<Integer> list = new ArrayList<Integer>(bills);
        tmp = list.get(0);
        long k = Cash.GetValue()/tmp;
        CurrentBillls.remove(tmp);
        long sum = Cash.GetValue();
        HashMap<Integer, Integer> q = (HashMap<Integer, Integer>) map.clone();
        if(n > 0) {
            for( int i = 0; i <=k; ++i){
                int s = 0;
                for(Map.Entry<Integer, Integer> entry : q.entrySet()){
                    s = s + entry.getKey() * entry.getValue();
                }
                HashMap<Integer, Integer> Tmp = (HashMap<Integer, Integer>) q.clone();
                s = tmp * i + s;
                Tmp.put(tmp, i);
                //System.out.println("Sum is " + s);
                //System.out.println("Tmp is" + Tmp);
                if(s == sum && !Cash.CheckHashMap(Tmp))
                {
                    //System.out.println("Map is " + Tmp);
                    Cash.AddNewHashMap(Tmp);
                    Vector<Integer> res = new Vector<>();
                    for(Map.Entry<Integer, Integer> entry : Tmp.entrySet()){
                        AddValueToVec(res, entry.getKey(), entry.getValue());
                    }
                    Collections.sort(res);
                    Cash.AddComb(res);
                }

                else if(s > sum)
                {
                    break;
                }
                else if(!CurrentBillls.isEmpty()){
                    Check(Cash, CurrentBillls, Tmp);
                }
            }
        }



    }
    public static void CheckAll(cash Cash, int q, final HashSet<Integer> bills)
    {
        //System.out.println("CheckAll");
        //System.out.println(bills);
        //System.out.println(Cash.GetSet());
        //System.out.println(Cash.GetValue());
        HashSet<Integer> set;
        set = (HashSet<Integer>) Cash.GetSet().clone();
        HashSet<Integer> CurrentBills;
        CurrentBills = (HashSet<Integer>) bills.clone();
        for (Integer s: set)
        {
            if(q > 0)
            {
                //System.out.println(s);
                HashSet<Integer> Tmp;
                Tmp = (HashSet<Integer>) CurrentBills.clone();
                Tmp.add(s);
                //System.out.println(Tmp);
                if(!Cash.CheckComb(Tmp)){
                    Cash.AddNewComb(Tmp);
                    //System.out.println("TMP remains " +Tmp);
                    int p = q -1;
                    //System.out.println("P is " + p);
                    CheckAll(Cash, p, Tmp);
                    //System.out.println("Finished one iteration");
                    HashMap<Integer, Integer> map = new HashMap<>();
                    Check(Cash, Tmp, map);
                }
            }
        }
    }


    public static void inputViaScanner() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму");
        long sum = sc.nextLong();
        System.out.println("введите количество купюр");
        int n = sc.nextInt();
        long[] nominals = new long[n];
        System.out.println("Введите купюры через пробел");
        for (int i = 0; i < n; i++) {
            nominals[i] = sc.nextLong();
        }
        System.out.println("Сумма для размена: " + sum);
        System.out.println("Купюры для размена: ");
        System.out.println(Arrays.toString(nominals));

        sc.close();
    }
}