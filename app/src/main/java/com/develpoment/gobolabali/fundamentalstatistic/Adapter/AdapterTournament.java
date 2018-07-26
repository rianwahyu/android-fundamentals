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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Match.EditTournamentActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTournament;
import com.develpoment.gobolabali.fundamentalstatistic.Player.EditPlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.EditTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

public class AdapterTournament extends RecyclerView.Adapter<AdapterTournament.AdapterTeamViewHolder> {

    private List<DataTournament> teamList;
    private Activity activity;



    DatabaseHelper db;

    public AdapterTournament(Activity activity, List<DataTournament> teamList) {
        this.activity = activity;
        this.teamList = teamList;
    }



    @Override
    public AdapterTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tournament, parent, false);
        AdapterTeamViewHolder adapterTeamViewHolder = new AdapterTeamViewHolder(view);
        return adapterTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTeamViewHolder holder, int position) {
        db = new DatabaseHelper(activity);
        final DataTournament team = teamList.get(position);
        holder.txt_Nama_Team.setText(team.getNamaTournament());

        final String idTournament = team.getId();

        final String namaTournament = team.getNamaTournament();

        holder.txt_Nama_Team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MatchListActivity.class);
                intent.putExtra("id",idTournament);
                intent.putExtra("namatournament",namaTournament);
                ((v.getContext())).startActivity(intent);
            }
        });

        holder.txt_Nama_Team.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(view.getContext(), EditTournamentActivity.class);
                                intent.putExtra("id",idTournament);
                                intent.putExtra("namatournament",namaTournament);
                                ((view.getContext())).startActivity(intent);
                                break;

                            case 1:
                                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(activity)
                                        .setTitle("Hapus Tournament !")
                                        .setBackgroundColor(Color.parseColor("#E12929"))
                                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                                        .setMessage("Apakah Anda Yakin Menghapus Tournament " +namaTournament+ " ?")
                                        .setNegativeBtnText("Tidak")
                                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                                        .setPositiveBtnText("Ya")
                                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                                        .setAnimation(Animation.SIDE)
                                        .isCancellable(true)
                                        .OnPositiveClicked(new FancyAlertDialogListener() {
                                            @Override
                                            public void OnClick() {
                                                db.deleteTournament(Integer.parseInt(idTournament));
                                                notifyDataSetChanged();
                                                Intent intent1 = new Intent(view.getContext(), MatchActivity.class);
                                                ((view.getContext())).startActivity(intent1);
                                            }
                                        })
                                        .OnNegativeClicked(new FancyAlertDialogListener() {
                                            @Override
                                            public void OnClick() {

                                            }
                                        })
                                        .build();

                        }
                    }
                });
                dialog.show();
                return false;
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

            txt_Nama_Team = (TextView) itemView.findViewById(R.id.textNamaTournament);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
