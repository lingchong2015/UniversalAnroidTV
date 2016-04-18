package curry.stephen.universalanroidtv.view;

import android.content.ContentResolver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import curry.stephen.universalanroidtv.R;
import curry.stephen.universalanroidtv.adapter.MainActivityAdapter;
import curry.stephen.universalanroidtv.global.GlobalVariables;
import curry.stephen.universalanroidtv.model.TabDataModel;

/**
 * Created by LingChong on 2016/4/11 0011.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private RadioButton localService;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private View mViews[];
    private int mCurrentIndex = 0;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        generateTestCase();

        initView();

//        setListener();

//        initFragment();
    }

    private void initView() {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        RadioButton radioButtonTest = (RadioButton) findViewById(R.id.radio_button_test);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);

        radioButtonTest.requestFocus();

        if (GlobalVariables.globalTabDataModelList.isEmpty()) {
            return;
        }

//        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        for (TabDataModel tabDataModel : GlobalVariables.globalTabDataModelList) {
            final RadioButton radioButton = (RadioButton) getLayoutInflater().from(this).inflate(R.layout.radio_button_tab, null);
            radioButton.setTag(tabDataModel.getID());
//            radioButton.setLayoutParams(layoutParams);
            radioButton.setFocusable(true);
//            radioButton.requestFocus();

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.requestFocus();
                }
            });

            try {
                Drawable drawableNormal = Drawable.createFromStream(getContentResolver().openInputStream(tabDataModel.getPictureNormal()), null);
                Drawable drawableSelected = Drawable.createFromStream(getContentResolver().openInputStream(tabDataModel.getPictureSelected()), null);

                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[] {android.R.attr.state_focused}, drawableSelected);
                stateListDrawable.addState(new int[] {android.R.attr.state_selected}, drawableSelected);
                stateListDrawable.addState(new int[] {}, drawableNormal);

                radioButton.setBackground(stateListDrawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            radioGroup.addView(radioButton);
        }

        radioGroup.post(new Runnable() {
            @Override
            public void run() {
                radioGroup.getWidth();
            }
        });

//        localService = (RadioButton) findViewById(R.id.main_title_local);
//        localService.setSelected(true);
//        mViews = new View[]{localService};
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

    /**
     * 顶部焦点获取
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
//
//            if (KeyEvent.KEYCODE_DPAD_DOWN == keyCode) {
//                ((BrowserFragment)  mFragmentList.get(0)).setFocus();
//            }
//        }
//
//        if (KeyEvent.KEYCODE_DPAD_DOWN == keyCode) {
//            ((BrowserFragment)  mFragmentList.get(0)).setFocus();
//        }
//
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            return true;
//        }

        return super.onKeyDown(keyCode, event);
    }

    private Uri getDrawableUri(int id) {
        return Uri.parse(String.format("%s://%s/%s/%s", ContentResolver.SCHEME_ANDROID_RESOURCE, getResources().getResourcePackageName(id),
                getResources().getResourceTypeName(id), getResources().getResourceEntryName(id)));
    }

    private void generateTestCase() {
        GlobalVariables.globalTabDataModelList.clear();

        TabDataModel tabDataModel1 = new TabDataModel.Builder()
                .id(0)
                .picureNormal(getDrawableUri(R.drawable.title_local_service))
                .pictureSelected(getDrawableUri(R.drawable.title_local_service_focus))
                .build();

        TabDataModel tabDataModel2 = new TabDataModel.Builder()
                .id(1)
                .picureNormal(getDrawableUri(R.drawable.title_local_service))
                .pictureSelected(getDrawableUri(R.drawable.title_local_service_focus))
                .build();

        GlobalVariables.globalTabDataModelList.add(tabDataModel1);
        GlobalVariables.globalTabDataModelList.add(tabDataModel2);
    }
}
