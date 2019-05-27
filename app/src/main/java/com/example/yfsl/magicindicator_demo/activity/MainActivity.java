package com.example.yfsl.magicindicator_demo.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yfsl.magicindicator_demo.helper.MagicIndicatorHelper;
import com.example.yfsl.magicindicator_demo.R;
import com.example.yfsl.magicindicator_demo.adapter.MyAdapter;
import com.example.yfsl.magicindicator_demo.bean.FragmentInfo;
import com.example.yfsl.magicindicator_demo.fragment.FifthFragment;
import com.example.yfsl.magicindicator_demo.fragment.FirstFragment;
import com.example.yfsl.magicindicator_demo.fragment.FourthFragment;
import com.example.yfsl.magicindicator_demo.fragment.SecondFragment;
import com.example.yfsl.magicindicator_demo.fragment.SixthFragment;
import com.example.yfsl.magicindicator_demo.fragment.ThirdFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MagicIndicator tablayout;//实现顶部tab的控件
    private ViewPager viewPager;//tab下方的展示fragment容器控件
    private MyAdapter myAdapter;//适配器 将tab、标题和viewpa联系起来
    private List<FragmentInfo> fragments;//存放fragment的集合
    private String[] titles;//tab的标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载页面布局
        setContentView(R.layout.activity_main);

        //获得标题
        getTitles();
        //设置fragment
        setFragments();
        //初始化viewpager
        initViewPager();
        //初始化tab控件
        initMagicIndicator();
    }

    /**
     * 设置fragment
     * 将fragment加到集合中去
     * 集合List中的元素是FragmentInfo这个实体类类型的 所以集合添加的元素是实体类对象
     * 创建实体类对象的时候传入要添加的fragment（实体类的构造方法决定其参数）
     */
    private void setFragments() {
        fragments = new ArrayList<>();
        for (String title : titles){
            fragments.add(new FragmentInfo(new FirstFragment()));
        }
//        fragments.add(new FragmentInfo(new FirstFragment()));
//        fragments.add(new FragmentInfo(new SecondFragment()));
//        fragments.add(new FragmentInfo(new ThirdFragment()));
//        fragments.add(new FragmentInfo(new FourthFragment()));
//        fragments.add(new FragmentInfo(new FifthFragment()));
//        fragments.add(new FragmentInfo(new SixthFragment()));
    }

    /**
     * 拿到标题数据
     * 因为一个标题对应一个fragment 所以实体类中没有定义title  将title单独拿出来
     */
    private void getTitles() {
        titles = getResources().getStringArray(R.array.tab_title);
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        //找到viewpager控件
        viewPager = findViewById(R.id.viewPager);
        //创建adapter对象 根据其构造方法传参 需要的参数具体定义
        myAdapter = new MyAdapter(getSupportFragmentManager(),fragments,titles);
        //viewpager绑定adapter
        viewPager.setAdapter(myAdapter);
    }

    /**
     * 初始化MagicIndicator控件
     */
    private void initMagicIndicator() {
        //找到控件
        tablayout = findViewById(R.id.tablayout);
        //配置控件
        MagicIndicatorHelper.getInstance().configMagicIndicator(MainActivity.this,viewPager,tablayout,titles);
    }
}
