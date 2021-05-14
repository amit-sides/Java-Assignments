import java.util.ArrayList;
import java.util.Iterator;

public class SortedGroup <T extends Comparable<T>>{
    private ArrayList<T> data;

    public SortedGroup()
    {
        data = new ArrayList<>();
    }

    // Finds the index in the ArrayList of the given item, if it would have been inserted into the ArrayList
    // Or, in simple words: Find the index that the given item should be inserted at in-order to keep the ArrayList sorted
    private int find(T item)
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
        // Find the index of the first item that is higher / equal to the given item.
        int i = this.find(item);

        // Remove items starting at index i until we encounter a higher item & count removed items
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