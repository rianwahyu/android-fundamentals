package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Helpers.ItemClickListener;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity2;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rian on 03/07/2018.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {
    private ClickListener mListener;
    private List<DataPlayerMatch> mainInfo = new ArrayList();



    public class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener clickListener;
        TextView txtAccountNumber;
        TextView txtName;
        TextView txtPlayerNumber;

        public PlayerHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtPlayerNumber = (TextView) itemView.findViewById(R.id.txtPlayerNumber);
//            this.txtAccountNumber = (TextView) itemView.findViewById(C0379R.id.txtAccountNumber);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public PlayerAdapter(ClickListener listener) {
        this.mListener = listener;
    }

    public void addItem(DataPlayerMatch item) {
        mainInfo.add(item);
        notifyDataSetChanged();
    }

    public DataPlayerMatch getSelectedItem(int position) {
        return (DataPlayerMatch) this.mainInfo.get(position);
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.player_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        holder.txtName.setText(((DataPlayerMatch) mainInfo.get(position)).getNickname());
        holder.txtPlayerNumber.setText(((DataPlayerMatch) mainInfo.get(position)).getNopunggun());

    }

    @Override
    public int getItemCount() {
        return mainInfo.size();
    }
//    DBConfig myDatabase;

    public interface ClickListener {
        void onClick(int i);
    }
}
