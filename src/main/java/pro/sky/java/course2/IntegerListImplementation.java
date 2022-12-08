package pro.sky.java.course2;


import java.util.NoSuchElementException;

public class IntegerListImplementation implements IntegerList {

    private Integer[] array;

    public IntegerListImplementation(int length) {
        array = new Integer[length];
    }

    // Добавление элемента.Вернуть добавленный элемент в качестве результата выполнения.
    //Добавить проверку на заполненность в метод add и, если массив заполнен, расширить его.
    @Override
    public Integer add(Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1){
            //массив заполнен - нужно расширить
            grow();
            //ищем первый нулевой элемент в расширенном массиве
            nullIndex = getNullIndex();
        }
        //заменяем первый нулевой элемент
        array[nullIndex]=item;
        return item;
    }

    //Реализовать приватный метод grow,
    // который будет отвечать за расширение массива-хранилища
    // в 1,5 раза в ситуации, когда место закончилось.
    private void grow(){
        Integer [] newArray = new Integer[3*array.length/2];
        //копируем наш массив в новый массив
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        //запоминаем новый массив
        array=newArray;
    }

    private int getNullIndex(){
        int index = -1;
        //ищем первый нулевой элемент
        for (int i = 0; i < array.length; i++) {
            if(array[i]==null){
                index = i;
                break;
            }
        }
        return index;
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    @Override
    public Integer add(int index, Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        //расширяем массив
        Integer [] newArray = new Integer[array.length+1];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = item;
        for (int i = index; i < array.length; i++) {
            newArray[i+1] = array[i];
        }
        array=newArray;
        return item;
    }

    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение,если индекс большe фактического количества элементов или выходит за пределы массива.
    @Override
    public Integer set(int index, Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
        return item;
    }

    // Удаление элемента. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Override
    public Integer remove(Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        int index = indexOf(item);
        if(index==-1){
            throw new NoSuchElementException();
        }
        //может быть несколько item в в массиве, нужно удалить все
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1) {
            //массив заполнен, нулевых элементов нет
            nullIndex = array.length;
        }

        Integer [] newArray = new Integer[array.length];
        int j=0;//индекс нового массива
        for (int i = 0; i < nullIndex; i++) {
            if(! array[i].equals(item)){ //совпадения не записываем в новый массив
                newArray[j]=array[i];
                j++;
            }
        }
        array=newArray;
        return item;
    }

    // Удаление элемента по индексу. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Override
    public Integer removeByIndex(int index) {
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }

        Integer deletedItem = array[index];

        Integer [] newArray = new Integer[array.length];
        int j=0;//индекс нового массива
        for (int i = 0; i < nullIndex; i++) {
            if(i!=index){
                newArray[j]=array[i];
                j++;
            }
        }
        array=newArray;
        return deletedItem;

    }

    // Проверка на существование элемента. Вернуть true/false;
    @Override
    public boolean contains(Integer item) {
        if(item==null){
            throw new NullPointerException();
        }

        //Добавить в реализацию приватный метод бинарного поиска.
        //Учесть, что метод contains уже был реализован в прошлом ДЗ.
        //Его следует переработать, осуществив сортировку и вызвав метод бинарного поиска.

        //переработала метод indxeOf (см ниже)
        return this.indexOf(item)>-1;

    }

    // Поиск элемента.Вернуть индекс элемента или -1 в случае отсутствия.
    //Изменить реализацию сортировки на рекурсивную из последней шпаргалки
    @Override
    public int indexOf(Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        Integer[] tempArray = this.toArray();//временная копия массива
        //сортировка временного массива по самому быстрому методу selection
        //sortSelection(tempArray);

        //Изменить реализацию сортировки на рекурсивную из последней шпаргалки
        quickSort(tempArray, 0, tempArray.length-1);//рекурсивная сортировка

        //поиск по binary search
        int index = binarySearch(tempArray, 0, tempArray.length-1, item);
        return index;
    }

    // Поиск элемента с конца.Вернуть индекс элемента или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(Integer item) {
        if(item==null){
            throw new NullPointerException();
        }
        int index = -1;
        for (int i = array.length-1; i >=0 ; i--) {
            if(array[i] !=null && array[i].equals(item)){
                index=i;
                break;
            }
        }
        return index;
    }

    // Получить элемент по индексу. Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    @Override
    public Integer get(int index) {
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    // Сравнить текущий список с другим. Вернуть true/false или исключение, если передан null.
    @Override
    public boolean equals(IntegerList otherList) {
        if(otherList==null){
            throw new NullPointerException();
        }
        //не совпадает фактическое количество элементов
        if(this.size() != otherList.size()){
            return false;
        }
        boolean result = true;
        for (int i = 0; i < this.size(); i++) {
            if(! this.get(i).equals(otherList.get(i))){
                result=false;
                break;
            }
        }
        return result;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1){
            return array.length;
        }else {
            return nullIndex;
        }
    }

    // Вернуть true,если элементов в списке нет,иначе false.
    @Override
    public boolean isEmpty() {
        return (array.length==0 || array[0]==null);
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i]=null;
        }
    }

    // Создать новый массив из строк в списке и вернуть его.
    @Override
    public Integer[] toArray() {
        Integer [] newArray = new Integer[this.size()];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i]=array[i];
        }
        return newArray;
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }



    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static int binarySearch(Integer arr[], int firstIndex, int lastIndex, Integer value){
        if (lastIndex>=firstIndex){
            int midIndex = firstIndex + (lastIndex - firstIndex)/2;
            if (arr[midIndex].equals(value)){
                return midIndex;
            }
            if (arr[midIndex] > value){
                return binarySearch(arr, firstIndex, midIndex-1, value);
            }else{
                return binarySearch(arr, midIndex+1, lastIndex, value);
            }
        }
        return -1;
    }


}

