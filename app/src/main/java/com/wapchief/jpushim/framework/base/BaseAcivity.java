package com.wapchief.jpushim.framework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wapchief.jpushim.R;

/**
 * Created by Wu on 2017/4/13 0013 上午 10:58.
 * 描述：
 */
public abstract class BaseAcivity extends FragmentActivity implements View.OnClickListener{

    //记录处于前台的Activity
    private static BaseAcivity mForegroundActivity = null;

    //记录所有活动的Activity
    private static final List<BaseAcivity> mActivities = new LinkedList<BaseAcivity>();

    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootContentView());
        mContext = BaseAcivity.this;
        initView();
        initData();
    }

    @Override
    public void onClick(View view) {
    }

    /**
     * 返回界面布局
     */
    protected abstract int setContentView();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 处理点击事件
     */
    protected void processClick(View v) {
    }

    /**
     * 获取根View
     */
    public View rootContentView() {


        if (setContentView() != 0) {
            return View.inflate(this, setContentView(), null);
        } else {
            return View.inflate(this, R.layout.page_default, null);
        }
    }

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

    /**
     * 返回错误
     */
    protected void showPhoneErr() {
       new SVProgressHUD(this).showErrorWithStatus("格式错误",
               SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    /***
     * 关软件盘
     * @param activity
     */
    protected void dismissKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.hideSoftInputFromWindow(activity
                            .getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示软键盘
     * @param v
     */
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    /**
     * 关闭所有activity
     */
    public static void finishAll() {
        List<BaseAcivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseAcivity>(mActivities);
        }
        for (BaseAcivity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 短时间Toast提示
     * @param activity
     * @param s
     */
    public void showToast(Activity activity,String s){
        Toast toast=Toast.makeText(activity,s,Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 长时间Toast提示
     * @param activity
     * @param s
     */
    public void showLongToast(Activity activity,String s){
        Toast toast=Toast.makeText(activity,s,Toast.LENGTH_LONG);
        toast.show();
    }
}
