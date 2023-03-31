package com.codeless.tracker;

import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2021年12月20日
 * 时间：3:30 下午
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class AutoTrackHelper {

    public static void trackViewOnClick(View view) {
        Log.d("TestKotlin", String.format("------->>>>%s", view.getContext().getResources().getResourceEntryName(view.getId())));
    }

    public static void trackRadioGroup(RadioGroup view, int checkedId) {
        Log.d("TestKotlin", String.format("------->>>>%s", view.getContext().getResources().getResourceEntryName(checkedId)));
    }

    public static void trackTest() {
        Log.i("TestKotlin", "trackTest:------>>>> ");
    }

    /**
     * 对应实现接口的埋点方法{@link TabLayout.OnTabSelectedListener#onTabSelected(TabLayout.Tab)}
     *
     * @param object this对象
     * @param tab    TabLayout.Tab对象
     */
    public static void trackTabLayoutSelected(Object object, Object tab) {
        Log.i("TestKotlin", "trackTabLayoutSelected: ");
    }
}
