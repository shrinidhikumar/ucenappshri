package com.example.example.Council;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import com.example.example.HomeActivity;
import com.example.example.R;
import com.example.example.Utils.CommonFunctions;
import java.util.ArrayList;
import java.util.Objects;

public class CouncilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;
    private CouncilRecyclerAdapter recyclerAdapter;
    private int numberOfColumns = 2;
    ArrayList<String> councilName;
    ArrayList<Integer> councilImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.include_council).setVisibility(View.VISIBLE);

        CommonFunctions.setUser(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        initCommitteeName();
        initArrayImage();
        recyclerView = findViewById(R.id.recycler_view_council);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerAdapter = new CouncilRecyclerAdapter(councilName, councilImage);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initCommitteeName() {
        councilName = new ArrayList<>();
        councilName.add("Hostel Warden");
        councilName.add("Care Takers");
        councilName.add("Hostel Representatives");
        councilName.add("Mess Committee");

    }

    private void initArrayImage(){
        councilImage = new ArrayList<Integer>();
        councilImage.add(R.drawable.hostel_warden);
        councilImage.add(R.drawable.office_worker);
        councilImage.add(R.drawable.music_girl);
        councilImage.add(R.drawable.food);

    }

    private static long back_pressed=100;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        startActivity(new Intent(CouncilActivity.this, HomeActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return CommonFunctions.navigationItemSelect(item, this);
    }
}
