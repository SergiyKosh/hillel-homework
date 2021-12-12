public class DataStructure<T> {
    private final T[] array;

    public java.util.Iterator<?> iterator() {
        return new EvenIterator();
    }

    public java.util.Iterator<?> iterator(int ind) {
        return new EvenIterator(ind);
    }

    public DataStructure(T[] array) {
        this.array = array;
    }

    public void printEven() {
        EvenIterator evenIterator = new EvenIterator(2);
        while (evenIterator.hasNext()) {
            System.out.println(evenIterator.next());
        }
    }

    interface ArrayIterator extends java.util.Iterator {

    }

    private class EvenIterator implements ArrayIterator {
        int index;

        public EvenIterator() {
            this.index = 0;
        }

        public EvenIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public T next() {
            T x = array[index];
            index += 2;
            return x;
        }
    }
}
