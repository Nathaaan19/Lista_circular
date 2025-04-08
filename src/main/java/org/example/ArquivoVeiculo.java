package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArquivoVeiculo {

    // Lê os dados do arquivo e retorna a lista circular de veículos que atendem ao filtro
    public static Veiculo carregarVeiculosDoArquivo(String nomeArquivo, String critDia, char critFinalPlaca) {
        Veiculo[] lista = new Veiculo[1]; // Wrapper para referência de objeto (simula ponteiro em Java)

        try (
                // Abertura do arquivo dentro da pasta resources
                InputStream inputStream = ArquivoVeiculo.class.getClassLoader().getResourceAsStream(nomeArquivo);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Quebra a linha por ponto-e-vírgula (;)
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    String placa = partes[0].trim();
                    String dia = partes[1].trim();
                    String horario = partes[2].trim();

                    // Verifica se o veículo atende ao critério escolhido
                    boolean atende = false;
                    if (!critDia.isEmpty() && dia.equalsIgnoreCase(critDia)) {
                        atende = true;
                    }
                    if (critFinalPlaca != '\0' && !placa.isEmpty() &&
                            placa.charAt(placa.length() - 1) == critFinalPlaca) {
                        atende = true;
                    }

                    // Adiciona à lista se atender
                    if (atende) {
                        Veiculo.adicionarVeiculo(lista, placa, dia, horario);
                    }
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            System.exit(1); // Encerra o programa se der erro
        }

        return lista[0]; // Retorna a lista preenchida
    }
}
