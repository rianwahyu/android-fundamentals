package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.EditTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.AdapterTeamViewHolder> {

    private List<DataTeam> teamList;
    private Activity activity;



    DatabaseHelper db;

    public AdapterTeam(Activity activity, List<DataTeam> teamList) {
        this.activity = activity;
        this.teamList = teamList;
    }



    @Override
    public AdapterTeam.AdapterTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_team, parent, false);
        AdapterTeamViewHolder adapterTeamViewHolder = new AdapterTeamViewHolder(view);
        return adapterTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTeam.AdapterTeamViewHolder holder, int position) {
        db = new DatabaseHelper(activity);
        final DataTeam team = teamList.get(position);
        holder.txt_Nama_Team.setText(team.getNama());

        final String idTeam = team.getId();
        final String hm = idTeam;

        final String nmTeam = team.getNama();

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity,"Ngedit",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), EditTeamActivity.class);
                intent.putExtra("id",hm);
                intent.putExtra("nama",nmTeam);
                ((view.getContext())).startActivity(intent);
            }
        });

        holder.imghapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(activity)
                        .setTitle("Hapus Data Team ?")
                        .setBackgroundColor(Color.parseColor("#E12929"))
                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                        .setMessage("Anda Yakin Ingin Menghapus Data Team " + nmTeam +" ?")
                        .setNegativeBtnText("Tidak")
                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                        .setPositiveBtnText("Ya")
                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                        .setAnimation(Animation.SIDE)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                db.delete(Integer.parseInt(hm));
                                notifyDataSetChanged();
                                Intent intent = new Intent(view.getContext(), TeamActivity.class);
                                ((view.getContext())).startActivity(intent);
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {

                            }
                        })
                        .build();

            }
        });

        holder.cardTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), PlayerActivity.class);
                intent.putExtra("id",idTeam);
                intent.putExtra("nama",nmTeam);
                ((view.getContext())).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class AdapterTeamViewHolder extends RecyclerView.ViewHolder {
        TextView txt_Nama_Team;
        ImageView imgedit,imghapus;
        CardView cardTeam;
        public AdapterTeamViewHolder(View itemView) {
            super(itemView);

            txt_Nama_Team = (TextView) itemView.findViewById(R.id.textNamaTeam);
            imgedit = (ImageView) itemView.findViewById(R.id.imgEditTeam);
            imghapus = (ImageView) itemView.findViewById(R.id.imgDeleteTeam);

            cardTeam = (CardView) itemView.findViewById(R.id.cardAdapterTeam);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
