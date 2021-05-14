import java.util.Iterator;

public class Util {

    public static <T extends Comparable<T>> SortedGroup<T> reduce(SortedGroup<T> group, T x)
    {
        SortedGroup<T> reduced = new SortedGroup<>();
        for (Iterator<T> i = group.iterator(); i.hasNext();)
        {
            T item = i.next();
            if (item.compareTo(x) > 0)
            {
                reduced.add(item);
            }
        }

        return reduced;
    }
}
