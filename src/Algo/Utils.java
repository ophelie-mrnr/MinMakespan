package Algo;

public class Utils {
    /**
     * Finds the index of the min item.
     * This function doesn't care about the values but for comparing, what we're
     * interested by is the indexes.
     *
     * @param   int[]   a   The array in which we want to find the index of the
     * minimum item.
     *
     * @return  int     The index.
     *
     * @throws IllegalArgumentException If the parameter is empty.
     */
    public static int indexMin(int[] a)
    {
        if (a.length == 0) {
            throw new IllegalArgumentException("The array shouldn't be empty.");
        } else if (a.length == 1) {
            return a[0];
        }
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            //Current item is lower that the current 'lowest'
            if (a[i] < a[index]) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Finds the maximum element of an array.
     *
     * @param   int[] a The array in which we search the maximum.
     *
     * @return  int The maximum element.
     *
     * @throws  IllegalArgumentException If the array is empty.
     */
    public static int max(int[] a)
    {
        if (a.length == 0) {
            throw new IllegalArgumentException("The array shouldn't be empty.");
        } else if (a.length == 1) {
            return a[0];
        }
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
