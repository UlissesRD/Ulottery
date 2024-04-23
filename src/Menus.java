import java.text.DecimalFormat;
import java.util.*;

public class Menus {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Registration> cadastros = new ArrayList<>();

    // Definindo codigos de escape ANSI para cores
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public int rodadasNecessarias = 0;

    public void Inicio()
    {
        System.out.println("Lets begin?");
        System.out.println("1 | For sure!");
        System.out.println("0 | Leave");
        System.out.println();
        System.out.print("Chose an option: ");
        System.out.println();

        try {
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Cadastramento();
                    break;
                case 0:
                    System.out.println("Leaving Ulottery, see you soon!");
                    break;
                default:
                    System.out.println(RED + "Invalid option. Choose another one." + RESET);
                    Inicio();
            }
        } catch (InputMismatchException e)
        {
            System.out.println(RED + "Invalid option. Choose another one" + RESET);
            scanner.nextLine();
            Inicio();
        }
    }

    public void Cadastramento()
    {
        System.out.println("Enter your full name: ");
        String nomeCompleto = scanner.next() + scanner.nextLine();
        //Usando next() e nextLine() juntos para capturar a linha completa

        //Dividindo o nome completo em nome e sobrenome
        String[] partesNome = nomeCompleto.split(" ", 2);
        String nome = partesNome[0];
        String sobrenome = partesNome.length > 1 ? partesNome[1] : "";
        //Caso a pessoa coloque apenas o nome, o sistema aceita e preenche o sobrenome com ""

        System.out.println("Enter your ID:");

        while (true) {
            String cpfString = scanner.nextLine().trim();

            if (cpfString.length() == 11) {
                try {
                    long cpf = Long.parseLong(cpfString);
                    cpfString = String.valueOf(cpf);
                    EscolhasDeNumero(nome, sobrenome, cpfString);
                    break; // Sai do loop enquanto se o CPF for valido
                } catch (NumberFormatException e) {
                    System.out.println("* Please, enter a " + RED + "valid ID (only numbers)." + RESET );
                }
            } else {
                System.out.println("* The " + RED + "ID" + RESET + " must have" + RED + " 11 digits (only numbers)" + RESET + ".");
            }
        }
    }

    public int[] EscolhasDeNumero(String nome, String sobrenome, String cpf)
    {
        int[] numerosAleatorios = new int[0];
        ArrayList<Integer> numerosSelecionados = new ArrayList<>();
        ArrayList<Integer> numerosOrganizados = new ArrayList<>();

        System.out.println("---------- Number Selection ----------");
        System.out.println();
        System.out.println("Menu:");
        System.out.println("1 | Quick Pick (5 random numbers)");
        System.out.println("2 | Manual bet");
        System.out.println("0 | Leave");
        System.out.println();
        System.out.print("Choose an option: ");
        System.out.println();

        try{
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("You chose QUICK PICK!");
                System.out.println("Those are your numbers:");
                numerosAleatorios = Randomization.GerarCincoNumeros();
                System.out.println(numerosAleatorios[0] + " | " + numerosAleatorios[1] + " | " + numerosAleatorios[2] +
                        " | " + numerosAleatorios[3] + " | " + numerosAleatorios[4]);
                System.out.println("Good luck!");
                System.out.println();

                Registration cadastro = new Registration(nome, sobrenome, cpf);

                cadastro.cadastrandoArray(cadastro, numerosAleatorios, cadastros);

                ProxPasso();

                break;
            case 2:
                int numEscolhido = 0;
                System.out.println("You chose MANUAL BET.");
                System.out.println();
                System.out.println("-----> YOUR NUMBER MUST BE BETWEEN 1 AND 50 <-----");
                System.out.println();
                for (int i = 0; i < 5; ++i)
                {
                    System.out.println("Enter your " + (i + 1) + "º number:");
                    boolean valido = false;

                    while (!valido) {
                        try{
                            numEscolhido = scanner.nextInt();
                            if (numEscolhido >= 1 && numEscolhido <= 50) {
                                if (!numerosSelecionados.contains(numEscolhido)) {
                                    valido = true;
                                } else {
                                    System.out.println(RED + "This number have already been selected." + RESET + "Por favor, escolha outro.");
                                }
                            } else {
                                System.out.println(RED + "You number must follow this rule:");
                                System.out.println("   - Must be between 1 and 50" + RESET);
                                System.out.println("Enter a new number that follow the rule:");
                            }
                        }catch (InputMismatchException e){
                            System.out.println(RED + "You put a invalid number" + RESET + ", try again");
                            scanner.next();
                            // Limpa o buffer do scanner
                        }
                    }
                    numerosSelecionados.add(numEscolhido);
                    System.out.println("------------------------------");
                }
                System.out.println();
                System.out.println("Those are your numbers:");
                numerosOrganizados = SortingArrayList.NumerosOrganizados(numerosSelecionados);
                System.out.println(numerosOrganizados.get(0) + " | " + numerosOrganizados.get(1) +
                        " | " + numerosOrganizados.get(2) + " | " + numerosOrganizados.get(3) + " | " + numerosOrganizados.get(4));
                System.out.println("Good luck!");
                System.out.println();

                Registration cadastroManual = new Registration(nome, sobrenome, cpf);

                cadastroManual.cadastrandoArrayList(cadastroManual, numerosOrganizados, cadastros);

                ProxPasso();

                break;
            case 0:
                System.out.println("Leaving the program, see you soon!");
                break;
            default:
                System.out.println(RED + "Invalid option. Choose another one." + RESET);
                EscolhasDeNumero(nome, sobrenome, cpf);
            }
        } catch (InputMismatchException e)
        {
            System.out.println(RED + "Invalid option. Choose another one." + RESET);
            scanner.nextLine();
            EscolhasDeNumero(nome, sobrenome, cpf);
        }
        return numerosAleatorios.length > 0 ? numerosAleatorios : null;
    }

    public void Sorteio() {
        System.out.println("-------------------------");
        System.out.println("The winning numbers are:");
        int[] numerosSorteados = Randomization.GerarCincoNumeros();
        //int[] numerosSorteados = {1,2,3,4,5};
        System.out.println(numerosSorteados[0] + "|" + numerosSorteados[1] + "|" + numerosSorteados[2] + "|" + numerosSorteados[3] + "|" + numerosSorteados[4]);
        Verificacao(numerosSorteados, cadastros);
    }

    public void ListaDeApostas()
    {
        System.out.println("---------- Bet list ----------");
        System.out.println();
        ExibirApostas();
    }

    public void ProxPasso()
    {
        System.out.println("Whats the next step?");
        System.out.println("1 | Make another bet");
        System.out.println("2 | Show bet list");
        System.out.println("3 | Draw the winning numbers");
        System.out.println("0 | Leave");
        System.out.println();
        System.out.print("Choose an option: ");
        System.out.println();

        try{
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Cadastramento();
                    break;
                case 2:
                    ListaDeApostas();
                    ProxPasso();
                    break;
                case 3:
                    Sorteio();
                    break;
                case 0:
                    System.out.println("Leaving, see you soon!");
                    break;
                default:
                    System.out.println(RED + "Invalid option. Try Again." + RESET);
                    ProxPasso();
            }
        } catch (InputMismatchException e)
        {
            System.out.println(RED + "Invalid option. Try Again." + RESET);
            scanner.nextLine();
            ProxPasso();
        }
    }

    public void ExibirApostas() {
        for (Registration cadastro : cadastros) {
            //String cpfFormatado = formatarCPF(cadastro.getCpf());
            System.out.println("Bet ID: " + cadastro.getNumAposta() + "\n" +
                    "Name: " + cadastro.getNome() + " " + cadastro.getSobrenome() + "\n" +
                    "ID: " + cadastro.getCpf() + "\n" +
                    "Numbers: " + cadastro.getNumeros() + "\n");
            System.out.println("-----------------------------------------------");
            System.out.println();
        }
    }

    public void ExibirVencedores(ArrayList<Registration> vencedores)
    {
        Collections.sort(vencedores, (cadastro1, cadastro2) -> {
            int compareNomes = cadastro1.getNome().compareTo(cadastro2.getNome());
            if (compareNomes == 0) { // Se os nomes forem iguais, compare os sobrenomes
                return cadastro1.getSobrenome().compareTo(cadastro2.getSobrenome());
            } else {
                return compareNomes;
            }
        });
        System.out.println("------------------------------------------");
        System.out.println("Alphabetic order of the winning bets:");
        System.out.println();

        for (Registration cadastro : vencedores)
        {
            //String cpfFormatado = formatarCPF(cadastro.getCpf());
            System.out.println("Name: " + cadastro.getNome() + " " + cadastro.getSobrenome() + "\n" +
                    "ID: " + cadastro.getCpf() + "\n" +
                    "Bet ID: " + cadastro.getNumAposta());
            System.out.println();
            System.out.println("-----------------------------------------------");
            System.out.println();
        }
        //String.format("%011d", Long.parseLong(cadastro.getCpf()))
    }

    private String formatarCPF(String cpf) {
        // Adiciona zeros a esquerda para garantir que o CPF tenha 11 digitos
        cpf = String.format("%011d", Long.parseLong(cpf));

        String primeiroTrio = cpf.substring(0, 3);
        String segundoTrio = cpf.substring(3, 6);
        String terceiroTrio = cpf.substring(6, 9);
        String dupla = cpf.substring(9);

        // Formata o CPF no formato desejado
        return String.format("%s.%s.%s-%s", primeiroTrio, segundoTrio, terceiroTrio, dupla);
    }

    public void Verificacao(int[] numerosSorteados, ArrayList<Registration> cadastros) {
        ArrayList<Registration> apostasNaoPremiadas = new ArrayList<>(cadastros);
        ArrayList<Registration> CadastrosVencedores = new ArrayList<>();

        for (Registration aux : cadastros) {
            int acertos = 0;
            int[] numerosApostados = aux.extrairNumeros(aux.getNumeros());

            for (int numAposta : numerosApostados) {
                for (int numSorteado : numerosSorteados) {
                    if (numAposta == numSorteado) {
                        acertos++;
                        break;
                    }
                }
            }
            if (acertos == 5) {
                CadastrosVencedores.add(aux);
                apostasNaoPremiadas.remove(aux);
            }
        }

        int apostasVencedoras = CadastrosVencedores.size();

        if (apostasVencedoras == 0) {
            System.out.println();
            System.out.println("We had no winners, let's proceed to the extra 25 rounds!");
            while (rodadasNecessarias < 25 && CadastrosVencedores.isEmpty())
            {
                int numeroExtra = Randomization.GeraNumeroExtra();

                // Verifica se o numero extra ja foi sorteado anteriormente
                if (Arrays.stream(numerosSorteados).anyMatch(num -> num == numeroExtra)) {
                    continue; // Reinicia o loop para sortear outro numero extra
                }
                System.out.println();
                System.out.println("Round #" + (rodadasNecessarias + 1));
                System.out.println("Extra number drawn: " + numeroExtra);

                // Adiciona o numero extra aos numeros sorteados
                numerosSorteados = adicionarNumeroSorteado(numerosSorteados, numeroExtra);

                // Pausa a execução por 3 segundos
                try {
                    Thread.sleep(1000); // 1000 milissegundos = 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Registration aux : cadastros) {
                    int acertos = 0;
                    int[] numerosApostados = aux.extrairNumeros(aux.getNumeros());

                    for (int numAposta : numerosApostados) {
                        for (int numSorteado : numerosSorteados) {
                            if (numAposta == numSorteado) {
                                acertos++;
                                break;
                            }
                        }
                    }

                    if (acertos == 5) {
                        CadastrosVencedores.add(aux);
                        apostasNaoPremiadas.remove(aux);
                    }
                }

                apostasVencedoras = CadastrosVencedores.size();
                rodadasNecessarias++;
            }

            if (apostasVencedoras == 0) {
                System.out.println();
                System.out.println("No winning bet found after 25 additional rounds.");
                //Ordenando os numeros sorteados
                SortingArray.quickSort(numerosSorteados, 0, numerosSorteados.length - 1);
                System.out.println("The lucky numbers were: " + Arrays.toString(numerosSorteados));
                System.out.println();
                ListaDeNumerosApostados(cadastros);
            }
            else
            {
                SortingArray.quickSort(numerosSorteados, 0, numerosSorteados.length-1);
                System.out.println();
                System.out.println("Lucky numbers: " + Arrays.toString(numerosSorteados));
                System.out.println("Additional rounds needed: " + rodadasNecessarias);
                System.out.println();
                ExibirVencedores(CadastrosVencedores);
                ListaDeNumerosApostados(cadastros);
                Premiacao(apostasVencedoras);
            }


        } else {
            System.out.println();
            System.out.println("Lucky numbers: " + Arrays.toString(numerosSorteados));
            System.out.println("Additional rounds needed: 0");
            System.out.println();
            ExibirVencedores(CadastrosVencedores);
            ListaDeNumerosApostados(cadastros);
            Premiacao(apostasVencedoras);
        }
    }

    public void ListaDeNumerosApostados(ArrayList<Registration> cadastros) {
        Map<Integer, Integer> numeroApostadoQuantidade = new HashMap<>();

        // Conta a quantidade de vezes que cada numero eh apostado
        for (Registration cad : cadastros) {
            for (int numero : cad.extrairNumeros(cad.getNumeros())) {
                numeroApostadoQuantidade.put(numero, numeroApostadoQuantidade.getOrDefault(numero, 0) + 1);
            }
        }

        // Ordena a lista de numeros apostados pela quantidade de apostas guardando-a em um mapa com duas chaves int
        Map<Integer, Integer> numeroApostadoQuantidadeOrdenado = new TreeMap<>((a, b) -> {
            int compare = numeroApostadoQuantidade.get(b).compareTo(numeroApostadoQuantidade.get(a));
            return compare != 0 ? compare : a.compareTo(b);
            //e o resultado da comparação for diferente de zero, então esse resultado eh retornado.
            // Caso contrário, se a e b forem iguais, a comparação padrao de a.compareTo(b) eh usada para determinar a ordem.
        });
        numeroApostadoQuantidadeOrdenado.putAll(numeroApostadoQuantidade);

        System.out.println("Number bet         Number of bets");
        System.out.println("------------       --------------");
        for (Map.Entry<Integer, Integer> entry : numeroApostadoQuantidadeOrdenado.entrySet()) {
            System.out.printf("%-20d%d%n", entry.getKey(), entry.getValue());
            //chave ocupando pelo menos 20 caracteres, alinhada a esquerda
        }
        System.out.println();
    }

    private int[] adicionarNumeroSorteado(int[] numerosSorteados, int numeroExtra) {
        int[] newNumerosSorteados = Arrays.copyOf(numerosSorteados, numerosSorteados.length + 1);
        newNumerosSorteados[newNumerosSorteados.length - 1] = numeroExtra;
        return newNumerosSorteados;
    }

    public void Premiacao(int vencedores)
    {
        double premio = 300000;
        String premioStr = "U$300.000,00";

        DecimalFormat formato = new DecimalFormat("U$ ###,###,##0.00");
        String premioFormatado = formato.format(premio / vencedores);

        if (vencedores == 1) {
            System.out.println();
            System.out.println("------ We have a winner bet! ------");
            System.out.println();
            System.out.println("Congratulations! In addition to the amount of " + premioStr + " just for you, you win something even better, a TOP Dell kit!");
            System.out.println("  - An incredible Dell G15 laptop with an NVIDIA 4050");
            System.out.println("  - Dell wireless mouse and keyboard (KB700)");
            System.out.println("  - Dell Gaming 17 backpack");
            System.out.println("  - Paid trip for you and a companion to visit Dell headquarters in Round Rock, Texas, United States");

        } else if (vencedores > 1 && vencedores < 4){

            System.out.println("------ There were " + vencedores + " winning bets! ------");
            System.out.println();
            System.out.println("Congratulations to the winners! You will share the prize of " + premioStr + ", but that's not all, check it out:");
            System.out.println("Each of you will get " + premioFormatado + " and besides the money, something even better.");
            System.out.println("Each of you will receive a Dell kit containing:");
            System.out.println("  - Inspiron 15 notebook with 8GB DDR4");
            System.out.println("  - An Alienware AW520H headset (white or black)");
            System.out.println("  - Dell EcoLoop Essential 14-16 backpack");
        }
        else
        {
            System.out.println("------ There were " + vencedores + " winning bets! ------");
            System.out.println();
            System.out.println("Congratulations to the winners! You will share the prize of " + premioStr + ", but that's not all, check it out:");
            System.out.println("Each of you will get " + premioFormatado + " and besides the money, something even better.");
            System.out.println("Each of you will receive a Dell kit containing:");
            System.out.println("  - Dell EcoLoop Essential 14-16 backpack");
            System.out.println("  - Dell DA310 USB-C 7-in-1 multi-port dockstation");
            System.out.println("  - Dell wireless mouse");
        }
    }
}