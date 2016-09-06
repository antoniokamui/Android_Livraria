package com.example.rm30316.livrariasqlite      ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private final String PREF_NAME = "LIVRARIA";
    private TextInputLayout tiUsername;
    private TextInputLayout tiPassword;
    private CheckBox ckManterConectado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tiUsername = (TextInputLayout)findViewById(R.id.tiUserName);
        tiPassword = (TextInputLayout)findViewById(R.id.tiPassword);
        ckManterConectado = (CheckBox) findViewById(R.id.ckManterConectado);

        SharedPreferences sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        if(sp.getBoolean("manterConectado",false)){
            proximaTela(sp.getString("nomeUsuario",""));
        }
    }

    public void doLogin(View view){
        if(tiUsername.getEditText().getText().toString().equals("fiap")
                && tiPassword.getEditText().getText().toString().equals("123")){

            SharedPreferences sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("manterConectado",ckManterConectado.isChecked());
            editor.putString("nomeUsuario",tiUsername.getEditText().getText().toString());
            editor.commit();

            proximaTela(tiUsername.getEditText().getText().toString());
        }else{
            Toast.makeText(this,"Usuário ou senha inválido!",Toast.LENGTH_LONG).show();
        }
    }

    private void proximaTela(String nome) {
        Intent telaPrincipal = new Intent(this,MainActivity.class);
        telaPrincipal.putExtra("nome",nome);
        startActivity(telaPrincipal);
        finish();
    }


}