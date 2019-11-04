package com.kangogo.attendancesystem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangogo.attendancesystem.R;

public class Studentfingerprint0 extends Activity {
    ImageView fingerprint;
    TextView first_name,last_name,regNo;
    Button dismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentfingerprint0);
        fingerprint =findViewById(R.id.ImageViewfinger);
        first_name=findViewById(R.id.textFname);
        last_name=findViewById(R.id.textLname);
        regNo=findViewById(R.id.textRegNo);
        fingerprint.setImageResource(R.drawable.fingerprint);
        dismiss=findViewById(R.id.dismiss);

    }
}
