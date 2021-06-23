package com.example.psm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.dashboardFragment, R.id.secondFragment, R.id.thirdFragment)
//                .build();
//        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
//        NavigationUI.setupActionBarWithNavController (this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController (bottomNavigationView, navController);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                new ProfileFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.dashboardFragment:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.secondFragment:
                            selectedFragment = new SecondFragment();
                            break;
                        case R.id.thirdFragment:
                            selectedFragment = new ThirdFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                            selectedFragment).commit();

                    return true;
                }
            };
}