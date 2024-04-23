public class SortingArray {
    public static void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(array, inicio, fim);
            quickSort(array, inicio, p - 1);
            quickSort(array, p + 1, fim);
        }
    }

    private static int particionar(int[] array, int inicio, int fim) {
        int pivot = array[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;
        return i + 1;
    }
}
