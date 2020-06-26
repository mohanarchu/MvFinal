package mv.hospital.cart.orders;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hospital.R;
import mv.hospital.base.BaseActivity;
import mv.hospital.cart.OldOrders.OldOrders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YourOrders extends BaseActivity implements OrderView {


    OrderPresenter orderPresenter;
    ProgressDialog progressDialog;
    @BindView(R.id.ordersTool)
    Toolbar ordersTool;
    @BindView(R.id.recyclerOrders)
    RecyclerView recyclerOrders;


    @Override
    protected void onViewBound() {
        orderPresenter = new OrderPresenter(getApplicationContext(), this);
        orderPresenter.getProViews();
        setSupportActionBar(ordersTool);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerOrders.setLayoutManager(layoutManager);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_your_orders;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
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
    public void getDetails(OrderPojo.Result[] orderPojo) {

        if (orderPojo.length != 0){
            recyclerOrders.setAdapter(new MyRecyclerViewAdapter(orderPojo));
            recyclerOrders.setVisibility(View.VISIBLE);
        } else  {
            recyclerOrders.setVisibility(View.GONE);
        }
    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<viewHolder> {

        OrderPojo.Result[] results;


        public MyRecyclerViewAdapter(OrderPojo.Result[] results) {
            this.results = results;
        }

        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.old_orders, parent, false);
            return new viewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            OrderPojo.Result or = results[position];
            holder.orderID.setText("Order Id  : "+ or.getOrderId());
            holder.orderAmounts.setText(or.getOrderAmount());
            holder.addressDeytails.setText(or.getShipName() +", \n"+or.getShipAddress() +", \n"+
                    or.getShipCity()+", \n"+or.getShipState()+" - "+or.getShipZipCode());
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
            try {
                date = dateFormat.parse(or.getOrderDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
            assert date != null;
            String dateStr = formatter.format(date);
            holder.orderedDate.setText(dateStr);
            holder.phoneNumber.setText(or.getShipContactNo());
            holder.deliveryStatus.setText("Stats");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(YourOrders.this, OldOrders.class);
                    intent.putExtra("key",or.getOrderId());
                    intent.putExtra("amount",or.getOrderAmount());
                   // intent.putParcelableArrayListExtra()

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return results.length;
        }
    }

    class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.orderID)
        TextView orderID;
        @BindView(R.id.orderAmounts)
        TextView orderAmounts;
        @BindView(R.id.addressDeytails)
        TextView addressDeytails;
        @BindView(R.id.orderedDate)
        TextView orderedDate;
        @BindView(R.id.deliveryStatus)
        TextView deliveryStatus;
        @BindView(R.id.phoneNumbers)
        TextView phoneNumber;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
