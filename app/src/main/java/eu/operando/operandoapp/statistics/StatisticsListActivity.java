package eu.operando.operandoapp.statistics;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import java.util.ArrayList;
import java.util.List;

import eu.operando.operandoapp.MainContext;
import eu.operando.operandoapp.database.DatabaseHelper;
import eu.operando.operandoapp.database.model.DomainFilter;
import eu.operando.operandoapp.database.model.UrlStatistic;
import eu.operando.operandoapp.R;
import eu.operando.operandoapp.service.connectWithServer;
import eu.operando.operandoapp.util.MainUtil;

public class StatisticsListActivity extends AppCompatActivity {
    private MainContext mainContext = MainContext.INSTANCE;
    private DatabaseHelper db;
    private List<UrlStatistic> urlStatistics = new ArrayList<UrlStatistic>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Switch s;
    private TextView t;
    private ImageButton r,rsync,rsyncdown;
    private int IsInSearchMode;
    private connectWithServer a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setTitle("Url Statistics");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        db = mainContext.getDatabaseHelper();
        MainUtil.initializeMainContext(getApplicationContext());

        //Check server connections
        a = new  connectWithServer(this);




        s = (Switch) findViewById(R.id.Hidden);
        t = (TextView) findViewById(R.id.SearchText);
        r = (ImageButton) findViewById(R.id.refreshButton);
        rsync = (ImageButton) findViewById(R.id.syncButton);
        rsyncdown = (ImageButton) findViewById(R.id.syncDownButton);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                createlist();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { createlist();

            }
        });
        rsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { syncData();

            }
        });
        rsyncdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { syncDataDown();

            }
        });
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (t.getText().toString().length() > 3){IsInSearchMode =1 ; createlist();}
                else  if (IsInSearchMode == 1) {IsInSearchMode =0 ; createlist();}

            }
        });

        createlist();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void createlist(){
        LinearLayout table = (LinearLayout) findViewById(R.id.myTableLayout);
        table.removeAllViews();
        // 1st  Table
        LinearLayout tableRowdefault = ((LinearLayout) table.getChildAt(0));

        //fix a table
        int includehidden;
        if (((Switch) findViewById(R.id.Hidden)).isChecked()){includehidden = 1;}else{includehidden = 0;};
        if (t.getText().toString().trim().length() > 3){urlStatistics = db.getUrlStatistics(t.getText().toString().trim(),includehidden);}
        else { urlStatistics = db.getUrlStatistics(includehidden);}
        for (int i = 0; i < urlStatistics.size(); i++) {
            // Inflate your row "template" and fill out the fields.
            table.addView(createRow(i));
        }
    }
    void syncData(){
        a.syncStatistics(urlStatistics);
    }
    void syncDataDown(){
        a.getSyncStatistics(db);
    }
    private LinearLayout createRow(int i) {
        final LinearLayout row = (LinearLayout) LayoutInflater.from(StatisticsListActivity.this).inflate(R.layout.content_statistics_list, null);
        ((TextView) row.findViewById(R.id.textDomain)).setText(urlStatistics.get(i).domainurl);
        ((TextView) row.findViewById(R.id.textCount)).setText(String.valueOf(urlStatistics.get(i).count));
        ((TextView) row.findViewById(R.id.textModified)).setText(urlStatistics.get(i).modified);
        ((TextView) row.findViewById(R.id.textCategory)).setText(urlStatistics.get(i).category);
        ToggleButton open = (ToggleButton) row.findViewById(R.id.toggleButton);
        final LinearLayout buttons = (LinearLayout) row.findViewById(R.id.buttons);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttons.getVisibility() == view.GONE) {
                    buttons.setVisibility(view.VISIBLE);
                } else {
                    buttons.setVisibility(view.GONE);
                }
            }
        });
        final Button categorizeBtn = (Button) row.findViewById(R.id.Categorize);
        final Button setcategoryBtn = (Button) row.findViewById(R.id.SetCategory);
        final Button hideBtn = (Button) row.findViewById(R.id.Hide);
        final Button blockBtn = (Button) row.findViewById(R.id.Block);
        final Button unBlockBtn = (Button) row.findViewById(R.id.UnBlock);
        final int tempi = i;
        if (urlStatistics.get(tempi).hidden == 1){ hideBtn.setText("UNHIDE"); } else { hideBtn.setText("HIDE");}
        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.hideUnhideURLStatistics(urlStatistics.get(tempi));
                urlStatistics.set(tempi,db.getUrlStatistic(urlStatistics.get(tempi).domainurl));

                if (urlStatistics.get(tempi).hidden == 1){ hideBtn.setText("UNHIDE"); if (!s.isChecked()){row.setVisibility(View.GONE);}} else { hideBtn.setText("HIDE");}

            }
        });
        if (urlStatistics.get(tempi).category == null){ categorizeBtn.setText("CATEGORIZE"); } else { categorizeBtn.setText(urlStatistics.get(tempi).category);}
        final LinearLayout categorizeLayout = (LinearLayout) row.findViewById(R.id.setCategoryLayout);
        categorizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categorizeLayout.getVisibility() == view.GONE) {
                    categorizeLayout.setVisibility(view.VISIBLE);
                } else {
                    categorizeLayout.setVisibility(view.GONE);
                }
            }
        });
        setcategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setCategoryURLStatistics(urlStatistics.get(tempi),((TextView)categorizeLayout.findViewById(R.id.EditCategory)).getText().toString());
                urlStatistics.set(tempi,db.getUrlStatistic(urlStatistics.get(tempi).domainurl));
                if (urlStatistics.get(tempi).category == null){
                    categorizeBtn.setText("CATEGORIZE");
                    ((TextView) row.findViewById(R.id.textCategory)).setText(urlStatistics.get(tempi).category);
                } else { categorizeBtn.setText(urlStatistics.get(tempi).category);}
                categorizeLayout.setVisibility(view.GONE);
            }
        });
        blockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainFilter domainFilter = new DomainFilter();
                domainFilter.setContent(urlStatistics.get(tempi).domainurl);
                domainFilter.setSource(null);
                domainFilter.setIsWildcard(false);
                db.createDomainFilter(domainFilter);
                if (db.isDomainBlocked(urlStatistics.get(tempi).domainurl))
                { blockBtn.setEnabled(false); unBlockBtn.setEnabled(true);}
                else { blockBtn.setEnabled(true); unBlockBtn.setEnabled(false);}
            }
        });

        unBlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainFilter domainFilter = new DomainFilter();
                domainFilter.setContent(urlStatistics.get(tempi).domainurl);
                db.deleteAllDomainBlocked(urlStatistics.get(tempi).domainurl);
                if (db.isDomainBlocked(urlStatistics.get(tempi).domainurl))
                { blockBtn.setEnabled(false); unBlockBtn.setEnabled(true);}
                else { blockBtn.setEnabled(true); unBlockBtn.setEnabled(false);}
            }
        });
        if (db.isDomainBlocked(urlStatistics.get(i).domainurl))
        { blockBtn.setEnabled(false); unBlockBtn.setEnabled(true);}
        else { blockBtn.setEnabled(true); unBlockBtn.setEnabled(false);}



        return row;

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("StatisticsList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        a.closeQueue();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}

