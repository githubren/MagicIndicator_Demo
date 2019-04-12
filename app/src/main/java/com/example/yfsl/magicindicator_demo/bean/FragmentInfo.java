package com.example.yfsl.magicindicator_demo.bean;

import android.support.v4.app.Fragment;

public class FragmentInfo {
    Fragment fragment;

    public FragmentInfo(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public String toString() {
        return "FragmentInfo{" +
                "fragment=" + fragment +
                '}';
    }
}
