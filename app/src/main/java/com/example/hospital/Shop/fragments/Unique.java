package com.example.hospital.Shop.fragments;


import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.ProductDB.ProductDb;
import com.example.hospital.R;
import com.example.hospital.Shop.PaginationScrollListener;
import com.example.hospital.Shop.ProductModel;
import com.example.hospital.Shop.ProductPojo;
import com.example.hospital.Shop.ProductPresent;
import com.example.hospital.Shop.adapter.MyRecyclerViewAdapter;
import com.example.hospital.base.FragmentBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Unique extends FragmentBase implements ProductPresent {

    ProductModel productModel;
    private static final int PAGE_START = 30;
    @BindView(R.id.uradioAll)
    RadioButton uradioAll;
    @BindView(R.id.uradioGents)
    RadioButton uradioGents;
    @BindView(R.id.uradioLadies)
    RadioButton uradioLadies;
    @BindView(R.id.uradioUnisex)
    RadioButton uradioUnisex;
    @BindView(R.id.uradioGroup)
    RadioGroup uradioGroup;
    RadioButton radioButton;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    ProductDb productDb;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @BindView(R.id.uniqueRecycler)
    RecyclerView itemRecycler;

    private String filterIds = "10,11,15";

    @Override
    protected int layoutRes() {
        return R.layout.fragment_unique;
    }

    @Override
    protected void onViewBound(View view) {


        productModel = new ProductModel(getActivity(), this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        itemRecycler.setLayoutManager(gridLayoutManager);
        loadFirst();
        uradioAll.setChecked(true);

        itemRecycler.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                isLastPage = false;
                currentPage += 30;
                loadNext();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        uradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton)view. findViewById(selectedId);

                String buttonString =  radioButton.getText().toString();
                if (buttonString.equals("All")) {
                    filterIds = "10,11,15";
                }else if (buttonString.equals("Gents")) {
                    filterIds = "10";
                }else if (buttonString.equals("Ladies")) {
                    filterIds = "11";
                } else if (buttonString.equals("Unisex")){
                    filterIds = "15";
                }
                currentPage = PAGE_START;
                loadFirst();

            }
        });
    }

    @Override
    public void hideProgress() {
        dismissDialogue();
    }

    private void loadFirst() {
        productModel.getList("AddedOn", "0", "30", true, filterIds);
    }

    private void loadNext() {
        productModel.getList("AddedOn", String.valueOf(currentPage), "30", false,
                filterIds);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(ProductPojo.Result[] productPojo) {
        List modifiableList = new ArrayList(Arrays.asList(productPojo));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(modifiableList, getActivity());
        itemRecycler.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void showProgress() {
        showDialogue();
    }

    @Override
    public void showNext(ProductPojo.Result[] productPojo) {
        new Handler().postDelayed(() -> {
            List<ProductPojo.Result> list = Arrays.asList(productPojo);
            if (currentPage != PAGE_START) myRecyclerViewAdapter.removeLoading();
            myRecyclerViewAdapter.addAll(list);

            if (productPojo.length != 0) myRecyclerViewAdapter.addLoading();
            else isLastPage = true;
            isLoading = false;


        }, 300);
    }

}
