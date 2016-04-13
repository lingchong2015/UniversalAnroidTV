package curry.stephen.universalanroidtv.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import curry.stephen.universalanroidtv.R;
import curry.stephen.universalanroidtv.adapter.MainActivityAdapter;

/**
 * Created by LingChong on 2016/4/11 0011.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private RadioButton localService;
    private RadioButton setting;
    private RadioButton app;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private View mViews[];
    private int mCurrentIndex = 0;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        setListener();
        initFragment();
    }

    private void initView() {
        mViewPager = (ViewPager) this.findViewById(R.id.main_viewpager);
        localService = (RadioButton) findViewById(R.id.main_title_local);
        localService.setSelected(true);
        mViews = new View[]{localService};
    }

    private void setListener() {
//        localService.setOnClickListener(this);
//
//        localService.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    mViewPager.setCurrentItem(0);
//                }
//            }
//        });
//        setting.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    mViewPager.setCurrentItem(1);
//                }
//            }
//        });
//        app.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    mViewPager.setCurrentItem(2);
//                }
//            }
//        });
    }

    // 初始化Fragment
    private void initFragment() {
        mFragmentList.clear();

        BrowserFragment browserFragment = new BrowserFragment();

        mFragmentList.add(browserFragment);

        mViewPager.setAdapter(new MainActivityAdapter(getSupportFragmentManager(), mFragmentList));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                switch (position) {
                    case 0:
                        localService.setSelected(true);
                        break;
                }
            }
        });
        mViewPager.setCurrentItem(0);

//        mFragmentList.clear();//清空

//        LocalServiceFragment interactTV = new LocalServiceFragment();//本地服务Fragment.
//        SettingFragment setting = new SettingFragment();
//        AppFragment app = new AppFragment();
//
//        mFragmentList.add(interactTV);
//        mFragmentList.add(setting);
//        mFragmentList.add(app);
//
//        MainActivityAdapter mAdapter = new MainActivityAdapter(getSupportFragmentManager(), mFragmentList);
//        mViewPager.setAdapter(mAdapter);
//        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                mViewPager.setCurrentItem(position);
//                switch (position) {
//                    case 0:
//                        localService.setSelected(true);
//                        MainActivity.this.setting.setSelected(false);
//                        MainActivity.this.app.setSelected(false);
//                        break;
//                    case 1:
//                        localService.setSelected(false);
//                        MainActivity.this.setting.setSelected(true);
//                        MainActivity.this.app.setSelected(false);
//                        break;
//                    case 2:
//                        localService.setSelected(false);
//                        MainActivity.this.setting.setSelected(false);
//                        MainActivity.this.app.setSelected(true);
//                        break;
//                }
//            }
//        });
//        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.main_title_local:
//                mViewPager.setCurrentItem(0);
//                break;
//            case R.id.main_title_setting:
//                mViewPager.setCurrentItem(1);
//                break;
//            case R.id.main_title_app:
//                mViewPager.setCurrentItem(2);
//                break;
//        }
    }

//    /**
//     * 顶部焦点获取
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        boolean focusFlag = false;
//        for (View v : mViews) {
//            if (v.isFocused()) {
//                focusFlag = true;
//            }
//        }
//        if (focusFlag) {
//            if (KeyEvent.KEYCODE_DPAD_LEFT == keyCode) {
//                if (mCurrentIndex > 0) {
//                    mViews[--mCurrentIndex].requestFocus();
//                }
//                return true;
//            } else if (KeyEvent.KEYCODE_DPAD_RIGHT == keyCode) {
//                if (mCurrentIndex < 2) {
//                    mViews[++mCurrentIndex].requestFocus();
//                }
//                return true;
//            }
//        }
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            Log.i(TAG, "KEYCODE_BACK is called.");
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}
