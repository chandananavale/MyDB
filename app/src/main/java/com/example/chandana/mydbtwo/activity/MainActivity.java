package com.example.chandana.mydbtwo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chandana.mydbtwo.adapter.EmployeeAdapter;
import com.example.chandana.mydbtwo.db.DBHelper;
import com.example.chandana.mydbtwo.R;
import com.example.chandana.mydbtwo.model.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private DBHelper mydb;
    private ArrayList<Employee> employees;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        employees = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employees);

        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(employeeAdapter);
        listView.setEmptyView(findViewById(R.id.no_data_found));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                long id_To_Search = arg3;
                Bundle dataBundle = new Bundle();
                dataBundle.putLong("id", id_To_Search);
                Intent intent = new Intent(getApplicationContext(), DisplayContactActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        ArrayList<Employee> list = mydb.getAllCotacts();
        if (list != null && !list.isEmpty()) {
            employees.clear();
            employees.addAll(list);
        }
        employeeAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), DisplayContactActivity.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}

