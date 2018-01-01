package com.br.tomei.api;


import com.br.tomei.model.Breja;
import com.br.tomei.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BrejaAPI {

    @GET("/breja")
    Call<List<Breja>> findAll();

    //busca item pelo nome
    @GET("/breja/nome/{nome}")
    Call<Breja> buscarItemNome(@Path("nome") String nome);

    //busca item pelo nome (conteudo)
    @GET("/breja/nomeParcial/{nome}")
    Call<List<Breja>> buscarItemNomeParc(@Path("nome") String nome);

    //busca item pelo id
    @GET("/breja/id/{id}")
    Call<Breja> buscarItemId(@Path("id") int id);

    //salva item
    @POST("/breja")
    Call<Void> salvarItem(@Body Breja breja);

    @PUT("/breja")
    Call<Void> atualizar(@Body Breja i);

    //delete item
    @DELETE("/breja/id/{id}")
    Call<Void> deleteById(@Body String id);

    //USUARIO

    @GET("/usuario/buscausuario/{usuario}")
    Call<Usuario> buscarUsuario(@Path("usuario") String usuario);

    @GET("/usuario/buscausuario/{usuario}/{senha}")
    Call<Usuario> buscarUsuario(@Path("usuario") String usuario, @Path("senha") String senha);

    @POST("/usuario")
    Call<Void> salvarUser(@Body Usuario usuario);



}
