package njust.dzh.dianying.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import njust.dzh.dianying.R;

public class ShenFenActivity extends AppCompatActivity {

    private EditText editTextZodiac;
    private Button buttonQuery;
    private ImageView imageViewFlower;

    // 中文星座与花的对应关系，以及图片资源ID
    private static final String[] ZODIAC_FLOWER_IMAGE = {
            "student - orchid - image01",
            "Mother - daisy - image02",
            "Father - poppy flower - image03",
            "teacher - lily - image04",
            "friends - Rose - image05",
            "younger brother - carnation - image06",
            "elder brother - sunflower - image01",
            "elder sister - gladiolus - image02",
            "Worker - iris - image03",
            "spouse - chrysanthemum - image04",
            "lover - freesia - image05",
            "other- Narcissus - image06",
            "Wife - Narcissus - image06",
            "husband - Narcissus - image02"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenfen);

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
                    Toast.makeText(ShenFenActivity.this, "Suitable" + zodiac + "the flower is：" + parts[1], Toast.LENGTH_SHORT).show();
                    imageViewFlower.setImageResource(getImageResource(parts[2]));
                } else {
                    Toast.makeText(ShenFenActivity.this, "the flower is", Toast.LENGTH_SHORT).show();
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
        // Return the corresponding image resource ID based on the image name.
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
                return R.drawable.default_flower; // Default image resource ID
        }
    }
}