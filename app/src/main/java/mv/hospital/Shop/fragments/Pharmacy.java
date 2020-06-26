package mv.hospital.Shop.fragments;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;

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
public class Pharmacy extends FragmentBase implements ProductPresent {

    ProductModel productModel;
    private static final int PAGE_START = 30;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 400;
    private int currentPage = PAGE_START;
    ProductDb productDb;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @BindView(R.id.pharmacyRecycler)
    RecyclerView itemRecycler;


    @Override
    protected void onViewBound(View view) {
        productDb = new ProductDb(getActivity());
        productModel = new ProductModel(getActivity(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        itemRecycler.setLayoutManager(gridLayoutManager);
        productModel.getList("AddedOn","0", "30",true,"14");
        itemRecycler.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                isLastPage = false;
                currentPage += 30;
                productModel.getList("AddedOn",String.valueOf(currentPage), "30",false,"14");
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
    }
    @Override
    public void showNext(ProductPojo.Result[] productPojo) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ProductPojo.Result> list = Arrays.asList(productPojo);
                if (currentPage != PAGE_START) myRecyclerViewAdapter.removeLoading();
                myRecyclerViewAdapter.addAll(list);
                if (productPojo.length != 0)  myRecyclerViewAdapter.addLoading();
                else isLastPage  = true;
                isLoading = false;
            }
        },300);
    }


    @Override
    public void hideProgress() {
        dismissDialogue();
    }
    @Override
    public void showToast(String message) {

    }

    @Override
    public void showDetails(ProductPojo.Result[] productPojo) {
        List modifiableList = new ArrayList(Arrays.asList(productPojo));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(modifiableList,getActivity());
        itemRecycler.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void showProgress() {
        showDialogue();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_pharmacy;
    }

}
