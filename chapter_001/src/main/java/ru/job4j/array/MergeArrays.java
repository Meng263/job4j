package ru.job4j.array;

/**
 * Слияение 2 осортированных массивов
 */
public class MergeArrays {
    /**
     * @param arrayOne 1 массив
     * @param arrayTwo 2 массив
     * @return массив, который получился в результате слияния 1 и 2
     */
    public int[] merge(int[] arrayOne, int[] arrayTwo) {
        int[] result = new int[arrayOne.length + arrayTwo.length];
        int res = result.length;
        int one = arrayOne.length - 1;
        int two = arrayTwo.length - 1;
        while (res-- > 0) {
            if (one == -1) {
                result[res] = arrayTwo[two--];
            } else if ((two == -1) || (arrayOne[one] > arrayTwo[two])) {
                result[res] = arrayOne[one--];
            } else {
                result[res] = arrayTwo[two--];
            }
        }
        return result;
    }
}
