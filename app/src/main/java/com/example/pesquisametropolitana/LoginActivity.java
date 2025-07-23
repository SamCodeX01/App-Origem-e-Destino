package com.example.pesquisametropolitana;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private BancoDeDados bd = null;
    private EditText txtUsuario, txtSenha;
    private Button btnLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

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
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
    }


    private void listeners() {
        btnLogar.setOnClickListener(r -> {
            String usuario = txtUsuario.getText().toString();
            String senha = txtSenha.getText().toString();

            if (usuario.equals("") && senha.equals("")) {
                startActivity(new Intent(this, PesquisaActivity.class));
                finish();
            }
            else if (usuario.equals("admin") && senha.equals("admin")) {
                startActivity(new Intent(this, AdminActivity.class));
                finish();
            }
            else
                Toast.makeText(this, "Dados inválidos ou campos vazios!", LENGTH_SHORT).show();
        });
    }

}