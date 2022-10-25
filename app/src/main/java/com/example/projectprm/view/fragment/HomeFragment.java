package com.example.projectprm.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.CategoryRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.view.adapter.CategoriesListAdapter;
import com.example.projectprm.view.adapter.NewestBookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView categoriesListRec;
    CategoriesListAdapter categoriesListAdapter;
    List<Category> categoryList;

    RecyclerView newestBookListRec;
    NewestBookAdapter newestBookAdapter;
    List<Book> bookList;
    List<Price> priceList;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        categoriesListRec = view.findViewById(R.id.categoryRecycler);
        newestBookListRec = view.findViewById(R.id.newestBookRec);

        categoryList = new CategoryRepository(this.getActivity().getApplication()).getAll();
        bookList = new BookRepository(this.getActivity().getApplication()).getAll();
        priceList = new PriceRepository(this.requireActivity().getApplication()).getAll();

        setCategoryRecycler(categoryList);
        setNewestBookRecycler(bookList, priceList);
        return view;
    }
    private void setCategoryRecycler(List<Category> datalist){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesListRec.setLayoutManager(layoutManager);

        categoriesListAdapter = new CategoriesListAdapter(this.getContext(), datalist);
        categoriesListRec.setAdapter(categoriesListAdapter);
    }

    private void setNewestBookRecycler(List<Book> bookList, List<Price> priceList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        newestBookListRec.setLayoutManager(layoutManager);

        newestBookAdapter = new NewestBookAdapter(this.getContext(), bookList, priceList);
        newestBookListRec.setAdapter(newestBookAdapter);
    }
}