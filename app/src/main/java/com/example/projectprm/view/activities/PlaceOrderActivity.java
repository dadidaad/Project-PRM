package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.CartItem;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.OrderDetail;
import com.example.projectprm.model.repos.AccountRepository;
import com.example.projectprm.model.repos.OrderDetailRepository;
import com.example.projectprm.model.repos.OrderRepository;
import com.example.projectprm.session.Session;
import com.example.projectprm.utils.CartHelper;
import com.example.projectprm.view.adapter.CartItemAdapter;
import com.example.projectprm.view.adapter.OrderItemListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    TextView customerName, customerAddress, customerPhone;
    RecyclerView recyclerViewOrderPurchase;
    OrderItemListAdapter orderItemListAdapter;
    Session session;
    CartHelper cartHelper;
    TextView textViewTotalAmount, textViewTotalQuantity;
    List<CartItem> cartItemList;
    AccountRepository accountRepository;
    Button btnPurchaseOrder;
    DatePicker requiredDate;
    Account user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        customerName = findViewById(R.id.textViewCardCustomerName);
        customerAddress = findViewById(R.id.textViewCardCustomerAddress);
        customerPhone = findViewById(R.id.textViewCardCustomerPhone);
        recyclerViewOrderPurchase = findViewById(R.id.recyclerview_item_purchase_books);
        cartHelper = new CartHelper(getApplication());
        session = new Session(this);
        cartItemList = cartHelper.convertFromMapToList();
        orderItemListAdapter = new OrderItemListAdapter(this, cartItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderPurchase.setLayoutManager(layoutManager);
        recyclerViewOrderPurchase.setAdapter(orderItemListAdapter);
        textViewTotalAmount = findViewById(R.id.textViewTotalPrice);
        textViewTotalQuantity = findViewById(R.id.textViewTotalQuantity);
        textViewTotalAmount.setText(cartHelper.getTotalPrice().toString());
        textViewTotalQuantity.setText(String.valueOf(cartHelper.getTotalQuantity()));
        user = session.getAccount();
        accountRepository = new AccountRepository(getApplication());
        Account userInfo = accountRepository.getById(user.getAccountId());
        customerName.setText(userInfo.getDisplayName());
        customerAddress.setText(userInfo.getAddress());
        customerPhone.setText(userInfo.getPhone());
        btnPurchaseOrder = findViewById(R.id.btn_checkout_purchase);
        requiredDate = findViewById(R.id.datePicker);

        btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewOrder();
            }
        });
    }

    public void createNewOrder(){
        int   day  = requiredDate.getDayOfMonth();
        int   month= requiredDate.getMonth();
        int   year = requiredDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(calendar.getTime());
        Date date = null;
        try {
            date = sdf.parse(formatedDate);
        } catch (ParseException e) {
            Toast.makeText(this, "Invalid datetime!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(date.before(new Date())){
            Toast.makeText(this, "Required Date must after order date", Toast.LENGTH_SHORT).show();
            return;
        }
        OrderRepository orderRepository = new OrderRepository(getApplication());
        orderRepository.insert(new Date(), date, cartHelper.getTotalPrice().intValue(), user.getAccountId());
        OrderDetailRepository orderDetailRepository = new OrderDetailRepository(getApplication());
        Order order = orderRepository.getLastOrder();
        for(CartItem cartItem : cartItemList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBook_id(cartItem.getBook().getBookID());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getNewPrice().getPrice());
            orderDetail.setOrder_id(order.getOrderId());
            orderDetailRepository.insert(orderDetail);
        }
        cartHelper.clear();
        Toast.makeText(this, "Order successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}