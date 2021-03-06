package mv.hospital.cart;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.codesgood.views.JustifiedTextView;
import mv.hospital.AllShoes.AllShoes;
import mv.hospital.Dim;
import mv.hospital.ProductDB.ProductDb;
import com.hospital.R;
import mv.hospital.Register.Login;
import mv.hospital.Shop.ProductArray;
import mv.hospital.Shop.reviews.ReviewActivity;
import mv.hospital.Shop.reviews.ReviiewAdapter;
import mv.hospital.cart.orders.YourOrders;
import mv.hospital.cart.pojo.ProductCommomPojo;
import mv.hospital.profile.Profile;
import mv.hospital.profile.Shared;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator2;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ViewProduct extends AppCompatActivity implements ProductPresent {
    Toolbar mainTool;
    JustifiedTextView alignText;
    FloatingActionButton addCart;
    ArrayList<ProductArray> productArrays;
    TextView sizeSpinenr, colorSpinner, nameOFproduct, productAmount, viewMoreButton;
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
    MaterialRatingBar ratingBar;
    CircleIndicator2 pagerIndicator;
    @BindView(R.id.specsRecycler)
    RecyclerView specsRecycler;
    @BindView(R.id.reviews_recycler)
    RecyclerView reviewRecycler;
    boolean isExpanded = true;
    Dialog otpDialog;
    @BindView(R.id.rate_product)
    LinearLayout rateProduct;
    @BindView(R.id.total_user_review)
    TextView totalUserReview;
    @BindView(R.id.your_rating_layout)
    LinearLayout your_rating_layout;
    @BindView(R.id.your_rating)
    MaterialRatingBar your_rating;
    @BindView(R.id.edit_review)
    TextView editRating;
    MaterialRatingBar postratingBar;
    EditText descriptionReview;
    CardView postRating;
    TextView productnameReview,viewAllReviews;
    ProductCommomPojo productCommomPojo;
    boolean newReview = false;
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
        viewMoreButton = findViewById(R.id.viewMoreButton);
        ratingBar = findViewById(R.id.star_rating);
        viewAllReviews = findViewById(R.id.view_all_reviews);
        // alignText.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent1));
        productArrays = new ArrayList<>();
        setSupportActionBar(mainTool);
        otpDialog = new Dialog(this);
        otpDialog.setContentView(R.layout.rating_design);
        otpDialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        postratingBar = otpDialog. findViewById(R.id.post_rating_tab);
        descriptionReview = otpDialog. findViewById(R.id.review_descriptions);
        postRating  = otpDialog. findViewById(R.id.post_rating);
        productnameReview = otpDialog.findViewById(R.id.productname_review);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            key = intent.getStringExtra("key");
            imageUrl = intent.getStringExtra("image");
            if (intent.getStringExtra("limage") != null) {
                Glide.with(getApplicationContext()).load(intent.getStringExtra("image")).into(myProductImage);
            } else {
                myProductImage.setImageResource(R.drawable.error);
            }
            productViewPresent.getProduct(key, Shared.id(getApplicationContext()), "Products");

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
        viewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), ReviewActivity.class);
                intent1.putExtra("id",key);
                startActivity(intent1);
            }
        });

        addtocart.setOnClickListener(view -> {
            if (productCommomPojo != null) {
                productDb.addProducts(productCommomPojo.getResult()[0].getProductName(),
                        productCommomPojo.getResult()[0].getProductId(),
                        colorSpinner.getText().toString(),
                        productCommomPojo.getResult()[0].getProductCode(), "1",
                        sizeSpinenr.getText().toString()
                        , productCommomPojo.getResult()[0].getPrice(),
                        convertArrayToString(items), convertArrayToString(sizes), imageUrl);
                showToast("Product added successfully");
            }
        });
        buyNow.setOnClickListener(view -> {
            if (productCommomPojo != null) {
                productDb.addProducts(productCommomPojo.getResult()[0].getProductName(),
                        productCommomPojo.getResult()[0].getProductId(),
                        colorSpinner.getText().toString(),
                        productCommomPojo.getResult()[0].getProductCode(), "1",
                        sizeSpinenr.getText().toString()
                        , productCommomPojo.getResult()[0].getPrice(),
                        convertArrayToString(items), convertArrayToString(sizes), imageUrl);
                startActivity(new Intent(getApplicationContext(), Cart.class));
            }
        });

        ratingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                otpDialog.show();
                postratingBar.setRating(rating);
                descriptionReview.setText("");
                productnameReview.setText(productCommomPojo.getResult()[0].getProductName());
                newReview = true;
            }
        });
        editRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpDialog.show();
                newReview = false;
                postratingBar.setRating(Float.parseFloat(productCommomPojo.getResult()[0].getUserRating()));
                descriptionReview.setText(productCommomPojo.getResult()[0].getUserDescription());
                productnameReview.setText(productCommomPojo.getResult()[0].getProductName());

            }
        });
        postRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newReview) {
                    String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.getDefault()).format(new Date());
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("ReviewId",productCommomPojo.getResult()[0].getReviewId() );
                    jsonObject.addProperty("Description",descriptionReview.getText().toString());
                    jsonObject.addProperty("Rating", postratingBar.getRating());
                    jsonObject.addProperty("ReviewDate", date);
                    productViewPresent.updateReview(jsonObject);
                } else {
                    String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.getDefault()).format(new Date());
                    JsonObject json= new JsonObject();
                    json.addProperty("table","ProductsReview");
                    json.addProperty("multipleInsert",false);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("ProductId",productCommomPojo.getResult()[0].getProductId());
                    jsonObject.addProperty("Description",descriptionReview.getText().toString());
                    jsonObject.addProperty("Rating", postratingBar.getRating());
                    jsonObject.addProperty("ReviewDate", date);
                    jsonObject.addProperty("UserId",Shared.id(getApplicationContext()));
                    json.add("data",jsonObject);
                    productViewPresent.createReview(json);

                }
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
        int positionOfMenuItem = 3;
        MenuItem item = menu.getItem(positionOfMenuItem);
        SpannableString s = new SpannableString("My orders");
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        item.setTitle(s);
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
            startActivity(intent);
        } else if (item.getItemId() == R.id.your_orders) {
            startActivity(new Intent(getApplicationContext(), YourOrders.class));
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
        if (otpDialog.isShowing()) {
            otpDialog.dismiss();
            productViewPresent.getProduct(key, Shared.id(getApplicationContext()), "Products");
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProductCommom(ProductCommomPojo sizePojos) {
        this.productCommomPojo = sizePojos;
        alignText.setText(sizePojos.getResult()[0].getDescription());
        nameOFproduct.setText(sizePojos.getResult()[0].getProductName());
        productAmount.setText(sizePojos.getResult()[0].getPrice());
        totalUserReview.setText(sizePojos.getResult()[0].getTotalReviews());
        if (!sizePojos.getResult()[0].getCategoryId().equals("14")) {
            setSpecslist();
        }
        if (sizePojos.getResult()[0].getUserRating() != null) {
            your_rating_layout.setVisibility(View.VISIBLE);
            rateProduct.setVisibility(View.GONE);
            your_rating.setRating(Float.parseFloat(sizePojos.getResult()[0].getUserRating()));
        } else {
            your_rating_layout.setVisibility(View.GONE);
            if (sizePojos.getResult()[0].getOrderCount() != null) {
                if (Shared.isLogged(getApplicationContext())) {
                    rateProduct.setVisibility(View.VISIBLE);
                } else  {
                    rateProduct.setVisibility(View.GONE);
                }
            }
        }
        if (sizePojos.getProductReview().length == 3) {
            viewAllReviews.setVisibility(View.VISIBLE);
        } else {
            viewAllReviews.setVisibility(View.GONE);
        }
        List<String> sizeList = Arrays.asList(sizePojos.getResult()[0].getSize().split(","));
        List<String> colorList = Arrays.asList(sizePojos.getResult()[0].getColors().split(","));
        List<String> imageList = Arrays.asList(sizePojos.getResult()[0].getImage().split(","));
        items = new String[colorList.size()];
        if (items.length != 0) {
            for (int i = 0; i < colorList.size(); i++) {
                items[i] = colorList.get(i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, R.layout.text, items);
            colorSpinner.setText(items[0]);
            if (colorList.size() == 0) {
                colorSpinner.setVisibility(View.GONE);
            }
        } else {
            colorSpinner.setVisibility(View.GONE);
            colorSpinner.setText("Nil");
        }
        sizes = new String[sizeList.size()];

        if (sizes.length != 0) {
            for (int i = 0; i < sizeList.size(); i++) {
                sizes[i] = sizeList.get(i);
            }
            sizeSpinenr.setText(sizes[0]);
            if (sizeList.size() == 0) {
                sizeSpinenr.setVisibility(View.GONE);
            }
        } else {
            sizeSpinenr.setVisibility(View.GONE);
            sizeSpinenr.setText("Nil");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        imageRecycler.setLayoutManager(linearLayoutManager);
        imageRecycler.setAdapter(new ImageAdapter(getApplicationContext(), imageList, key));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        imageRecycler.setOnFlingListener(null);
        pagerSnapHelper.attachToRecyclerView(imageRecycler);
        pagerIndicator.attachToRecyclerView(imageRecycler, pagerSnapHelper);
        setReviewRecycler(sizePojos.getProductReview());
    }

    private void setReviewRecycler(ProductCommomPojo.ProductReview[] review) {

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        reviewRecycler.setLayoutManager(linearLayoutManager);
        ReviiewAdapter reviiewAdapter = new ReviiewAdapter();
        reviewRecycler.setAdapter(reviiewAdapter);
        reviiewAdapter.setReview(review);
        reviiewAdapter.notifyDataSetChanged();
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

        items = new String[colorPojos.length];
        if (colorPojos.length != 0) {
            for (int i = 0; i < colorPojos.length; i++) {
                items[i] = colorPojos[i].getColorName();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, R.layout.text, items);
            colorSpinner.setText(items[0]);
            if (items[0].equals("Nil") || items[0].equals("Nill")) {
                colorSpinner.setVisibility(View.GONE);
            }
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
            if (sizes[0].equals("Nil") || sizes[0].equals("Nill")) {
                sizeSpinenr.setVisibility(View.GONE);
            }
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
        //    imageRecycler.setAdapter(new ImageAdapter(getApplicationContext(), sizePojos));
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
