package com.josue_martinez.memory.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.josue_martinez.memory.R;
import com.josue_martinez.memory.adapters.PuntuacionAdapter;
import com.josue_martinez.memory.models.Puntuacion;

import java.util.Collections;
import java.util.Comparator;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class PuntuacionesActivity extends AppCompatActivity {
    Realm realm;
    RealmResults<Puntuacion> listaPuntuaciones;
    RecyclerView recyclerViewPuntuaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        realm = Realm.getDefaultInstance();
        listaPuntuaciones = realm.where(Puntuacion.class).sort("puntos", Sort.DESCENDING).findAll();
        //Collections.reverse(listaPuntuaciones);

        recyclerViewPuntuaciones = (RecyclerView) findViewById(R.id.recyclerViewPuntuaciones);

        final PuntuacionAdapter puntuacionAdapter = new PuntuacionAdapter(listaPuntuaciones);
        recyclerViewPuntuaciones.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerViewPuntuaciones.setAdapter(puntuacionAdapter);
        //linea decorativa
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewPuntuaciones.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewPuntuaciones.addItemDecoration(mDividerItemDecoration);

        listaPuntuaciones.addChangeListener(new RealmChangeListener<RealmResults<Puntuacion>>() {
            @Override
            public void onChange(RealmResults<Puntuacion> puntuaciones) {

                puntuacionAdapter.notifyDataSetChanged();

            }
        });
    }
}