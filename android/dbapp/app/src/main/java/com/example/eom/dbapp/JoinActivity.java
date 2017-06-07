package com.example.eom.dbapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class JoinActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordInitView;
    private EditText mPasswordCheckView;
    private EditText mPassword;
    private EditText mNickname;
    private View mProgressView;
    private View mLoginFormView;
    private RadioButton radioButton;
    public int year=2014,month=0,day=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        findViewById(R.id.input_child_age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아이나이 (Child Age)
                new DatePickerDialog(JoinActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        JoinActivity.this.year = year;
                        JoinActivity.this.month = month+1;
                        JoinActivity.this.day = day;
                        ((TextView)JoinActivity.this.findViewById(R.id.input_child_age)).setText(""+year+"-"+month+"-"+day);
                    }
                }, year, month, day).show();
            }
        });

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radio_group);

    }



}
