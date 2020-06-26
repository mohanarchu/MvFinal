package mv.hospital.Shop;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import mv.hospital.ProductDB.ProductDb;
import com.hospital.R;
import mv.hospital.Shop.adapter.MyRecyclerViewAdapter;
import mv.hospital.base.FragmentBase;
import mv.hospital.profile.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class Sopping extends FragmentBase implements ProductPresent{

    ProductModel productModel;
    private static final int PAGE_START = 30;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 400;
    private int currentPage = PAGE_START;
    ProductDb productDb;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @BindView(R.id.itemRecycler)
    RecyclerView itemRecycler;


    @Override
    protected void onViewBound(View view) {

        Log.i("TAG","User id "+ Shared.id(getActivity()) );
        productDb = new ProductDb(getActivity());


        productModel = new ProductModel(getActivity(),this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        itemRecycler.setLayoutManager(gridLayoutManager);


        productModel.getList("AddedOn","0", "30",true, "14,10,11,15,16,17");

        itemRecycler.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                isLastPage = false;
                currentPage += 30;
                productModel.getList("AddedOn",String.valueOf(currentPage),
                        "30",false,"14,10,11,15,16,17");
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
        new Handler().postDelayed(() -> {
            List<ProductPojo.Result> list = Arrays.asList(productPojo);
            if (currentPage != PAGE_START) myRecyclerViewAdapter.removeLoading();
            myRecyclerViewAdapter.addAll(list);
            if (productPojo.length != 0)  myRecyclerViewAdapter.addLoading();
            else isLastPage  = true;
            isLoading = false;
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
        return R.layout.activity_sopping;
    }



}
