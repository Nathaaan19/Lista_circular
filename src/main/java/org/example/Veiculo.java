package org.example;

import java.util.Objects;

public class Veiculo {
    // Atributos de cada veículo
    String placa;
    String diaRestricao;
    String horarioRestricao;
    Veiculo next; // Referência ao próximo veículo (para lista circular)

    // Construtor da classe Veiculo
    public Veiculo(String placa, String diaRestricao, String horarioRestricao) {
        this.placa = placa;
        this.diaRestricao = diaRestricao;
        this.horarioRestricao = horarioRestricao;
        this.next = this; // Lista circular: inicialmente aponta para si mesmo
    }

    // metodo estático para adicionar um veículo à lista circular
    public static void adicionarVeiculo(Veiculo[] listaRef, String placa, String dia, String horario) {
        Veiculo novo = new Veiculo(placa, dia, horario);
        if (listaRef[0] == null) {
            listaRef[0] = novo;
        } else {
            Veiculo temp = listaRef[0];
            while (temp.next != listaRef[0]) {
                temp = temp.next;
            }
            temp.next = novo;
            novo.next = listaRef[0];
        }
    }

    // Exibe todos os veículos da lista circular
    public static void exibirVeiculos(Veiculo lista) {
        if (lista == null) {
            System.out.println("Nenhum veículo na lista!");
            return;
        }
        Veiculo temp = lista;
        do {
            System.out.printf("Placa: %s, Dia de Restrição: %s, Horário de Restrição: %s%n",
                    temp.placa, temp.diaRestricao, temp.horarioRestricao);
            temp = temp.next;
        } while (temp != lista);
    }

    // Libera a memória da lista circular (anula as referências)
    public static void liberarLista(Veiculo lista) {
        if (lista == null) return;
        Veiculo atual = lista.next;
        while (atual != lista) {
            Veiculo prox = atual.next;
            atual.next = null;
            atual = prox;
        }
        lista.next = null; // Desconecta o último nó
    }
}
