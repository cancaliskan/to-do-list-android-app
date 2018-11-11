package com.example.kamran.bluewhite;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class yapilacaklarListesiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String ISIM_KEY = "com.example.kamran.bluewhite.ISIM";
    private String EPOSTA_KEY = "com.example.kamran.bluewhite.EPOSTA";
    private String MAIN_KEY = "com.example.kamran.bluewhite.MAIN_DATA";

    SharedPreferences sharedPreferences;

    String isimVerisi, epostaVerisi;

    TextView navBarIsim, navBarEposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacaklar_listesi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            Intent intent = new Intent(yapilacaklarListesiActivity.this, yapilacakEkle.class);
            yapilacaklarListesiActivity.this.startActivity(intent);
            }
        });


        setTitle("Yapılacaklar Listesi");

        if (getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY, "eposta bulunamadı").equals("eposta bulunamadı")) {
            Intent intent = new Intent(yapilacaklarListesiActivity.this, main.class);
            yapilacaklarListesiActivity.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.yapilacaklar_listesi, menu);
        navBarIsim = (TextView) findViewById(R.id.navBarIsim);
        navBarEposta = (TextView) findViewById(R.id.navBarEposta);

        isimVerisi = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(ISIM_KEY, "isim bulunamadı");
        epostaVerisi = getSharedPreferences(MAIN_KEY, MODE_PRIVATE).getString(EPOSTA_KEY, "eposta bulunamadı");

        navBarIsim.setText(isimVerisi);
        navBarEposta.setText(epostaVerisi);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getFragmentManager();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/
        if (id == R.id.nav_cikisYap) {
            sharedPreferences = getSharedPreferences(MAIN_KEY, MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            Toast.makeText(yapilacaklarListesiActivity.this, "Çıkış Başarılı", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(yapilacaklarListesiActivity.this, yapilacaklarListesiActivity.class);
            yapilacaklarListesiActivity.this.startActivity(intent);
        } else if (id == R.id.nav_yapilacaklar) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.show();
            fragmentManager.beginTransaction().replace(R.id.content_FrameLayout, new yapilacaklarListele()).commit();
        } else if (id == R.id.nav_ayarlar) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.hide();
            fragmentManager.beginTransaction().replace(R.id.content_FrameLayout, new Ayarlar()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
