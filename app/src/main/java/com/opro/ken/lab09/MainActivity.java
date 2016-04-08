package com.opro.ken.lab09;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import layout.User_Dialog;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener {
    private TextView m_tv_message;
    private int mChoise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        m_tv_message = (TextView) findViewById(R.id.tv);
    }

    public void AlertDialog_OK(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("我知道", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("我知道");
    }

    public void AlertDialog_Yes_No(View view) {
        AlertDialog_Yes_NoListener listener = new AlertDialog_Yes_NoListener();
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝", listener)
                .setNegativeButton("狗腿", listener)
                .show();
    }

    public void AlertDialog_Yes_No_Cancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝讚美", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("謝謝讚美");
                    }
                })
                .setNegativeButton("太狗腿了吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("太狗腿了吧");
                    }
                })
                .setNeutralButton("不知該說甚麼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("不知該說甚麼");
                    }
                })
                .show();

    }

    public void AlertDialog_Item(View view) {
        final String[] respone = getResources().getStringArray(R.array.respone);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("你好帥")
                .setItems(respone, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(respone[which]);
                    }
                })
                .show();
    }

    public void AlertDialog_MuiltiChoise(View view) {
        final String[] respone = getResources().getStringArray(R.array.respone);
        final boolean[] select = new boolean[respone.length];
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setMultiChoiceItems(respone, select,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                select[which] = isChecked;
                            }
                        }
                )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < select.length; i++) {
                            if (select[i]) {
                                result.append(respone[i]).append("\n");
                            }
                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    public void AlertDialog_SingleChoise(View view) {
        final String[] respone = getResources().getStringArray(R.array.respone);
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(respone, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mChoise = which;
                            }
                        }
                )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(respone[mChoise]);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }



    public void User_Dialog(View view){
        DialogFragment dialog = new User_Dialog();
        dialog.show(getSupportFragmentManager(),"User　Dialog");

    }
    public class AlertDialog_Yes_NoListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("謝謝");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }
        }
    }
}