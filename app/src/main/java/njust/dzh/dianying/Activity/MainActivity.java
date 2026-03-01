package njust.dzh.dianying.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import njust.dzh.dianying.Fragment.CartFragment;
import njust.dzh.dianying.Fragment.HomeFragment;
import njust.dzh.dianying.Fragment.PersonFragment;
import njust.dzh.dianying.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        replaceFragment(new HomeFragment());

        // Get bottom navigation bar instance
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.home);

        // Bottom navigation bar listener
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        return true;

                    case R.id.xingzuo:
                        Intent intent = new Intent(MainActivity.this, XingPeiActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.zizhu:
                        Intent intent12 = new Intent(MainActivity.this, ZizhuActivity.class);
                        startActivity(intent12);
                        return true;

                    case R.id.cart:
                        replaceFragment(new CartFragment());
                        return true;
                    case R.id.person:
                        replaceFragment(new PersonFragment());
                        return true;
                }
                return false;
            }
        });
    }

    // Dynamically replace fragments
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        // Simulating the return stack in fragments
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}