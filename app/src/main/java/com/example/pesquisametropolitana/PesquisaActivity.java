package com.example.pesquisametropolitana;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisaActivity extends AppCompatActivity {

//    private static final int LENGHT_SHORT = ;
    private String[] linhaVermelha, linhaDiamante, linhaDiamanteEvermelha;
    private Spinner spnOrigem, spnDestino;
    private Button btnProxima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa);

        mainConfig();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    private void mainConfig() {
        // Estações disponíveis //CARREGAR DEPOIS
        linhaVermelha = new String[]{
                "Corinthians-Itaquera",
                "Artur Alvim",
                "Patriarca-Vila Ré",
                "Guilhermina-Esperança",
                "Vila Matilde",
                "Penha-Lojas Besni",
                "Carrão-Assaí Atacadista",
                "Tatuapé",
                "Belém",
                "Bresser-Mooca",
                "Brás",
                "Pedro II",
                "Sé",
                "Anhangabaú",
                "República",
                "Santa Cecília",
                "Marechal Deodoro",
                "Palmeiras-Barra Funda"
        };


        linhaDiamante = new String[]{
                "Júlio Prestes",
                "Palmeiras-Barra Funda",
                "Lapa",
                "Domingos de Moraes",
                "Imperatriz Leopoldina",
                "Presidente Altino",
                "Osasco",
                "Comandante Sampaio",
                "Quitaúna",
                "General Miguel Costa",
                "Carapicuíba",
                "Santa Terezinha",
                "Antonio João",
                "Barueri",
                "Jardim Belval",
                "Jardim Silveira",
                "Jandira",
                "Sagrado Coração",
                "Engenheiro Cardoso",
                "Itapevi",
                "Santa Rita",
                "Amador Bueno"
        };

        linhaDiamanteEvermelha = new String[] {
                "Selecione uma parada",
                "LINHA VERMELHA: ",
                "Corinthians-Itaquera",
                "Artur Alvim",
                "Patriarca-Vila Ré",
                "Guilhermina-Esperança",
                "Vila Matilde",
                "Penha-Lojas Besni",
                "Carrão-Assaí Atacadista",
                "Tatuapé",
                "Belém",
                "Bresser-Mooca",
                "Brás",
                "Pedro II",
                "Sé",
                "Anhangabaú",
                "República",
                "Santa Cecília",
                "Marechal Deodoro",
                "Palmeiras-Barra Funda",

                "LINHA DIAMANTE: ",
                "Júlio Prestes",
                "Palmeiras-Barra Funda",
                "Lapa",
                "Domingos de Moraes",
                "Imperatriz Leopoldina",
                "Presidente Altino",
                "Osasco",
                "Comandante Sampaio",
                "Quitaúna",
                "General Miguel Costa",
                "Carapicuíba",
                "Santa Terezinha",
                "Antonio João",
                "Barueri",
                "Jardim Belval",
                "Jardim Silveira",
                "Jandira",
                "Sagrado Coração",
                "Engenheiro Cardoso",
                "Itapevi",
                "Santa Rita",
                "Amador Bueno"
    };

        spnOrigem = (Spinner) findViewById(R.id.spnOrigem);
        spnDestino = (Spinner) findViewById(R.id.spnDestino);
        btnProxima = (Button) findViewById(R.id.btnProxima);

        configurarSpinner(spnOrigem);
        configurarSpinner(spnDestino);
    }

    private void configurarSpinner(Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, linhaDiamanteEvermelha);
        spinner.setAdapter(adapter);
    }

    private int metodo(String estacaoEscolhida) {
        for(String estacao: linhaDiamante) { // Pega cada estação que existe na linha diamante
            if (estacaoEscolhida.equals(estacao)){
                spnOrigem.setBackgroundColor(Color.GRAY);
                return 0;
            }
        }


        for(String estacao: linhaVermelha) { // Pega cada estação que existe na linha diamante
            if (estacaoEscolhida.equals(estacao)){
                spnOrigem.setBackgroundColor(Color.RED);
                return 0;
            }
        }
        return 0;
    }
    private void listeners() {
        btnProxima.setOnClickListener(r -> { //TEM QUE VER
            String origem = spnOrigem.getSelectedItem().toString();
            String destino = spnDestino.getSelectedItem().toString();
            String padrao = "Selecione uma parada";

            if (origem != padrao && destino != padrao) {
                Toast.makeText(this, "Obrigado pela resposta!", LENGTH_SHORT).show();
                resetarCampos();
            }
            else
                Toast.makeText(this, "Dados inválidos ou campos vazios!", LENGTH_SHORT).show();
        });



        spnOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnOrigem.setBackgroundColor(Color.WHITE);
                metodo(spnOrigem.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional (mas obrigatório implementar)
            }
        });

    }

    private void resetarCampos() {
        // FAZER
    }

}