package com.develpoment.gobolabali.fundamentalstatistic.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentPlayer3 extends Fragment {

    String idteam2, idtournament, idmatch2, poss;
    private Bundle bundle1;
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private AdapterPlayerList adapterPlayer;
    private DatabaseHelper db = new DatabaseHelper(getActivity());
    private List<DataPlayerMatch> teamList = new ArrayList<DataPlayerMatch>();

    public FragmentPlayer3() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_player1,container,false);
        bundle1 = this.getArguments();
        idteam2 = bundle1.getString("welcome2");
        idtournament = bundle1.getString("idtournament2");
        idmatch2 = bundle1.getString("idmatch2");
        poss = bundle1.getString("pos2");

        recyclerView = (RecyclerView) frag.findViewById(R.id.listfragteam1);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(getActivity());
//        teamList = db.getTeam();
//        adapterTeam = new AdapterTeam(teamList);
//        recyclerView.setAdapter(adapterTeam);

        ArrayList<HashMap<String,String>> row = db.getAllPlayerMatchforUpdate(Integer.parseInt(idteam2));

        for (int i=0; i <row.size(); i++){
            String idmatchplayer = row.get(i).get("id");
//            String idmatch = row.get(i).get("idmatch");
            String idteam = row.get(i).get("idteam");
            String idplayer = row.get(i).get("idplayer");
            String posisi = row.get(i).get("posisi");
            String nopunggung = row.get(i).get("nopunggung");
            String nickname = row.get(i).get("nickname");
            String status = row.get(i).get("status");

//            db.insertPlayerMatch3(Integer.parseInt(idmatch),Integer.parseInt(idteam),
//                    Integer.parseInt(idplayer),posisi,nopunggung, nickname);

            db.updatePlayerMatch(Integer.parseInt(idmatchplayer), Integer.parseInt(idteam), Integer.parseInt(idplayer), Integer.parseInt(idmatch2));

        }

        ArrayList<HashMap<String,String>> rows = db.getAllPlayerMatch(Integer.parseInt(idmatch2),Integer.parseInt(idteam2));

        for (int j=0; j<rows.size(); j++){

            String idmatchplayer = rows.get(j).get("id");
            String idmatch = rows.get(j).get("idmatch");
            String idteam = rows.get(j).get("idteam");
            String idplayer = rows.get(j).get("idplayer");
            String posisi = rows.get(j).get("posisi");
            String posnomor = rows.get(j).get("posnomor");
            String nopunggung = rows.get(j).get("nopunggung");
            String nickname = rows.get(j).get("nickname");
            String status = rows.get(j).get("status");

            DataPlayerMatch dataPlayer = new DataPlayerMatch();
            dataPlayer.setIdMatch(idmatch);
            dataPlayer.setIdPlayer(idplayer);
            dataPlayer.setIdTournament(idtournament);
            dataPlayer.setNopunggun(nopunggung);
            dataPlayer.setStatus(status);
            dataPlayer.setNickname(nickname);
            dataPlayer.setPosisi(posisi);
            dataPlayer.setIdTeam(idteam);

            teamList.add(dataPlayer);

        }


//        adapterTeam = new AdapterTeam(teamList);
        adapterPlayer = new AdapterPlayerList(getActivity(),teamList);
        recyclerView.setAdapter(adapterPlayer);
        adapterPlayer.notifyDataSetChanged();

        return frag;
    }


    public class AdapterPlayerList extends RecyclerView.Adapter<SimpleViewHolder> {
        private List<DataPlayerMatch> playerList;
        private Activity activity;
        LayoutInflater inflater;
        private Context ctx1;


        DatabaseHelper db;

        public AdapterPlayerList(Context context, List<DataPlayerMatch> mplayerList) {
            this.playerList = mplayerList;
            inflater = LayoutInflater.from(getActivity());
            this.ctx1 = context;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) ctx1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.adapter_match_player, parent, false);
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final SimpleViewHolder holder, int position) {
            db = new DatabaseHelper(getActivity());
            final DataPlayerMatch player = playerList.get(position);

            String nopunggung =player.getNopunggun();
            final String idmatch = player.getIdMatch();
            final String idplayer = player.getIdPlayer();
            final String idteam = player.getIdTeam();
            String posisi = player.getPosisi();
            final String status = player.getStatus();
            final String nickname = player.getNickname();

            holder.textNo.setText(nopunggung);
            holder.textPosisi.setText(posisi);
            holder.textNama.setText(db.getPlayerName(idplayer));

            if (status.equals("0")){
                holder.textLineUp.setText("TIDAK");
            }else {
                holder.textLineUp.setText("YA");
            }

            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (status.equals("0")){
                        final CharSequence[] dialogitem = {"Add To LineUp"};
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setCancelable(true);
                        dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case 0:
                                        db.updatestatusplayermatch(Integer.parseInt(idmatch2),Integer.parseInt(idteam),Integer.parseInt(idplayer));
                                        holder.textLineUp.setText("YA");
                                        Toast.makeText(getActivity(), nickname + " Masuk Lineup ", Toast.LENGTH_SHORT ).show();
                                        break;

                                }
                            }
                        });
                        dialog.show();
                    }else if (status.equals("1")){
                        final CharSequence[] dialogitem = {"Remove From LineUp"};
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setCancelable(true);
                        dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case 0:
                                        db.updatestatusplayermatchNon(Integer.parseInt(idmatch2),Integer.parseInt(idteam),Integer.parseInt(idplayer));
                                        holder.textLineUp.setText("TIDAK");
                                        Toast.makeText(getActivity(), nickname + " Masuk Cadangan ", Toast.LENGTH_SHORT ).show();
                                        break;
                                }
                            }
                        });
                        dialog.show();
                    }
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            return playerList.size();
        }
    }

    /**
     * A Simple ViewHolder for the RecyclerView
     */
    public static class SimpleViewHolder extends RecyclerView.ViewHolder{

        TextView textNo,textNama,textPosisi, textLineUp;
        CardView cardView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textNo      =   (TextView) itemView.findViewById(R.id.txtNoPemain);
            textNama    =   (TextView) itemView.findViewById(R.id.textNamaPemain);
            textPosisi  =   (TextView) itemView.findViewById(R.id.textPosisiPemain);
            cardView    =   (CardView) itemView.findViewById(R.id.cardMatchPlayer);
            textLineUp  =   (TextView) itemView.findViewById(R.id.textLineup);
        }
    }
}
