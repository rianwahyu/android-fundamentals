package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Helpers.ItemClickListener;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Utils;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Player;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rian on 03/07/2018.
 */

public class PlayerMatchAdapterLineUp extends RecyclerView.Adapter<PlayerMatchAdapterLineUp.PlayerHolder> {

    private ClickListener mListener;
    private String mPos;
    private List<Player> mainInfo = new ArrayList();


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
        TextView txtStatus;

        public PlayerHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.textNamaPemain);
            txtPlayerNumber = (TextView) itemView.findViewById(R.id.txtNoPemain);
//            txtAccountNumber = (TextView) itemView.findViewById(C0379R.id.txtAccountNumber);
            //llPosition = (LinearLayout) itemView.findViewById(R.id.llPosition);
            txtPosition = (TextView) itemView.findViewById(R.id.textPosisiPemain);
            txtStatus = (TextView) itemView.findViewById(R.id.textLineup);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public PlayerMatchAdapterLineUp(ClickListener listener, String pos) {
        this.mListener = listener;
        this.mPos = pos;
    }

    public void addItem(Player item) {
        mainInfo.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mainInfo.clear();
        notifyDataSetChanged();
    }


    public Player getSelectedItem(int position) {
        return (Player) mainInfo.get(position);
    }

    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_match_player, parent, false));
    }

    public void onBindViewHolder(PlayerHolder holder, int position) {
        Player cur = (Player) mainInfo.get(position);
        holder.txtName.setText(((Player) mainInfo.get(position)).getNickname());
        holder.txtPlayerNumber.setText(((Player) mainInfo.get(position)).getPlayer_number());
        if (cur.getStatus().equals("0")) {
            holder.txtStatus.setText("TIDAK");
        }else if (cur.getStatus().equals("1")){
            holder.txtStatus.setText("YA");
        }else {
            holder.txtStatus.setText("DIHAPUS");
        }
        String pos = Utils.convertPositionNumber(cur.getPosition(), mPos);
        holder.txtPosition.setText(pos);
    }

    public int getItemCount() {
        return mainInfo.size();
    }

}
