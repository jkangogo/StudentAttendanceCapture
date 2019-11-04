package com.kangogo.attendancesystem.activity;

import com.kangogo.attendancesystem.bean.StudentBean;
import com.kangogo.attendancesystem.db.DBAdapter;
import com.kangogo.attendancesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;
	EditText textcontact;
	EditText textaddress;
	Spinner spinnerfaculty,spinnerdepartmet;
	ImageView fingerprint;
	String userrole,faculty,department;
	private String[] facultyString = new String[] { "SCAI","FESS","SOBE"};
	private String[] departmentString = new String[] {"IT","Computer Science","Information Science","BCOM","BBM","EDA","EDS"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstudent);

		spinnerfaculty=(Spinner)findViewById(R.id.spinnerdept);
		spinnerdepartmet=(Spinner)findViewById(R.id.spinneryear);
		textFirstName=(EditText)findViewById(R.id.editTextFirstName);
		textLastName=(EditText)findViewById(R.id.editTextLastName);
		textcontact=(EditText)findViewById(R.id.editTextPhone);
		textaddress=(EditText)findViewById(R.id.TextAddr);
		fingerprint = (ImageView)findViewById(R.id.imageView2);
		registerButton=(Button)findViewById(R.id.RegisterButton);
		fingerprint.setImageResource(R.drawable.finger);

		spinnerfaculty.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
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

		spinnerdepartmet.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
				department =(String) spinnerdepartmet.getSelectedItem();

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
		spinnerdepartmet.setAdapter(adapter_department);



		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//......................................validation
				String first_name = textFirstName.getText().toString();
				String last_name = textLastName.getText().toString();
				String phone_no = textcontact.getText().toString();
				String student_address = textaddress.getText().toString();

				if (TextUtils.isEmpty(first_name)) {
					textFirstName.setError("Please Enter First name");
				}

				else if (TextUtils.isEmpty(last_name)) {
					textLastName.setError("Please Enter Last name");
				}
				else if (TextUtils.isEmpty(phone_no)) {
					textcontact.setError("Please Enter Phoneno");
				}

				else if (TextUtils.isEmpty(student_address)) {
					textaddress.setError("Enter Regno");
				}				
				else { 
					
					StudentBean studentBean = new StudentBean();

					studentBean.setStudent_firstname(first_name);
					studentBean.setStudent_lastname(last_name);
					studentBean.setStudent_mobilenumber(phone_no);
					studentBean.setStudent_address(student_address);
					studentBean.setStudent_department(faculty);
					studentBean.setStudent_class(department);
					
					DBAdapter dbAdapter= new DBAdapter(AddStudentActivity.this);
					dbAdapter.addStudent(studentBean);
					
					Intent intent =new Intent(AddStudentActivity.this,MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
