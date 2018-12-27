package com.w.proandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.w.proandroid.common.utils.EnumDigest;
import com.w.proandroid.ui.BaseActivity;
import com.w.proandroid.ui.home.HomeFragment;
import com.w.proandroid.ui.main.MainBottonView;

public class MainActivity extends BaseActivity {

    public enum MainMenu {
        HomePage,
        Category,
        ShoppigCart,
        MinePage;

        public static EnumDigest<MainMenu, Integer> DIGEST = new EnumDigest<MainMenu, Integer>(MainMenu.values(), null) {
            @Override
            protected Integer getKey(MainMenu value) {
                return value.ordinal();
            }
        };
    }

    private MainBottonView mBottonView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        setTabSelection(MainMenu.HomePage);
    }

    private void initView() {
        mBottonView = findViewById(R.id.main_bottom);
        mBottonView.setClickTabCallbackListener(new MainBottonView.MainBottomClickTabCallbackListener() {
            @Override
            public void onClickTab(int var1) {
                MainMenu menu = MainMenu.DIGEST.from(var1);
                setTabSelection(menu);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setTabSelection(MainMenu.HomePage);
    }

    public void setTabSelection(MainMenu menu) {
        if (menu == null)
            return;
        mBottonView.refreshUI(menu.ordinal());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        Fragment fragment = findFragmentByMenu(menu);
        if (fragment == null) {
            fragment = mMainManager.get(menu);
            transaction.replace(R.id.main_fragment, fragment, menu.toString());
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        for (MainMenu menu : MainMenu.values()) {
            Fragment fragment = findFragmentByMenu(menu);
            if (fragment != null) {
                transaction.remove(fragment);
            }
        }
    }

    private Fragment findFragmentByMenu(MainMenu menu) {
        return getSupportFragmentManager().findFragmentByTag(menu.toString());
    }

    private static MainMenu mMainMenuOnResume;

    private void handleResumeMenu() {
        if (mMainMenuOnResume != null) {
            setTabSelection(mMainMenuOnResume);
            mMainMenuOnResume = null;
        }
    }

    private MainManager mMainManager = new MainManager();

    private class MainManager {
        private Fragment get(MainMenu menu) {
            Fragment fragment = null;
            switch (menu) {
                case HomePage:
                    HomeFragment homeFragment = new HomeFragment();
                    break;
                case Category:
                    break;
                case ShoppigCart:
                    break;
                case MinePage:
                    break;
            }
            return fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleResumeMenu();
    }

    private void initData() {

    }
}
