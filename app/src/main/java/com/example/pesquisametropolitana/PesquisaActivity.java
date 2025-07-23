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
    private BancoDeDados bd = null;
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
        bd = BancoDeDados.getInstance();

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
                "LINHA VERMELHA:",
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

                "LINHA DIAMANTE:",
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
    } // OK


    private void configurarSpinner(Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, linhaDiamanteEvermelha);
        spinner.setAdapter(adapter);
    } // OK


    private void pintarSpinner(String estacaoEscolhida) {
        for(String estacao: linhaDiamante) { // Pega cada estação que existe na linha diamante
            if (estacaoEscolhida.equals(estacao)){
                spnOrigem.setBackgroundColor(Color.GRAY);
                return;
            }
        }

        for(String estacao: linhaVermelha) { // Pega cada estação que existe na linha vermelha
            if (estacaoEscolhida.equals(estacao)){
                spnOrigem.setBackgroundColor(Color.RED);
                return;
            }
        }
    } // OK


    private void listeners() {
        // Passa para a próxima tela (de cadastro)
        btnProxima.setOnClickListener(r -> {
            String origem = spnOrigem.getSelectedItem().toString();
            String destino = spnDestino.getSelectedItem().toString();

            String padrao = "Selecione uma parada";
            String sel_diamante = "LINHA DIAMANTE:";
            String sel_vermelha = "LINHA VERMELHA:";

            if (origem != padrao && origem != sel_diamante && origem != sel_vermelha &&
                    destino != padrao && destino != sel_diamante && destino != sel_vermelha) {

                // Adicionando ao banco
                bd.setOrigens(origem);
                bd.setDestinos(destino);

                startActivity(new Intent(this, CadastroActivity.class));
                finish();
            }
            else
                Toast.makeText(this, "Dados inválidos ou campos vazios!", LENGTH_SHORT).show();
        });


        // Aguarda a seleção de uma opção do spinner
        spnOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String origem = spnOrigem.getSelectedItem().toString();
                spnOrigem.setBackgroundColor(Color.WHITE);
                pintarSpinner(origem);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional (mas obrigatório implementar)
            }
        });
    }

}