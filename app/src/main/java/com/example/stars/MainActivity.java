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
                    Toast.makeText(MainActivity.this, "����Ϊ�յ�ѡ�����д��ϣ�", Toast.LENGTH_SHORT).show();
                }else{
                    String username = et1.getText().toString();
                    String password = et2.getText().toString();
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("users", new String[]{"uname", "upassword"}, "uname=?", new String[]{username}, null, null, null, "0,1");
                // �α��ƶ�����У��
                if (cursor.moveToNext()) {
                    // �����ݿ��ȡ�������У��
                    String dbUsername = cursor.getString(cursor.getColumnIndex("uname"));
                    String dbPassword = cursor.getString(cursor.getColumnIndex("upassword"));
                    // �ر��α�
                    cursor.close();
                    if (password.equals(dbPassword)) {
                        // У��ɹ�����ת�� SQLiteListActivity
                        Intent intent = new Intent(MainActivity.this, OneActivity.class);
                        //����¼�û���ֵ
                        Save.saveInfo(MainActivity.this, username);
                        // ����
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "��ӭ" + username + "��¼���ڵ���!", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                    // ��תʧ��ҲҪ���йر�
                    cursor.close();
                    // ��תʧ�ܾ���ʾ�û���صĴ�����Ϣ
                    Toast.makeText(MainActivity.this, "������Ϣ����,���飡", Toast.LENGTH_SHORT).show();
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