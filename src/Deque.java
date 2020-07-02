import java.util.NoSuchElementException;

class DequeTest {
    public static void main(String[] args) {
        Deque deque = new Deque(20);
        deque.insertHead(1);
        System.out.println(deque.toString());
        deque.insertHead(2);
        System.out.println(deque.toString());
        deque.insertTail(5);
        System.out.println(deque.toString());
        deque.insertTail(10);
        System.out.println(deque.toString());
        deque.insertHead(20);
        System.out.println(deque.toString());
        System.out.println(deque.peekHead());
        System.out.println(deque.peekTail());
        System.out.println(deque.removeHead());
        System.out.println(deque.removeTail());
        System.out.println(deque.toString());
        System.out.println(deque.size());
        deque.insertHead(-20);
        deque.insertTail(-10);
        System.out.println(deque.toString());
        System.out.println(deque.size());
    }
}

/**
 * Класс двусторонней очереди.
 * Сделал сам. Не осилил на данном этапе увеличение размера.
 * Очередь на основе массива фиксированной (пока) длинны.
 * Заполнение очереди начинаем с середины.
 */
class Deque {

    private int capacity;
    private int[] deque;
    private int head;
    private int tail;
    private int items;

    public Deque(int capacity) {
        this.capacity = capacity;
        deque = new int[capacity];
        head = -1;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == capacity;
    }

    public int size() {
        return items;
    }

    public void insertHead(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Голова упёрлась в край");
        }
        if (isEmpty()) {                // Если очередь пустая
            int index = capacity / 2;   // Делим размер пополам
            deque[index] = value;       // Вставим туда значение
            head = index;               // Присвоим этот индекс голове и хвосту
            tail = index;
        } else {
            deque[--head] = value;      // Если очередь не пустая - сдвинем указатель головы и поместим value
        }
        items++;
    }

    public void insertTail(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Хвост упёрся в край");
        }
        if (isEmpty()) {                // Если очередь пустая
            int index = capacity / 2;   // Делим размер пополам
            deque[index] = value;       // Вставим туда значение
            head = index;               // Присвоим этот индекс голове и хвосту
            tail = index;
        } else {
            deque[++tail] = value;      // Если очередь не пустая - сдвинем указатель хвоста и поместим value
        }
        items++;
    }


    public int peekHead() {             // Глянем что в голове
        return deque[head];
    }

    public int peekTail() {             // Глянем что в хвосте
        return deque[tail];
    }

    public int removeHead() {
        if (isEmpty())
            throw new NoSuchElementException("Очередь пуста");
        if (head + 1 == capacity) {     // Если голова упирается в край массива очереди,
            int tmp = head;             // Запомним индекс головы
            head = capacity / 2;        // Присвоим голове и хвосту начальные индексы
            tail = capacity / 2;
            items--;
            return deque[tmp];          // Вернём значение головы
        } else {
            items--;
            return deque[head++];
        }
    }

    public int removeTail() {
        if (isEmpty())
            throw new NoSuchElementException("Очередь пуста");
        if (tail - 1 == 0) {            // Если хвост упирается в начало массива очереди,
            int tmp = tail;             // Запомним индекс хвоста
            head = capacity / 2;        // Присвоим голове и хвосту начальные индексы
            tail = capacity / 2;
            items--;
            return deque[tmp];          // Вернём значение хвоста
        } else {
            items--;
            return deque[tail--];
        }
    }

    // Просто для удобства теста, чтоб видеть состояние очереди
    @Override
    public String toString() {
        if (deque.length == 0) return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(deque[i]);
            if (i == deque.length - 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}


