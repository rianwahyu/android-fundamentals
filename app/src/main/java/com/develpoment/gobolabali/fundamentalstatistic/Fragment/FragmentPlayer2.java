package com.develpoment.gobolabali.fundamentalstatistic.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentPlayer2 extends Fragment {

    String storeText, idtournament, idmatch, poss;
    private Bundle bundle1;
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private AdapterPlayerList adapterPlayer;
    private DatabaseHelper db = new DatabaseHelper(getActivity());
    private List<DataPlayerMatch> teamList = new ArrayList<DataPlayerMatch>();

    public FragmentPlayer2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_player2,container,false);
        bundle1 = this.getArguments();
        storeText = bundle1.getString("welcome2");
        idtournament = bundle1.getString("idtournament2");
        idmatch = bundle1.getString("idmatch2");
        poss = bundle1.getString("pos2");

        recyclerView = (RecyclerView) frag.findViewById(R.id.listfragteam2);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(getActivity());
//        teamList = db.getTeam();
//        adapterTeam = new AdapterTeam(teamList);
//        recyclerView.setAdapter(adapterTeam);

        ArrayList<HashMap<String,String>> row = db.getAllPlayer(Integer.parseInt(storeText));

        for (int i=0; i <row.size(); i++){
            String idplayer = row.get(i).get("id");
            String fullname = row.get(i).get("fullname");
            String nickname = row.get(i).get("nickname");
            String posisi = row.get(i).get("posisi");
            String birth = row.get(i).get("birth");
            String nopunggung = row.get(i).get("nopunggung");
            String status = row.get(i).get("status");
            String idteam = row.get(i).get("idteam");

            db.insertPlayerMatch(Integer.parseInt(idmatch),Integer.parseInt(idteam),
                    Integer.parseInt(idplayer),status,nopunggung, nickname);

            DataPlayerMatch dataPlayer = new DataPlayerMatch();
            dataPlayer.setIdMatch(idmatch);
            dataPlayer.setIdPlayer(idplayer);
            dataPlayer.setIdTournament(idtournament);
            dataPlayer.setNopunggun(nopunggung);
            dataPlayer.setStatus(status);
            dataPlayer.setNickname(nickname);
            dataPlayer.setPosisi(posisi);

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
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            db = new DatabaseHelper(getActivity());
            final DataPlayerMatch player = playerList.get(position);

            holder.textNo.setText(player.getNopunggun());
            holder.textNama.setText(player.getNickname());
            holder.textPosisi.setText(player.getPosisi());
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

        TextView textNo,textNama,textPosisi;
        CheckBox checkBox;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textNo      =   (TextView) itemView.findViewById(R.id.txtNoPemain);
            textNama    =   (TextView) itemView.findViewById(R.id.textNamaPemain);
            textPosisi  =   (TextView) itemView.findViewById(R.id.textPosisiPemain);
        }
    }
}
