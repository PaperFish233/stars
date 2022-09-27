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

public class UpdateContentsActivity extends Activity {
    private RatingBar mRatingBar;
    private EditText ed1,ed2,ed3,ed4,ed5,ed6;
    private Button bt1,bt2;
    String stop,simg,sword,send,sstar,stime;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Intent bundle;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatecontents);

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


        bundle=getIntent();
        if (bundle!=null){
            ed1.setText(bundle.getStringExtra("stop"));
            ed2.setText(bundle.getStringExtra("simg"));
            ed3.setText(bundle.getStringExtra("sword"));
            ed4.setText(bundle.getStringExtra("send"));
            ed5.setText(bundle.getStringExtra("sstar"));
            ed6.setText(bundle.getStringExtra("stime"));
        }



        mRatingBar = (RatingBar)findViewById(R.id.mRatingBar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ed5.setText(String.valueOf(rating));
            }
        });

        if(ed5.getText().toString().equals("1.0")){
            mRatingBar.setRating(1);
        }else if(ed5.getText().toString().equals("2.0")){
            mRatingBar.setRating(2);
        }else if(ed5.getText().toString().equals("3.0")){
            mRatingBar.setRating(3);
        }else if(ed5.getText().toString().equals("4.0")){
            mRatingBar.setRating(4);
        }else if(ed5.getText().toString().equals("5.0")){
            mRatingBar.setRating(5);
        }

        bt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(UpdateContentsActivity.this, QueryMeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(ed2.getText().toString().equals("")||ed3.getText().toString().equals("")||ed4.getText().toString().equals("")||ed5.getText().toString().equals("")){
                    Toast.makeText(UpdateContentsActivity.this, "您有为空的选项，请填写完毕！", Toast.LENGTH_SHORT).show();
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

                DbHelper dbHelper=DbHelper.getInstance(UpdateContentsActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                long status=0;

                status=db.update("contents",value,"_id=?",new String[]{getIntent().getLongExtra("_id", 0)+""});

                db.close();
                if(status!=-1){
                    Toast.makeText(UpdateContentsActivity.this, "更新成功！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(UpdateContentsActivity.this, QueryMeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(UpdateContentsActivity.this,"更新失败！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(UpdateContentsActivity.this, QueryMeActivity.class);
                    startActivity(intent);
                    finish();
                }
                }
            }

        });
    }

}

