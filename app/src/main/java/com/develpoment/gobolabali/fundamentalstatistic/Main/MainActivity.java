package com.develpoment.gobolabali.fundamentalstatistic.Main;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.develpoment.gobolabali.fundamentalstatistic.About.AboutActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Games.GameChooseActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Setting.SettingActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity implements  ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    CardView cardTeam,cardAbout,cardMatch,cardGames,cardSetting;

    BannerSlider bannerSlider;
    List<Banner> banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Fundamental Statistic");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"font/questrial.regular.ttf");

        cardTeam = (CardView) findViewById(R.id.cardTeam);
        cardTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TeamActivity.class));
            }
        });

        cardAbout = (CardView) findViewById(R.id.cardAbout);
        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });

        cardSetting = (CardView) findViewById(R.id.cardSettings);
        cardSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });

        cardMatch = (CardView) findViewById(R.id.cardMatch);
        cardMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MatchActivity.class));
            }
        });

        cardGames = (CardView) findViewById(R.id.cardGames);
        cardGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GameChooseActivity.class));
            }
        });



        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);*/

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("cc",R.drawable.heung);
        file_maps.put("bb",R.drawable.slide2);
        file_maps.put("aa",R.drawable.slide3);
        file_maps.put("dd", R.drawable.hore);
        file_maps.put("ee", R.drawable.futsal1);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        /*mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());*/
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        cekVersion();

        /*bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        banners = new ArrayList<>();
        addbanner();*/

    }

    private void addbanner() {
        banners.add(new DrawableBanner(R.drawable.slide1));
        banners.add(new DrawableBanner(R.drawable.slide2));
        banners.add(new DrawableBanner(R.drawable.slide3));
        banners.add(new DrawableBanner(R.drawable.slide4));
        bannerSlider.setBanners(banners);
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

  /*  @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(MainActivity.this)

                .setBody("Apakah anda yakin akan keluar")
                .setNegativeColor(R.color.green)
                .setNegativeButtonText("TIDAK")
                .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButtonText("YA")
                .setPositiveColor(R.color.red)
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        moveTaskToBack(true);
                        dialog.dismiss();
                    }
                })
                /* .setAutoHide(true)*/
                .build();
        alert.show();
    }

    String versi = "1.0.0";
    String versi2 ="1.0.0";
    private void cekVersion() {
        if (versi.equals(versi2)){

        }else {
            new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(MainActivity.this)
                    .setTitle("Update Aplikasi Anda")
                    .setBackgroundColor(Color.parseColor("#E12929"))
                    .setIcon(R.drawable.ic_news, Icon.Visible)
                    .setMessage("Versi Terbaru Aplikasi Telah Release, Update Aplikasi Anda")
                    .setNegativeBtnText("Tidak")
                    .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                    .setPositiveBtnText("Ya")
                    .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                    .setAnimation(Animation.SIDE)
                    .isCancellable(true)
                    .OnPositiveClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            /*MainActivity.this.finish();*/
//                            finish();
                            /*  onBackPressed();*/
                            /*onDestroy();*/
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
}
