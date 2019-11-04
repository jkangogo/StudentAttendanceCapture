package com.kangogo.attendancesystem.activity;

import com.kangogo.attendancesystem.bean.FacultyBean;
import com.kangogo.attendancesystem.db.DBAdapter;
import com.kangogo.attendancesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFacultyActivity extends Activity {

	Button registerButton;
	EditText textFirstName;
	EditText textLastName;
	EditText textemail;
	EditText textcontact;
	EditText textaddress;
	EditText textusername;
	EditText textpassword;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfaculty);


		textFirstName=(EditText)findViewById(R.id.editTextFirstName);
		textLastName=(EditText)findViewById(R.id.editTextLastName);
		textcontact=(EditText)findViewById(R.id.editTextPhone);
		textaddress=(EditText)findViewById(R.id.TextAddr);
		textusername=(EditText)findViewById(R.id.editTextusername);
		textpassword=(EditText)findViewById(R.id.editTextPassword);
		registerButton=(Button)findViewById(R.id.RegisterButton);

		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String first_name = textFirstName.getText().toString();
				String last_name = textLastName.getText().toString();
				String phone_no = textcontact.getText().toString();
				String address = textaddress.getText().toString();
				String username = textusername.getText().toString();
				String passWord = textpassword.getText().toString();

				if (TextUtils.isEmpty(first_name)) {
					textFirstName.setError("please enter firstname");
				}
				else if (TextUtils.isEmpty(last_name)) {
					textLastName.setError("please enter lastname");
				}
				else if (TextUtils.isEmpty(phone_no)) {
					textcontact.setError("please enter phoneno");
				}

				else if (TextUtils.isEmpty(address)) {
					textaddress.setError("enter address");
				}	
				else if (TextUtils.isEmpty(username)) {
					textcontact.setError("please enter PfNo");
				}
				else if (TextUtils.isEmpty(passWord)) {
					textaddress.setError("enter password");
				}				
				else { 
					
					FacultyBean facultyBean = new FacultyBean();
					facultyBean.setFaculty_firstname(first_name);
					facultyBean.setFaculty_lastname(last_name);
					facultyBean.setFaculty_mobilenumber(phone_no);
					facultyBean.setFaculty_address(address);
					facultyBean.setFaculty_username(username);
					facultyBean.setFaculty_password(passWord);
					
					DBAdapter dbAdapter = new DBAdapter(AddFacultyActivity.this);
					dbAdapter.addFaculty(facultyBean);
					
					Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Lecturer added successfully", Toast.LENGTH_SHORT).show();

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
