import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Randomization
{
    public static int[] GerarCincoNumeros() {
        int[] numeros = new int[5];
        Set<Integer> numerosUnicos = new HashSet<>();
        Random r = new Random();

        while (numerosUnicos.size() < 5) {
            int numero = r.nextInt(50) + 1;
            numerosUnicos.add(numero);
        }
        //Laco que gera numeros aleatorios de 1 a 50

        int i = 0;
        for (Integer numero : numerosUnicos) {
            numeros[i++] = numero;
        }

        SortingArray.quickSort(numeros, 0, numeros.length - 1);

        return numeros;
    }

    public static int GeraNumeroExtra()
    {
        Random r = new Random();
        int numero = r.nextInt(50) + 1;
        return numero;
    }
}
