import java.util.ArrayList;
import java.util.Iterator;

public class SortedGroup <T extends Comparable<T>>{
    private ArrayList<T> data;

    public SortedGroup()
    {
        data = new ArrayList<>();
    }

    // Finds the index in the ArrayList of the given item, if it would have been inserted into the ArrayList
    public int find(T item)
    {
        int low = 0;
        int high = data.size();
        int mid;

        while (low < high)
        {
            mid = (low + high) / 2;
            if (data.get(mid).compareTo(item) < 0)
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }
        return low;
    }

    public void add(T item)
    {
        int i = this.find(item);
        this.data.add(i, item);
    }

    public int remove(T item)
    {
        int i = this.find(item);
        int count = 0;
        for (; i < this.data.size(); count++) {
            if (this.data.get(i).compareTo(item) != 0)
            {
                break;
            }
            this.data.remove(i);
        }
        return count;
    }

    public Iterator<T> iterator() {
        return new SortedGroupIterator();
    }

    public class SortedGroupIterator implements Iterator<T> {

        private int index;

        public SortedGroupIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {

            return index < data.size();
        }

        @Override
        public T next() {
            return data.get(index++);
        }
    }
}