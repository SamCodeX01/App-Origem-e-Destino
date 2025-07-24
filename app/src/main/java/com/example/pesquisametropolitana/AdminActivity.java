package com.example.pesquisametropolitana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.badge.BadgeUtils;

public class AdminActivity extends AppCompatActivity {
    private BancoDeDados bd = null;
    private TextView txtResultado, txtQtdRespostas;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        mainConfig();
        listeners();
        exibirRespostas();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void mainConfig() {
        bd = BancoDeDados.getInstance();
        txtQtdRespostas = (TextView) findViewById(R.id.txtQtdRespostas);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        String textoPadrao = txtQtdRespostas.getText().toString();
        txtQtdRespostas.setText(textoPadrao + bd.quantidadeRespostas);
    }


    private void listeners() {
        // Botão de VOLTAR para a tela anterior
        btnVoltar.setOnClickListener(r -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }


    private void exibirRespostas() {
        String respostas = "";

        for (int i=0; i < bd.quantidadeRespostas; i++) {
            respostas +=
                    "Nome: " + bd.getNome(i) + "\n" +
                    "Telefone: " + bd.getTelefone(i) + "\n" +
                    "Estação de Origem: " + bd.getOrigem(i) + "\n" +
                    "Estação de Destino: " + bd.getDestino(i) + "\n\n";
        }
        txtResultado.setText(respostas);
    }

}