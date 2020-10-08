package cash_count.Cash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class cash {
private long cash;
private HashSet<Integer> bills;
private HashSet<Vector<Integer>> comb = new HashSet<>();
private HashSet<HashSet<Integer>> added_comb = new HashSet<>();
private HashSet<HashMap<Integer,Integer>> added_HashMap = new HashSet<>();
public cash(Long c, final HashSet<Integer> bill){
    this.cash = c;
    this.bills= bill;
}
public void AddComb(final Vector<Integer> new_comb){
    comb.add(new_comb);
}
public final void PrintComb() {
    System.out.println("Количество комбинаций: " + comb.size());
    for(Vector<Integer> vec: comb)
    {
        System.out.println(vec);
    }
}
public void AddNewComb(final HashSet<Integer> comb){
    added_comb.add(comb);
}
public HashSet<Integer> GetSet(){
    return bills;
}
public long GetValue() {
    return cash;
}
public boolean CheckComb(final HashSet<Integer> comb){
    if(added_comb.contains(comb) ){
        return true;
    }
    else
        return false;
}
public void AddNewHashMap(final HashMap<Integer, Integer> HashMap1)
{
    added_HashMap.add(HashMap1);
}
public boolean CheckHashMap(HashMap<Integer, Integer> HashMap1)
{
    if(added_HashMap.contains(HashMap1))
        return true;
    else
        return false;
}

}
