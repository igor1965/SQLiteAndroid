package com.igor.sqliteandroid;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText first_name,last_name,numder,id;
    Button btnAdd,btnRead,btnUpdate,btnDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        first_name = (EditText)findViewById(R.id.Firstname);
        last_name = (EditText)findViewById(R.id.LastName);
        numder = (EditText)findViewById(R.id.Phone);
        id = (EditText)findViewById(R.id.ID);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = databaseHelper.deleteData(id.getText().toString());
                if (deletedRows >0)
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
            else
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = databaseHelper.updateData(id.getText().toString(),first_name.getText().toString(),last_name.getText().toString(),numder.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void AddData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = databaseHelper.insertData(first_name.getText().toString(),last_name.getText().toString(),numder.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewAll(){
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor cursor = databaseHelper.getAllData();
               if (cursor.getCount() == 0){
                   //show message
                   showMessage("Error","Mothing found");
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while (cursor.moveToNext()){
                   buffer.append("ID: "+ cursor.getString(0)+"\n");
                   buffer.append("FIRST_NAME: "+ cursor.getString(1)+"\n");
                   buffer.append("LAST_NAME: "+ cursor.getString(2)+"\n");
                   buffer.append("PHONE: "+ cursor.getString(3)+"\n\n");
               }
               //show data
                showMessage("Data",buffer.toString());
            }
        });

    }
    public  void showMessage(String title,String messge){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(messge);
        builder.show();


    }
}
