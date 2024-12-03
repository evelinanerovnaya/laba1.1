package main;

import java.util.Arrays;

/**Класс JavaArrayBox реализует интерфейс Box и представляет собой обобщенный контейнер,
 *который использует массив для хранения элементов.
 * @param <E> тип элементов, которые могут храниться в контейнере*/
public class JavaArrayBox<E> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

 /**Конструктор по умолчанию, создает массив с начальной емкостью по умолчанию.*/
    public JavaArrayBox() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

/**Конструктор, создающий массив указанной емкости.
 * @param capacity начальная емкость массива
 * @throws IllegalArgumentException если задана емкость меньше 0 */
    public JavaArrayBox(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Данный индекс некорректен: " + capacity);
        } else {
            this.array = new Object[capacity];
        }
    }

    /**Возвращает текущее количество элементов в массиве.
     * @return текущее количество элементов */
    public int size() {
        return this.size;
    }

    /**Увеличивает размер массива, если он заполнен.
     * @return новый массив, имеющий увеличенный размер */
    private Object[] grow() {
        Object[] newArray = new Object[(int) (array.length * 1.5 + 1)];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        return array;
    }

    /**Добавляет элемент в массив. Если массив заполнен, его размер увеличивается.
     * @param e элемент, который нужно добавить
     * @return true, если элемент был успешно добавлен
     */
    public boolean add(E e) {
        if (size == array.length) {
            grow();
        }
        array[size] = e;
        size++;
        return true;
    }

    /** Добавляет элемент в массив по указанному индексу.
     *
     * @param index Позиция, по которой будет добавлен элемент.
     * @param element Элемент, который нужно добавить.
     * @return true, если элемент был успешно добавлен.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы текущего размера массива.
     */
    public boolean add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Данный индекс некорректен: " + index);
        } else if (size == array.length) {
            grow();
        }
        Object[] newArray = new Object[size - index];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[index + i];
        }
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == index) {
                array[i] = element;
            } else if (i > index) {
                array[i] = newArray[j];
                j++;
            }
        }
        size++;
        return true;
    }


    /** Возвращает элемент массива по указанному индексу.
     *
     * @param index Индекс элемента, который необходимо получить.
     * @return Элемент массива по указанному индексу.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы текущего размера массива.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Данный индекс некорректен: " + index);
        } else {
            return (E) array[index];
        }
    }


    /** Удаляет элемент из массива по указанному индексу.
     *
     * @param index Индекс элемента, который нужно удалить.
     * @return true, если элемент был успешно удален.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы текущего размера массива.
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Данный индекс некорректен: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return true;
    }


    /**Заменяет элемент в массиве по указанному индексу на новый элемент.
     *
     * @param index Индекс элемента, который нужно заменить.
     * @param element Новый элемент, на который нужно заменить существующий элемент.
     * @return Предыдущий элемент массива на заданной позиции.
     * @throws IndexOutOfBoundsException Если индекс выходит за пределы текущего размера массива.
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Данный индекс некорректен: " + index);
        }
        array[index] = element;
        return (E) array[index];
    }

    /** Возвращает массив, содержащий все элементы текущего массива.
     *
     * @return Массив, содержащий все элементы текущего массива.
     */
    public E[] toArray() {
        E[] resultArray = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            resultArray[i] = (E) array[i];
        }
        return resultArray;
    }

    /**
     * Возвращает строковое представление текущего массива.
     *
     * @return Строковое представление текущего массива.
     */
    public String toString(){
        return Arrays.toString(toArray());
    }
}

