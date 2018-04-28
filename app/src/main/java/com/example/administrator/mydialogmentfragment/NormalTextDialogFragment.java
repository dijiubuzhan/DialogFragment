package com.example.administrator.mydialogmentfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

public class NormalTextDialogFragment extends DialogFragment {
    private static final String DIALOG_TITLE_KEY ="dialog_title" ;
    private static final String DIALOG_MSG_KEY ="dialog_msg" ;
    private static final String DIALOG_OK_KEY ="dialog_ok";
    private static final String DIALOG_CANCEL_KEY ="dialog_cancel";

    private DialogInterface.OnClickListener
            positiveButtonClickListener,
            negativeButtonClickListener;
    private String positiveButtonText;
    private String negativeButtonText;
    private String title;
    private String msg;
    /**
     * 创建Dialog时调用
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null ) {
            if (args.containsKey(DIALOG_TITLE_KEY)) {
                title = args.getString(DIALOG_TITLE_KEY);
            }
            if (args.containsKey(DIALOG_MSG_KEY)) {
                msg=args.getString(DIALOG_MSG_KEY);
            }
            if (args.containsKey(DIALOG_OK_KEY)) {
                positiveButtonText=args.getString(DIALOG_OK_KEY);
            }
            if (args.containsKey(DIALOG_CANCEL_KEY)) {
                negativeButtonText=args.getString(DIALOG_CANCEL_KEY);
            }
        }


        // 创建构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置参数
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        if (!TextUtils.isEmpty(msg)) {
            builder.setMessage(msg);
        }

        if (!TextUtils.isEmpty(positiveButtonText)) {
            builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (positiveButtonClickListener!=null) {
                        positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }else {
                        dialog.dismiss();
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(negativeButtonText)) {
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (negativeButtonClickListener!=null) {
                        negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }else {
                        dialog.dismiss();
                    }
                }
            });
        }
        // 创建对话框并返回.
        return builder.create();
    }


    /**
     * Set the positive button text and it's listener
     * @param positiveButtonText
     * @param listener
     * @return
     */
    public void setPositiveButton(String positiveButtonText,
                                     DialogInterface.OnClickListener listener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonClickListener = listener;
    }

    /**
     * Set the negative button text and it's listener
     * @param negativeButtonText
     * @param listener
     * @return
     */
    public void setNegativeButton(String negativeButtonText,
                                     DialogInterface.OnClickListener listener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonClickListener = listener;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public static NormalTextDialogFragment newInstance(String title, String msg, String ok,
                                                       String cancel) {
        final NormalTextDialogFragment frag = new NormalTextDialogFragment();
        Bundle args = new Bundle();
        args.putString(DIALOG_TITLE_KEY, title);
        args.putString(DIALOG_MSG_KEY, msg);
        args.putString(DIALOG_OK_KEY, ok);
        args.putString(DIALOG_CANCEL_KEY, cancel);

        frag.setArguments(args);
        return frag;
    }
}