public class ArrayDeque<T> {
    // the array on which the Class is based
    private T[] items;
    // the integer value that keeps track of the array size
    private int size;
    // the index position of the next added item from the beginning
    private int nextFirst;
    // the index position of the next added item from the end
    private int nextLast;
    /* the amount of memory that the program uses at any given time
    * is proportional to the number of items. The usage factor is
    * always at least 25% */
    private static final double USAGE_FACTOR = 0.25;

    /* the value by which the original size of the array is multiplied
    * or divided in getting the size value for the new resized array */
    private static final int EXPANSION_FACTOR = 2;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.nextFirst = 3;
        this.nextLast = 4;
        this.size = 0;
    }

    /* resizing the array to the given capacity */
    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int entryPoint = capacity / 2 - size / 2;
        for (int i = 0; i < size; i++) {
            newArray[entryPoint + i] = items[(nextFirst + 1 + i) % size];
        }
        items = newArray;
        nextFirst = entryPoint - 1;
        nextLast = nextFirst + size + 1;
    }

    /* adding an item as the first item of the array */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * EXPANSION_FACTOR);
        }

        size++;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    /* adding an item as the last item of the array */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * EXPANSION_FACTOR);
        }

        size++;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    /* check if the array is empty */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* return the size of the array */
    public int size() {
        return size;
    }

    /* print out the entire array by elements */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(nextFirst + 1 + i) % items.length] + " ");
        }
    }

    /* remove the first item from the array and return the removed item */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        int firstItemIndex = (nextFirst + 1) % items.length;
        T removedItem = items[firstItemIndex];
        items[firstItemIndex] = null;
        nextFirst = firstItemIndex;
        if (size > 16 && size < items.length * USAGE_FACTOR) {
            resize(items.length / EXPANSION_FACTOR);
        }
        return removedItem;
    }

    /* remove the last item from the array and return the removed item */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        size--;
        int lastItemIndex = (nextLast - 1 + items.length) % items.length;
        T removedItem = items[lastItemIndex];
        items[lastItemIndex] = null;
        nextLast = lastItemIndex;
        if (size > 16 && size < items.length * USAGE_FACTOR) {
            resize(items.length / EXPANSION_FACTOR);
        }
        return removedItem;
    }

    /* return the item at (int index) index position of the array */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        int i = 0;
        while (i < 14) {
            arr.addLast(i);
            i++;
        }
        arr.printDeque();
    }

}
