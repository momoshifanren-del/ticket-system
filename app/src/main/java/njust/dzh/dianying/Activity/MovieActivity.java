package njust.dzh.dianying.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import njust.dzh.dianying.Bean.Movie;
import njust.dzh.dianying.DataBase.MovieDao;
import njust.dzh.dianying.R;

public class MovieActivity extends AppCompatActivity {
    public static final String FOOD_DATA = "food_data";
    private FloatingActionButton fab;
    private MovieDao foodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initView();
    }

    private void initView() {
        foodDao = new MovieDao(this);
        Intent intent = getIntent();
        // Retrieve the serialized object passed as a parameter and downcast it to Food.
        Movie food = (Movie)intent.getSerializableExtra(FOOD_DATA);
        // Get the properties of an object
        String foodName = food.getName();
        int foodImageId = food.getImageId();
        String foodComment = food.getComment();
        // Get examples of toolbars, collapsible bars, etc.
        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView foodImageView = findViewById(R.id.food_image_view);
        TextView foodContentText = findViewById(R.id.food_content_text);
        fab = findViewById(R.id.fab);
        // Set up a custom toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        // Set default back button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(foodName);
        Glide.with(this).load(foodImageId).into(foodImageView);
        String foodContent = generateFoodContent(foodComment);
        foodContentText.setText(foodContent);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                foodDao.openDB();
                foodDao.addFood(food);
                Toast.makeText(MovieActivity.this, "Success will"+foodName+"add to the cart!", Toast.LENGTH_SHORT).show();
                foodDao.closeDB();
            }
        });
    }

    // Generate content
    private String generateFoodContent(String foodComment) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            stringBuilder.append(foodComment);
        }
        return stringBuilder.toString();
    }
    // Click event of the back button in the menu bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}