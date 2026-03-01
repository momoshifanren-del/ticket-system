package njust.dzh.dianying.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import njust.dzh.dianying.R;

public class ZizhuActivity extends AppCompatActivity {

    private EditText editTextFlower1;
    private EditText editTextFlower2;
    private Button buttonGenerate;
    private LinearLayout linearLayoutImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhu);

        // Initialize controls
        editTextFlower1 = findViewById(R.id.editTextFlower1);
        editTextFlower2 = findViewById(R.id.editTextFlower2);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        linearLayoutImages = findViewById(R.id.linearLayoutImages);

        // Set button click event
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flower1 = editTextFlower1.getText().toString().trim();
                String flower2 = editTextFlower2.getText().toString().trim();
                generateImages(flower1, flower2);
            }
        });
    }

    private void generateImages(String flower1, String flower2) {
        // The image to display is determined based on the entered bouquet name.
        int imageResId1 = getImageResId(flower1);
        int imageResId2 = getImageResId(flower2);

        // Clear previous pictures
        linearLayoutImages.removeAllViews();

        // Add new picture
        if (imageResId1 != -1) {
            addImageToLayout(imageResId1);
        }
        if (imageResId2 != -1) {
            addImageToLayout(imageResId2);
        }
    }

    private int getImageResId(String flowerName) {
        switch (flowerName) {
            case "rose":
                return R.drawable.p1;
            case "peony":
                return R.drawable.p2;
            case "tulip":
                return R.drawable.p3;
            case "carnation":
                return R.drawable.p4;
            case "lily":
                return R.drawable.p5;
            case "forget me not":
                return R.drawable.p6;
            default:
                Toast.makeText(this, "Unknown bouquet name", Toast.LENGTH_SHORT).show();
                return -1;
        }
    }

    private void addImageToLayout(int imageResId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutImages.addView(imageView, params);
    }
}