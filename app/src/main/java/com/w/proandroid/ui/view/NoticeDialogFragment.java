package com.w.proandroid.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.w.proandroid.R;

public class NoticeDialogFragment extends DialogFragment {

    public static NoticeDialogFragment newInstance(Object title, Object message, Object positibeText, Object negativeText,String tag) {
        NoticeDialogFragment fragment = new NoticeDialogFragment();

        return fragment;
    }

    public NoticeDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.R.style.Theme_Holo_Dialog, R.style.CustomDialog);
    }

    public interface NoticeDialogListener {
        void onDialogPositiveClick(String tag);
        void onDialogNegativeClick(String tag);
    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view getActivity().getLayoutInflater().inflate(R.layout.notice_dialog_layout, null);
        return super.onCreateDialog(savedInstanceState);
    }

    private void dialogDismiss() {
        if (getDialog() != null && getDialog().isShowing()) {
            try {
                dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
