package com.example.mobile_app_final_project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Movie_query#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Movie_query extends Fragment {
    EditText search_bar;
    Button search_btn;
    ListView result_container;

    String[] movie_title;
    ArrayList<String> search_results = new ArrayList<>();
    public Movie_query() {
        // Required empty public constructor
    }
    public static Movie_query newInstance(String param1, String param2) {
        Movie_query fragment = new Movie_query();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_query, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        search_bar = view.findViewById(R.id.search_bar);
        search_btn = view.findViewById(R.id.search_btn);
        result_container = view.findViewById(R.id.result_container);
        movie_title = getResources().getStringArray(R.array.movie_title);

        search_btn.setOnClickListener(btn -> {
            String keyword = search_bar.getText().toString();
            if (!keyword.matches("")) {
                for (String title: movie_title) {
                    int index = kmpSearch(title, keyword);
                    if (index != -1) {
                        search_results.add(title);
                    }
                    System.out.println(title + index);
                }
                if (search_results.isEmpty()) {
                    Toast.makeText(getActivity(), "未找到匹配內容", Toast.LENGTH_SHORT).show();
                }
                else {
                    displaySearchResult(search_results);
                }
            }
            else {
                Toast.makeText(getActivity(), "請輸入搜尋內容", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void displaySearchResult(ArrayList<String> result) {
        ArrayList<Integer> images_id = new ArrayList<>();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (String title: result) {
            if (title.equals("星際異攻隊3")) {
                images_id.add(R.drawable.guardiansofthegalaxy3);
                fragmentArrayList.add(new Info_GuardiansOfTheGalaxy());
            } else if (title.equals("超級瑪利歐電影")) {
                images_id.add(R.drawable.supermariomovie);
                fragmentArrayList.add(new Info_SuperMarioMovie());
            } else {
                images_id.add(R.drawable.fastandfuriousx);
                fragmentArrayList.add(new Info_FastAndFurious());
            }
        }
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(
                getActivity().getBaseContext(),
                images_id,
                result,
                fragmentArrayList,
                getActivity().getSupportFragmentManager()
        );
        result_container.setAdapter(searchResultAdapter);
    }

    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0; // 最長前綴後綴長度
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static int kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = computeLPSArray(pattern);

        int i = 0; // text 中的索引
        int j = 0; // pattern 中的索引

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j; // 找到匹配，返回起始索引
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1; // 未找到匹配
    }
}