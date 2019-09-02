public class ArrayDeque<T> {
    private T[] item;
    private int size;

    public ArrayDeque(){
        item = (T[]) new Object[8];
        size = 0;
    }

    /** Create a deep copy of other. */
    public ArrayDeque(ArrayDeque other){
        T[] a = (T[]) new Object[other.item.length];
        size = other.size;
        System.arraycopy(other.item, 0, a, 0, size);
        item = a;
    }

    /** Resize the array to a certain size on the front or back ends. */
    public void resize(int capacity, String ForB){
        T[] a = (T[]) new Object[size + 1];
        if (ForB == "F")
            System.arraycopy(item, 0, a, 1, size);
        else if (ForB == "B")
            System.arraycopy(item, 0, a, 0, size);
        else
            System.out.println("Please choose the direct F or B.");
        item = a;
    }
    /** Add an item to the front of the list. */
    public void addFirst(T x){
        if(size == item.length){
            resize(size +1, "F");
        }
        item[0] = x;
        size = size + 1;
    }

    /** Add an item to the back of teh list. */
    public void addLast(T x){
        if(size == item.length){
            resize(size +1, "B");
        }
        item[size] = x;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last. */
    public void printDeque(){
        for (int i = 0; i < size; i++)
            System.out.print(item[i] + " ");
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst(){
        if (size == 0)
            return null;
        T returnItem = item[0];
        T[] a = (T[]) new Object[size - 1];
        System.arraycopy(item, 1, a, 0, size - 1);
        item = a;
        size = size - 1;
        if (item.length >=16 && (size / item.length) < 0.25)
            resize(size, "B");
        return returnItem;
    }

    /**  Removes and returns the item at the back of the deque. */
    public T removeLast(){
        if (size == 0)
            return null;
        T returnItem = item[size - 1];
        T[] a = (T[]) new Object[size - 1];
        System.arraycopy(item, 0, a, 0, size - 1);
        item = a;
        size = size - 1;
        if (item.length >=16 && (size / item.length) < 0.25)
            resize(size, "B");
        return returnItem;
    }

    /** Gets the item at the given index. */
    public T get(int index){
        return item[index];
    }
}
