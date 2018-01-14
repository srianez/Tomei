package com.br.tomei;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.tomei.api.BrejaAPI;
import com.br.tomei.model.Breja;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadBreja extends AppCompatActivity {

    private Intent intent;

    private EditText txtNome;
    //private EditText txtTipo;
    private EditText txtDescricao;

    private Spinner txtTipo;

    private RatingBar ratingBar;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_breja);

        txtNome = (EditText) findViewById(R.id.txtNome);
        //txtTipo = (EditText) findViewById(R.id.txtTipo);
        txtTipo = (Spinner) findViewById(R.id.txtTipo);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        progressDialog = new ProgressDialog(this);
    }

    public void salvarBreja(View v) {

        if(txtNome.getText().toString().isEmpty()) {
            Toast.makeText(CadBreja.this, "Informe ao menos o nome da breja né fera?!", Toast.LENGTH_LONG).show();
        }

        showProgress("Breja", "Salvando a breja...");

        BrejaAPI api = getRetrofit().create(BrejaAPI.class);

        Breja item = new Breja();

        item.setNome(txtNome.getText().toString());
        //item.setTipo(txtTipo.getText().toString());
        item.setTipo(String.valueOf(txtTipo.getSelectedItem()));
        item.setDescricao(txtDescricao.getText().toString());
        item.setAvaliacao(ratingBar.getRating());

        api.salvarItem(item)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dismissProgress();
                        Toast.makeText(CadBreja.this,
                                "Breja cadastrada com sucesso! :)", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dismissProgress();
                        Toast.makeText(CadBreja.this,
                                "Ohhh shittt. Erro ao cadastrar breja :(", Toast.LENGTH_SHORT).show();

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
            progressDialog = new ProgressDialog(CadBreja.this);

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
