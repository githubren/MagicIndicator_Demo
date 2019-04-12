package com.example.yfsl.magicindicator_demo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yfsl.magicindicator_demo.bean.FragmentInfo;

import java.util.List;

/**
 * 适配器
 * 在控件之间传参
 */
public class MyAdapter extends FragmentPagerAdapter {
    List<FragmentInfo> fragments;
    String[] titles;

    public MyAdapter(FragmentManager fm, List<FragmentInfo> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i).getFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    /**
     * 必须重写此方法 设置的标题文本才得以显示
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
