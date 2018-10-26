package com.w.proandroid.ui.fragmentsinfragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.w.proandroid.R;
import com.w.proandroid.ui.fragmentsinfragment.adapter.FragmentAdapter;
import com.w.proandroid.ui.fragmentsinfragment.viewpager.NoScrollViewPager;
import com.w.proandroid.utils.DisplayMetricsTools;
import com.w.proandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class TabManagerFragment extends Fragment {

    private NoScrollViewPager mOrderStatusViewpage;
    private ImageView mViewPageIndicator;
    private View view;

    private final int mTagLineWidth = Utils.mWidthPixels / 4;
    private final int[] mRadioButtons = {
            R.id.allOrder_tab, R.id.pendingPaymentOrder_tab, R.id.pendingReceivingOrder_tab, R.id.rderFollowUp_tab,
    };
    private List<Fragment> fragments = new ArrayList<>();

    public enum OrderStatusCode {
        ALL_ORDER("0"),
        PENDING_PAYMENT_ORDER("1"),
        PENDING_RECEIVING_ORDER("2"),
        FOLLOW_UP_SERVICE_ORDER("3");
        public final String value;

        private OrderStatusCode(String aValue) {
            value = aValue;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_manager, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        RadioGroup orderStatusTabGroup = view.findViewById(R.id.orderStatus_tabGroup);
        mOrderStatusViewpage = view.findViewById(R.id.myOrder_viewpage);
        mViewPageIndicator = view.findViewById(R.id.order_viewpage_indicator);
        orderStatusTabGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    private void initData() {
        fragments.add(OrderHistoryFragment.getIntance(OrderStatusCode.ALL_ORDER.value));
        fragments.add(OrderHistoryFragment.getIntance(OrderStatusCode.PENDING_PAYMENT_ORDER.value));
        fragments.add(OrderHistoryFragment.getIntance(OrderStatusCode.PENDING_RECEIVING_ORDER.value));
        fragments.add(OrderHistoryFragment.getIntance(OrderStatusCode.FOLLOW_UP_SERVICE_ORDER.value));

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        mOrderStatusViewpage.setAdapter(fragmentAdapter);
        mOrderStatusViewpage.addOnPageChangeListener(mOnPageChangeListener);
        setIndicatorImgBitmap();
        initFragment(getActivity().getIntent().getIntExtra("tabIndex", -1));//可以从我的页面 直接跳转到相对应订单状态的列表
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //new 一个矩阵
            Matrix matrix = new Matrix();
            //设置激活条件的最终位置
            float dx = mTagLineWidth * position;
            matrix.setTranslate(dx, 0);
            //在滑动过程中，计算出激活条件应该要滑动的距离
            float t = (mTagLineWidth) * positionOffset;
            //使用post追加数值
            matrix.postTranslate(t, 0);
            mViewPageIndicator.setImageMatrix(matrix);
        }

        @Override
        public void onPageSelected(int position) {
            switchRadioStatus(mRadioButtons[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void switchRadioStatus(int resId) {
        for (int button : mRadioButtons) {
            RadioButton radioButton = view.findViewById(button);
            if (resId == button) {
                radioButton.setTextColor(getResources().getColor(R.color.colorAccent));
            } else {
                radioButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }
    }

    RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.allOrder_tab:
                    mOrderStatusViewpage.setCurrentItem(0, true);
                    break;
                case R.id.pendingPaymentOrder_tab:
                    mOrderStatusViewpage.setCurrentItem(1, true);
                    break;
                case R.id.pendingReceivingOrder_tab:
                    mOrderStatusViewpage.setCurrentItem(2, true);
                    break;
                case R.id.rderFollowUp_tab:
                    mOrderStatusViewpage.setCurrentItem(3, true);
                    break;
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
    }

    private void setIndicatorImgBitmap() {
        try {
            int imgWidth = DisplayMetricsTools.dip2px(65);
            int imgHeight = DisplayMetricsTools.dip2px(2);
            Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
            mViewPageIndicator.setImageBitmap(createRoundConerImage(bitmap));
            mViewPageIndicator.setPadding((mTagLineWidth - imgWidth) / 2, 0, (mTagLineWidth - imgWidth) / 2, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap createRoundConerImage(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        Canvas canvas = new Canvas(source);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, 10, 10, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return source;
    }

    private void initFragment(int tabIndex) {
        if (tabIndex != -1) {
            mOrderStatusViewpage.setCurrentItem(tabIndex, true);
            if (tabIndex == 0) {
                mOnPageChangeListener.onPageSelected(0);
            }
        } else {

        }
    }
}
