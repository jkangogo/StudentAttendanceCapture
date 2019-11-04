package com.kangogo.attendancesystem.activity;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kangogo.attendancesystem.bean.FacultyBean;
import com.kangogo.attendancesystem.context.ApplicationContext;
import com.kangogo.attendancesystem.db.DBAdapter;
import com.kangogo.attendancesystem.R;

public class LoginActivity extends Activity {

	Button login;
	EditText username,password;
	Spinner spinnerloginas;
	String userrole;
	private String[] userRoleString = new String[] { "admin", "lecturer"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		login =(Button)findViewById(R.id.buttonlogin);
		username=(EditText)findViewById(R.id.editTextusername);
		password=(EditText)findViewById(R.id.editTextpassword);
		spinnerloginas=(Spinner)findViewById(R.id.spinnerloginas);

		spinnerloginas.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
				userrole =(String) spinnerloginas.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, userRoleString);
		adapter_role
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerloginas.setAdapter(adapter_role);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(userrole.equals("admin"))
				{

					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name)) 
					{
						username.setError("Please type Username");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("Enter Password!");
					}
					else
					{
						if(user_name.equals("KIbu") & pass_word.equals("KIbuacke")){
						Intent intent =new Intent(LoginActivity.this,MenuActivity.class);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getApplicationContext(), "Please type correct Username/Password", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
				else
				{
					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name))
					{
						username.setError("Invalid Personal File Number");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("Enter Password");
					}
					DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
					FacultyBean facultyBean = dbAdapter.validateFaculty(user_name, pass_word);
					
					if(facultyBean!=null)
					{
						Intent intent = new Intent(LoginActivity.this,AddAttandanceSessionActivity.class);
						startActivity(intent);
						((ApplicationContext)LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
						Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Please type correct Username/Password", Toast.LENGTH_SHORT).show();
					}
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
