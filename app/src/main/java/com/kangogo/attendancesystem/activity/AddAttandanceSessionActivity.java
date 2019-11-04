package com.kangogo.attendancesystem.activity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.kangogo.attendancesystem.bean.AttendanceBean;
import com.kangogo.attendancesystem.bean.AttendanceSessionBean;
import com.kangogo.attendancesystem.bean.FacultyBean;
import com.kangogo.attendancesystem.bean.StudentBean;
import com.kangogo.attendancesystem.context.ApplicationContext;
import com.kangogo.attendancesystem.db.DBAdapter;
import com.kangogo.attendancesystem.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddAttandanceSessionActivity<AddAttandanceActivity> extends Activity {

	private ImageButton date;
	private Calendar cal;
	private int day;
	private int month;
	private int dyear;
	private EditText dateEditText;
	Button submit;
	Button viewAttendance;
	Button viewTotalAttendance;
	Spinner spinnerfaculty,spinnerdepartment,spinnerSubject;
	String faculty = "SCAI";
	String department = "IT";
	String subject = "BIT424";

	private String[] facultyString = new String[] { "SCAI","FESS","SOBE"};
	private String[] departmentString = new String[] {"IT","Computer Science","Information Science","BCOM","BBM","EDA","EDS"};
	private String[] subjectITString = new String[] {"BIT424","DIT211","CIT101"};
	private String[] subjectComputerScienceString = new String[] {"CSC313","CSC211","CSC101"};
	private String[] subjectInformationScienceString = new String[] {"IS122","IS211","IS422"};

	private String[] subjectFinal = new String[] {"BIT323","COM221","CIT110","MIS500","IS412","COM311","BIT424","CSC211","IS422"};
	AttendanceSessionBean attendanceSessionBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_attandance);

		//Assume subject will be SE
		//subjectFinal = subjectSEString;

		spinnerfaculty=(Spinner)findViewById(R.id.spinner1);
		spinnerdepartment=(Spinner)findViewById(R.id.spinneryear);
		spinnerSubject=(Spinner)findViewById(R.id.spinnerSE);

		ArrayAdapter<String> adapter_faculty = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, facultyString);
		adapter_faculty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerfaculty.setAdapter(adapter_faculty);
		spinnerfaculty.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
				faculty =(String) spinnerfaculty.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		///......................spinner2
		ArrayAdapter<String> adapter_department = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departmentString);
		adapter_department.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerdepartment.setAdapter(adapter_department);
		spinnerdepartment.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
				department =(String) spinnerdepartment.getSelectedItem();
				//Toast.makeText(getApplicationContext(), "department:"+department, Toast.LENGTH_SHORT).show();

				/*if(year.equalsIgnoreCase("se"))
				{
					subjectFinal = subjectSEString;
				}
				else if(year.equalsIgnoreCase("te"))
				{
					subjectFinal = subjectTEString;
				}
				else if(year.equalsIgnoreCase("be"))
				{
					subjectFinal = subjectBEString;
				}*/

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectFinal);
		adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSubject.setAdapter(adapter_subject);
		spinnerSubject.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.RED);
				subject =(String) spinnerSubject.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});


		date = (ImageButton) findViewById(R.id.DateImageButton);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		dyear = cal.get(Calendar.YEAR);
		dateEditText = (EditText) findViewById(R.id.DateEditText);
		date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(0);

			}
		});

		submit=(Button)findViewById(R.id.buttonsubmit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
				attendanceSessionBean.setAttendance_session_department(faculty);
				attendanceSessionBean.setAttendance_session_class(department);
				attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
				attendanceSessionBean.setAttendance_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				int sessionId=	dbAdapter.addAttendanceSession(attendanceSessionBean);

				ArrayList<StudentBean> studentBeanList=dbAdapter.getAllStudentByFacultyDepartment(faculty, department);
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);


				Intent intent = new Intent(AddAttandanceSessionActivity.this,Studentfingerprint0.class);
				intent.putExtra("sessionId", sessionId);
				startActivity(intent);
			}
		});

		viewTotalAttendance=(Button)findViewById(R.id.viewTotalAttendanceButton);
		viewTotalAttendance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
				attendanceSessionBean.setAttendance_session_department(faculty);
				attendanceSessionBean.setAttendance_session_class(department);
				attendanceSessionBean.setAttendance_session_subject(subject);

				DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
				
				ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
				((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
				
				Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivity.class);
				startActivity(intent);
				
			}
		});
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, dyear, month, day);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear);
		}
	};

}
