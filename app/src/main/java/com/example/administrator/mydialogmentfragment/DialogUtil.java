package com.example.administrator.mydialogmentfragment;




import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogUtil {
    private static AlertDialogFragment frag;
    private static DialogUtil dialogUtil;
    public static DialogUtil getDialogInstance() {
        if (null == dialogUtil) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    private DialogUtil() {
        frag=new AlertDialogFragment();
    }
    public void showSingleDialog(AppCompatActivity activity, String title, String msg, String positive,
                                        DialogInterface.OnClickListener listener) {
        frag=createSingleDialog(title, msg, positive, listener);
        if(!frag.isAdded() && !frag.isVisible() && !frag.isRemoving())
        {
            frag.show(activity.getSupportFragmentManager().beginTransaction(),"single");
        }
    }

    public  void showDoubleDialog(AppCompatActivity activity, String title, String msg, String positive,
                                        String negative, DialogInterface.OnClickListener listener) {
        frag=createDoubleDialog(title, msg, positive, negative,listener);
        if(!frag.isAdded() && !frag.isVisible() && !frag.isRemoving())
        {
            frag.show(activity.getSupportFragmentManager().beginTransaction(),"double");
        }
    }


    public  AlertDialogFragment createSingleDialog(String title, String msg, String positive,
                                                    DialogInterface.OnClickListener listener) {
        return AlertDialogFragment.newSingleInstance(title, msg, positive, listener);
    }

    public  AlertDialogFragment createDoubleDialog(String title, String msg, String positive,
                                                    String negative, DialogInterface.OnClickListener listener) {
        return AlertDialogFragment.newDoubleInstance(title, msg, positive, negative, listener);
    }

    public static class AlertDialogFragment extends DialogFragment {

        private static DialogInterface.OnClickListener mListener;

        public static AlertDialogFragment newSingleInstance(String title, String msg, String positive,
                                                            DialogInterface.OnClickListener listener) {
            return newDoubleInstance(title, msg, positive, null, listener);
        }

        public static AlertDialogFragment newDoubleInstance(String title, String msg, String positive,
                                                            String negative, DialogInterface.OnClickListener listener) {
            if (frag==null) {
                new DialogUtil();
            }else if(frag.isVisible()){
                frag.dismiss();
            }
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("msg", msg);
            args.putString("negative", negative);
            args.putString("positive", positive);
            mListener = listener;
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String title = getArguments().getString("title");
            String msg = getArguments().getString("msg");
            String positive = getArguments().getString("positive");
            String negative = getArguments().getString("negative");

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton(positive, mListener);

            if (negative != null) {
                builder.setNegativeButton(negative, mListener);
            }
            return builder.create();
        }
    }


}
