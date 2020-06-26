package mv.hospital.Shop.fragments;


import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mv.hospital.ProductDB.ProductDb;
import com.hospital.R;
import mv.hospital.Shop.PaginationScrollListener;
import mv.hospital.Shop.ProductModel;
import mv.hospital.Shop.ProductPojo;
import mv.hospital.Shop.ProductPresent;
import mv.hospital.Shop.adapter.MyRecyclerViewAdapter;
import mv.hospital.base.FragmentBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Designer extends FragmentBase implements ProductPresent {
    ProductModel productModel;
    private static final int PAGE_START = 30;
    @BindView(R.id.dAll)
    RadioButton dAll;
    @BindView(R.id.dGents)
    RadioButton dGents;
    @BindView(R.id.dLadies)
    RadioButton dLadies;
    @BindView(R.id.dradioGroup)
    RadioGroup dradioGroup;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    ProductDb productDb;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @BindView(R.id.designerRecycler)
    RecyclerView itemRecycler;
    private RadioButton radioButton;
    private String filterIds = "16,17";

    @Override
    protected int layoutRes() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void onViewBound(View view) {

        productModel = new ProductModel(getActivity(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        itemRecycler.setLayoutManager(gridLayoutManager);
        dAll.setChecked(true);
        loadFirst();


        itemRecycler.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                isLastPage = false;
                currentPage += 30;
                //    productModel.getList("AddedOn",String.valueOf(currentPage), "20",false,"14,10,11,15,16,17");
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


        dradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton)view. findViewById(selectedId);

                String buttonString =  radioButton.getText().toString();
                switch (buttonString) {
                    case "All":
                        filterIds = "16,17";
                        break;
                    case "Gents":
                        filterIds = "16";
                        break;
                    case "Ladies":
                        filterIds = "17";
                        break;
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

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void loadFirst() {

        productModel.getList("AddedOn", "0", "30", true, filterIds);
    }

    private void loadNext() {
        productModel.getList("AddedOn", String.valueOf(currentPage), "30", false, filterIds);
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
