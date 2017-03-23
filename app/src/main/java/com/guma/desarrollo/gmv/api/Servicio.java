package com.guma.desarrollo.gmv.api;

import com.google.gson.JsonObject;
import com.guma.desarrollo.core.Pedidos_model;
import com.guma.desarrollo.gmv.models.Respuesta_articulos;
import com.guma.desarrollo.gmv.models.Respuesta_clientes;
import com.guma.desarrollo.gmv.models.Respuesta_indicadores;
import com.guma.desarrollo.gmv.models.Respuesta_mora;

import com.guma.desarrollo.gmv.models.Respuesta_pedidos;
import com.guma.desarrollo.gmv.models.Respuesta_usuario;

import com.guma.desarrollo.gmv.models.Respuesta_puntos;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by maryan.espinoza on 01/03/2017.
 */

public interface Servicio {

    @GET("ARTICULOS")
    Call<Respuesta_articulos> obtenerListaArticulos();

    @FormUrlEncoded
    @POST("Clientes")
    Call<Respuesta_clientes> obtenerListaClientes(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("ClientesIndicadores")
    Call<Respuesta_indicadores> obtenerListaClienteIndicadores(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("ClientesMora")
    Call<Respuesta_mora> obtenerListaClienteMora(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("Login")
    Call<Respuesta_usuario> obtenerListaUsuario(@Field("usuario") String apiKey, @Field("pass") String apiKey2);

    @FormUrlEncoded
    @POST("url_pedidos")
    Call<Respuesta_pedidos> enviarPedidos(@Field("PEDIDOS") String pedidos);

    @FormUrlEncoded
    @POST("Puntos")
    Call<Respuesta_puntos> obtenerFacturasPuntos(@Field("mVendedor") String mVendedor);

    @FormUrlEncoded
    @POST("InsertCobros")
    Call<String> InserCorbos(@Field("pCobros") String pCobros);

    @FormUrlEncoded
    @POST("inVisitas")
    Call<String> inVisitas(@Field("mVisitas") String mVisitas);

}
