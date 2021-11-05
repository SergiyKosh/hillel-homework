package object_queue;

public class ObjectQueue {
    private ObjectBox head = null;
    private ObjectBox tail = null;
    private int size = 0;

    public void push(Object obj) {
        ObjectBox ob = new ObjectBox();
        ob.setObject(obj);
        if (head == null) {
            head = ob;
        } else {
            tail.setNext(ob);
        }
        tail = ob;
        size++;
    }

    public Object pull() {
        if (size == 0) {
            return null;
        }
        Object obj = head.getObject();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return obj;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        }
        ObjectBox current = head;
        int pos = 0;
        while (pos < index) {
            current = current.getNext();
            pos++;
        }
        Object obj = current.getObject();
        return obj;
    }

    private class ObjectBox {
        private Object object;
        private ObjectBox next;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public ObjectBox getNext() {
            return next;
        }

        public void setNext(ObjectBox next) {
            this.next = next;
        }
    }
}
