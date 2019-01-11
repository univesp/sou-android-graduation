package com.soares.leonardo.soucolacaov1.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.soares.leonardo.soucolacaov1.R;
import com.soares.leonardo.soucolacaov1.testefragment.FragEventos;

//
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragEventos fragEventos = new FragEventos();
        FragmentManager fragmentManager_eventos = getSupportFragmentManager();
        fragmentManager_eventos.beginTransaction().replace(R.id.cl_eventos, fragEventos).commit();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                Toast.makeText(this, "Ver listas", Toast.LENGTH_SHORT).show();
              //  Intent intent1 = new Intent(this,ListarEventosActivity.class);
              //  startActivity(intent1);
                FragEventos fragEventos = new FragEventos();
                FragmentManager fragmentManager_eventos = getSupportFragmentManager();
                fragmentManager_eventos.beginTransaction().replace(R.id.cl_eventos, fragEventos).commit();
                break;
            case R.id.nav_chat:
                Toast.makeText(this, "Ver Alunos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Finalizar", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}