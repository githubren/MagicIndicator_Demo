package com.example.yfsl.magicindicator_demo.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * 控件帮助类  主要完成一些控件的配置
 * 减少activity的代码量 将控件的配置抽出来
 */
public class MagicIndicatorHelper {
    //定义对象
    private static MagicIndicatorHelper magicIndicatorHelper;

    /**
     * 懒汉式单例模式
     * @return 返回一个帮助类对象  在activit中调用创建一个且只有一个对象
     */
    public static MagicIndicatorHelper getInstance(){
        if (magicIndicatorHelper ==null){
            synchronized (MagicIndicatorHelper.class){
                if (magicIndicatorHelper == null){
                    magicIndicatorHelper = new MagicIndicatorHelper();
                }
            }
        }
        return magicIndicatorHelper;
    }

    /**
     * 配置控件 此类中不再定义参数 直接传入配置中需要的参数
     * @param context
     * @param viewPager
     * @param tablayout
     * @param titles
     */
    public void configMagicIndicator(Context context, final ViewPager viewPager, MagicIndicator tablayout, final String[] titles){
        //设置背景颜色
        tablayout.setBackgroundColor(Color.BLACK);
        //创建CommonNavigator对象 对tab的标题、指示器等进行设置
        CommonNavigator commonNavigator = new CommonNavigator(context);
        //true  标题较少时 平分屏幕宽度  false tab较多时用这个模式
        commonNavigator.setAdjustMode(false);
        //绑定适配器 传数据
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                //三元运算
                return titles == null ? 0 : titles.length;
            }

            /**
             * 对tab标题进行相关设置
             * @param context
             * @param i
             * @return
             */
            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                //创建一个标题样式对象
                final SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                //设置未被选中时标题的颜色
                simplePagerTitleView.setNormalColor(Color.GRAY);
                //设置选中时标题的颜色
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                //设置标题字体大小
                simplePagerTitleView.setTextSize(18);
                //设置标题文本
                simplePagerTitleView.setText(titles[i]);
                //viewpager可以左右滑动实现页面切换 再给tab标题设置一个点击监听 实现点击标题切换页面
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //将点击的标题对应的页面设置为当前展示的页面
                        viewPager.setCurrentItem(i);
                    }
                });
                return simplePagerTitleView;
            }

            /**
             * 设置指示器
             * @param context
             * @return
             */
            @Override
            public IPagerIndicator getIndicator(Context context) {
                //创建一个直线型指示器
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                //设置指示器模型
                // MODE_MATCH_EDGE:相当于布局文件中的match parent 指示器的宽度充满整个分配给他的空间
                // MODE_WRAP_CONTENT:指示器的宽度和标题文字的宽度一致
                // MODE_EXACTLY:根据自己设定的宽度值呈现 相当于wrap content
                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //设置指示器颜色
                linePagerIndicator.setColors(Color.WHITE);
                //对指示器的形状进行配置
                linePagerIndicator.setYOffset(UIUtil.dip2px(context,2.0f));//指示器相对于底部的偏移量
                linePagerIndicator.setLineHeight(UIUtil.dip2px(context,2));//指示器高度
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context,30));//指示器宽度
                linePagerIndicator.setRoundRadius(UIUtil.dip2px(context,2));//圆角

                //设置开始插值器  传入一个加速插值器对象
                linePagerIndicator.setStartInterpolator(new AccelerateInterpolator());
                //设置结束插值器  传入一个减速插值器对象
                linePagerIndicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                return linePagerIndicator;
            }
        });
        //MagicIndicator控件绑定设置好的CommonNavigator
        tablayout.setNavigator(commonNavigator);
        //绑定MagicIndicator和ViewPager（这个控件中直接用ViewPagerHelper这个类调用bind（）方法进行绑定）
        ViewPagerHelper.bind(tablayout,viewPager);
    }
}
