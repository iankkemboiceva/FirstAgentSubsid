package firstmob.firstbank.com.subsidiary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapter.BillMenuParcelable;
import adapter.CountriesAdapt;
import adapter.CountriesList;
import adapter.ServicesMenuAdapt;
import model.GetServicesData;
import rest.ApiClient;
import rest.ApiInterface;
import rest.ApiSecurityClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import security.SecurityLayer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChooseCountry extends AppCompatActivity {

    List<CountriesList> contlist = new ArrayList<CountriesList>();

    ListView lv;
    CountriesAdapt aAdpt;
    ProgressDialog prgDialog, prgDialog2;
    SessionManagement session;
    String sbpam = "0", pramo = "0";
    boolean blsbp = false, blpr = false, blpf = false, bllr = false, blms = false, blmpesa = false, blcash = false;
    ArrayList<String> ds = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseconttoolbar);
        session = new SessionManagement(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
boolean easd = false;
                if(position == 0){
                    session.setString("APPLICATIONURL","http://196.11.150.200:7787/agencyapi/app/");
                    session.setString("CURRENCY","(GHC)");
                    session.setString("PHONEPREFIX","233");
                    session.setString("ACCLENGTH","13");
                    session.setString("COUNTRY","GH");
                    easd = true;
                }
                if(position == 1){
                    session.setString("APPLICATIONURL","http://196.11.150.201:7787/agencyapi/app/");
                    session.setString("CURRENCY","(GNF)");
                    session.setString("PHONEPREFIX","244");
                    session.setString("ACCLENGTH","15");
                    session.setString("COUNTRY","GUI");
                    easd = true;
                }
if(position == 2){
    session.setString("APPLICATIONURL","http://196.11.150.200:7777/agencyapi/app/");
    session.setString("CURRENCY","(CDF)");
    session.setString("PHONEPREFIX","243");
    session.setString("COUNTRY","DRC");
    session.setString("ACCLENGTH","13");
    easd = true;
}

                if(position == 3){
session.setString("APPLICATIONURL","http://196.11.150.201:7777/agencyapi/app/");
                    session.setString("CURRENCY","(CFA)");
                    session.setString("PHONEPREFIX","221");
                    session.setString("COUNTRY","SEN");
                    session.setString("ACCLENGTH","16");
                    easd = true;
                }
                if(easd) {
                    Intent intent = new Intent(ChooseCountry.this, ActivateAgentBefore.class);


                    startActivity(intent);
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "The country selected is not active yet",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        SetCountries();
    }


    public void SetCountries(){
        contlist.add( new CountriesList(R.drawable.ghana,"Ghana","") );
        contlist.add( new CountriesList(R.drawable.guinea,"Guinea","") );
        contlist.add( new CountriesList(R.drawable.drc,"Democratic Republic of Congo","") );
        contlist.add( new CountriesList(R.drawable.senegal,"Senegal","") );
        aAdpt = new CountriesAdapt(contlist, ChooseCountry.this);


        lv.setAdapter(aAdpt);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    private void dismissProgressDialog() {
        if (prgDialog != null && prgDialog.isShowing()) {
            prgDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }
}
