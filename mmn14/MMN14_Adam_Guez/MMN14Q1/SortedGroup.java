import java.util.ArrayList;
import java.util.Iterator;

public class SortedGroup <E extends Comparable> {
    private ArrayList<E> sortedArray;

    // Constructor
    public SortedGroup(){
        this.sortedArray = new ArrayList<E>();
    }

    // Iterator
    public Iterator<E> iterator(){
        return this.sortedArray.iterator();
    }

    // add a new element by finding the right place to out it
    public void add(E data){
        Iterator<E> current = this.iterator();
        int sortedIndex = 0;
        while(current.hasNext() && current.next().compareTo(data) <= 0){
            sortedIndex++;
        }
        sortedArray.add(sortedIndex, data);
    }

    // Remove the received element from the group
    public int remove(E data){
        Iterator<E> current = this.iterator();
        int removed = 0;
        E temp;
        while(current.hasNext()){
            temp = current.next();
            if(temp.equals(data)){
                current.remove();
                removed++;
            }
        }
        return removed;
    }

    // To String
    public String toString(){
        Iterator<E> current;
        String s = "";
        for(current = sortedArray.iterator(); current.hasNext();){
            s += current.next()+" ";
        }
        return s;
    }

    // Getter
    public ArrayList<E> getSortedArray() {
        return sortedArray;
    }
}