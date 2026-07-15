package br.com.nicolas.fipe.Principal;

import br.com.nicolas.fipe.model.Dados;
import br.com.nicolas.fipe.model.Modelos;
import br.com.nicolas.fipe.model.Veiculo;
import br.com.nicolas.fipe.service.ConsumoApi;
import br.com.nicolas.fipe.service.Convertedados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private Convertedados conversor = new Convertedados();


    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("===== TABELA FIPE =====");
        System.out.println();
        System.out.println("- Carros");
        System.out.println("- Motos");
        System.out.println("- Caminhoes");
        System.out.println("\nEscolha o tipo de veiculo:");
        var opcao = scanner.nextLine();

        String endereco;

        if(opcao.equalsIgnoreCase("Carros")){
            endereco = URL_BASE + "carros/marcas";
        } else if(opcao.equalsIgnoreCase("Motos")){
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("\nInforme o codigo da marca para consulta: ");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modelosLista = conversor.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca: ");

        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do veiculo a ser buscado: ");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modelosLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite o codigo do modelo para buscar os valores de avaliacao: ");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("Todos os veiculos filtrados com avaliacoes por ano: ");
        veiculos.forEach(System.out::println);

    }
}
