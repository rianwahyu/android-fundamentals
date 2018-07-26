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
import com.develpoment.gobolabali.fundamentalstatistic.Match.AddTournamentActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Player.EditPlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.EditTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;

public class AdapterPlayer extends RecyclerView.Adapter<AdapterPlayer.AdapterTeamViewHolder> {

    private List<DataPlayer> playerList;
    private Activity activity;

    DatabaseHelper db;

    public AdapterPlayer(Activity activity, List<DataPlayer> playerList) {
        this.activity = activity;
        this.playerList = playerList;
    }

    @Override
    public AdapterPlayer.AdapterTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_player, parent, false);
        AdapterTeamViewHolder adapterTeamViewHolder = new AdapterTeamViewHolder(view);
        return adapterTeamViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPlayer.AdapterTeamViewHolder holder, int position) {

        db = new DatabaseHelper(activity);
        final DataPlayer player = playerList.get(position);
        holder.txtnoPunggung.setText(player.getNopunggung());
        holder.txtNickName.setText(player.getNickname());
        holder.txtPosisi.setText(player.getPosisi());

        final String idx = player.getId();

        final String idp= player.getId();
        final String fullp = player.getFullname();
        final String nickp = player.getNickname();
        final String posisi = player.getPosisi();

        final String punggung = player.getNopunggung();
        final String status = player.getStatus();
        final String idteam = player.getIdteam();

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
//                Toast.makeText(activity,"di klik", Toast.LENGTH_LONG).show();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(view.getContext(), EditPlayerActivity.class);
                                intent.putExtra("id",idp);
                                intent.putExtra("fullname",fullp);
                                intent.putExtra("nickname",nickp);
                                intent.putExtra("posisi",posisi);
                                intent.putExtra("punggung",punggung);
                                intent.putExtra("idteam",idteam);

                                ((view.getContext())).startActivity(intent);
                                break;

                            case 1:
                                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(activity)
                                        .setTitle("Delete Player !")
                                        .setBackgroundColor(Color.parseColor("#E12929"))
                                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                                        .setMessage("Apakah Anda Yakin Ingin Menghapus " + fullp + " ?")
                                        .setNegativeBtnText("Tidak")
                                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                                        .setPositiveBtnText("Ya")
                                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                                        .setAnimation(Animation.SIDE)
                                        .isCancellable(true)
                                        .OnPositiveClicked(new FancyAlertDialogListener() {
                                            @Override
                                            public void OnClick() {
                                                db.deletePlayer(Integer.parseInt(idx));
//                                                notifyDataSetChanged();
                                                Intent intent1 = new Intent(view.getContext(), PlayerActivity.class);
                                                intent1.putExtra("id",idteam);
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
                }).show();


                return false;
            }
        });

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

            txtnoPunggung = (TextView) itemView.findViewById(R.id.textNoPunggungPlayer);
            txtNickName = (TextView) itemView.findViewById(R.id.textNickNamePlayer);
            txtPosisi = (TextView) itemView.findViewById(R.id.textPosisiPlayer);
            card = (CardView) itemView.findViewById(R.id.cardPlayer);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
