package com.example.hospital.cart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital.AllShoes.AllShoes;
import com.example.hospital.Dim;
import com.example.hospital.ProductDB.ProductDb;
import com.example.hospital.R;
import com.example.hospital.Register.Login;
import com.example.hospital.Shop.ProductArray;
import com.example.hospital.profile.Profile;
import com.example.hospital.profile.Shared;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator2;

public class ViewProduct extends AppCompatActivity implements ProductPresent {
    Toolbar mainTool;
    TextView alignText;
    FloatingActionButton addCart;
    ArrayList<ProductArray> productArrays;
    TextView sizeSpinenr, colorSpinner, nameOFproduct, productAmount;
    CardView addtocart, buyNow;
    LinearLayout goneLayout;
    String key;
    ProductViewPresent productViewPresent;
    ImageView myProductImage;
    ProductDb productDb;
    ProgressDialog progressDialog;
    DetailsPojo.Result[] detailsPojos;
    public static String strSeparator = "__,__";
    String[] items, sizes;
    String imageUrl;
    RecyclerView imageRecycler;
    PopupWindow popupWindow;
    CircleIndicator2 pagerIndicator;
    @BindView(R.id.specsRecycler)
    RecyclerView specsRecycler;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        mainTool = findViewById(R.id.producttool);
        mainTool.setTitle("Product");
        colorSpinner = findViewById(R.id.colorSpinner);
        // addCart = findViewById(R.id.addCart);
        productViewPresent = new ProductViewPresent(getApplicationContext(), this);
        productDb = new ProductDb(getApplicationContext());
        //  mainTool.setTitleTextColor(Color.parseColor("#FFFFFF"));
        alignText = findViewById(R.id.alignText);
        sizeSpinenr = findViewById(R.id.sizeSpinner);
        goneLayout = findViewById(R.id.goneLayout);
        myProductImage = findViewById(R.id.myProductImage);
        addtocart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buyNow);
        productAmount = findViewById(R.id.amountOfPro);
        imageRecycler = findViewById(R.id.imageRecycler);
        pagerIndicator = findViewById(R.id.pagerIndicator);
        nameOFproduct = findViewById(R.id.nameOfProduct);
        // alignText.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent1));
        productArrays = new ArrayList<>();
        setSupportActionBar(mainTool);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            key = intent.getStringExtra("key");
            imageUrl = intent.getStringExtra("image");
            if (intent.getStringExtra("limage") != null) {
                Glide.with(getApplicationContext()).load(intent.getStringExtra("image")).into(myProductImage);
            } else {
                myProductImage.setImageResource(R.drawable.error);
            }

            productViewPresent.getDetails(key, "ProductId", "Products");
        }

        colorSpinner.setOnClickListener(view -> {
            if (!colorSpinner.getText().toString().equals("Nil")) {
                showLists(items, colorSpinner, false);
            }
        });
        sizeSpinenr.setOnClickListener(view -> {
            if (!sizeSpinenr.getText().toString().equals("Nil")) {
                showLists(sizes, sizeSpinenr, true);
            }
        });


        addtocart.setOnClickListener(view -> {
            if (detailsPojos != null) {
                productDb.addProducts(detailsPojos[0].getProductName(), detailsPojos[0].getProductId(),
                        colorSpinner.getText().toString(),
                        detailsPojos[0].getProductCode(), "1", sizeSpinenr.getText().toString()
                        , detailsPojos[0].getPrice(), convertArrayToString(items), convertArrayToString(sizes), imageUrl);
                showToast("Product added successfully");
            }
        });
        buyNow.setOnClickListener(view -> {
            if (detailsPojos != null) {
                productDb.addProducts(detailsPojos[0].getProductName(), detailsPojos[0].getProductId(), colorSpinner.getText().toString(),
                        detailsPojos[0].getProductCode(), "1", sizeSpinenr.getText().toString()
                        , detailsPojos[0].getPrice(), convertArrayToString(items), convertArrayToString(sizes), imageUrl);
                startActivity(new Intent(getApplicationContext(), Cart.class));
            }
        });


    }

    public void setSpecslist() {
        specsRecycler.setVisibility(View.VISIBLE);
        ArrayList<SpecsArray> specsArrays = new ArrayList<>();
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.light_weight), "Light weight"));
      //  specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.flexible), "Flexible"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.shock_absorbing_sole), "Shock absorbing sole"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.soft_fabric), "Soft fabric"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.air_pockets), "Air pockets"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.comfort_lining), "Comfort lining"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.foam_padding), "Foam padding"));
        specsArrays.add(new SpecsArray(getResources().getDrawable(R.drawable.padded_collar), "Padded collar"));
        SpecsAdapter specsAdapter = new SpecsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        specsRecycler.setLayoutManager(linearLayoutManager);
        specsRecycler.setAdapter(specsAdapter);
        specsAdapter.setArray(specsArrays);
        specsAdapter.notifyDataSetChanged();
    }

    public static String convertArrayToString(String[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i];
            if (i < array.length - 1) {
                str = str + strSeparator;
            }
        }
        return str;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart_action) {
            startActivity(new Intent(ViewProduct.this, Cart.class));
        } else if (item.getItemId() == R.id.profile) {
            if (Shared.isLogged(getApplicationContext())) {
                startActivity(new Intent(ViewProduct.this, Profile.class));
            } else {
                Intent intent = new Intent(ViewProduct.this, Login.class);
                intent.putExtra("key", 1);
                startActivity(intent);
            }

        } else if (item.getItemId() == R.id.infoWhite) {
            Intent intent = new Intent(ViewProduct.this, AllShoes.class);
            //  intent.putExtra("key",1);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
        progressDialog.setMessage("Please wait..");
    }

    @Override
    public void hideProgress() {

        progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(DetailsPojo.Result[] detailsPojo) {


        detailsPojos = detailsPojo;
        //Log.i("TAg","ProductDetails"+detailsPojo[0].getProductName());
        alignText.setText(detailsPojo[0].getDescription());
        nameOFproduct.setText(detailsPojo[0].getProductName());
        productViewPresent.getColor(key);
        productAmount.setText(detailsPojo[0].getPrice());
        if (!detailsPojo[0].getCategoryId().equals("14")) {
            setSpecslist();
        }
    }

    @Override
    public void showColor(ColorPojo.Result[] colorPojos) {
        //   Log.i("TAg","ProductDetails"+colorPojos[0].getColorName());
        items = new String[colorPojos.length];
        if (colorPojos.length != 0) {
            for (int i = 0; i < colorPojos.length; i++) {
                items[i] = colorPojos[i].getColorName();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, R.layout.text, items);
            colorSpinner.setText(items[0]);

        } else {
            colorSpinner.setVisibility(View.GONE);
            colorSpinner.setText("Nil");
        }

        productViewPresent.getSize(key);
    }

    @Override
    public void showSize(SizePojo.Result[] sizePojos) {
        sizes = new String[sizePojos.length];
        productViewPresent.getImages(key);
        if (sizePojos.length != 0) {
            for (int i = 0; i < sizePojos.length; i++) {
                sizes[i] = sizePojos[i].getSize();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, R.layout.text, sizes);
            sizeSpinenr.setText(sizes[0]);
        } else {
            sizeSpinenr.setVisibility(View.GONE);
            sizeSpinenr.setText("Nil");
        }
    }

    @Override
    public void showImages(ImegesPojo.Result[] sizePojos) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        imageRecycler.setLayoutManager(linearLayoutManager);
        imageRecycler.setAdapter(new ImageAdapter(getApplicationContext(), sizePojos));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imageRecycler);
        pagerIndicator.attachToRecyclerView(imageRecycler, pagerSnapHelper);
    }

    void showLists(String[] array, View view, boolean size) {
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

                if (size) {
                    sizeSpinenr.setText(parent.getItemAtPosition(position).toString());
                } else {
                    colorSpinner.setText(parent.getItemAtPosition(position).toString());
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(view);
        Dim.dimBehind(popupWindow);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();

    }

}
