package com.example.stars;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContentsActivity extends Activity {
    private RatingBar mRatingBar;
    private EditText ed1,ed2,ed3,ed4,ed5,ed6;
    private Button bt1,bt2;
    String stop,simg,sword,send,sstar,stime;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Bundle bundle;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontents);

        ed1=(EditText) findViewById(R.id.ed1);
        ed2=(EditText) findViewById(R.id.ed2);
        ed3=(EditText) findViewById(R.id.ed3);
        ed4=(EditText) findViewById(R.id.ed4);
        ed5=(EditText) findViewById(R.id.ed5);
        ed6=(EditText) findViewById(R.id.ed6);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button) findViewById(R.id.bt2);

        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        ed6.setText(simpleDateFormat.format(date));

        //用户名取值
        SharedPreferences sharedPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username=sharedPreferences.getString("login_username","");
        ed1.setText(username);

        //设置不可编辑
        ed1.setEnabled(false);
        ed6.setEnabled(false);


        bundle=this.getIntent().getExtras();
        if (bundle!=null){
            ed1.setText(bundle.getString("stop"));
            ed2.setText(bundle.getString("simg"));
            ed3.setText(bundle.getString("sword"));
            ed4.setText(bundle.getString("send"));
            ed5.setText(bundle.getString("sstar"));
            ed6.setText(bundle.getString("stime"));
        }



        mRatingBar = (RatingBar)findViewById(R.id.mRatingBar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ed5.setText(String.valueOf(rating));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AddContentsActivity.this, OneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(ed2.getText().toString().equals("")||ed3.getText().toString().equals("")||ed4.getText().toString().equals("")||ed5.getText().toString().equals("")){
                    Toast.makeText(AddContentsActivity.this, "您有为空的选项，请填写完毕！", Toast.LENGTH_SHORT).show();
                }else{
                stop=ed1.getText().toString();
                simg=ed2.getText().toString();
                sword=ed3.getText().toString();
                send=ed4.getText().toString();
                sstar=ed5.getText().toString();
                stime=ed6.getText().toString();

                ContentValues value=new ContentValues();
                value.put("stop",stop);
                value.put("simg",simg);
                value.put("sword",sword);
                value.put("send",send);
                value.put("sstar",sstar);
                value.put("stime",stime);
                value.put("sheart","2");

                DbHelper dbHelper=DbHelper.getInstance(AddContentsActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                long status=0;
                 status=db.insert("contents",null,value);

                db.close();
                if(status!=-1){
                    Toast.makeText(AddContentsActivity.this, "发布成功！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(AddContentsActivity.this, OneActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(AddContentsActivity.this,"发布失败！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(AddContentsActivity.this, OneActivity.class);
                    startActivity(intent);
                    finish();
                }
                }
            }

        });
    }

}

