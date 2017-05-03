package com.example.chandana.mydbtwo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chandana.mydbtwo.R;
import com.example.chandana.mydbtwo.model.Employee;

import java.util.ArrayList;

/**
 * Created by Chandana on 02-05-2017.
 */

public class EmployeeAdapter extends BaseAdapter {
    private ArrayList<Employee> employees;
    private Activity activity;
    private LayoutInflater inflater;

    public EmployeeAdapter(Activity activity, ArrayList<Employee> employees) {
        this.activity = activity;
        this.employees = employees;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Employee getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employees.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Employee employee = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_employee, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtvwEmployeeName.setText(employee.getName());
//        viewHolder.txtvwEmployeeName.setTag(employee.getId());
        return convertView;
    }

    private static class ViewHolder {
        TextView txtvwEmployeeName;

        public ViewHolder(View view) {
            txtvwEmployeeName = (TextView) view.findViewById(R.id.employee_name);
        }

    }
}
