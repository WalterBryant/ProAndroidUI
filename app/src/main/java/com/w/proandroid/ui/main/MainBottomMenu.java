package com.w.proandroid.ui.main;

import android.view.ViewGroup;
import android.widget.TextView;

import com.w.proandroid.MainActivity;
import com.w.proandroid.MainApplication;
import com.w.proandroid.R;
import com.w.proandroid.ui.view.PortionImageView;

/**
 * 主底部菜单 view 组件 id
 */
public class MainBottomMenu {

    public MainBottomMenu() {

    }

    public final static int ITEM_COUNT = MainActivity.MainMenu.values().length;
    public final PortionImageView[] images = new PortionImageView[ITEM_COUNT];
    public final TextView[] texts = new TextView[ITEM_COUNT];
    public final ViewGroup[] containers = new ViewGroup[ITEM_COUNT];

    public void setSelectionMenu(int index) {
        texts[index].setTextColor(MainApplication.getApp().getResources().getColor(MainBottomMenu.text_selected_color[index]));
        images[index].setImageResource(MainBottomMenu.image_selected[index]);
    }

    public void resetMenu() {
        for (int i = 0; i < MainBottomMenu.ITEM_COUNT; i++) {
            texts[i].setTextColor(MainApplication.getApp().getResources().getColor(MainBottomMenu.texts_unselected_color));
            images[i].setImageResource(MainBottomMenu.image_unselected[i]);
        }
    }

    public final static int[] images_id = new int[]{
            R.id.main_homepage_img,
            R.id.main_categry_img,
            R.id.main_cart_img,
            R.id.main_my_img,
    };

    public final static int[] texts_id = new int[]{
            R.id.main_homepage_text,
            R.id.main_categry_text,
            R.id.main_cart_text,
            R.id.main_my_text,
    };

    public final static int[] linears_id = new int[]{
            R.id.main_homepage_linear,
            R.id.main_categry_linear,
            R.id.main_cart_linear,
            R.id.main_my_linear,
    };

    public final static int[] image_selected = new int[]{
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round
    };

    public final static int[] image_unselected = new int[]{
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    public final static int[] text_selected_color = new int[]{
            R.color.colorAccent,
            R.color.colorAccent,
            R.color.colorAccent,
            R.color.colorAccent
    };

    public final static int texts_unselected_color = R.color.common_line_gray;
}
