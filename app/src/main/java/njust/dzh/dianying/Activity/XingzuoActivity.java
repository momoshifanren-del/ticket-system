package njust.dzh.dianying.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import njust.dzh.dianying.R;

public class XingzuoActivity extends AppCompatActivity {

    private EditText editTextZodiac;
    private Button buttonQuery;
    private ImageView imageViewFlower;

    // 中文星座与花的对应关系，以及图片资源ID
    private static final String[] ZODIAC_FLOWER_IMAGE = {
            "aquarius - orchid - image01",
            "Pisces - daisy - image02",
            "Aries - poppy flower - image03",
            "Taurus - lily - image04",
            "Gemini - Rose - image05",
            "Cancer - carnation - image06",
            "leo - sunflower - image01",
            "Virgo - gladiolus - image02",
            "Libra - iris - image03",
            "Scorpio - chrysanthemum - image04",
            "Sagittarius - freesia - image05",
            "Capricorn - Narcissus - image06"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xingzuo);

        editTextZodiac = findViewById(R.id.editTextZodiac);
        buttonQuery = findViewById(R.id.buttonQuery);
        imageViewFlower = findViewById(R.id.imageViewFlower);

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zodiac = editTextZodiac.getText().toString().trim();
                String result = getFlowerAndImageByZodiac(zodiac);
                if (result != null) {
                    String[] parts = result.split(" - ");
                    Toast.makeText(XingzuoActivity.this, "适合" + zodiac + "的花是：" + parts[1], Toast.LENGTH_SHORT).show();
                    imageViewFlower.setImageResource(getImageResource(parts[2]));
                } else {
                    Toast.makeText(XingzuoActivity.this, "请输入正确的星座", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getFlowerAndImageByZodiac(String zodiac) {
        for (String zodiacFlowerImage : ZODIAC_FLOWER_IMAGE) {
            if (zodiacFlowerImage.split(" - ")[0].equalsIgnoreCase(zodiac)) {
                return zodiacFlowerImage;
            }
        }
        return null;
    }

    private int getImageResource(String imageName) {
        // 根据图片名称返回对应的图片资源ID
        switch (imageName) {
            case "image01":
                return R.drawable.image01;
            case "image02":
                return R.drawable.image02;
            case "image03":
                return R.drawable.image03;
            case "image04":
                return R.drawable.image04;
            case "image05":
                return R.drawable.image05;
            case "image06":
                return R.drawable.image06;
            default:
                return R.drawable.default_flower; // 默认图片资源ID
        }
    }
}