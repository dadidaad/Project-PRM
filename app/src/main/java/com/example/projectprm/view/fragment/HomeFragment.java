package com.example.projectprm.view.fragment;

import android.content.Intent;
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
import com.example.projectprm.model.entities.OrderDetail;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.CategoryRepository;
import com.example.projectprm.model.repos.OrderDetailRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.view.activities.ListBookActivity;
import com.example.projectprm.view.activities.MainActivity;
import com.example.projectprm.view.adapter.CategoriesListAdapter;
import com.example.projectprm.view.adapter.NewestBookAdapter;
import com.example.projectprm.view.adapter.OnClickItemRecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnClickItemRecyclerView {

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

    RecyclerView highestRateRec;
    List<Book> highestRateBookList;
    NewestBookAdapter highestBookAdapter;

    RecyclerView bestSellingRec;
    List<Book> bestSellingBookList;
    NewestBookAdapter bestSellingBookAdapter;
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

        //Get Category Recycler View and Init Data
        categoriesListRec = view.findViewById(R.id.categoryRecycler);
        categoryList = new CategoryRepository(this.getActivity().getApplication()).getAll();
        setCategoryRecycler(categoryList);

        //Get NewestBook Recycler View and Init Data
        newestBookListRec = view.findViewById(R.id.newestBookRec);
        bookList = new BookRepository(this.getActivity().getApplication()).getAll();
        priceList = new PriceRepository(this.requireActivity().getApplication()).getAll();
        setNewestBookRecycler(bookList, priceList);

        //Get HighestBook Recycler View and Init Data;
        highestRateBookList = new BookRepository(this.getActivity().getApplication()).getHighestRatBook();
        highestRateRec = view.findViewById(R.id.highestRateRec);
        setHighestBookRecycler(highestRateBookList.subList(0,5),priceList);

        //Get Best Selling Recycler View and Init Data;
        bestSellingBookList = new BookRepository(this.getActivity().getApplication()).getAll();
        bestSellingRec = view.findViewById(R.id.bestSellingRec);
        setBestSellingBookRecycler(bookList, priceList);
        return view;
    }
    //Set Category for RecyclerView Category
    private void setCategoryRecycler(List<Category> datalist){
        //Inflate Layout Linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesListRec.setLayoutManager(layoutManager);

        //Init and set Adapter
        categoriesListAdapter = new CategoriesListAdapter(this.getContext(), datalist, this);
        categoriesListRec.setAdapter(categoriesListAdapter);
    }

    private void setNewestBookRecycler(List<Book> bookList, List<Price> priceList){
        List<Book> newestBookList = new ArrayList<>();

        for(Book b : bookList){
            int month = b.getAddDate().getMonth();
            int year = b.getAddDate().getYear();
            if(b.getAddDate().getMonth() + 1 == 10  && b.getAddDate().getYear() == 122){
                newestBookList.add(b);
            }
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        newestBookListRec.setLayoutManager(layoutManager);

        newestBookAdapter = new NewestBookAdapter(this.getContext(), newestBookList, priceList);
        newestBookListRec.setAdapter(newestBookAdapter);
    }

    private void setHighestBookRecycler(List<Book> bookList, List<Price> priceList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        highestRateRec.setLayoutManager(layoutManager);

        highestBookAdapter = new NewestBookAdapter(this.getContext(), bookList, priceList);
        highestRateRec.setAdapter(highestBookAdapter);
    }

    private void setBestSellingBookRecycler(List<Book> bookList, List<Price> priceList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        bestSellingRec.setLayoutManager(layoutManager);

        List<Book>filterBestSellingBook = new ArrayList<>();
        List<OrderDetail> allOrderDetails = new OrderDetailRepository(this.getActivity().getApplication()).getAll();
        List<Integer> bestSellingBookID = new OrderDetailRepository(this.getActivity().getApplication()).getListBestSellingBookID();

        for( int i=0; i< bookList.size(); i++){
            for (int j : bestSellingBookID){
                if(j==bookList.get(i).getBookID()){
                    filterBestSellingBook.add(bookList.get(i));
                }
            }
        }
        bestSellingBookAdapter = new NewestBookAdapter(this.getContext(), filterBestSellingBook.subList(0,4), priceList);
        bestSellingRec.setAdapter(bestSellingBookAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this.getContext(), ListBookActivity.class);

        intent.putExtra("CatID", categoryList.get(position).getCategoryId());

        startActivity(intent);
    }
}