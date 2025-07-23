package com.example.pesquisametropolitana;
//import com.example.pesquisametropolitana.BancoDeDados;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_LONG;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastroActivity extends AppCompatActivity {
    private BancoDeDados bd = null;
    private EditText txtNome, txtTelefone;
    private Button btnVoltar, btnFinalizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

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
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
    }


    private void listeners() {
        // Guarda respostas e volta para a tela de login
        btnVoltar.setOnClickListener(r -> {

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // Guarda respostas e inicia nova pesquisa
        btnFinalizar.setOnClickListener(r -> {

            String nome = txtNome.getText().toString();
            String telefone = txtTelefone.getText().toString();

            bd.setNome(nome);
            bd.setTelefone(telefone);
            bd.quantidadeRespostas++;
            Toast.makeText(this, "Obrigado pela resposta!", LENGTH_LONG).show();

            startActivity(new Intent(this, PesquisaActivity.class));
            finish();
        });
    }




}