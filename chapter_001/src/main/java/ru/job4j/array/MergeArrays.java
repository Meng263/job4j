package ru.job4j.array;

/**
 * Слияение 2 осортированных массивов
 */
public class MergeArrays {
    /**
     *
     * @param arrayOne 1 массив
     * @param arrayTwo 2 массив
     * @return массив, который получился в результате слияния 1 и 2
     */
    public int[] merge(int[] arrayOne, int[] arrayTwo){
        int[] result = new int[arrayOne.length + arrayTwo.length];
        int countResult = result.length;
        int countArrayOne = arrayOne.length - 1;
        int countArrayTwo = arrayTwo.length - 1;

       while ((countArrayOne >= 0) && (countArrayTwo >= 0)) { //выполняем слияние массивов сравнивая их элементы
           countResult--;
        if (arrayOne[countArrayOne] > arrayTwo[countArrayTwo]) {
            result[countResult] = arrayOne[countArrayOne];
            countArrayOne--;
        }
        else {
            result[countResult] = arrayTwo[countArrayTwo];
            countArrayTwo--;
        }
       }

     while ((countArrayOne >= 0) || (countArrayTwo >= 0)){ // оставшийся хвост массива записываем в результирующий массив
          countResult--;
           if (countArrayOne >= 0) {
               result[countResult] = arrayOne[countArrayOne];
               countArrayOne--;
           } else {
               result[countResult] = arrayTwo[countArrayTwo];
               countArrayTwo--;
           }
       }
        return result;
    }
}
