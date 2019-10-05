package com.example.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfazPokemon {

        @GET("pokemon/{id}/")
        Call<Pokemon>obtenerPokemon(@Path("id")int id);



    }


