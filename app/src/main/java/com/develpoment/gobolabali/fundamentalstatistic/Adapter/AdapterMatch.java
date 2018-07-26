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
import com.develpoment.gobolabali.fundamentalstatistic.Match.EditMatchListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchPlayerListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataMatchList;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Player.EditPlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.EditTeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

public class AdapterMatch extends RecyclerView.Adapter<AdapterMatch.AdapterTeamViewHolder> {

    private List<DataMatchList> matchList;
    private Activity activity;

    DatabaseHelper db;

    public AdapterMatch(Activity activity, List<DataMatchList> matchList) {
        this.activity = activity;
        this.matchList = matchList;
    }

    @Override
    public AdapterMatch.AdapterTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_match_list, parent, false);
        AdapterTeamViewHolder adapterTeamViewHolder = new AdapterTeamViewHolder(view);
        return adapterTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMatch.AdapterTeamViewHolder holder, int position) {

        db = new DatabaseHelper(activity);
        final DataMatchList match = matchList.get(position);
        holder.txtNamaPertandingan.setText(match.getNamaPertandingan());
        holder.txtHome.setText(db.getTeamName(match.getIdteam1()));
        holder.txtAway.setText(db.getTeamName(match.getIdteam2()));

        final String idmatch = match.getId();
        final String namamatch = match.getNamaPertandingan();
        final String idtournament = match.getIdtournament();
        final String idteam1 = match.getIdteam1();
        final String idteam2 = match.getIdteam2();
        final String tanggal = match.getTanggal();
        final String mulai = match.getMulai();
        final String akhir = match.getAkhir();

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(view.getContext(), EditMatchListActivity.class);
                                intent.putExtra("id",idmatch);
                                intent.putExtra("namapertandingan", namamatch);
                                intent.putExtra("idtournament", idtournament);
                                intent.putExtra("idteam1", idteam1);
                                intent.putExtra("idteam2", idteam2);
                                intent.putExtra("tanggal", tanggal);
                                intent.putExtra("mulai", mulai);
                                intent.putExtra("akhir", akhir);
                                ((view.getContext())).startActivity(intent);
                                break;
                            case 1:
                                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(activity)
                                        .setTitle("Hapus Match !")
                                        .setBackgroundColor(Color.parseColor("#E12929"))
                                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                                        .setMessage("Apakah Anda Yakin Menhgapus Match " + namamatch + " ?")
                                        .setNegativeBtnText("Tidak")
                                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                                        .setPositiveBtnText("Ya")
                                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                                        .setAnimation(Animation.SIDE)
                                        .isCancellable(true)
                                        .OnPositiveClicked(new FancyAlertDialogListener() {
                                            @Override
                                            public void OnClick() {
                                                db.deleteMatch(Integer.parseInt(idmatch));
                                                notifyDataSetChanged();
                                                Intent intent1 = new Intent(view.getContext(), MatchListActivity.class);
                                                intent1.putExtra("id", idtournament);
                                                ((view.getContext())).startActivity(intent1);
                                            }
                                        })
                                        .OnNegativeClicked(new FancyAlertDialogListener() {
                                            @Override
                                            public void OnClick() {

                                            }
                                        })
                                        .build();

                                break;
                        }
                    }
                });
                dialog.show();
                return false;
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), MatchPlayerListActivity.class);
                intent1.putExtra("idteam1", idteam1);
                intent1.putExtra("idteam2", idteam2);
                intent1.putExtra("id",idmatch);
                intent1.putExtra("idtournament", idtournament);
//                intent1.putExtra("namapertandingan", namamatch);
                ((view.getContext())).startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class AdapterTeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaPertandingan, txtHome, txtAway;
        CardView card;
        public AdapterTeamViewHolder(View itemView) {
            super(itemView);

            txtNamaPertandingan = (TextView) itemView.findViewById(R.id.textNamaMatch);
            txtHome = (TextView) itemView.findViewById(R.id.txtTeamHome);
            txtAway = (TextView) itemView.findViewById(R.id.txtTeamAway);

            card = (CardView) itemView.findViewById(R.id.cardMatch);
//            card = (CardView) itemView.findViewById(R.id.cardPlayer);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}