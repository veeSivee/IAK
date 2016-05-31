package vee.apps.publicholidays;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

//import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import vee.apps.publicholidays.api.request.HolidaysRequest;
import vee.apps.publicholidays.api.response.Holidays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HolidaysRequest.OnHolidaysRequestListener {

    /*@BindView(R.id.listView)
    ListView listTanggal;

    @BindView(R.id.editText2)
    EditText etTahun;

    @BindView(R.id.datePicker)
    DatePicker picker;

    @BindView(R.id.calendarView)
    CalendarView kalender;
    */

    @BindView(R.id.textView2)
    TextView tvHello;

    @BindView(R.id.editText)
    TextView etBulan;

    @BindView(R.id.imageView2)
    ImageView countryIcon;

    @BindView(R.id.textView3)
    TextView countryName;

    @BindView(R.id.btnTime)
    Button btnTime;

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private String country = "US";
    private String Year = "2016";
    private String Month = "05";

    private ProgressDialog progressDialog;
    private HolidaysRequest mHolidaysRequest;
    private  MyTimePicker myTime;
    private static Holidays mHolidays;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sabar -_-  On Progress  :D", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myTime = new MyTimePicker(MainActivity.this,btnTime);

        tvHello.setText("");
        countryName.setText(getString(R.string.united_states));
        btnTime.setText(myTime.getMonth(5)+Year);

        mHolidaysRequest = new HolidaysRequest(this);
        progressDialog = new ProgressDialog(this);

        getDataHolidays();

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTime.getDialog();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        tvHello.setText("");

        if (id == R.id.nav_share) {

            ToastView("Share function on progress");
            /*mShareActionProvider = (ShareActionProvider) item.getActionProvider();
            mShareActionProvider.setShareIntent(getDefaultShareIntent());*/

        } else if (id == R.id.nav_send) {
            ToastView("Send function on progress");
        } else if (id == R.id.nav_brazil) {

            country = getString(R.string.code_brazil);
            countryIcon.setImageResource(R.mipmap.ic_round_brazil);
            countryName.setText(getString(R.string.brazil));

        } else if (id == R.id.nav_bulgaria) {

            country = getString(R.string.code_bulgaria);
            countryIcon.setImageResource(R.mipmap.ic_round_bulgaria);
            countryName.setText(getString(R.string.bulgaria));

        } else if (id == R.id.nav_canada) {

            country = getString(R.string.code_canada);
            countryIcon.setImageResource(R.mipmap.ic_round_canada);
            countryName.setText(getString(R.string.canada));

        } else if (id == R.id.nav_czech_republic) {

            country = getString(R.string.code_czech_republic);
            countryIcon.setImageResource(R.mipmap.ic_round_czech_republic);
            countryName.setText(getString(R.string.czech_republic));

        } else if (id == R.id.nav_france) {

            country = getString(R.string.code_france);
            countryIcon.setImageResource(R.mipmap.ic_round_france);
            countryName.setText(getString(R.string.france));

        } else if (id == R.id.nav_germany) {

            country = getString(R.string.code_germany);
            countryIcon.setImageResource(R.mipmap.ic_round_germany);
            countryName.setText(getString(R.string.germany));

        } else if (id == R.id.nav_netherlands) {

            country = getString(R.string.code_netherlands);
            countryIcon.setImageResource(R.mipmap.ic_round_netherlands);
            countryName.setText(getString(R.string.netherlands));

        } else if (id == R.id.nav_norway) {

            country = getString(R.string.code_norway);
            countryIcon.setImageResource(R.mipmap.ic_round_norway);
            countryName.setText(getString(R.string.norway));

        } else if (id == R.id.nav_poland) {

            country = getString(R.string.code_poland);
            countryIcon.setImageResource(R.mipmap.ic_round_poland);
            countryName.setText(getString(R.string.poland));

        } else if (id == R.id.nav_slovakia) {

            country = getString(R.string.code_slovakia);
            countryIcon.setImageResource(R.mipmap.ic_round_slovakia);
            countryName.setText(getString(R.string.slovakia));

        } else if (id == R.id.nav_slovenia) {

            country = getString(R.string.code_slovenia);
            countryIcon.setImageResource(R.mipmap.ic_round_slovenia);
            countryName.setText(getString(R.string.slovenia));

        } else if (id == R.id.nav_spain) {

            country = getString(R.string.code_spain);
            countryIcon.setImageResource(R.mipmap.ic_round_spain);
            countryName.setText(getString(R.string.spain));

        } else if (id == R.id.nav_united_kingdom) {

            country = getString(R.string.code_united_kingdom);
            countryIcon.setImageResource(R.mipmap.ic_round_uk);
            countryName.setText(getString(R.string.united_kingdom));

        } else if (id == R.id.nav_united_states) {

            country = getString(R.string.code_united_states);
            countryIcon.setImageResource(R.mipmap.ic_round_us);
            countryName.setText(getString(R.string.united_states));
        }

        if (id != R.id.nav_share && id != R.id.nav_send){
            getDataHolidays();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestHolidaysSuccess(Holidays holidaysResponse) {
        try{
            progressDialog.dismiss();

            MyAdapter adapter = new MyAdapter(new String[(holidaysResponse.getListHolidays().size())]);
            recyclerView.setAdapter(adapter);


            mHolidays = holidaysResponse;

        }catch (Exception e){
            ToastView(e.getMessage());
        }
    }

    @Override
    public void onRequestHolidaysFailure(String message) {

        progressDialog.dismiss();
        tvHello.setText("GAGAL : " + message);
    }

    private void ToastView(String isi){
        Toast.makeText(getApplicationContext(),isi,Toast.LENGTH_SHORT).show();
    }

    private void getDataHolidays(){

        try{
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();

            mHolidaysRequest.callApi(country,Year,Month);
        }catch (Exception e){
            progressDialog.dismiss();
            ToastView(e.getMessage());
        }
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;
            public TextView title;

            public ViewHolder(View v) {
                super(v);
                view = v;
                title = (TextView) v.findViewById(R.id.card_title);
            }
        }

        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View cardview = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview, parent, false);
            return new ViewHolder(cardview);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            String isi = "Date      : " + mHolidays.getListHolidays().get(position).getDate();
            isi += "\nName    : " + mHolidays.getListHolidays().get(position).getName();

            holder.title.setText(isi);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }

    }
}
