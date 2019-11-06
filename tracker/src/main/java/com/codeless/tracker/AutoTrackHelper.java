package com.codeless.tracker;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codeless.tracker.utils.PathUtil;
import com.codeless.tracker.utils.StringEncrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:xishuang
 * Date:2018-12-06
 * Des:全埋点的入口类
 */
public class AutoTrackHelper {


    /**
     * 对应的埋点方法{@link android.support.v4.app.Fragment#onViewCreated(View, Bundle)}
     *
     * @param object   Fragment对象
     * @param rootView 对应View对象
     * @param bundle   对应Bundle对象
     */
    public static void onFragmentViewCreated(Object object, View rootView, Bundle bundle) {

    }

    /**
     * 对应的埋点方法{@link android.support.v4.app.Fragment#onResume()}
     *
     * @param object Fragment对象
     */
    public static void trackFragmentResume(Object object) {

    }

    /**
     * 对应的埋点方法{@link android.support.v4.app.Fragment#setUserVisibleHint(boolean)}
     *
     * @param object          Fragment对象
     * @param isVisibleToUser 对应boolean值
     */
    public static void trackFragmentSetUserVisibleHint(Object object, boolean isVisibleToUser) {

    }

    /**
     * 对应的埋点方法{@link android.support.v4.app.Fragment#onHiddenChanged(boolean)}
     *
     * @param object Fragment对象
     * @param hidden 对应boolean值
     */
    public static void trackOnHiddenChanged(Object object, boolean hidden) {

    }


    /**
     * Fragment日志处理
     */
    private static void trackFragmentAppViewScreen(android.support.v4.app.Fragment fragment) {

    }

    /**
     * 对应实现接口的埋点方法{@link ExpandableListView.OnGroupClickListener#onGroupClick(ExpandableListView, View, int, long)}
     *
     * @param expandableListView 参数对应ExpandableListView
     * @param view               参数对应View
     * @param groupPosition      参数对应int
     */
    public static void trackExpandableListViewOnGroupClick(ExpandableListView expandableListView, View view,
                                                           int groupPosition) {

    }


    /**
     * 对应实现接口的埋点方法{@link ExpandableListView.OnChildClickListener#onChildClick(ExpandableListView, View, int, int, long)}
     *
     * @param expandableListView 参数对应ExpandableListView
     * @param view               参数对应View
     * @param groupPosition      参数对应groupPosition
     * @param childPosition      参数对应childPosition
     */
    public static void trackExpandableListViewOnChildClick(ExpandableListView expandableListView, View view,
                                                           int groupPosition, int childPosition) {

    }

    /**
     * 对应实现接口的埋点方法{@link AdapterView.OnItemSelectedListener#onItemSelected(AdapterView, View, int, long)}
     * 和{@link AdapterView.OnItemClickListener#onItemClick(AdapterView, View, int, long)}
     *
     * @param adapterView 参数对应AdapterView
     * @param view        参数对应View
     * @param position    参数对应int
     */
    public static void trackListView(AdapterView<?> adapterView, View view, int position) {

    }


    /**
     * 对应实现接口的埋点方法{@link android.widget.TabHost.OnTabChangeListener#onTabChanged(String)}
     *
     * @param tabName 参数对应String
     */
    public static void trackTabHost(String tabName) {

    }


    /**
     * 对应实现接口的埋点方法{@link TabLayout.OnTabSelectedListener#onTabSelected(TabLayout.Tab)}
     *
     * @param object this对象
     * @param tab    TabLayout.Tab对象
     */
    public static void trackTabLayoutSelected(Object object, Object tab) {

    }


    /**
     * 对应实现接口的埋点方法{@link android.support.design.widget.NavigationView.OnNavigationItemSelectedListener#onNavigationItemSelected(MenuItem)}
     *
     * @param object   this对象
     * @param menuItem 参数对应MenuItem
     */
    public static void trackMenuItem(Object object, MenuItem menuItem) {

    }


    /**
     * 对应实现接口的埋点方法{@link RadioGroup.OnCheckedChangeListener#onCheckedChanged(RadioGroup, int)}
     *
     * @param view      RadioGroup对象
     * @param checkedId 参数对应int
     */
    public static void trackRadioGroup(RadioGroup view, int checkedId) {

    }


    /**
     * 对应实现接口的埋点方法{@link DialogInterface.OnClickListener#onClick(DialogInterface, int)}
     *
     * @param dialogInterface DialogInterface对象
     * @param whichButton     参数对应int
     */
    public static void trackDialog(DialogInterface dialogInterface, int whichButton) {

    }

    /**
     * 监听 void onDrawerOpened(View)方法
     *
     * @param view 方法中的view参数
     */
    public static void trackDrawerOpened(View view) {

    }

    /**
     * 监听{@link View.OnClickListener#onClick(View)}方法
     *
     * @param view
     */
    public static void trackViewOnClick(View view) {
        boolean hasBusiness = false;
        // 解析dataPath
        Context context = view.getContext();
        if (context instanceof Activity) {
            String pageName = context.getClass().getSimpleName();
            String currViewPath = PathUtil.getViewPath(view);
            String eventId = StringEncrypt.Encrypt(pageName + currViewPath, StringEncrypt.DEFAULT);
            Map<String, Object> configureMap = Tracker.instance(context).getConfigureMap();
            if (null != configureMap) {
                JSONArray nodesArr = (JSONArray) configureMap.get(pageName);
                if (null != nodesArr && nodesArr.size() > 0) {
                    for (int i = 0; i < nodesArr.size(); i++) {
                        JSONObject nodeObj = nodesArr.getJSONObject(i);
                        String viewPath = nodeObj.getString(ConfigConstants.VIEWPATH);
                        String dataPath = nodeObj.getString(ConfigConstants.DATAPATH);
                        if (currViewPath.equals(viewPath) || PathUtil.match(currViewPath, viewPath)) {
                            // 按照路径dataPath搜集数据
                            Object businessData = PathUtil.getDataObj(view, dataPath);
                            Map<String, Object> attributes = new HashMap<>();
                            attributes.put(ConfigConstants.PAGENAME, pageName);
                            attributes.put(ConfigConstants.VIEWPATH, currViewPath);
                            JSONArray subPaths = nodeObj.getJSONArray(ConfigConstants.VIEWPATHSUB);
                            if (null == subPaths || subPaths.size() == 0) {
                                attributes.put(ConfigConstants.BUSINESSDATA, businessData);
                            } else {
                                for (int j = 0; j < subPaths.size(); j++) {
                                    String subPath = subPaths.getString(j);
                                    Object obj = PathUtil.getDataObj(businessData, subPath);
                                    attributes.put(subPath, obj);
                                }
                            }
                            Tracker.instance(context).trackEvent(eventId, attributes);
                            hasBusiness = true;
                            break;
                        }
                    }
                }
            }
            if (!hasBusiness) {
                Tracker.instance(context).trackEvent(eventId, null);
            }
        }
    }

}
