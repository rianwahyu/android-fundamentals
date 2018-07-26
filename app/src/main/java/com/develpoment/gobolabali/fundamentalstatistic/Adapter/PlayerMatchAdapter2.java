package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Helpers.ItemClickListener;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Utils;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rian on 03/07/2018.
 */

public class PlayerMatchAdapter2 extends RecyclerView.Adapter<PlayerMatchAdapter2.PlayerHolder> {

    private ClickListener mListener;
    private String mPos;
    private List<DataPlayerMatch> mainInfo = new ArrayList();


    public interface ClickListener {
        void onClick(int i);
    }

    public class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener clickListener;
        LinearLayout llPosition;
        TextView txtAccountNumber;
        TextView txtName;
        TextView txtPlayerNumber;
        TextView txtPosition;

        public PlayerHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPlayerNumber = (TextView) itemView.findViewById(R.id.txtPlayerNumber);
//            txtAccountNumber = (TextView) itemView.findViewById(C0379R.id.txtAccountNumber);
            llPosition = (LinearLayout) itemView.findViewById(R.id.llPosition);
            txtPosition = (TextView) itemView.findViewById(R.id.txtPlayerPosition);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public PlayerMatchAdapter2(ClickListener listener, String pos) {
        this.mListener = listener;
        this.mPos = pos;
    }

    public void addItem(DataPlayerMatch item) {
        mainInfo.add(item);
        notifyDataSetChanged();
    }

    public DataPlayerMatch getSelectedItem(int position) {
        return (DataPlayerMatch) mainInfo.get(position);
    }

    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.player_match_raw, parent, false));
    }

    public void onBindViewHolder(PlayerHolder holder, int position) {
        DataPlayerMatch cur = (DataPlayerMatch) mainInfo.get(position);
        holder.txtName.setText(((DataPlayerMatch) mainInfo.get(position)).getNickname());
        holder.txtPlayerNumber.setText(((DataPlayerMatch) mainInfo.get(position)).getNopunggun());
//        holder.txtAccountNumber.setText(((DataPlayerMatch) mainInfo.get(position)).getAccount_number());
        if (cur.getStatus().equals("1")) {
            holder.llPosition.setVisibility(View.GONE);
            holder.txtName.setVisibility(View.GONE);
            holder.txtPlayerNumber.setVisibility(View.GONE);
            return;
        }
        String pos = Utils.convertPositionNumber(cur.getPosisi(), mPos);
        holder.llPosition.setVisibility(View.VISIBLE);
        holder.txtPosition.setText(pos);
    }

    public int getItemCount() {
        return mainInfo.size();
    }

}
