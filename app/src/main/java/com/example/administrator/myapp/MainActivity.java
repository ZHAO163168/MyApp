package com.example.administrator.myapp;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toast toast;
    StringBuffer sb;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = new StringBuffer();
        this.addItem();;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addItem() {
        Spinner spinner = (Spinner) findViewById(R.id.spacerid);
        List<String> list = new ArrayList<String>();
        list.add("计算机科学与技术");
        list.add("软件工程");
        list.add("网络工程");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
              // sb.delete(0,sb.length());
                sb.setLength(0);
                EditText et = (EditText) findViewById(R.id.user);
                if (et.getText().length() == 0) {
                    //if (((EditText) findViewById(R.id.user)).getText().length() ==0)
                    this.showResult("请输入您的姓名！");
                }else{
                    sb.append("姓名:"+et.getText()+"\n");
                    sb.append(this.serde());
                    toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.canCel:
                this.Onfinish();
                break;
        }
    }

    public void showResult(String s) {
        toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    protected void Onfinish() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("確定要退出吗？").setPositiveButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface,int i){
              return;
            }
        });
        ab.setNegativeButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface,int i){
                exit();
            }
        });
        ab.create().show();
    }

    public void exit(){
        super.finish();
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    public  String  serde(){
        String s = "";
        RadioButton radio=(RadioButton)findViewById(R.id.man);
        if(radio.isChecked())
            s+="性别:男\n";
        else {
            s += "性别:女\n";
        }
         Spinner spinner  =(Spinner)findViewById(R.id.spacerid);
         s+=("专业:"+spinner.getSelectedItem().toString()+"\n");

         List<CheckBox> list = new ArrayList<>();
         list.add((CheckBox) findViewById(R.id.checkbox01));
        list.add((CheckBox) findViewById(R.id.checkbox02));
        list.add((CheckBox) findViewById(R.id.checkbox03));
        list.add((CheckBox) findViewById(R.id.checkbox04));
        s+="爱好:";
        String str="";
        int n=0;
        for (int i=0;i<list.size();i++)
            if (list.get(i).isChecked())
                str+=(list.get(i).getText()+" ");
            else
                   n++;
        if(n==4)
            str="无";
        return s+str;
    }
}
