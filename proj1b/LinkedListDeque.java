public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private int size;

    /** Define class IntNode. */
    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n){
            prev = p;
            item = i;
            next = n;
        }
    }
    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel = new IntNode(sentinel, null, sentinel );
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Create a deep copy of other. */
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new IntNode(sentinel, null, sentinel );
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    /** Add an item to the front of the list. */
    @Override
    public void addFirst(T item){
        size += 1;
        IntNode first = new IntNode(sentinel, item, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
    }

    /** Add an item to the last of the list. */
    @Override
    public void addLast(T item){
        size += 1;
        IntNode last = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = last;
        last.prev.next = last;
    }

    /** Return true if the list is empty.
     * use default method
    public boolean isEmpty(){
        if (size == 0)
            return true;
        else
            return false;
    }
    */

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }


    /** Prints the items in the deque from first to last. */
    @Override
    public void printDeque(){
        int i = size;
        while (i != 0){
            System.out.print(sentinel.next.item + " ");
            sentinel.next = sentinel.next.next;
            i -= 1;
        }
        System.out.println(" ");
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst(){
        if (isEmpty())
            return null;
        size -= 1;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev =sentinel;
        if(isEmpty())
            return null;
        return sentinel.next.item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast(){
        if (isEmpty())
            return null;
        size -= 1;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if(isEmpty())
            return null;
        return sentinel.prev.item;
    }

    /** Gets the item at the given index. */
    @Override
    public T get(int index){
        if (index >= size)
            return null;
        IntNode ptr = sentinel;
        for (int i = 1; i < index; i++)
            ptr.next = ptr.next.next;
        return ptr.next.item;
    }

    /** Gets the item at the given index using recursive. */
    public T getRecursive(int index){
        if (index >= size)
            return null;
        else if (index == 0)
            return sentinel.next.item;
        sentinel.next = sentinel.next.next;
        return getRecursive(index -= 1);

    }

    public static void main(String[] args){
        LinkedListDeque<String> i = new LinkedListDeque<>();
        System.out.println(i.size());
        i.addLast("1");
        System.out.println(i.size());
        i.addFirst("2");
        System.out.println(i.get(0));
        System.out.println(i.getRecursive(0));

    }

}
