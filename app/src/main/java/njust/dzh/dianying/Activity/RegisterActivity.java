package njust.dzh.dianying.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import njust.dzh.dianying.Bean.User;
import njust.dzh.dianying.DataBase.UserDao;
import njust.dzh.dianying.R;

public class RegisterActivity extends AppCompatActivity {
    private static final int RESULT_OK = 1;

    private Button btnRegister;
    private Button btnCancel;
    private EditText etAccount;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView() {
        // Remove default title bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        // Bind controls
        etAccount =findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        btnCancel = findViewById(R.id.btn_cancel);

        // Set click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = etAccount.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String confirm = etConfirmPassword.getText().toString().trim();
                User user = new User(acc, pass);
                userDao = new UserDao(getApplicationContext());
                userDao.open();
                if (userDao.findUser(user)) {
                    Toast.makeText(RegisterActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "The two passwords were different", Toast.LENGTH_SHORT).show();
                } else {
                    userDao.addUser(user);
                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    //Pass the username and password over
                    intent.putExtra("acc", acc);
                    intent.putExtra("pass", pass);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                userDao.close();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}