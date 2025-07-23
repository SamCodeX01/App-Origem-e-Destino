package com.example.pesquisametropolitana;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminActivity extends AppCompatActivity {
    private BancoDeDados bd = null;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        organizar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void organizar() {
        bd = BancoDeDados.getInstance();
        txtResultado = (TextView) findViewById(R.id.txtResultado);
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