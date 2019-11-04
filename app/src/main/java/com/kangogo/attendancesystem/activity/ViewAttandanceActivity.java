package com.kangogo.attendancesystem.activity;

import com.kangogo.attendancesystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ViewAttandanceActivity extends Activity {

	Spinner spinnerfaculty,spinnerdepartment;
	String userrole,faculty,department;
	private String[] facultyString = new String[] { "SCAI"};
	private String[] departmentString = new String[] {"IT","Computer Science","Information Science"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewstudent);
		
		spinnerfaculty=(Spinner)findViewById(R.id.spinnerbranchView);
		spinnerdepartment=(Spinner)findViewById(R.id.spinneryearView);
		
		
		spinnerfaculty.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				faculty =(String) spinnerfaculty.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_faculty = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, facultyString);
		adapter_faculty
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerfaculty.setAdapter(adapter_faculty);

		///......................spinner2

		spinnerdepartment.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				department =(String) spinnerdepartment.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_department = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, departmentString);
		adapter_department
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerdepartment.setAdapter(adapter_department);

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
