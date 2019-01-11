package com.soares.leonardo.soucolacaov1.JsonClasses;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataMainInterface {
    String BASE_URL = "https://sheetlabs.com/TECN/";

    @GET("convidados?listaid=4532")
    Call<JsonArray> loadEvento(); // Método para obter o Json inteiro, que no caso é um JSON Object


    @GET("convidados?listaid=6851")
    Call<JsonArray> loadEvento2(); // Método para obter o Json inteiro, que no caso é um JSON Object


    @GET("convidados?listaid=6862")
    Call<JsonArray> loadEvento3(); // Método para obter o Json inteiro, que no caso é um JSON Object

}
