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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisaActivity extends AppCompatActivity {
    private BancoDeDados bd = null;
    private String[] linhaVermelha, linhaAmarela, linhaAmarelaEvermelha;
    private Spinner spnOrigem, spnDestino;
    private Button btnProxima;

    private String padrao = "Selecione a estação";
    private String sel_amarela = "LINHA AMARELA:";
    private String sel_vermelha = "LINHA VERMELHA:";


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

        // Estações disponíveis
        linhaAmarelaEvermelha = new String[] {
                "Selecione a estação",
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

                "LINHA AMARELA:",
                "Vila Sônia Profa. Elisabeth Tenreiro",
                "São Paulo - Morumbi",
                "Butantã",
                "Pinheiros",
                "Faria Lima",
                "Fradique Coutinho",
                "Oscar Freire",
                "Paulista - Pernambucanas",
                "Higienópolis - Mackenzie",
                "República",
                "Luz"
        };

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

        linhaAmarela = new String[]{
                "Vila Sônia Profa. Elisabeth Tenreiro",
                "São Paulo - Morumbi",
                "Butantã",
                "Pinheiros",
                "Faria Lima",
                "Fradique Coutinho",
                "Oscar Freire",
                "Paulista - Pernambucanas",
                "Higienópolis - Mackenzie",
                "República",
                "Luz"
        };

        spnOrigem = (Spinner) findViewById(R.id.spnOrigem);
        spnDestino = (Spinner) findViewById(R.id.spnDestino);
        btnProxima = (Button) findViewById(R.id.btnProxima);

        configurarSpinner(spnOrigem);
        configurarSpinner(spnDestino);
    } // OK


    private void configurarSpinner(Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.campo_spinner, linhaAmarelaEvermelha);
        spinner.setAdapter(adapter);
    } // OK


    private void pintarSpinner(String estacaoEscolhida, Spinner spinner) {
        String escolha = spinner.getSelectedItem().toString();

        if (escolha.equals(padrao) || escolha.equals(sel_amarela) || escolha.equals(sel_vermelha)) {
            spinner.setBackgroundColor(Color.WHITE);
            return;
        }

        for(String estacao: linhaAmarela) { // Pega cada estação que existe na linha diamante
            if (estacaoEscolhida.equals(estacao)){
                spinner.setBackgroundColor(Color.YELLOW);
                return;
            }
        }

        for(String estacao: linhaVermelha) { // Pega cada estação que existe na linha vermelha
            if (estacaoEscolhida.equals(estacao)){
                spinner.setBackgroundColor(Color.RED);
                return;
            }
        }
    } // OK


    private void listeners() {
        // Passa para a próxima tela (TELA DE CADASTRO)
        btnProxima.setOnClickListener(r -> {
            String origem = spnOrigem.getSelectedItem().toString();
            String destino = spnDestino.getSelectedItem().toString();

            if (origem != padrao && origem != sel_amarela && origem != sel_vermelha &&
                    destino != padrao && destino != sel_amarela && destino != sel_vermelha) {

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
                pintarSpinner(origem, spnOrigem);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional (mas obrigatório implementar)
            }
        });

        spnDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String destino = spnDestino.getSelectedItem().toString();
                pintarSpinner(destino, spnDestino);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional (mas obrigatório implementar)
            }
        });


    }

}