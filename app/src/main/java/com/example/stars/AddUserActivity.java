package com.example.stars;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends Activity {

    private EditText ed1,ed2;
    private Button bt1,bt2;
    String uname,upassword;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Bundle bundle;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);

        ed1=(EditText) findViewById(R.id.ed1);
        ed2=(EditText) findViewById(R.id.ed2);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button) findViewById(R.id.bt2);

        bundle=this.getIntent().getExtras();
        if (bundle!=null){
            ed1.setText(bundle.getString("uname"));
            ed2.setText(bundle.getString("upassword"));

        }

        bt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AddUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if (ed1.getText().toString().equals("")||ed2.getText().toString().equals("")) {
                    Toast.makeText(AddUserActivity.this, "您有为空的选项，请填写完毕！", Toast.LENGTH_SHORT).show();
                }else{
                uname=ed1.getText().toString();
                upassword=ed2.getText().toString();

                ContentValues value=new ContentValues();
                value.put("uname",uname);
                value.put("upassword",upassword);

                DbHelper dbHelper=new DbHelper(AddUserActivity.this,"Db_Stars",null,1);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                long status;

                status=db.insert("users",null,value);

                if(status!=-1){
                    Toast.makeText(AddUserActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(AddUserActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                }
            }

        });
    }

}

