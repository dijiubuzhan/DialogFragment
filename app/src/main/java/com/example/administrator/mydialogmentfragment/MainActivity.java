package com.example.administrator.mydialogmentfragment;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        goDevelop();
    }

    private void goDevelop() {
    }

    public void show(View view) {
             new DialogFramentBuilder(this)
                .setFragmentManager(getSupportFragmentManager())
                .title("title")
                .content("here is the message")
                .positiveText(getString(android.R.string.ok))
                .onPositive(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"click ok",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .negativeText(getString(android.R.string.cancel))
                .onNegative(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"click cancel",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show("test");
             /*DialogUtil.getDialogInstance().showDoubleDialog(this,"title", "here is the message", getString(android.R.string.ok), getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                 }
             });*/
    }
}
