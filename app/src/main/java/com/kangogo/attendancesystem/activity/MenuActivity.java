package com.kangogo.attendancesystem.activity;

import java.util.ArrayList;

import com.kangogo.attendancesystem.bean.AttendanceBean;
import com.kangogo.attendancesystem.context.ApplicationContext;
import com.kangogo.attendancesystem.db.DBAdapter;
import com.kangogo.attendancesystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	Button addStudent;
	Button addLecturer;
	Button viewStudent;
	Button viewLecturer;
	Button logout;
	Button attendancePerStudent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		addStudent =(Button)findViewById(R.id.buttonaddstudent);
		addLecturer =(Button)findViewById(R.id.buttonaddfaculty);
		viewStudent =(Button)findViewById(R.id.buttonViewstudent);
		viewLecturer =(Button)findViewById(R.id.buttonviewfaculty);
		logout =(Button)findViewById(R.id.buttonlogout);
		
		addStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,AddStudentActivity.class);
				startActivity(intent);
			}
		});
		
		addLecturer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,AddFacultyActivity.class);
				startActivity(intent);
			}
		});
		
		viewLecturer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,ViewFacultyActivity.class);
				startActivity(intent);
			}
		});


		viewStudent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,ViewStudentActivity.class);
				startActivity(intent);
			}
		});
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent =new Intent(MenuActivity.this,MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
		attendancePerStudent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
				ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
				((ApplicationContext)MenuActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
				
				Intent intent = new Intent(MenuActivity.this,ViewAttendancePerStudentActivity.class);
				startActivity(intent);
				
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
