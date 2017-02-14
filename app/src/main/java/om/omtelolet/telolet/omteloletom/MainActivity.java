package om.omtelolet.telolet.omteloletom;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import om.omtelolet.telolet.omteloletom.widget.PageIndicator;

public class MainActivity extends AppCompatActivity {

    PageIndicator mIndicator;
    ViewPager mViewPager;
    RelativeLayout mLayoutSlider;
    private CustomPagerAdapter mCustomPagerAdapter;
    private int[] mResource = {R.drawable.year, R.drawable.telolet_dua, R.drawable.telolet_satu};
    private String[] mResourceTitle = {"TELOLET OM", "PLEASE OM", "TELOLET"};
    private Handler handler;
    private boolean stopSliding = false;
    private static final int ANIM_VIEWPAGER_DELAY = 4000;
    private  MediaPlayer mMediaPlayer;
    private Runnable animateViewPager;

    private CardView cv1;
    private ImageView play1,play2,play3,play4,play5,play6,play7,play8,play9,play10,play11,play12;
    private ImageView pause1,pause2,pause3,pause4,pause5,pause6,pause7,pause8,pause9,pause10,pause11,pause12;
    private boolean play = false;
    private  int horn1,horn2,horn3,horn4,horn5,horn6,horn7,horn8,horn9,horn10,horn11,horn12 =0;
    private AdView mAdView,mAdView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndicator    = (PageIndicator)findViewById(R.id.indicatorHome);
        mViewPager    = (ViewPager)findViewById(R.id.pagerBrowseSlider);
        mLayoutSlider = (RelativeLayout)findViewById(R.id.rl_home_slider);
        play1         = (ImageView)findViewById(R.id.play1);
        pause1        = (ImageView)findViewById(R.id.pause1);
        play2         = (ImageView)findViewById(R.id.play2);
        pause2        = (ImageView)findViewById(R.id.pause2);
        play3         = (ImageView)findViewById(R.id.play3);
        pause3        = (ImageView)findViewById(R.id.pause3);

        play4         = (ImageView)findViewById(R.id.play4);
        pause4        = (ImageView)findViewById(R.id.pause4);
        play5         = (ImageView)findViewById(R.id.play5);
        pause5        = (ImageView)findViewById(R.id.pause5);
        play6         = (ImageView)findViewById(R.id.play6);
        pause6        = (ImageView)findViewById(R.id.pause6);

        play7         = (ImageView)findViewById(R.id.play7);
        pause7        = (ImageView)findViewById(R.id.pause7);
        play8         = (ImageView)findViewById(R.id.play8);
        pause8        = (ImageView)findViewById(R.id.pause8);
        play9         = (ImageView)findViewById(R.id.play9);
        pause9        = (ImageView)findViewById(R.id.pause9);

        play10         = (ImageView)findViewById(R.id.play10);
        pause10        = (ImageView)findViewById(R.id.pause10);
        play11         = (ImageView)findViewById(R.id.play11);
        pause11        = (ImageView)findViewById(R.id.pause11);
        play12         = (ImageView)findViewById(R.id.play12);
        pause12        = (ImageView)findViewById(R.id.pause12);

        mAdView2 = (AdView)findViewById(R.id.adVie2);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView2.loadAd(adRequest);


        mLayoutSlider.setFocusable(true);
        mLayoutSlider.setFocusableInTouchMode(true);
        setupSliderHome();
    }



    private void setupSliderHome() {
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager.setAdapter(mCustomPagerAdapter);
        mIndicator.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        runnable(mResource.length);
        handler.postDelayed(animateViewPager,
                ANIM_VIEWPAGER_DELAY);
    }


    private void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (mViewPager.getCurrentItem() == size - 1) {
                        mViewPager.setCurrentItem(0);
                    } else {
                        mViewPager.setCurrentItem(
                                mViewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
                }
            }
        };
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResource.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.home_slider, container, false);

            ImageView ivCoverSlide = (ImageView) itemView.findViewById(R.id.iv_home_slider);
            TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_home_slider);

            Picasso.with(mContext)
                    .load((mResource[position]))
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(ivCoverSlide);
            tvTitle.setText(mResourceTitle[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        mMediaPlayer.pause();
        Intent intent =new Intent(MainActivity.this,InterestialLogout.class);
        startActivity(intent);
        finish();


    }


    public void Horn1(View view){
        if(horn2==1  || horn3==1 || horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn1 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn1);
            mMediaPlayer.setLooping(true);
            horn1 = 1;
            mMediaPlayer.start();
            play1.setVisibility(View.GONE);
            pause1.setVisibility(View.VISIBLE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);

        }else{
            mMediaPlayer.pause();
            horn1 = 0;
            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);
        }
    }



    public void Horn2(View view){
        if(horn1==1||horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn2 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn2);
            mMediaPlayer.setLooping(true);
            horn2 = 1;
            mMediaPlayer.start();
            play2.setVisibility(View.GONE);
            pause2.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);

        }else{
            mMediaPlayer.pause();
            horn2 = 0;
            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);
        }
    }

    public void Horn3(View view){
        if(horn1==1||horn2==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn3 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn3);
            mMediaPlayer.setLooping(true);
            horn3 = 1;
            mMediaPlayer.start();
            play3.setVisibility(View.GONE);
            pause3.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);
            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);

        }else{
            mMediaPlayer.pause();
            horn3 = 0;
            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);
        }
    }


    public void Horn4(View view){
        if(horn1==1||horn2==1 || horn3==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn4 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn4);
            mMediaPlayer.setLooping(true);
            horn4 = 1;
            mMediaPlayer.start();
            play4.setVisibility(View.GONE);
            pause4.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);


            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn4 = 0;
            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);
        }
    }

    public void Horn5(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn5 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn5);
            mMediaPlayer.setLooping(true);
            horn5 = 1;
            mMediaPlayer.start();
            play5.setVisibility(View.GONE);
            pause5.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn5 = 0;
            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);
        }
    }

    public void Horn6(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn6 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn6);
            mMediaPlayer.setLooping(true);
            horn6 = 1;
            mMediaPlayer.start();
            play6.setVisibility(View.GONE);
            pause6.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn6 = 0;
            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);
        }
    }


    public void Horn7(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn8==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn7 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn7);
            mMediaPlayer.setLooping(true);
            horn7 = 1;
            mMediaPlayer.start();
            play7.setVisibility(View.GONE);
            pause7.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn7 = 0;
            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);
        }
    }


    public void Horn8(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn9==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn8 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn8);
            mMediaPlayer.setLooping(true);
            horn8 = 1;
            mMediaPlayer.start();
            play8.setVisibility(View.GONE);
            pause8.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn8 = 0;
            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);
        }
    }


    public void Horn9(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn10==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn9 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn9);
            mMediaPlayer.setLooping(true);
            horn9 = 1;
            mMediaPlayer.start();
            play9.setVisibility(View.GONE);
            pause9.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn9 = 0;
            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);
        }
    }


    public void Horn10(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn11==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn10 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn10);
            mMediaPlayer.setLooping(true);
            horn10 = 1;
            mMediaPlayer.start();
            play10.setVisibility(View.GONE);
            pause10.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);


        }else{
            mMediaPlayer.pause();
            horn10 = 0;
            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);
        }
    }

    public void Horn11(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn12==1){
            mMediaPlayer.pause();
        }
        if (horn11 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn11);
            mMediaPlayer.setLooping(true);
            horn11 = 1;
            mMediaPlayer.start();
            play11.setVisibility(View.GONE);
            pause11.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);





        }else{
            mMediaPlayer.pause();
            horn11 = 0;
            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);
        }
    }

    public void Horn12(View view){
        if(horn1==1||horn2==1 || horn3==1||horn4==1||horn5==1||horn6==1||horn7==1||horn8==1||horn9==1||horn10==1||horn11==1){
            mMediaPlayer.pause();
        }
        if (horn12 == 0) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.horn12);
            mMediaPlayer.setLooping(true);
            horn12 = 1;
            mMediaPlayer.start();
            play12.setVisibility(View.GONE);
            pause12.setVisibility(View.VISIBLE);

            play1.setVisibility(View.VISIBLE);
            pause1.setVisibility(View.GONE);

            play2.setVisibility(View.VISIBLE);
            pause2.setVisibility(View.GONE);

            play3.setVisibility(View.VISIBLE);
            pause3.setVisibility(View.GONE);

            play4.setVisibility(View.VISIBLE);
            pause4.setVisibility(View.GONE);

            play5.setVisibility(View.VISIBLE);
            pause5.setVisibility(View.GONE);

            play6.setVisibility(View.VISIBLE);
            pause6.setVisibility(View.GONE);

            play7.setVisibility(View.VISIBLE);
            pause7.setVisibility(View.GONE);

            play8.setVisibility(View.VISIBLE);
            pause8.setVisibility(View.GONE);

            play9.setVisibility(View.VISIBLE);
            pause9.setVisibility(View.GONE);

            play10.setVisibility(View.VISIBLE);
            pause10.setVisibility(View.GONE);

            play11.setVisibility(View.VISIBLE);
            pause11.setVisibility(View.GONE);




        }else{
            mMediaPlayer.pause();
            horn12 = 0;
            play12.setVisibility(View.VISIBLE);
            pause12.setVisibility(View.GONE);
        }
    }


}
