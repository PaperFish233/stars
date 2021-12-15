package com.example.stars;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TwoActivity extends ActionBarActivity {
    private RelativeLayout r1,r2,r3;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    int num;
    private int num1,num2;
    private List Title=new ArrayList();
    private List Simg=new ArrayList();
    private List Content=new ArrayList();
    private List Star=new ArrayList();
    private List SStar=new ArrayList();
    private List Stime=new ArrayList();

    //将数据封装成数据源
    List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();

    //查数据库表总条数
    public long findMaxId(String table) {
        DbHelper dbHelper =DbHelper.getInstance(this);
        db=dbHelper.getWritableDatabase();
        Cursor cursor= db.rawQuery("select count(2) from "+table,null);
        // cursor.getCount();
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        DbHelper dbHelper =DbHelper.getInstance(this);
        db=dbHelper.getWritableDatabase();

            num2= (int) findMaxId("contents");
        if(num2>0) {

            //取随机数
            int min = 1;
            int max = num2;
            Random random = new Random();
            num1 = random.nextInt(max) % (max - min + 1) + min;

            Cursor cursor = db.query("contents", new String[]{"_id", "stop", "simg", "sword", "send", "stime", "sstar"}, "_id=?", new String[]{num1 + ""}, null, null, null);

            int num = 0;
            while (cursor.moveToNext()) {
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

            Toast.makeText(TwoActivity.this, "已随机刷新1条帖子！", Toast.LENGTH_SHORT).show();


            if (num > 0) {
                //将数据封装成数据源
                for (int i = 0; i < num; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", Title.get(i));
                    map.put("simg", Simg.get(i));
                    map.put("content", Content.get(i));
                    map.put("star", Star.get(i));
                    map.put("stime", Stime.get(i));
                    map.put("sstar", SStar.get(i));
                    list.add(map);
                }
                ListView listview = (ListView) this.findViewById(R.id.listView);
                listview.setAdapter(new MyAdapter());

            }
        }

        r1=(RelativeLayout)findViewById(R.id.dhl1);
        r2=(RelativeLayout)findViewById(R.id.dhl2);
        r3=(RelativeLayout)findViewById(R.id.dhl3);

        r1.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TwoActivity.this, OneActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r2.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TwoActivity.this, TwoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        r3.setOnClickListener(new RelativeLayout.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TwoActivity.this, ThreeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class MyAdapter extends BaseAdapter {

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder mHolder;
            if(convertView==null){
                convertView= LayoutInflater.from(TwoActivity.this).inflate(R.layout.list_suijiitem,null);
                mHolder=new ViewHolder();
                mHolder.card_title=(TextView)convertView.findViewById(R.id.cardTitle);
                mHolder.card_Simg=(TextView)convertView.findViewById(R.id.cardSimg);
                mHolder.card_image=(ImageView)convertView.findViewById(R.id.cardImg);
                mHolder.card_image1=(ImageView)convertView.findViewById(R.id.cardImg1);
                mHolder.card_content=(TextView)convertView.findViewById(R.id.cardContent);
                mHolder.card_star=(TextView)convertView.findViewById(R.id.cardStar);
                mHolder.card_sstar=(TextView)convertView.findViewById(R.id.cardSStar);

                convertView.setTag(mHolder);  //将ViewHolder存储在View中
            }else {

                mHolder=(ViewHolder)convertView.getTag();  //重新获得ViewHolder
            }



            mHolder.card_title.setText(list.get(position).get("title").toString() + " 发布于" + list.get(position).get("stime").toString());
            mHolder.card_Simg.setText(list.get(position).get("simg").toString());
            mHolder.card_content.setText(list.get(position).get("content").toString());
            mHolder.card_star.setText("         " + list.get(position).get("star").toString());
            mHolder.card_sstar.setText(list.get(position).get("sstar").toString());


            //网络获取图片
            String url=list.get(position).get("simg").toString();
            Glide.with(TwoActivity.this).load(url).into(mHolder.card_image);


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
    }

}