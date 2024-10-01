package br.com.robsonlmds.moviesteam.principal;

import br.com.robsonlmds.moviesteam.excecao.ErroDeConversaoDeAnoException;
import br.com.robsonlmds.moviesteam.modelos.Titulo;
import br.com.robsonlmds.moviesteam.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        String busca = "";

        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("\nsair")) {

            System.out.println("\nDigite um filme para busca, os DIGITE SAIR");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=630bffab";
            System.out.println("\nurl Da API: " + endereco);
            System.out.println("========================================================================================");

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println("Json Do Request: " + json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println("Json Formatado!");
                System.out.println(meuTituloOmdb);
                System.out.println("========================================================================================");

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("\nTitulo já convertido");
                System.out.println(meuTitulo);
                System.out.println("========================================================================================");

                titulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("\nAconteceu um erro: ");
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("\nAlgum erro de argumento na busca, verifique o endereço");

            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("O programa finalizou corretamente!"); ///////////////////////////FIM
    }
}

