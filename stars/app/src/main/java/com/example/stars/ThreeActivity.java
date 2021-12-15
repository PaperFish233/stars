package com.example.stars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ThreeActivity extends ActionBarActivity {
    private Button bt1;
    private TextView username1;
    private RelativeLayout r1, r2, r3;
    private GridView gv11;
    private List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        bt1=(Button)findViewById(R.id.bt1);
        username1=(TextView)findViewById(R.id.name1);

       //用户名取值
        SharedPreferences sharedPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username=sharedPreferences.getString("login_username","");
        username1.setText(username);

        bt1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDialog(1);
            }
        });

        //九宫格
        gv11=(GridView)findViewById(R.id.gv1);
        int[] picIDs={R.drawable.s1,R.drawable.x2};
        int[] bookIDs={R.string.a,R.string.b};
        int rowCnt=picIDs.length;
        list=new ArrayList<Map<String,Object>>();
        for (int i=0;i<rowCnt;i++){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("picCol",picIDs[i]);
            map.put("bookCol",this.getResources().getString(bookIDs[i]));
            list.add(map);}
        SimpleAdapter ada=new SimpleAdapter(this,list,R.layout.gridxml,new String[]{"picCol","bookCol"},new int[]{R.id.imageview1,R.id.textview1});
        gv11.setAdapter(ada);

        //九宫格点击监听器
        gv11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(arg2 == 0){
                    Intent intent = new Intent();
                    intent.setClass(ThreeActivity.this, QueryMeActivity.class);
                    startActivity(intent);
                }else if (arg2 == 1) {
                    Intent intent = new Intent();
                    intent.setClass(ThreeActivity.this, HeartActivity.class);
                    startActivity(intent);
                }
            }
        });

        r1 = (RelativeLayout) findViewById(R.id.dhl1);
        r2 = (RelativeLayout) findViewById(R.id.dhl2);
        r3 = (RelativeLayout) findViewById(R.id.dhl3);

        r1.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ThreeActivity.this, OneActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r2.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ThreeActivity.this, TwoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r3.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ThreeActivity.this, ThreeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    //信息框方法
    protected Dialog onCreateDialog(int id) {
        Dialog aDialog = null;
        switch (id) {
            case 1:
                aDialog = new AlertDialog.Builder(this)
                        .setTitle("Tips：")
                        .setMessage("确定要登出当前账号？")
                        .setIcon(R.drawable.tips)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent();
                                intent.setClass(ThreeActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create();
        }
        return aDialog;
    }
}
