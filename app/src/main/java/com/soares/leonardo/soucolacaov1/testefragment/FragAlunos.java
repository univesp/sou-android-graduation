package com.soares.leonardo.soucolacaov1.testefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.soares.leonardo.soucolacaov1.JsonClasses.DataList;
import com.soares.leonardo.soucolacaov1.JsonClasses.DataMainInterface;
import com.soares.leonardo.soucolacaov1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAlunos extends Fragment {

    ArrayList<String> lista_alunos;

    ArrayAdapter <String> adapter_alunos;

    final DataList d = new DataList();

    public FragAlunos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);

        final ListView lvalunos = view.findViewById(R.id.lv_frag_2);

        /*ARRAYLIST DE ACORDO COM A OPÇÃO SELECIONADA E CRIANDO O ADAPTER*/
        lista_alunos = new ArrayList<>();
        adapter_alunos = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, lista_alunos);

        lvalunos.setAdapter(adapter_alunos);

        final Bundle bundle = getArguments();

        /*CRIAÇÃO DO OBJETO RETROFIT, RESPONSÁVEL POR MONTAR A URI E CONSTRUIR O JSON QUANDO FOR CHAMADO*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMainInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /*CRIAÇÃO DE INTERFACE PARA UTILIZAÇÃO DO OBJETO RETROFIT CRIADO ACIMA PARA CHAMAR A URI*/
        DataMainInterface dataMainInterface = retrofit.create(DataMainInterface.class);

        /*CRIAÇÃO DO JSON OBJECT ATRAVÉS DA INTERFACE CRIADA ACIMA*/
        Call<JsonArray> call1 = dataMainInterface.loadEvento();
        Call<JsonArray> call2 = dataMainInterface.loadEvento2();
        Call<JsonArray> call3 = dataMainInterface.loadEvento3();

        if (bundle != null) {
                if (bundle.getString("nome_evento") != null ) {
                    final String nome_do_evento = bundle.getString("nome_evento");

                    switch (nome_do_evento) {
                        case "4532":
                            //EVENTO 4532
                            call1.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    if(!response.isSuccessful()){

                                        /*CASO TENHA CONEXÃO MAS NÃO TENHA NENHUMA RESPOSTA OBTIDA*/
                                        Toast.makeText(getContext(),"ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        /*CRIAÇÃO DO JSONOBJECT UTILIZANDO A BIBLIOTECA GSON PARA OBTER O CORPO DA RESPONSE*/
                                        JsonArray resposta = response.body();

                                        /*CRIAÇÃO DO ARRAY QUE CONTÉM OS DADOS FINAIS DESEJADOS, LOCALIZADO PELA TAG "data"*/
                                        final JsonArray jsonArray = resposta.getAsJsonArray();

                                        /*LOOP PARA PERCORRER O ARRAY CRIADO E EXIBIR NA LISTVIEW EM TEMPO DE EXECUÇÃO*/
                                        for (int i = 0; i < jsonArray.size(); i++) {

                                            final JsonObject js_data = (JsonObject) jsonArray.get(i);

                                            d.id = js_data.get("__id").toString();
                                            d.nomealuno = js_data.get("nomealuno").toString();
                                            d.ra = js_data.get("ra").toString();
                                            d.rg = js_data.get("rg").toString();
                                            d.curso = js_data.get("curso").toString();

                                            lista_alunos.add("NOME: " + d.nomealuno
                                                    +"\nRA:" + d.ra);

                                            lvalunos.setAdapter(adapter_alunos);
                                        }

                                        /* AÇÃO DE CLICAR EM UM ALUNO ESPECÍFICO DA LISTA  */
                                        lvalunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                for (int i = 0; i < jsonArray.size(); i++) {

                                                    final JsonObject js_data = (JsonObject) jsonArray.get(i);
                                                    
                                                    if (position == i){

                                                        d.id = js_data.get("__id").toString();
                                                        d.nomealuno = js_data.get("nomealuno").toString();
                                                        d.ra = js_data.get("ra").toString();
                                                        d.rg = js_data.get("rg").toString();
                                                        d.curso = js_data.get("curso").toString();

                                                        FragDashboard fragDashboard = new FragDashboard();
                                                        Bundle bundle1 = new Bundle();
                                                        bundle1.putString("id", d.id);
                                                        bundle1.putString("nome_selecionado", d.nomealuno);
                                                        bundle1.putString("ra", d.ra);
                                                        bundle1.putString("rg", d.rg);
                                                        bundle1.putString("curso", d.curso);

                                                        fragDashboard.setArguments(bundle1);

                                                        FragmentManager fragmentManager_alunos = getFragmentManager();
                                                        fragmentManager_alunos.beginTransaction().replace(R.id.cl_eventos, fragDashboard).commit();
                                                    }

                                                }

                                            }
                                        });

                                    }
                                }

                                /*EM CASO DE FALHA*/
                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {
                                   Toast.makeText(getContext(),"FAILURE ON RESPONSE: " + t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                            break;
/********************************************************************************************/
                        case "6862":
                            //EVENTO 6862
                            call3.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    if(!response.isSuccessful()){

                                        /*CASO TENHA CONEXÃO MAS NÃO TENHA NENHUMA RESPOSTA OBTIDA*/
                                        Toast.makeText(getContext(),"ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        /*CRIAÇÃO DO JSONOBJECT UTILIZANDO A BIBLIOTECA GSON PARA OBTER O CORPO DA RESPONSE*/
                                        JsonArray resposta = response.body();

                                        /*CRIAÇÃO DO ARRAY QUE CONTÉM OS DADOS FINAIS DESEJADOS, LOCALIZADO PELA TAG "data"*/
                                        JsonArray jsonObject = resposta.getAsJsonArray();

                                        for (int i = 0; i < jsonObject.size(); i++) {
                                            DataList d = new DataList();

                                            JsonObject js_data = (JsonObject) jsonObject.get(i);

                                            d.nomealuno = js_data.get("nomealuno").toString();

                                            lista_alunos.add("\nNAME: " + d.nomealuno);
                                            lvalunos.setAdapter(adapter_alunos);

                                        }

                                    }
                                }

                                /*EM CASO DE FALHA*/
                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {
                                    Toast.makeText(getContext(),"FAILURE ON RESPONSE: " + t.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });
                            break;

                        case "6851":
                            //EVENTO 6851
                            call2.enqueue(new Callback<JsonArray>() {
                                @Override
                                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                    if(!response.isSuccessful()){

                                        /*CASO TENHA CONEXÃO MAS NÃO TENHA NENHUMA RESPOSTA OBTIDA*/
                                        Toast.makeText(getContext(),"ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        /*CRIAÇÃO DO JSONOBJECT UTILIZANDO A BIBLIOTECA GSON PARA OBTER O CORPO DA RESPONSE*/
                                        JsonArray resposta = response.body();

                                        /*CRIAÇÃO DO ARRAY QUE CONTÉM OS DADOS FINAIS DESEJADOS, LOCALIZADO PELA TAG "data"*/
                                        JsonArray jsonObject = resposta.getAsJsonArray();

                                        for (int i = 0; i < jsonObject.size(); i++) {
                                            DataList d = new DataList();

                                            JsonObject js_data = (JsonObject) jsonObject.get(i);

                                            d.nomealuno = js_data.get("nomealuno").toString();

                                            lista_alunos.add("\nNAME: " + d.nomealuno);
                                            lvalunos.setAdapter(adapter_alunos);

                                        }
                                    }
                                }
                                /*EM CASO DE FALHA*/
                                @Override
                                public void onFailure(Call<JsonArray> call, Throwable t) {
                                    Toast.makeText(getContext(),"FAILURE ON RESPONSE: " + t.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });
                            break;
                    }
                }
        }



        return view;
    }

}
