package com.example.th2test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.th2test.adapters.MyViewPagerAdapter;
import com.example.th2test.views.AddActivity;
import com.example.th2test.views.FirstFragment;
import com.example.th2test.views.SecondFragment;
import com.example.th2test.views.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private MyViewPagerAdapter myViewPagerAdapter;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        myViewPagerAdapter.addFragment(new FirstFragment());
        myViewPagerAdapter.addFragment(new SecondFragment());
        myViewPagerAdapter.addFragment(new ThirdFragment());

        viewPager2.setAdapter(myViewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_first:
                        viewPager2.setCurrentItem(0,false);
                        break;
                    case R.id.menu_second:
                        viewPager2.setCurrentItem(1,false);
                        break;
                    case R.id.menu_third:
                        viewPager2.setCurrentItem(2,false);
                        break;
                }
                return true;
            }
        });

        fab.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        viewPager2 = findViewById(R.id.viewPager2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
    }
}