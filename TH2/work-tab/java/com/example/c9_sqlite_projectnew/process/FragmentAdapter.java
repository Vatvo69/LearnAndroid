package com.example.c9_sqlite_projectnew.process;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.c9_sqlite_projectnew.ui.FirstFragment;
import com.example.c9_sqlite_projectnew.ui.SecondFragment;
import com.example.c9_sqlite_projectnew.ui.ThirdFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int numPage;
    public static final int product_result = 0;

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return new FirstFragment();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
