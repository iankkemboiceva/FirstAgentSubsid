package firstmob.firstbank.com.subsidiary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.adapter.OTBAdapt;
import adapter.adapter.OTBList;
import rest.ApiInterface;
import rest.ApiSecurityClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import security.SecurityLayer;


public class NubanGetAccoNumb extends Fragment implements View.OnClickListener {
ImageView imageView1;

    EditText amon, edacc,pno,txtamount,txtnarr,edname,ednumber;
    Button btnsub;
    String bankname,bankcode;
    SessionManagement session;
    ProgressDialog prgDialog,prgDialog2;
    private static int SPLASH_TIME_OUT = 2500;
    String depositid;
    OTBAdapt aAdpt;

    LinearLayout linearLayoutMine;
    List<OTBList> planetsList = new ArrayList<OTBList>();
    MaterialDialog builder;
    EditText accountoname;
    String acname;
    TextView step2;
    public NubanGetAccoNumb() {
        // Required empty public constructor
    }
  /*  private static Fragment newInstance(Context context) {
        LayoutOne f = new LayoutOne();

        return f;
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.nubangetacconumb, null);
        session = new SessionManagement(getActivity());
        edacc = (EditText) root.findViewById(R.id.input_payacc);

        prgDialog = new ProgressDialog(getActivity());
        prgDialog.setMessage("Loading Account Details....");
        // Set Cancelable as False

        prgDialog.setCancelable(false);


        prgDialog2 = new ProgressDialog(getActivity());
        prgDialog2.setMessage("Loading....");
        prgDialog2.setCancelable(false);


        btnsub = (Button) root.findViewById(R.id.button2);
        accountoname = (EditText) root.findViewById(R.id.cname);

        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();

        edacc.setOnFocusChangeListener(ofcListener);

      btnsub.setOnClickListener(this);

        step2 = (TextView) root.findViewById(R.id.tv2);
        step2.setOnClickListener(this);


        edacc.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edacc.getText().toString().length() == 13) {
                    if (!(getActivity() == null)) {

                        // TODO Auto-generated method stub
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
                getActivity(), R.array.banks, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spn2.setAdapter(adapter4);
        return root;
    }


    private class MyFocusChangeListener implements View.OnFocusChangeListener {

        public void onFocusChange(View v, boolean hasFocus){

            if(v.getId() == R.id.amount && !hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String txt = txtamount.getText().toString();
                String fbal = Utility.returnNumberFormat(txt);
                txtamount.setText(fbal);

            }

            if(v.getId() == R.id.ednarr && !hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
            if(v.getId() == R.id.sendname && !hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
            if(v.getId() == R.id.sendnumber && !hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
            if(v.getId() == R.id.input_payacc && !hasFocus) {
                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button2) {

            final String recanno = edacc.getText().toString();

            if (Utility.isNotNull(recanno)) {

                Bundle b = new Bundle();
                b.putString("recanno", recanno);


                Fragment fragment = new NubanGetBanks();

                fragment.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //  String tag = Integer.toString(title);
                fragmentTransaction.replace(R.id.container_body, fragment, "Confirm Other Bank");
                fragmentTransaction.addToBackStack("Confirm Other Bank");
                ((FMobActivity) getActivity())
                        .setActionBarTitle("Confirm Other Bank");
                fragmentTransaction.commit();
            } else {
                Toast.makeText(
                        getActivity(),
                        "Please enter a valid account number",
                        Toast.LENGTH_LONG).show();
            }

        }



        if (view.getId() == R.id.textVipp) {
         //   SetDialog("Select Bank");

            Fragment  fragment = new OtherBankPage();


            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //  String tag = Integer.toString(title);
            fragmentTransaction.replace(R.id.container_body, fragment,"Select Bank");
            fragmentTransaction.addToBackStack("Select Bank");
            ((FMobActivity)getActivity())
                    .setActionBarTitle("Select Bank");
            fragmentTransaction.commit();

        }

        if (view.getId() == R.id.tv2) {
            Fragment  fragment = new FTMenu();


            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //  String tag = Integer.toString(title);
            fragmentTransaction.replace(R.id.container_body, fragment,"Confirm Transfer");
            fragmentTransaction.addToBackStack("Confirm Transfer");
            ((FMobActivity)getActivity())
                    .setActionBarTitle("Confirm Transfer");
            fragmentTransaction.commit();
        }
    }
    public void SetDialog(String title){
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View stdView = factory.inflate(R.layout.dialoglistview, null);
        linearLayoutMine  = (LinearLayout) stdView.findViewById(R.id.lay);

     final ListView   lv = (ListView) stdView.findViewById(R.id.lv);

        planetsList.add(new OTBList("Access Bank PLC","057"));
        planetsList.add(new OTBList("Aso Savings","058"));
        planetsList.add(new OTBList("CitiBank Nigeria LTD","059"));
        planetsList.add(new OTBList("Diamond Bank Plc","057"));
        planetsList.add(new OTBList("Ecobank Nigeria Plc","058"));
        planetsList.add(new OTBList("Enterprise Bank","059"));
        planetsList.add(new OTBList("Fidelity Bank Plc","057"));
        planetsList.add(new OTBList("First City Monument Bank Plc","058"));
        planetsList.add(new OTBList("Guaranty Trust Bank Plc","059"));
        planetsList.add(new OTBList("Heritage Bank","057"));
        planetsList.add(new OTBList("JAIZ Bank","058"));
        planetsList.add(new OTBList("Keystone Bank","059"));
        planetsList.add(new OTBList("Mainstreet Bank","059"));
        planetsList.add(new OTBList("Parallex MFB","057"));
        planetsList.add(new OTBList("Skye Bank Plc","058"));
        planetsList.add(new OTBList("StanbicIBTC Bank Plc","059"));

        planetsList.add(new OTBList("Standard Chartered Bank of Nigeria","058"));
        planetsList.add(new OTBList("Sterling Bank Plc","059"));
        planetsList.add(new OTBList("Union Bank of Nigeria Plc","059"));
        planetsList.add(new OTBList("United Bank for Africa Plc","057"));
        planetsList.add(new OTBList("Unity Bank Plc","058"));
        planetsList.add(new OTBList("Wema Bank Plc","059"));
        planetsList.add(new OTBList("Zenith Bank","059"));
        aAdpt = new OTBAdapt(planetsList, getActivity());
lv.setAdapter(aAdpt);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String benname = planetsList.get(position).getBenName();

                builder.dismiss();

            }
        });
      builder =   new MaterialDialog.Builder(getActivity())
                .title(title)

                .customView(linearLayoutMine,true)

                .negativeText("Close")

                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                     /*   Toast.makeText(
                                getActivity(),
                                "You have successfully added an offer",
                                Toast.LENGTH_LONG).show();*/

                    }
                })
                .show();
    }



    public void SetDialog(String msg,String title){
        new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(msg)

                .negativeText("Close")
                .show();
    }



    @Override
    public void onPause() {
        super.onPause();

        if ((getActivity() != null && prgDialog != null) && prgDialog.isShowing()) {
            prgDialog.dismiss();

        }
    }

}
