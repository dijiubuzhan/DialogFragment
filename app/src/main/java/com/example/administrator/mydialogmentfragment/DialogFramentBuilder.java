package com.example.administrator.mydialogmentfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

public class DialogFramentBuilder {

    private Context context;
    int titleGravity = Gravity.START;
    int bottomGravity = Gravity.END;
    String title;
    String message;
    String positiveButtonText;
    String negativeButtonText;
    View contentView;
    boolean cancelable = true, wrapInScrollView;
    private FragmentManager manager; // Required

    DialogInterface.OnKeyListener onKeyListener = null;
    DialogInterface.OnCancelListener onCancelListener = null;
    DialogInterface.OnDismissListener dismissListener = null;
    DialogInterface.OnClickListener onPositiveCallback=null,
            onNegativeCallback=null;

    public DialogFramentBuilder(Context context) {
        this.context = context;
    }


    public DialogFramentBuilder dismissListener(DialogInterface.OnDismissListener listener) {
        this.dismissListener = listener;
        return this;
    }

    /**
     * Set the Dialog message from String
     *
     * @param message
     * @return
     */
    public DialogFramentBuilder content(String message) {
        this.message = message;
        return this;
    }

    public View getContentView() {
        return contentView;
    }

    /**
     * Set the Dialog message from resource
     *
     * @param message
     * @return
     */
    public DialogFramentBuilder content(int message) {
        this.message = (String) context.getText(message);
        return this;
    }

    /**
     * Set the Dialog title from resource
     *
     * @param title
     * @return
     */
    public DialogFramentBuilder title(int title) {
        this.title = (String) context.getText(title);
        return this;
    }

    /**
     * Set the Dialog title from String
     *
     * @param title
     * @return
     */
    public DialogFramentBuilder title(String title) {
        this.title = title;
        return this;
    }

    public DialogFramentBuilder titleGravity(int gravity) {
        this.titleGravity = gravity;
        return this;
    }

    public DialogFramentBuilder buttonsGravity(int gravity) {
        this.bottomGravity = gravity;
        return this;
    }



    /**
     * Set the positive button resource and it's listener
     *
     * @param positiveButtonText
     * @return
     */
    public DialogFramentBuilder positiveText(int positiveButtonText) {
        this.positiveButtonText = (String) context
                .getText(positiveButtonText);
        return this;
    }


    public DialogFramentBuilder cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    /**
     * Set the positive button text and it's listener
     *
     * @param positiveButtonText
     * @return
     */
    public DialogFramentBuilder positiveText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public DialogFramentBuilder negativeText(@StringRes int negativeRes) {
        if (negativeRes == 0) return this;
        return negativeText(this.context.getText(negativeRes));
    }

    public DialogFramentBuilder negativeText(@NonNull CharSequence message) {
        this.negativeButtonText = (String) message;
        return this;
    }


    public DialogFramentBuilder onPositive(DialogInterface.OnClickListener onPositiveCallback) {
        this.onPositiveCallback = onPositiveCallback;
        return this;
    }

    public DialogFramentBuilder onNegative(DialogInterface.OnClickListener onNegativeCallback) {
        this.onNegativeCallback = onNegativeCallback;
        return this;
    }


    public NormalTextDialogFragment show(String tag) {
        NormalTextDialogFragment dialogFragment = NormalTextDialogFragment
                .newInstance(title,message,positiveButtonText,negativeButtonText);
        if (!TextUtils.isEmpty(positiveButtonText)) {
            dialogFragment.setPositiveButton(positiveButtonText,onPositiveCallback);
        }
        if (!TextUtils.isEmpty(negativeButtonText)) {
            dialogFragment.setNegativeButton(negativeButtonText,onNegativeCallback);
        }
        dialogFragment.show(manager, tag);
        return dialogFragment;
    }

    public DialogFramentBuilder setFragmentManager(FragmentManager manager) {
        this.manager = manager;
        return this;
    }

}
