package com.example.chandana.mydbtwo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chandana.mydbtwo.db.DBHelper;
import com.example.chandana.mydbtwo.R;
import com.example.chandana.mydbtwo.model.Employee;

/**
 * Created by Chandana on 02-05-2017.
 */

public class DisplayContactActivity extends AppCompatActivity {
    private DBHelper mydb;
    private TextView Ename, Emobile, Email, Estate, Ecity, EmpId;
    private Button save;
    private Bundle extras;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail_activity);
        Ename = (TextView) findViewById(R.id.editename);
        EmpId = (TextView) findViewById(R.id.editeid);
        Emobile = (TextView) findViewById(R.id.editmobile);
        Email = (TextView) findViewById(R.id.editemail);
        Estate = (TextView) findViewById(R.id.editstate);
        Ecity = (TextView) findViewById(R.id.editcity);
        save = (Button) findViewById(R.id.save);
        mydb = new DBHelper(this);

        extras = getIntent().getExtras();
        if (extras != null) {
            long empId = extras.getLong("id");

            if (empId > 0) {
                //means this is the view part not the add contact part.
                employee = mydb.findById(empId);
                Ename.setText(employee.getName());
                EmpId.setText(employee.getEmpId());
                Emobile.setText(employee.getMobileNumber());
                Email.setText(employee.getEmail());
                Estate.setText(employee.getState());
                Ecity.setText(employee.getCity());
                enableDisableFields(false);
                invalidateOptionsMenu();
            }
        }
    }

    private void enableDisableFields(boolean status) {
        save.setVisibility(status ? View.VISIBLE : View.INVISIBLE);
        Ename.setEnabled(status);
        EmpId.setEnabled(status);
        Emobile.setEnabled(status);
        Email.setEnabled(status);
        Estate.setEnabled(status);
        Ecity.setEnabled(status);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (employee != null && employee.getId() > 0) {
            getMenuInflater().inflate(R.menu.menu_display_contact, menu);
            return true;
        }
        getMenuInflater().inflate(R.menu.menu_none, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                enableDisableFields(true);
                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(employee.getId());
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        if (employee == null)
            employee = new Employee();
        employee.setName(Ename.getText().toString().trim());
        employee.setEmpId(EmpId.getText().toString().trim());
        employee.setMobileNumber(Emobile.getText().toString().trim());
        employee.setEmail(Email.getText().toString().trim());
        employee.setState(Estate.getText().toString().trim());
        employee.setCity(Ecity.getText().toString().trim());

        if (employee.getId() > 0) {
            if (mydb.update(employee)) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (mydb.save(employee)) {
                Toast.makeText(getApplicationContext(), "done",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "not done",
                        Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}

