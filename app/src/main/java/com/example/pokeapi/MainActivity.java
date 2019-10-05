package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    ImageView img;

    EditText codigo;
    Button btn_consulta;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img = findViewById(R.id.img_pokemon);
        codigo = findViewById(R.id.txt_numero);
        btn_consulta = findViewById(R.id.btn_pokemon);


        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())


                        .build();

                InterfazPokemon ip =retrofit.create(InterfazPokemon.class);
                Call<Pokemon> llamada =ip.obtenerPokemon(Integer.parseInt(codigo.getText().toString()));


                //estara en enquegue osea en cola hasta que reciba los daos que pedimos
                llamada.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                        Pokemon poke =response.body();
                        Toast.makeText(MainActivity.this,poke.name,Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,poke.sprites.front_default,Toast.LENGTH_SHORT).show();

                        Glide.with(MainActivity.this)
                                .load(poke.sprites.front_default)
                                .into(img);





                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {

                    }
                });






            }
        });









    }
}
