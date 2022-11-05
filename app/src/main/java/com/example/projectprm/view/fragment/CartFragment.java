package com.example.projectprm.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.exceptions.BookNotFoundException;
import com.example.projectprm.exceptions.QuantityOutOfRangeException;
import com.example.projectprm.model.CartItem;
import com.example.projectprm.utils.CartHelper;
import com.example.projectprm.view.adapter.CartItemAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartItemAdapter.OnCartItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CartHelper cartHelper;
    List<CartItem> cartItems;
    TextView totalPrice, totalQuantity;
    CartItemAdapter cartItemAdapter;
    RecyclerView recyclerView;
    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_item_cart_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        cartHelper = new CartHelper(this.getActivity().getApplication());
        cartItems = cartHelper.convertFromMapToList();
        if(cartItems.size() == 0){
            LinearLayout emptyLinerLayout = view.findViewById(R.id.empty_cart);
            LinearLayout listCartItem = view.findViewById(R.id.ll_item_cart_books);
            LinearLayout infoCart = view.findViewById(R.id.ll_item);
            emptyLinerLayout.setVisibility(View.VISIBLE);
            listCartItem.setVisibility(View.GONE);
            infoCart.setVisibility(View.GONE);
        }
        else{
            cartItemAdapter = new CartItemAdapter(this.getActivity().getApplicationContext(), cartItems, this);
            recyclerView.setAdapter(cartItemAdapter);
            totalPrice = view.findViewById(R.id.textViewTotalPrice);
            totalQuantity = view.findViewById(R.id.textViewTotalQuantity);
            totalQuantity.setText(String.valueOf(cartHelper.getTotalQuantity()));
            totalPrice.setText(cartHelper.getTotalPrice().toString());
        }
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onQuantityReduced(int position) {
        CartItem cartItem = cartItems.get(position);
        try {
            cartHelper.remove(cartItem.getBook().getBookID(),  1, cartItem.getNewPrice());
            cartItemAdapter.notifyItemChanged(position);
            totalQuantity.setText(String.valueOf(cartHelper.getTotalQuantity()));
            totalPrice.setText(cartHelper.getTotalPrice().toString());
        } catch (BookNotFoundException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (QuantityOutOfRangeException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onQuantityIncreased(int position) {
        CartItem cartItem = cartItems.get(position);
        try {
            cartHelper.update(cartItem.getBook().getBookID(),1, cartItem.getNewPrice());
            cartItemAdapter.notifyItemChanged(position);
            totalQuantity.setText(String.valueOf(cartHelper.getTotalQuantity()));
            totalPrice.setText(cartHelper.getTotalPrice().toString());
        } catch (BookNotFoundException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (QuantityOutOfRangeException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemRemoved(int position) {
        CartItem cartItem = cartItems.get(position);
        try {
            cartHelper.remove(cartItem.getBook().getBookID());
            cartItemAdapter.notifyItemChanged(position);
            totalQuantity.setText(String.valueOf(cartHelper.getTotalQuantity()));
            totalPrice.setText(cartHelper.getTotalPrice().toString());
        } catch (BookNotFoundException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (QuantityOutOfRangeException ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}