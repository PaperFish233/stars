package com.example.stars;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText et1,et2;
    private Button bt1,bt2;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        bt1=(Button)findViewById(R.id.bt);
        bt2=(Button)findViewById(R.id.bt2);

        dbHelper = DbHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

        bt1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (et1.getText().toString().equals("")||et2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "您有为空的选项，请填写完毕！", Toast.LENGTH_SHORT).show();
                }else{
                    String username = et1.getText().toString();
                    String password = et2.getText().toString();
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("users", new String[]{"uname", "upassword"}, "uname=?", new String[]{username}, null, null, null, "0,1");
                // 游标移动进行校验
                if (cursor.moveToNext()) {
                    // 从数据库获取密码进行校验
                    String dbUsername = cursor.getString(cursor.getColumnIndex("uname"));
                    String dbPassword = cursor.getString(cursor.getColumnIndex("upassword"));
                    // 关闭游标
                    cursor.close();
                    if (password.equals(dbPassword)) {
                        // 校验成功则跳转到 SQLiteListActivity
                        Intent intent = new Intent(MainActivity.this, OneActivity.class);
                        //传登录用户的值
                        Save.saveInfo(MainActivity.this, username);
                        // 启动
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "欢迎" + username + "登录大众点评!", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                    // 跳转失败也要进行关闭
                    cursor.close();
                    // 跳转失败就提示用户相关的错误信息
                    Toast.makeText(MainActivity.this, "输入信息有误,请检查！", Toast.LENGTH_SHORT).show();
                }

                }
            }
        } );


        bt2.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
                finish();
            }
        } );
    }
}