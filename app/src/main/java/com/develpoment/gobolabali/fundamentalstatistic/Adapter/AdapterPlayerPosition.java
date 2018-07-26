package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Player.EditPlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

public class AdapterPlayerPosition extends RecyclerView.Adapter<AdapterPlayerPosition.AdapterTeamViewHolder> {

    private List<DataPlayer> playerList;
    private Activity activity;

    DatabaseHelper db;

    public AdapterPlayerPosition(Activity activity, List<DataPlayer> playerList) {
        this.activity = activity;
        this.playerList = playerList;
    }

    @Override
    public AdapterPlayerPosition.AdapterTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_goal_keeper1, parent, false);
        AdapterTeamViewHolder adapterTeamViewHolder = new AdapterTeamViewHolder(view);
        return adapterTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPlayerPosition.AdapterTeamViewHolder holder, int position) {

        db = new DatabaseHelper(activity);
        final DataPlayer player = playerList.get(position);
        holder.txtnoPunggung.setText(player.getNopunggung());
        holder.txtNickName.setText(player.getNickname());
//        holder.txtPosisi.setText(player.getPosisi());

        final String idx = player.getId();

        final String idp= player.getId();
        final String fullp = player.getFullname();
        final String nickp = player.getNickname();
        final String posisi = player.getPosisi();

        final String punggung = player.getNopunggung();
        final String idteam = player.getIdteam();

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class AdapterTeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtnoPunggung, txtNickName, txtPosisi;
        CardView card;
        public AdapterTeamViewHolder(View itemView) {
            super(itemView);

            txtnoPunggung = (TextView) itemView.findViewById(R.id.textGkteam1);
            txtNickName = (TextView) itemView.findViewById(R.id.textnamaGKteam1);
//            txtPosisi = (TextView) itemView.findViewById(R.id.textPosisiPlayer);
//            card = (CardView) itemView.findViewById(R.id.cardPlayer);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
