import java.util.Iterator;

// Generic method, add only the fits values and return new group
public class Reduce {
    public static <E extends Comparable> SortedGroup<E> reduce(SortedGroup<E> sGroup, E x){
        SortedGroup<E> sGroupNew = new SortedGroup<E>();
        for(int i = 0; i < sGroup.getSortedArray().size(); i++){
            if(sGroup.getSortedArray().get(i).compareTo(x) > 0){
                sGroupNew.add(sGroup.getSortedArray().get(i));
            }
        }
        return sGroupNew;
    }

}
