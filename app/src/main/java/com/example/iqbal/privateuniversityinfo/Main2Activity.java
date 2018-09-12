package com.example.iqbal.privateuniversityinfo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;

// https://api.myjson.com/bins/lb6b8

// new https://api.myjson.com/bins/1gzo6k
public class Main2Activity extends AppCompatActivity {
    FacultyAndSubject facultyAndSubject;
    TutionFees tutionFees;


    private String div;
    private int pos;
    String fac,tution_fees, admission_info, contact;;

    private RequestQueue mqueue;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fac = getIntent().getStringExtra("fac");
        tution_fees = getIntent().getStringExtra("tution_fees");
        admission_info = getIntent().getStringExtra("adm");
        contact = getIntent().getStringExtra("contact");


        bundle = new Bundle();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in,R.anim.activity_back_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.university_details, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    facultyAndSubject = new FacultyAndSubject();
                    bundle.putString("fac",fac);
                    facultyAndSubject.setArguments(bundle);
                    return facultyAndSubject;

                case 1:
                    tutionFees = new TutionFees();
                    bundle.putString("fees",tution_fees);
                    tutionFees.setArguments(bundle);
                    return tutionFees;
                case 2:
                    Admission_info admission = new Admission_info();
                    bundle.putString("admission",admission_info);
                    admission.setArguments(bundle);
                    return admission;
                case 3:
                    Contact contatc_info = new Contact();
                    bundle.putString("contact",contact);
                    contatc_info.setArguments(bundle);
                    return contatc_info;

                default:
                    return null;
            }


        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }



}
