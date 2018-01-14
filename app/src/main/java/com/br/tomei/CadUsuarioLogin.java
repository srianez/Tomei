package com.br.tomei;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.br.tomei.api.BrejaAPI;
import com.br.tomei.model.Usuario;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadUsuarioLogin extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private EditText etEmail;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario_login);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etEmail = (EditText) findViewById(R.id.etEmail);

        progressDialog = new ProgressDialog(this);
    }

    public void salvarUsuario(View v) {

        if(etUsuario.getText().toString().isEmpty()) {
            Toast.makeText(CadUsuarioLogin.this, "Informe o usuário!", Toast.LENGTH_LONG).show();
        }

        if(etSenha.getText().toString().isEmpty()) {
            Toast.makeText(CadUsuarioLogin.this, "Informe a senha!", Toast.LENGTH_LONG).show();
        }

        showProgress("Usuário", "Salvando usuário...");

        BrejaAPI api = getRetrofit().create(BrejaAPI.class);

        Usuario usuario = new Usuario();

        usuario.setUsuario(etUsuario.getText().toString());
        usuario.setSenha(etSenha.getText().toString());

        usuario.setEmail(etEmail.getText().toString());

        api.salvarUser(usuario)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dismissProgress();
                        Toast.makeText(CadUsuarioLogin.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadUsuarioLogin.this, LoginActivity.class);
                        CadUsuarioLogin.this.finish();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dismissProgress();
                        Toast.makeText(CadUsuarioLogin.this, "Ohh shiiit...deu erro! :/", Toast.LENGTH_SHORT).show();

                    }

                });
    }

    public Retrofit getRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl("https://silasloja.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void showProgress(String titulo, String mensagem) {
        if(progressDialog == null)
            progressDialog = new ProgressDialog(CadUsuarioLogin.this);

        if(!progressDialog.isShowing()) {
            progressDialog.setMessage(mensagem);
            progressDialog.setTitle(titulo);
            progressDialog.show();
        }
    }

    private void dismissProgress() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }

}
