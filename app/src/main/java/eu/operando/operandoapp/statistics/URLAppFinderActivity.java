package eu.operando.operandoapp.statistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import eu.operando.operandoapp.R;
import eu.operando.operandoapp.database.DatabaseHelper;
import eu.operando.operandoapp.database.model.UrlAppChecker;
import eu.operando.operandoapp.service.connectWithServer;
import eu.operando.operandoapp.util.MainUtil;
import eu.operando.operandoapp.MainContext;
import eu.operando.operandoapp.util.URLCheckerUtils;

public class URLAppFinderActivity extends AppCompatActivity {
    private MainContext mainContext = MainContext.INSTANCE;
    private DatabaseHelper db;
    private ImageButton RecButton,RecordingButton, SearchButton , RefreshButton;
    private TextView EditText;
    private Boolean recording = false;
    private String currentApp = "mock" ;
    private Button  btnok,btnnotok ;
    private connectWithServer serverConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlapp_finder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setTitle("App Finder");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Log.d("appfinder","starting...");

        //Check server connections
        serverConnection = new  connectWithServer(this);
        serverConnection.TestGetApps();
        serverConnection.TestGetApp();


        db = mainContext.getDatabaseHelper();
        MainUtil.initializeMainContext(getApplicationContext());
        RecButton = (ImageButton) findViewById(R.id.recButton);
        RecordingButton = (ImageButton) findViewById(R.id.recordingButton);
        SearchButton = (ImageButton) findViewById(R.id.searchButton);
        RefreshButton = (ImageButton)findViewById(R.id.refreshButton);
        EditText = (TextView)findViewById(R.id.editText);
        RecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!recording){
                    //stopRecording();
                    recording = !recording;
                    RecordingButton.setVisibility(View.VISIBLE);
                    RecButton.setVisibility(View.GONE);
                    currentApp = EditText.getText().toString();
                    if (currentApp.equals(null) || currentApp.equals("")){
                        EditText.setText("mock");
                        currentApp = "mock";
                    }
                    EditText.setEnabled(false);
                    URLCheckerUtils.startRecording(currentApp);
                }
            }
        });
        RecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recording){
                    //startRecording();
                    recording = !recording;
                    RecButton.setVisibility(View.VISIBLE);
                    RecordingButton.setVisibility(View.GONE);
                    List<UrlAppChecker> a = URLCheckerUtils.stopRecording(currentApp);
                    EditText.setEnabled(true);
                    createlist(a);
                }
            }
        });
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!recording){
                    createapplist();
                }
            }
        });
        createapplist();
    }


    void createapplist(){
        final LinearLayout tableapp = (LinearLayout) findViewById(R.id.myTableAppLayout);
        tableapp.removeAllViews();
        List<String> clist = db.getUrlAppCheckerApps();
        for (int i = 0; i < clist.size(); i++) {
            // Inflate your row "template" and fill out the fields.
            tableapp. addView(createAppRow(i,clist.get(i)));
        }
    };
    private LinearLayout createAppRow(final int i, final String urlApp) {
        final LinearLayout row = (LinearLayout) LayoutInflater.from(URLAppFinderActivity.this).inflate(R.layout.create_url_finder_content, null);
        ((TextView) row.findViewById(R.id.textapp)).setText(urlApp);
        final ToggleButton a =(ToggleButton) row.findViewById(R.id.toggleButtonF);
        a.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             int notificationId = mainContext.getNotificationId();
                             List<UrlAppChecker> urlAppCheckerApps = db.getUrlAppChecker(urlApp);
                             if (i == 1 &&  urlAppCheckerApps.size()>0) {
                                 serverConnection.TestSendApp(urlAppCheckerApps.get(0));
                             }
                             if (a.isChecked()) {
                                    URLCheckerUtils.addToMonitor(urlApp, urlAppCheckerApps);
                             }
                             else
                             {
                                 URLCheckerUtils.removeFromMonitor(urlApp);
                             }
                         }
                     }
        );
        final String s = urlApp;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText.setText(s);
            }
        });
        return row;
    }
    void createlist(List<UrlAppChecker> clist ){
        ((ScrollView) findViewById(R.id.myTableFatherLayout)).setVisibility(View.VISIBLE);
        final LinearLayout table = (LinearLayout) findViewById(R.id.myTableLayout);
        table.removeAllViews();
        //fix a table for input or edit
        for (int i = 0; i < clist.size(); i++) {
            // Inflate your row "template" and fill out the fields.
            table.addView(createRow(i,clist.get(i)));
        }
        //fix return buttons
        table.addView((LinearLayout) LayoutInflater.from(URLAppFinderActivity.this).inflate(R.layout.ok_button, null));
        btnok = (Button) findViewById(R.id.btnok);
        btnnotok = (Button) findViewById(R.id.btnnotok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UrlAppChecker> urlcheckers =  new ArrayList<>();
                final int childCount = table.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View v = table.getChildAt(i);
                    if (v instanceof LinearLayout && v.getId() != R.id.bottonsok) {
                        if (((CheckBox) v.findViewById(R.id.cBoxUseIt)).isChecked()) {
                            Log.d("testit", ((TextView) v.findViewById(R.id.textAppName)).getText().toString());
                            Log.d("testit", ((TextView) v.findViewById(R.id.textUrl)).getText().toString());
                            Log.d("testit", ((TextView) v.findViewById(R.id.textDuration)).getText().toString());
                            Log.d("testit", ((TextView) v.findViewById(R.id.textCount)).getText().toString());
                            Log.d("testit", ((CheckBox) v.findViewById(R.id.cBoxUseIt)).isChecked() + " ");
                            Log.d("testit", "-------");
                            UrlAppChecker addme = new UrlAppChecker(
                                    ((TextView) v.findViewById(R.id.textAppName)).getText().toString(),
                                    ((TextView) v.findViewById(R.id.textUrl)).getText().toString(),
                                    Integer.parseInt(((TextView) v.findViewById(R.id.textDuration)).getText().toString())  ,
                                    Integer.parseInt(((TextView) v.findViewById(R.id.textCount)).getText().toString()));
                            urlcheckers.add(addme);
                        }
                    }
                }
                db.createUrlAppCheckers(urlcheckers);
                ((ScrollView) findViewById(R.id.myTableFatherLayout)).setVisibility(View.GONE);
            }
        });
        btnnotok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  ((ScrollView) findViewById(R.id.myTableFatherLayout)).setVisibility(View.GONE); }
        });
    }
    private LinearLayout createRow(int i,UrlAppChecker urlApp) {
        final LinearLayout row = (LinearLayout) LayoutInflater.from(URLAppFinderActivity.this).inflate(R.layout.content_urlapp_finder, null);
        ((TextView) row.findViewById(R.id.textUrl)).setText(urlApp.domainurl);
        ((TextView) row.findViewById(R.id.textAppName)).setText(urlApp.app_name);
        ((TextView) row.findViewById(R.id.textCount)).setText(String.valueOf(urlApp.count));
        ((TextView) row.findViewById(R.id.textDuration)).setText(String.valueOf(urlApp.duration));
        return row;
    }
    @Override
    protected void onStop () {
        super.onStop();
        serverConnection.closeQueue();
    }
}