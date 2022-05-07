package hu.mobilalkfejl.villanyorajelentes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import hu.mobilalkfejl.villanyorajelentes.R;
import hu.mobilalkfejl.villanyorajelentes.model.VillanyoraJelentes;

public class VillanyOraAdapter extends RecyclerView.Adapter<VillanyOraAdapter.ViewHolder>{
    // Member variables.
    private ArrayList<VillanyoraJelentes> mShoppingData;
    private ArrayList<VillanyoraJelentes> mSoppingDataAll;
    private Context mContext;
    private int lastPosition = -1;

    public VillanyOraAdapter(Context context, ArrayList<VillanyoraJelentes> itemsData) {
        this.mShoppingData = itemsData;
        this.mSoppingDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public VillanyOraAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_element, parent, false));
    }

    @Override
    public void onBindViewHolder(VillanyOraAdapter.ViewHolder holder, int position) {
        // Get current sport.
        VillanyoraJelentes currentItem = mShoppingData.get(position);
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(mContext, 17432578);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mShoppingData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        // Member Variables for the TextViews
        private TextView mOraAz;
        private TextView mOraAllas;
        private TextView mDatum;

        ViewHolder(View itemView) {
            super(itemView);
            // Initialize the views.
         //   mOraAz = itemView.findViewById(R.id.listOraAz);
         //   mOraAllas = itemView.findViewById(R.id.listOraAllas);
         //   mDatum = itemView.findViewById(R.id.listOraDatum);
        }

        void bindTo(VillanyoraJelentes currentItem){
            mOraAz.setText(currentItem.getOraAz());
            mOraAllas.setText(currentItem.getOraAllas());
            mDatum.setText(currentItem.getDatum().toString());

        }
    }
}