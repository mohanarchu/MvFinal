package mv.hospital.Shop.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hospital.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {


    AddressPojo.Result[] addressPojo;
    Listener listener;




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setList(addressPojo[position]);
    }

    @Override
    public int getItemCount() {
        return addressPojo == null ? 0 : addressPojo.length;
    }

    public void setList(AddressPojo.Result[] addressPojo) {
        this.addressPojo = addressPojo;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void selectedAddress(AddressPojo.Result result, int pos);
        void setPrimary(String id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ad_mobilenumber)
        TextView adMame;
        @BindView(R.id.ad_phone)
        TextView adPhone;
        @BindView(R.id.ad_address)
        TextView adAddress;
        @BindView(R.id.primary_color)
        LinearLayout primaryColor;
        @BindView(R.id.set_as_primary)
        TextView setAsPrimary;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,  itemView);
            itemView.setOnClickListener(view -> listener.selectedAddress(addressPojo[getAdapterPosition()],getAdapterPosition()));
        }
        void setList(AddressPojo.Result result) {
            adMame.setText(result.getName());
            adPhone.setText(result.getPhone());
            String address  = result.getAddress() + ",\n" + result.getCity() +","+result.getState() +"-"+ result.getPostalcode();
            adAddress.setText(address);
            setAsPrimary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            if (result.getPrimary().equals("1")) {

                primaryColor.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.colorPrimary));
            } else {

                primaryColor.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.white));
            }
        }
    }
}
