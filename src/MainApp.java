import memento.*;
import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private final Originator originator = new Originator();
    private final Caretaker caretaker = new Caretaker(originator);
    private final List<Pessoa> pessoas = new ArrayList<>();
    private final List<Sindico> sindicos = new ArrayList<>();
    private final List<Condominio> condominios = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.iniciar();
    }

    public void iniciar() {
        int opcao;
        do {
            salvarEstado();
            mostrarMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    gerenciarPessoas();
                    break;
                case 2:
                    gerenciarSindicos();
                    break;
                case 3:
                    gerenciarCondominios();
                    break;
                case 4:
                    caretaker.desfazer();
                    restaurarEstado();
                    System.out.println("Última operação desfeita!");
                    break;
                case 5:
                    caretaker.refazer();
                    restaurarEstado();
                    System.out.println("Operação refeita!");
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void salvarEstado() {
        originator.setEstado(pessoas, sindicos, condominios);
        caretaker.salvarEstado();
    }

    private void restaurarEstado() {
        pessoas.clear();
        sindicos.clear();
        condominios.clear();

        pessoas.addAll(originator.getEstadoPessoas());
        sindicos.addAll(originator.getEstadoSindicos());
        condominios.addAll(originator.getEstadoCondominios());
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\nSISTEMA DE CONDOMÍNIOS (MEMENTO)");
        System.out.println("1. Gerenciar Pessoas");
        System.out.println("2. Gerenciar Síndicos");
        System.out.println("3. Gerenciar Condomínios");
        System.out.println("4. Desfazer");
        System.out.println("5. Refazer");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void gerenciarPessoas() {
        int opcao;
        do {
            salvarEstado();
            System.out.println("\nMENU PESSOA");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    atualizarPessoa();
                    break;
                case 4:
                    deletarPessoa();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarPessoa() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        pessoas.add(new Pessoa(nome, cpf, telefone));
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private void listarPessoas() {
        System.out.println("\nLISTA DE PESSOAS:");
        pessoas.forEach(System.out::println);
    }

    private void atualizarPessoa() {
        System.out.print("CPF da pessoa a atualizar: ");
        String cpf = scanner.nextLine();

        Pessoa pessoa = pessoas.stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (pessoa != null) {
            System.out.print("Novo nome: ");
            pessoa.setNome(scanner.nextLine());
            System.out.print("Novo telefone: ");
            pessoa.setTelefone(scanner.nextLine());
            System.out.println("Pessoa atualizada!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }
    }

    private void deletarPessoa() {
        System.out.print("CPF da pessoa a deletar: ");
        String cpf = scanner.nextLine();

        if (pessoas.removeIf(p -> p.getCpf().equals(cpf))) {
            System.out.println("Pessoa removida!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }
    }

    private void gerenciarSindicos() {
        int opcao;
        do {
            salvarEstado();
            System.out.println("\nMENU SÍNDICO");
            System.out.println("1. Cadastrar Síndico");
            System.out.println("2. Listar Síndicos");
            System.out.println("3. Associar a Condomínio");
            System.out.println("4. Atualizar Síndico");
            System.out.println("5. Deletar Síndico");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarSindico();
                    break;
                case 2:
                    listarSindicos();
                    break;
                case 3:
                    associarSindicoCondominio();
                    break;
                case 4:
                    atualizarSindico();
                    break;
                case 5:
                    deletarSindico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarSindico() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Registro: ");
        String registro = scanner.nextLine();
        System.out.print("Data de Eleição: ");
        String dataEleicao = scanner.nextLine();

        sindicos.add(new Sindico(nome, cpf, telefone, registro, dataEleicao));
        System.out.println("Síndico cadastrado com sucesso!");
    }

    private void listarSindicos() {
        System.out.println("\nLISTA DE SÍNDICOS:");
        sindicos.forEach(System.out::println);
    }

    private void associarSindicoCondominio() {
        System.out.print("Registro do Síndico: ");
        String registro = scanner.nextLine();
        System.out.print("Nome do Condomínio: ");
        String nomeCondominio = scanner.nextLine();
        System.out.print("Data de Início: ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data de Fim: ");
        String dataFim = scanner.nextLine();

        Sindico sindico = sindicos.stream()
                .filter(s -> s.getRegistro().equals(registro))
                .findFirst()
                .orElse(null);

        Condominio condominio = condominios.stream()
                .filter(c -> c.getNome().equals(nomeCondominio))
                .findFirst()
                .orElse(null);

        if (sindico != null && condominio != null) {
            condominio.adicionarSindico(sindico, dataInicio, dataFim);
            System.out.println("Associação realizada com sucesso!");
        } else {
            System.out.println("Síndico ou Condomínio não encontrado!");
        }
    }

    private void atualizarSindico() {
        System.out.print("Registro do síndico a atualizar: ");
        String registro = scanner.nextLine();

        Sindico sindico = sindicos.stream()
                .filter(s -> s.getRegistro().equals(registro))
                .findFirst()
                .orElse(null);

        if (sindico != null) {
            System.out.print("Novo nome: ");
            sindico.setNome(scanner.nextLine());
            System.out.print("Novo telefone: ");
            sindico.setTelefone(scanner.nextLine());
            System.out.print("Nova data de eleição: ");
            sindico.setDataEleicao(scanner.nextLine());
            System.out.println("Síndico atualizado!");
        } else {
            System.out.println("Síndico não encontrado!");
        }
    }

    private void deletarSindico() {
        System.out.print("Registro do síndico a deletar: ");
        String registro = scanner.nextLine();

        if (sindicos.removeIf(s -> s.getRegistro().equals(registro))) {
            // Remove associações (composição)
            condominios.forEach(c -> c.getSindicos().removeIf(a -> a.getSindico().getRegistro().equals(registro)));
            System.out.println("Síndico removido!");
        } else {
            System.out.println("Síndico não encontrado!");
        }
    }

    private void gerenciarCondominios() {
        int opcao;
        do {
            salvarEstado();
            System.out.println("\nMENU CONDOMÍNIO");
            System.out.println("1. Cadastrar Condomínio");
            System.out.println("2. Listar Condomínios");
            System.out.println("3. Atualizar Condomínio");
            System.out.println("4. Deletar Condomínio");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCondominio();
                    break;
                case 2:
                    listarCondominios();
                    break;
                case 3:
                    atualizarCondominio();
                    break;
                case 4:
                    deletarCondominio();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCondominio() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        condominios.add(new Condominio(nome, endereco));
        System.out.println("Condomínio cadastrado com sucesso!");
    }

    private void listarCondominios() {
        System.out.println("\nLISTA DE CONDOMÍNIOS:");
        condominios.forEach(c -> {
            System.out.println(c);
            c.getSindicos().forEach(a -> System.out.println("  " + a));
        });
    }

    private void atualizarCondominio() {
        System.out.print("Nome do condomínio a atualizar: ");
        String nome = scanner.nextLine();

        Condominio condominio = condominios.stream()
                .filter(c -> c.getNome().equals(nome))
                .findFirst()
                .orElse(null);

        if (condominio != null) {
            System.out.print("Novo nome: ");
            condominio.setNome(scanner.nextLine());
            System.out.print("Novo endereço: ");
            condominio.setEndereco(scanner.nextLine());
            System.out.println("Condomínio atualizado!");
        } else {
            System.out.println("Condomínio não encontrado!");
        }
    }

    private void deletarCondominio() {
        System.out.print("Nome do condomínio a deletar: ");
        String nome = scanner.nextLine();

        if (condominios.removeIf(c -> c.getNome().equals(nome))) {
            System.out.println("Condomínio removido!");
        } else {
            System.out.println("Condomínio não encontrado!");
        }
    }
}