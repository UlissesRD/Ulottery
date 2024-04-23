import java.util.ArrayList;

public class SortingArrayList {
    public static ArrayList<Integer> NumerosOrganizados(ArrayList<Integer> numeros) {
        quickSortArrayList(numeros, 0, numeros.size() - 1);
        return numeros;
    }

    public static void quickSortArrayList(ArrayList<Integer> array, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionarArrayList(array, inicio, fim);
            quickSortArrayList(array, inicio, p - 1);
            quickSortArrayList(array, p + 1, fim);
        }
    }

    private static int particionarArrayList(ArrayList<Integer> array, int inicio, int fim) {
        int pivot = array.get(fim);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (array.get(j) < pivot) {
                i++;
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }
        int temp = array.get(i + 1);
        array.set(i + 1, array.get(fim));
        array.set(fim, temp);
        return i + 1;
    }
}
