import java.util.NoSuchElementException;

class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(3);
        queue.insert(4);
        System.out.println(queue.toString());
        queue.insert(3);
        System.out.println(queue.toString());
        queue.insert(6);
        System.out.println(queue.toString());
        queue.insert(1);
        System.out.println(queue.toString());
        queue.insert(10);
        System.out.println(queue.toString());
        queue.insert(3);
        System.out.println(queue.toString());
        queue.insert(-1);
        System.out.println(queue.toString());
        System.out.println("size:" + queue.size());
        System.out.println("removeMax:" + queue.removeMax());
        System.out.println(queue.toString());
        System.out.println("size:" + queue.size());
        System.out.println("removeMax:" + queue.removeMax());
        System.out.println(queue.toString());
        System.out.println("size:" + queue.size());
        queue.insert(-10);
        System.out.println(queue.toString());
        System.out.println("size:" + queue.size());
        queue.insert(-5);
        System.out.println(queue.toString());
        System.out.println("size:" + queue.size());
    }
}

/**
 * Приоритетная очередь как смог.
 * Максимальный приоритет всегда лежит по индексу 0.
 * Новые приоритеты кладём либо в конец, либо с поиском их места с сдвигом массива.
 * Изымаем макс приоритет всегда из 0 индекса с дальнейшим сдвигом массива к голове.
 * Временная сложность поместить элемент в лучшем случае О(1), в худшем О(n).
 *
 * Можно оптимизаровать исходя из того, что tail это всегда items - 1
 */
class PriorityQueue {
    private int capacity;
    private int[] queue;
    private final int head;
    private int tail;
    private int items;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        head = 0; // Всегда будет ноль, максимальный приоритет всегда будет лежать в этом индексе.
        tail = 0; // А хвост будем двигать по мере заполнения
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

    public void insert(int priority) {
        if (isFull()) {
            capacity *= 2;
            int[] newQ = new int[capacity];
            System.arraycopy(queue, 0, newQ, 0, queue.length);
            queue = newQ;
        }
        if (isEmpty()) {                                        // Если очередь пуста
            queue[head] = priority;                             // Кладем приоритет в ячейку для максимума
            items++;
            return;                                             // И на этом всё.
        }
        if (queue[tail] > priority) {                           // Если помещаемый элемент меньше меньшего приоритета
            queue[++tail] = priority;                           // Просто положим его в конец и сдвинем указатель хвоста
        } else if (queue[head] < priority) {                    // Если помещаемый с большим приритетом чем уже есть
            System.arraycopy(queue, 0, queue, 1, items);  // Сдвинем массив на 1 элемент
            queue[head] = priority;                             // И поместим его на первое место в голову
            tail++;                                             // Не забываем подвинуть указатель хвоста
        } else {
            for (int i = 1; i < items; i++) {                   // Или найдем место для нового элемента
                if (queue[i] <= priority) {                     // Сдвинем нужное к-во элементов в массиве
                    System.arraycopy(queue, i, queue, i + 1,items - i);
                    queue[i] = priority;                        // И поместим его на свое место
                    tail++;                                     // Ах да, не забываем подвинуть указатель хвоста
                    break;
                }
            }
        }
        items++;
    }

    public int removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Нечего выпихнуть");
        }
        int temp = queue[head];                                     // Запомним выкидываемое значение
        System.arraycopy(queue, 1, queue, 0 , items - 1);  // Сдвинем массив на 1 элемент к голове
        items--;                                                    // Поправим значения к-ва элементов
        tail--;                                                     // И указателя на хвост
        return temp;
    }

    public int peekMax() {                                          // Оно может и не надо, но мы же можем.
        return queue[head];
    }

    // Просто для удобства теста, чтоб видеть состояние очереди
    @Override
    public String toString() {
        if (queue.length == 0) return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(queue[i]);
            if (i == queue.length - 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}


