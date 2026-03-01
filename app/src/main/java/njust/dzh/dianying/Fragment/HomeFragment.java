package njust.dzh.dianying.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import njust.dzh.dianying.Bean.Movie;
import njust.dzh.dianying.Adapter.MovieAdapter;
import njust.dzh.dianying.R;

public class HomeFragment extends Fragment {
    private Movie[] foods = {
            new Movie("《Her Story》", R.drawable.p1, "18", "4.7", "A humorous and heartwarming love story about urban men and women in Shanghai."),
            new Movie("《 Johnny Keep Walking!》", R.drawable.p2, "15", "4.5", "Satirical comedies expose the malpractices and corruption in the workplace."),
            new Movie("《YOLO》", R.drawable.p3, "58", "4.7", "It showcases the current living conditions of women and conveys the spirit of female friendship."),
            new Movie("《Pegasus 2》", R.drawable.p4, "25", "4.6", "With a racing theme, Shen Teng humorously portrays the thrilling racetrack."),
            new Movie("《 Gold or Shit》", R.drawable.p5, "38", "4.4", "A snapshot of life, Hu Ge and Gao Yuanyuan's collaboration is as beautiful as a poem or a painting."),
            new Movie("《雪豹》", R.drawable.p6, "12", "4.8", "Snow Leopard"),

    };

    private List<Movie> foodList = new ArrayList<>();
    private MovieAdapter foodAdapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initFoods();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        foodAdapter = new MovieAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFoods();
            }
        });
        return view;
    }

    private void initFoods() {
        foodList.clear();
        Random random = new Random();
        int index = random.nextInt(foods.length);
        for (int i = index; i < foods.length; i++) {
            foodList.add(foods[i]);
        }
        for (int i = 0; i < index; i++) {
            foodList.add(foods[i]);
        }
    }

    private void refreshFoods() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFoods();
                        foodAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}