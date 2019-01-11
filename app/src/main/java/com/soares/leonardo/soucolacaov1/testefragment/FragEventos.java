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

import com.soares.leonardo.soucolacaov1.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragEventos extends Fragment {

    ArrayList<String> lista_eventos;
    ArrayAdapter <String> adapter_eventos;

    public FragEventos() {
        // Required empty public constructor
    }
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView lveventos = view.findViewById(R.id.lv_frag_1);

        /*ADICIONANDO EVENTOS DE FORMA MANUAL E CRIANDO O ADAPTER*/
        lista_eventos = new ArrayList<>();

        lista_eventos.add("4532");
        lista_eventos.add("6862");
        lista_eventos.add("6851");

        adapter_eventos = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, lista_eventos);
        lveventos.setAdapter(adapter_eventos);

        lveventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Pega a opção selecionada*/
                String nome_evento_selecionado = lista_eventos.get(position);

                /*Abrindo a lista de alunos referente a opção selecionada*/
                FragAlunos fragAlunos = new FragAlunos();

                Bundle bundle = new Bundle();

                bundle.putString("nome_evento", nome_evento_selecionado);

                fragAlunos.setArguments(bundle);

                FragmentManager fragmentManager_alunos = getFragmentManager();
                fragmentManager_alunos.beginTransaction().replace(R.id.cl_eventos, fragAlunos).commit();

            }
        });

        return view;
    }

}
