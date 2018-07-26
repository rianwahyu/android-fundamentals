package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Helpers.ItemClickListener;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Player;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

public class CadanganAdapter2 extends RecyclerView.Adapter<CadanganAdapter2.PlayerHolder> {
    private ClickListener mListener;
    private List<Player> mainInfo = new ArrayList();


//    DBConfig myDatabase;

    public interface ClickListener {
        void onClick2(int i);

        //void onClick2(int position);
    }

    public class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btnCadangan;
        private ItemClickListener clickListener;
        TextView txtNickname;

        public PlayerHolder(View itemView) {
            super(itemView);
            txtNickname = (TextView) itemView.findViewById(R.id.txtNickname);
            btnCadangan = (Button) itemView.findViewById(R.id.btnCadangan);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
                mListener.onClick2(getLayoutPosition());
        }
    }

    public CadanganAdapter2(ClickListener listener) {
        this.mListener = listener;
    }

    public void addItem(Player item) {
        mainInfo.add(item);
        notifyDataSetChanged();
    }

    public void clearItem() {
        mainInfo.clear();
        notifyDataSetChanged();
    }

    public Player getSelectedItem(int position) {
        return (Player) mainInfo.get(position);
    }

    public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cadangan_raw, parent, false));
    }

    public void onBindViewHolder(PlayerHolder holder, int position) {
        holder.txtNickname.setText(((Player) mainInfo.get(position)).getNickname());
        holder.btnCadangan.setText(((Player) mainInfo.get(position)).getPlayer_number());
        if (((Player) mainInfo.get(position)).getStatus().equals("0")) {
            holder.btnCadangan.setBackgroundResource(R.drawable.touch_blue_dark);
        }else {
            holder.btnCadangan.setBackgroundResource(R.drawable.touch_blue);
        }
    }

    public int getItemCount() {
        return mainInfo.size();
    }
}
