package com.example.stars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QueryMeActivity extends ActionBarActivity {
    private RelativeLayout r1,r2,r3;
    private ImageView ivt1;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    int num;
    private String num3,Simg1,Content1,Star1,SStar1,username,Stime1;
    private List Sid=new ArrayList();
    private List Title=new ArrayList();
    private List Simg=new ArrayList();
    private List Content=new ArrayList();
    private List Star=new ArrayList();
    private List SStar=new ArrayList();
    private List Stime=new ArrayList();


    //将数据封装成数据源
    List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryme);

        DbHelper dbHelper =DbHelper.getInstance(this);
        db=dbHelper.getWritableDatabase();

        //用户名取值
        SharedPreferences sharedPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username=sharedPreferences.getString("login_username", "");
        Cursor cursor = db.query("contents", new String[]{"_id","stop","simg","sword","send","stime","sstar"}, "stop=?", new String[]{username}, null, null, "_id desc");//"_id desc"表示根据id倒叙查表

          int num=0;
            while (cursor.moveToNext()) {
                    Sid.add(cursor.getString(cursor.getColumnIndex("_id")));
                    Title.add(cursor.getString(cursor.getColumnIndex("stop")));
                    Simg.add(cursor.getString(cursor.getColumnIndex("simg")));
                    Content.add(cursor.getString(cursor.getColumnIndex("sword")));
                    Star.add(cursor.getString(cursor.getColumnIndex("send")));
                    Stime.add(cursor.getString(cursor.getColumnIndex("stime")));
                    SStar.add(cursor.getString(cursor.getColumnIndex("sstar")));
                num++;
            }
        //关闭游标
        cursor.close();

        Toast.makeText(QueryMeActivity.this, "已刷新，您共发布了"+ num + "条内容！", Toast.LENGTH_SHORT).show();

        if(num>0){
            //将数据封装成数据源
            for(int i=0;i<num;i++){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("_id",Sid.get(i));
                map.put("title",Title.get(i));
                map.put("simg",Simg.get(i));
                map.put("content",Content.get(i));
                map.put("star",Star.get(i));
                map.put("stime",Stime.get(i));
                map.put("sstar",SStar.get(i));
                list.add(map);
            }
            ListView listview=(ListView) this.findViewById(R.id.listView);
            MyAdapter adapter =new MyAdapter();

            listview.setAdapter(adapter);

        }


        r1=(RelativeLayout)findViewById(R.id.dhl1);
        r2=(RelativeLayout)findViewById(R.id.dhl2);
        r3=(RelativeLayout)findViewById(R.id.dhl3);

        r1.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(QueryMeActivity.this, OneActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r2.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(QueryMeActivity.this, TwoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r3.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(QueryMeActivity.this, ThreeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivt1=(ImageView)findViewById(R.id.ivbt1);
        ivt1.setOnClickListener(new RelativeLayout.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(QueryMeActivity.this, ThreeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    class MyAdapter extends BaseAdapter {
        private int mPosition;
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {

            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    private void setmPosition(int mPosition){
        this.mPosition = mPosition;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder mHolder;
            setmPosition(position);
            if(convertView==null){
                convertView= LayoutInflater.from(QueryMeActivity.this).inflate(R.layout.list_meitem,null);
                mHolder=new ViewHolder();

                mHolder.card_title=(TextView)convertView.findViewById(R.id.cardTitle);
                mHolder.card_Simg=(TextView)convertView.findViewById(R.id.cardSimg);
                mHolder.card_image=(ImageView)convertView.findViewById(R.id.cardImg);
                mHolder.card_image1=(ImageView)convertView.findViewById(R.id.cardImg1);
                mHolder.card_content=(TextView)convertView.findViewById(R.id.cardContent);
                mHolder.card_star=(TextView)convertView.findViewById(R.id.cardStar);
                mHolder.card_sstar=(TextView)convertView.findViewById(R.id.cardSStar);
                mHolder.delete=(TextView)convertView.findViewById(R.id.delete);
                mHolder.update=(TextView)convertView.findViewById(R.id.update);

                convertView.setTag(mHolder);  //将ViewHolder存储在View中
            }else {

                mHolder=(ViewHolder)convertView.getTag();  //重新获得ViewHolder
            }

            mHolder.update.setTag(position);
            mHolder.delete.setTag(position);
            mHolder.card_title.setText(list.get(position).get("title").toString() + " 发布于" + list.get(position).get("stime").toString());
            mHolder.card_Simg.setText(list.get(position).get("simg").toString());
            mHolder.card_content.setText(list.get(position).get("content").toString());
            mHolder.card_star.setText("         " + list.get(position).get("star").toString());
            mHolder.card_sstar.setText(list.get(position).get("sstar").toString());


            //更新

            //用户名取值
            SharedPreferences sharedPreferences=getSharedPreferences("config", MODE_PRIVATE);
            username=sharedPreferences.getString("login_username", "");

            //获取当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            Date date = new Date(System.currentTimeMillis());
            Stime1 = simpleDateFormat.format(date);


            mHolder.update.setOnClickListener(new RelativeLayout.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    int indext =Integer.parseInt(v.getTag().toString());
                    intent.setClass(QueryMeActivity.this, UpdateContentsActivity.class);
                    num3 = list.get(indext).get("_id").toString();
                    Simg1 = list.get(indext).get("simg").toString();
                    Content1 = list.get(indext).get("content").toString();
                    Star1 = list.get(indext).get("star").toString();
                    SStar1 = list.get(indext).get("sstar").toString();
                    intent.putExtra("_id", Long.parseLong(num3));
                    intent.putExtra("stop", username);
                    intent.putExtra("simg", Simg1);
                    intent.putExtra("sword", Content1);
                    intent.putExtra("send", Star1);
                    intent.putExtra("stime", Stime1);
                    intent.putExtra("sstar", SStar1);
                    startActivity(intent);
                }
            });
            //删除
            mHolder.delete.setOnClickListener(new RelativeLayout.OnClickListener() {
                public void onClick(View v) {
                    int indext =Integer.parseInt(v.getTag().toString());
                    db.delete("contents","_id=?", new String[]{list.get(indext).get("_id").toString()});
                    Toast.makeText(QueryMeActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(QueryMeActivity.this, QueryMeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


            //网络获取图片
            String url=list.get(position).get("simg").toString();
            Glide.with(QueryMeActivity.this).load(url).into(mHolder.card_image);


            //星星
            String num1=list.get(position).get("sstar").toString();

            if(num1.equals("1.0")) {
                mHolder.card_image1.setImageResource(R.drawable.s11);
            }else if(num1.equals("2.0")){
                mHolder.card_image1.setImageResource(R.drawable.s12);
            }else if(num1.equals("3.0")){
                mHolder.card_image1.setImageResource(R.drawable.s13);
            }else if(num1.equals("4.0")){
                mHolder.card_image1.setImageResource(R.drawable.s14);
            }else if(num1.equals("5.0")){
                mHolder.card_image1.setImageResource(R.drawable.s15);
            }
            return convertView;

        }

    }

    class ViewHolder{
        TextView card_title;
        TextView card_Simg;
        ImageView card_image;
        ImageView card_image1;
        TextView card_content;
        TextView card_star;
        TextView card_sstar;
        TextView delete;
        TextView update;

    }

}