package com.mobile.m_flix.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.mobile.m_flix.fragment.FragmentFavoriteMovie;
import com.mobile.m_flix.fragment.FragmentFavoriteTv;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final Fragment[] tabFragments;

    public ViewPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabFragments = new Fragment[]{
                new FragmentFavoriteMovie(),
                new FragmentFavoriteTv()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabFragments[position];
    }

    @Override
    public int getCount() {
        return tabFragments.length;
    }
}