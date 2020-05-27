package com.example.hospital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hospital.AllShoes.AllShoes;
import com.example.hospital.Register.Login;
import com.example.hospital.Shop.Sopping;
import com.example.hospital.Shop.fragments.Designer;
import com.example.hospital.Shop.fragments.HomeShop;
import com.example.hospital.Shop.fragments.Pharmacy;
import com.example.hospital.Shop.fragments.Unique;
import com.example.hospital.base.BaseActivity;
import com.example.hospital.cart.Cart;
import com.example.hospital.cart.orders.YourOrders;
import com.example.hospital.profile.Profile;
import com.example.hospital.profile.Shared;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingMain extends BaseActivity {

    @BindView(R.id.shoppingMainTool)
    Toolbar shoppingMainTool;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavBar;
    Fragment fragmentTemp,  currentFragment;
    public static final String TAB_HOME = "home";
    public static final String TAB_PARMACY = "appointment";
    public static final String TAB_UNIQUE = "unique";
    public static final String TAB_FOOTWEAR = "footwear";
    String current=TAB_HOME;
    @Override
    protected int layoutRes() {
        return R.layout.activity_shopping_main;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        int positionOfMenuItem = 3;
        MenuItem item = menu.getItem(positionOfMenuItem);
        SpannableString s = new SpannableString("My orders");
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        item.setTitle(s);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint({"NewApi", "ResourceType"})
    @Override
    protected void onViewBound() {
        bottomNavBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavBar.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.drawable.selecter,null)));
        selectedTab(TAB_HOME);
        setSupportActionBar(shoppingMainTool);
    }
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selectedTab(TAB_HOME);
                return true;
            case R.id.navigation_shoes:
                selectedTab(TAB_FOOTWEAR);
                return true;
            case R.id.navigation_unique:
                selectedTab(TAB_UNIQUE);
                return true;
            case R.id.navigation_pharmacy:
                selectedTab(TAB_PARMACY);
                return true;
        }
        return false;
    };
    private void selectedTab(String tabId) {

        switch (tabId) {
            case TAB_HOME:
                setTitle("Home");
                changeFragment(new Sopping(), TAB_HOME);
                break;
            case TAB_PARMACY:
                setTitle("Pharmacy Products");
                changeFragment(new Pharmacy(), TAB_PARMACY);
                break;
            case TAB_UNIQUE:
                setTitle("Unique Diabetic");
                changeFragment(new Unique(), TAB_UNIQUE);
                break;
            case TAB_FOOTWEAR:
                setTitle("New Designer ");
                changeFragment(new Designer(),TAB_FOOTWEAR);
                break;
        }
    }
    public void changeFragment(Fragment fragment, String tagFragmentName) {



        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment  currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.mainCpntainer, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart_action){
            startActivity(new Intent(ShoppingMain.this, Cart.class));
        }else if (item.getItemId() == R.id.profile){
            if (Shared.isLogged(getApplicationContext())){
                startActivity(new Intent(ShoppingMain.this, Profile.class));
            }else {
                Intent intent = new Intent(ShoppingMain.this, Login.class);
                intent.putExtra("key",1);
                startActivity(intent);
            }

        } else if (item.getItemId() == R.id.infoWhite){
            Intent intent = new Intent(ShoppingMain.this, AllShoes.class);
            //  intent.putExtra("key",1);
            startActivity(intent);

        } else if (item.getItemId() == R.id.your_orders) {
            startActivity(new Intent(getApplicationContext(), YourOrders.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
