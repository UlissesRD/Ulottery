import java.util.ArrayList;
import java.util.List;

public class Registration {
    public String nome;
    public String sobrenome;
    public String cpf;
    private List<Integer> numeros;
    private int numAposta;

    public Registration(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.numeros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarNumeros(int numero) {
        numeros.add(numero);
    }

    public String getNumeros() {
        StringBuilder resposta = new StringBuilder();
        //System.out.println("Números do cadastro de " + nome + ":");
        for (int numero : numeros) {
            //System.out.println(numero);
            resposta.append(numero).append("|");
        }
        return resposta.toString();
    }

    public int getNumAposta() {
        return numAposta;
    }

    public void setNumAposta(int numAposta) {
        this.numAposta = numAposta;
    }

    public void cadastrandoArray(Registration cadastro, int[] arrInt, ArrayList<Registration> arrCadastros)
    {
        for (int numero : arrInt) {
            cadastro.adicionarNumeros(numero);
        }

        if (arrCadastros.isEmpty())
        {
            cadastro.setNumAposta(1000);
        }
        else
        {
            int ultimoNumero = arrCadastros.get(arrCadastros.size() - 1).getNumAposta();
            cadastro.setNumAposta(ultimoNumero + 1);
        }

        arrCadastros.add(cadastro);
    }
    public void cadastrandoArrayList(Registration cadastro, ArrayList<Integer> arrInt, ArrayList<Registration> arrCadastros)
    {
        for (int numero : arrInt) {
            cadastro.adicionarNumeros(numero);
        }

        if (arrCadastros.isEmpty())
        {
            cadastro.setNumAposta(1000);
        }
        else
        {
            int ultimoNumero = arrCadastros.get(arrCadastros.size() - 1).getNumAposta();
            cadastro.setNumAposta(ultimoNumero + 1);
        }

        arrCadastros.add(cadastro);
    }

    public int[] extrairNumeros(String numerosString) {
        String[] numerosStringArray = numerosString.split("\\|");
        int[] numerosInt = new int[numerosStringArray.length];

        for (int i = 0; i < numerosStringArray.length; i++) {
            // Remover espaços em branco e converter para int
            numerosInt[i] = Integer.parseInt(numerosStringArray[i].trim());
        }

        return numerosInt;
    }
}
