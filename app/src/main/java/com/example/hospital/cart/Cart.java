package com.example.hospital.cart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hospital.AddressDetails;
import com.example.hospital.Dim;
import com.example.hospital.ProductDB.ProductDb;
import com.example.hospital.ProductDB.proLocalArray;
import com.example.hospital.R;
import com.example.hospital.Register.Login;
import com.example.hospital.Shop.ProductArray;
import com.example.hospital.aboutUs.Founder;
import com.example.hospital.cart.orders.YourOrders;
import com.example.hospital.profile.Shared;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    Toolbar mainTool;
    RecyclerView recyclerView;
    ArrayList<ProductArray> productArrays;
    LinearLayout checkOut;
    Adapter adapter;
    ProductDb productDb;
    TextView totalAmount;
    int amount;
    ArrayList<Double> totalAmounts = new ArrayList<>();
    String t_amount;
    PopupWindow popupWindow;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        productDb = new ProductDb(getApplicationContext());
        recyclerView = findViewById(R.id.cartRecycle);
        checkOut = findViewById(R.id.checkOut);
        mainTool = findViewById(R.id.cartTool);
        totalAmount = findViewById(R.id.totalAmount);
        mainTool.setTitle("Cart");
        setSupportActionBar(mainTool);
        Window window =  getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent1));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new Adapter(productDb.getAllProducts()));
        if (productDb.getAllProducts() != null && productDb.getAllProducts().size() != 0){
            validate();
        }else {
            totalAmount.setText("-");
        }
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productDb.getAllProducts().size() == 0){
                    Toast.makeText(getApplicationContext(),"Your cart is empty",Toast.LENGTH_SHORT).show();
                }else {
                    if (Shared.isLogged(getApplicationContext())){
                        Intent intent = new Intent(getApplicationContext(),AddressDetails.class);
                        intent.putExtra("amount",
                                totalAmount.getText().toString().replaceAll(",","").trim());
                        intent.putExtra("type","1");
                        startActivity(intent);
                    }else {
                        showDialog(Cart.this,"Login Alert","Kindly login and proceed to checkout..!");
                    }
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.you_or, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.you_order) {

            startActivity(new Intent(Cart.this, YourOrders.class));

        }
        return super.onOptionsItemSelected(item);
    }
    public class Adapter extends RecyclerView.Adapter<ViewHoler>
    {
        List<proLocalArray> proLocalArrays;
        public Adapter(List<proLocalArray> proLocalArrays){
            this.proLocalArrays = proLocalArrays;
        }
        @NonNull
        @Override
        public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cart_view, viewGroup, false);
            return new ViewHoler(view);
        }
        @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
        @Override
        public void onBindViewHolder(@NonNull final ViewHoler viewHoler, final int i) {
            viewHoler.name.setText(proLocalArrays.get(i).getName());
          //  viewHoler.imageView.setImageResource(productLoArrays.get(i).getArray());

            RequestOptions options = new RequestOptions()
                     .fitCenter()
                    .error(R.drawable.error);
            Glide.with(getApplicationContext()).load(proLocalArrays.get(i).getImageURL()).apply(options).into(viewHoler.p_image);



            viewHoler.count.setText(proLocalArrays.get(i).getQuantity());
            viewHoler.price.setText(proLocalArrays.get(i).getPrice());
            final int[] value = {Integer.valueOf(proLocalArrays.get(i).getQuantity())};
            viewHoler.code.setText("Code : " +proLocalArrays.get(i).getCode());
            viewHoler.decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value[0]--;
                    if (Integer.valueOf(viewHoler.count.getText().toString())<=0){
                         value[0] = 0;
                    }
                    viewHoler.count.setText(value[0]+"");
                    String values = Integer.valueOf(proLocalArrays.get(i).getPrice())
                            * value[0]+"";
                    productDb.updateProducts(proLocalArrays.get(i).getSerial(),String.valueOf(value[0]),
                            proLocalArrays.get(i).getColor(),proLocalArrays.get(i).getSize(),values);
                    viewHoler.price.setText(values);
                    validate();



//                    validate();
//                    notifyDataSetChanged();
                }
            });
            viewHoler.colorSpinner.setText(proLocalArrays.get(i).getColor());
            viewHoler.sizeSpinner.setText(proLocalArrays.get(i).getSize());
            if (proLocalArrays.get(i).getColor() == null && proLocalArrays.get(i).getColor().length() == 0){
                viewHoler.colorSpinner.setText("Nil");
            }else {
            }
            if (proLocalArrays.get(i).getSize() == null && proLocalArrays.get(i).getSize().length() == 0){
                viewHoler.sizeSpinner.setText("Nil");
            }else {

            }

            viewHoler.colorSpinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!viewHoler.colorSpinner.getText().toString().equals("Nil"))
                    {
                        showLists(proLocalArrays.get(i).getColorArray(),viewHoler.colorSpinner,false,proLocalArrays,i);
                    }
                }
            });
            viewHoler.sizeSpinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!viewHoler.sizeSpinner.getText().toString().equals("Nil")){
                        showLists(proLocalArrays.get(i).getSizeArray(),viewHoler.sizeSpinner,true,proLocalArrays,i);
                    }
                }
            });

            viewHoler.deleteProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productDb.deleteProduct(proLocalArrays.get(i).getSerial());
                    validate();
                    recyclerView.setAdapter(new Adapter(productDb.getAllProducts()));

                }
            });
            viewHoler.increate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value[0]++;
                    viewHoler.count.setText(value[0]+"");
                    String values = Integer.valueOf(proLocalArrays.get(i).getPrice())
                            * value[0]+"";

                    productDb.updateProducts(proLocalArrays.get(i).getSerial(),
                            String.valueOf(value[0]),    proLocalArrays.get(i).getColor(),proLocalArrays.get(i).getSize(),values);
                    viewHoler.price.setText(values);
                     validate();
                 //   notifyDataSetChanged();
                }
            });
            viewHoler.price.setText(Integer.valueOf(proLocalArrays.get(i).getPrice())
                    * Integer.valueOf(proLocalArrays.get(i).getQuantity())+"");
        }
        @Override
        public int getItemCount()
        {
            return proLocalArrays.size();
        }
    }
    void    showLists(String[] array,TextView views,boolean size,List<proLocalArray> proLocalArrays, int i)
    {
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.list, null);
            popupWindow = new PopupWindow(dialogView,
                250, ViewGroup.LayoutParams.WRAP_CONTENT);
            final ListView county;
            county = dialogView.findViewById(R.id.country_list);
            county.setVisibility(View.VISIBLE);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, R.layout.text, array);
            adapter.notifyDataSetChanged();
            county.setAdapter(adapter);
            county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (size){
                        productDb.updateProducts(proLocalArrays.get(i).getSerial(), proLocalArrays.get(i).getQuantity(),
                                proLocalArrays.get(i).getColor(), parent.getItemAtPosition(position).toString(),  proLocalArrays.get(i).getTotalAmount());
                    } else {
                        productDb.updateProducts(proLocalArrays.get(i).getSerial(), proLocalArrays.get(i).getQuantity(),
                                parent.getItemAtPosition(position).toString() , proLocalArrays.get(i).getSize(), proLocalArrays.get(i).getTotalAmount());
                    }
                    views.setText(parent.getItemAtPosition(position).toString());
                    popupWindow.dismiss();
                    recyclerView.setAdapter(new Adapter(productDb.getAllProducts()));

                }
            });
            popupWindow.showAsDropDown(views);
            Dim.dimBehind(popupWindow);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.update();
    }
    void  validate()
    {
        totalAmounts.clear();
        if (productDb.getAllProducts().size() == 0){
            totalAmount.setText("-");
        }
        for (int i=0;i<productDb.getAllProducts().size();i++) {
            addCount(Double.valueOf(productDb.getAllProducts().get(i).getTotalAmount()));
        }
        if (totalAmounts.size() !=0) {
            double my1 = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                my1 = totalAmounts.stream().mapToDouble(value -> value).sum();
            }

            totalAmount.setText(currencyFormat(String.format("%2f",my1)));
        }

    }
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }
    void addCount(Double f   ){
        Log.i("TAG","Total Amounts"+ f);
        totalAmounts.add(f);
    }
    void  calculate(String amount,boolean increse) {

    }
    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Cart.this, Login.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    class ViewHoler extends RecyclerView.ViewHolder
    {
        ImageView imageView,deleteProduct;
        TextView price,count,name,code;
        CardView increate,decrease;
        ImageView p_image;
        TextView sizeSpinner,colorSpinner;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            p_image = itemView.findViewById(R.id.p_image);
            sizeSpinner = itemView.findViewById(R.id.sizeSpinner);
            colorSpinner = itemView.findViewById(R.id.colorSpinner);
            name = itemView.findViewById(R.id.ProductName);
            code = itemView.findViewById(R.id.ProductCode);
            price = itemView.findViewById(R.id.totalAmount);
            count = itemView.findViewById(R.id.totalCount);
            increate = itemView.findViewById(R.id.increae);
            decrease = itemView.findViewById(R.id.decrease);
            deleteProduct = itemView.findViewById(R.id.deleteProduct);
        }
    }
}
