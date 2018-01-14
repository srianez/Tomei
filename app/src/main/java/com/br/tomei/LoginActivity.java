package com.br.tomei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.tomei.api.BrejaAPI;
import com.br.tomei.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private ProgressBar mProgressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.txUsuario);
        etSenha = (EditText) findViewById(R.id.txtPassword);
        mProgressView = (ProgressBar) findViewById(R.id.login_progressL);

    }

    public void goCadUsuario(View v) {
        Intent proximaTela = new Intent(this, CadUsuarioLogin.class);
        startActivity(proximaTela);
    }

    public void goMain(View v) {

        if (verificaCamposObrigatorios()){
                mProgressView.setVisibility(View.VISIBLE);

                BrejaAPI api = getRetrofit().create(BrejaAPI.class);
                api.buscarUsuario(etUsuario.getText().toString(), etSenha.getText().toString())
                        .enqueue(new Callback<Usuario>() {

                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        //ListarBrejas.class);
                                LoginActivity.this.finish();
                                startActivity(intent);
                                mProgressView.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, getString(R.string.erroEncontrarUser), Toast.LENGTH_LONG).show();
                                etUsuario.setText("");
                                etSenha.setText("");
                                etUsuario.requestFocus();
                                mProgressView.setVisibility(View.GONE);
                            }
                        });
        }

    }

    private boolean verificaCamposObrigatorios()
    {
      return true;
    }


    private Retrofit getRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl("https://silasloja.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
