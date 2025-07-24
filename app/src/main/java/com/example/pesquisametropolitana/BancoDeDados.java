package com.example.pesquisametropolitana;

import java.util.ArrayList;

public class BancoDeDados {
    private static BancoDeDados bancoDeDados = null;
    public int quantidadeRespostas = 0;
    private ArrayList<String> origens = new ArrayList<>();
    private ArrayList<String> destinos = new ArrayList<>();
    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayList<String> telefones = new ArrayList<>();


    public static BancoDeDados getInstance() {
        if (bancoDeDados == null)
            bancoDeDados = new BancoDeDados();
        return bancoDeDados;
    }


    // Métodos assessores
    public String getOrigem(int indice) {
        return origens.get(indice);
    }
    public void setOrigens(String origem) {
        origens.add(origem);
    }

    public String getDestino(int indice) {
        return destinos.get(indice);
    }
    public void setDestinos(String destino) {
        destinos.add(destino);
    }

    public String getNome(int indice) {
        return nomes.get(indice);
    }
    public void setNome(String nome) {
        nomes.add(nome);
    }

    public String getTelefone(int indice) {
        return telefones.get(indice);
    }
    public void setTelefone(String telefone) {
        telefones.add(telefone);
    }

}