import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.IntegerListImplementation;

import java.util.NoSuchElementException;

public class IntegerListTest {
    IntegerListImplementation list;

    @BeforeEach
    public void init(){
        list = new IntegerListImplementation(5);
        list.add(3);
        list.add(5);
        list.add(3);
        list.add(7);
    }

    // Добавление элемента.Вернуть добавленный элемент в качестве результата выполнения
    @Test
    public void addTest1(){
        Integer result = list.add(4);
        Integer[] expectedArray = {3, 5, 3, 7, 4};
        Assertions.assertEquals(4, result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void addTest2(){
        //тест расширения массива
        list.add(4);
        list.add(2);
        list.add(8);
        list.add(6);
        Integer[] expectedArray = {3, 5, 3, 7, 4, 2, 8, 6};
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void addTest3(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.add(null)
        );
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    @Test
    public void addTest4(){
        Integer result = list.add(1, 4);
        Integer[] expectedArray = {3, 4, 5, 3, 7};
        Assertions.assertEquals(4, result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void addTest5(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.add(1, null)
        );
    }
    @Test
    public void addTest6(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.add(4, 4)
        );
    }
    @Test
    public void addTest7(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.add(5, 4)
        );
    }


    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение,если индекс большe фактического количества элементов или выходит за пределы массива.
    @Test
    public void setTest1(){
        Integer result = list.set(1, 4);
        Integer[] expectedArray = {3, 4, 3, 7};
        Assertions.assertEquals(4, result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void setTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.set(1, null)
        );
    }
    @Test
    public void setTest3(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.set(4, 4)
        );
    }
    @Test
    public void setTest4(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.set(5, 4)
        );
    }


    // Удаление элемента. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Test
    public void removeTest1(){
        Integer result = list.remove(5);
        Integer[] expectedArray = {3, 3, 7};
        Assertions.assertEquals(5, result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void removeTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.remove(null)
        );
    }
    @Test
    public void removeTest3(){
        Assertions.assertThrows(
                NoSuchElementException.class,
                ()->list.remove(4)
        );
    }

    // Удаление элемента по индексу. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Test
    public void removeTest4(){
        Integer result = list.removeByIndex(1);
        Integer[] expectedArray = {3, 3, 7};
        Assertions.assertEquals(5, result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void removeTest5(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.removeByIndex(4)
        );
    }
    @Test
    public void setTest6(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.removeByIndex(5)
        );
    }


    // Проверка на существование элемента. Вернуть true/false;
    @Test
    public void containsTest1(){
        Assertions.assertTrue(list.contains(7));
        Assertions.assertFalse(list.contains(4));
    }
    @Test
    public void containsTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.contains(null)
        );
    }

    // Поиск элемента.Вернуть индекс элемента или -1 в случае отсутствия.
    @Test
    public void indexOfTest1(){
        Assertions.assertEquals(3,list.indexOf(7));
        Assertions.assertEquals(-1, list.indexOf(4));
    }
    @Test
    public void indexOfTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.indexOf(null)
        );
    }


    // Поиск элемента с конца.Вернуть индекс элемента или -1 в случае отсутствия.
    @Test
    public void lastIndexOfTest1(){
        Assertions.assertEquals(2,list.lastIndexOf(3));
        Assertions.assertEquals(-1, list.lastIndexOf(4));
    }
    @Test
    public void lastIndexOfTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.lastIndexOf(null)
        );
    }


    // Получить элемент по индексу. Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    @Test
    public void getTest1(){
        Assertions.assertEquals(3, list.get(2));
    }
    @Test
    public void getTest2(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.get(4)
        );
    }
    @Test
    public void getTest3(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.get(5)
        );
    }


    // Сравнить текущий список с другим. Вернуть true/false или исключение, если передан null.
    @Test
    public void equalsTest1() {
        IntegerListImplementation otherList = new IntegerListImplementation(4);
        otherList.add(3);
        otherList.add(5);
        otherList.add(3);
        otherList.add(7);
        Assertions.assertTrue(list.equals(otherList));
    }
    @Test
    public void equalsTest2() {
        IntegerListImplementation otherList = new IntegerListImplementation(7);
        otherList.add(3);
        otherList.add(5);
        otherList.add(3);
        otherList.add(7);
        otherList.add(4);
        Assertions.assertFalse(list.equals(otherList));
    }
    @Test
    public void equalsTest3() {
        IntegerListImplementation otherList = new IntegerListImplementation(5);
        otherList.add(3);
        otherList.add(8);
        otherList.add(3);
        otherList.add(7);
        Assertions.assertFalse(list.equals(otherList));
    }
    @Test
    public void  equalsTest4(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.equals(null)
        );
    }

    // Вернуть фактическое количество элементов.
    @Test
    public void sizeTest() {
        Assertions.assertEquals(4, list.size());
    }

    // Вернуть true,если элементов в списке нет,иначе false.
    @Test
    public void isEmptyTest() {
        IntegerListImplementation list1 = new IntegerListImplementation(0);
        IntegerListImplementation list2 = new IntegerListImplementation(5);
        IntegerListImplementation list3 = new IntegerListImplementation(3);
        list3.add(4);
        Assertions.assertTrue(list1.isEmpty());
        Assertions.assertTrue(list2.isEmpty());
        Assertions.assertFalse(list3.isEmpty());
    }


    // Удалить все элементы из списка.
    @Test
    public void clearTest() {
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    // Создать новый массив из строк в списке и вернуть его.
    @Test
    public void toArrayTest() {
        Integer[] expectedArray = {3, 5, 3, 7};
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

}
