package njust.dzh.dianying.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import njust.dzh.dianying.R;

public class XingPeiActivity extends AppCompatActivity {


    private Button xingzuo;
    private Button shenfen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinpei);

        xingzuo  = findViewById(R.id.btn_xingzuo01);
        shenfen  = findViewById(R.id.btn_shen);


        xingzuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(XingPeiActivity.this, XingzuoActivity.class);
                startActivity(intent);

            }
        });

        shenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(XingPeiActivity.this, ShenFenActivity.class);
                startActivity(intent);

            }
        });




    }

}
