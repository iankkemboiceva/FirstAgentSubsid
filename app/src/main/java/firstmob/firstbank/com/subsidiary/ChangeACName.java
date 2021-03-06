package firstmob.firstbank.com.subsidiary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapter.AccountList;
import adapter.AccountSetDefAdapter;
import adapter.BioList;
import adapter.BioListAdapter;
import adapter.ImeiList;
import adapter.ImeiRvAdapter;
import de.hdodenhof.circleimageview.CircleImageView;


public class ChangeACName extends Fragment implements View.OnClickListener {
    TextView agentname,agemail,agphonenumb;

    CheckBox chkb,chkus,chkast,chktpin,chkbal;

    String numb;
    boolean initdisp = false;

    String upLoadServerUri = null;

    private final int CAMERA_RESULT = 1;

    private static String filePath;
    private static final String IMAGE_DIRECTORY_NAME = Constant.ROOT_FOLDER_NAME;
    private Bitmap photoBitmap;
    RelativeLayout rlem,rlid,rlno,rllast;
    CardView cvlast;
    LinearLayout lyf;
    SessionManagement session;
    AccountSetDefAdapter aAdpt;
    ImeiRvAdapter adap;
    BioListAdapter bioadap;
    List<AccountList> planetsList = new ArrayList<AccountList>();
    List<ImeiList> imeilist = new ArrayList<ImeiList>();
    List<BioList> biolist = new ArrayList<BioList>();

    int serverResponseCode = 0;
    public  String acc,defac;
    String uploadFilePath = null;
    String uploadFileName = null;
    private String image;
  RecyclerView lv,lv2,lv3;
 Button myact,chglgpin;
    RadioButton grid,list;
ImageView ivgrid,ivlist;
    CircleImageView iv;
RelativeLayout rlbutton;




    public ChangeACName() {
        // Required empty public constructor
    }

    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.newchangeacname, container, false);
        session = new SessionManagement(getActivity());
        lv = (RecyclerView) rootView.findViewById(R.id.listView1);
       cvlast = (CardView) rootView.findViewById(R.id.card_view023);
        cvlast.setOnClickListener(this);

        lv2 = (RecyclerView) rootView.findViewById(R.id.listView2);

        rlbutton = (RelativeLayout) rootView.findViewById(R.id.rlbutton);
        rlbutton.setOnClickListener(this);
        lv3 = (RecyclerView) rootView.findViewById(R.id.listView3);

        ivgrid = (ImageView) rootView.findViewById(R.id.ivgrid);
        ivlist = (ImageView) rootView.findViewById(R.id.ivlist);
        ivgrid.setOnClickListener(this);
        ivlist.setOnClickListener(this);
       /* grid = (RadioButton) rootView.findViewById(R.id.grid);
        list = (RadioButton) rootView.findViewById(R.id.list);
        String vtype = session.getString("VTYPE");
        if( vtype == null){
           grid.setChecked(true);
        }else {
            if (vtype.equals("N") || vtype.equals("grid")) {
                grid.setChecked(true);
            }
            if (vtype.equals("list")) {
                list.setChecked(true);
            }
        }
        grid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(grid.isChecked()){
//Utility.setUtilView(getActivity(),"grid");
                    session.setString("VTYPE","grid");
                }
            }
        });
        list.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(list.isChecked()){
                   // Utility.setUtilView(getActivity(),"list");
                    session.setString("VTYPE","list");
                }
            }
        });*/
        chkb = (CheckBox) rootView.findViewById(R.id.biochk);
        chkb.setOnClickListener(this);



        iv = (CircleImageView) rootView.findViewById(R.id.profile_image);
        //  iv.setImageBitmap(null);
        iv.setOnClickListener(this);
        //loadImage();

        agentname = (TextView) rootView.findViewById(R.id.textViewnb2);
        agemail = (TextView) rootView.findViewById(R.id.textViewrrs);
        agphonenumb = (TextView) rootView.findViewById(R.id.textViewcvv);

        String txtphonenumb = Utility.gettUtilMobno(getActivity());
        String txtagname =   Utility.gettUtilCustname(getActivity());
        String txtemail =   Utility.gettUtilEmail(getActivity());
        agphonenumb.setText(txtphonenumb);
        agemail.setText(txtemail);
        agentname.setText(txtagname);

        myact = (Button) rootView.findViewById(R.id.tdispedit);
        chglgpin = (Button) rootView.findViewById(R.id.button10);


        session = new SessionManagement(getActivity());


        uploadFilePath = Environment.getExternalStorageDirectory() + File.separator + "cache" + File.separator;
        // uploadFileName = numb;
        uploadFileName = numb;

        uploadFilePath = Environment.getExternalStorageDirectory() + File.separator + "req_images" + File.separator;




        upLoadServerUri = "";
       // prgDialog2.setCancelable(false);


        MyLinearLayoutManager layoutManager3 = new MyLinearLayoutManager(getActivity());
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.scrollToPosition(currPos);





            return rootView;
    }



    public void StartChartAct(int i){


    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }
    @Override
    public void onClick(View v) {



        if(v.getId() == R.id.rlbutton){
            Fragment  fragment = new ChangePin();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //  String tag = Integer.toString(title);
            fragmentTransaction.replace(R.id.container_body, fragment,"Change Pin");
            fragmentTransaction.addToBackStack("Change Pin");
            fragmentTransaction.commit();
            ((FMobActivity)getActivity())
                    .setActionBarTitle("Change Pin");

        }

        if(v.getId() == R.id.homepagead){
            if(chkast.isChecked()){
                session.setAst();

            }else{
                session.UnSetAst();
            }

            Toast.makeText(
                    getActivity(),
                    "Settings Applied Successfully",
                    Toast.LENGTH_LONG).show();

        }
        if(v.getId() == R.id.shwbal){
            if(chkbal.isChecked()){
                session.UnSetShwBal();

            }else{
                session.setShwbal();
            }

            Toast.makeText(
                    getActivity(),
                    "Settings Applied Successfully",
                    Toast.LENGTH_LONG).show();

        }
        if(v.getId() == R.id.distpin){
            if(chktpin.isChecked()){
                session.setTpinPref();
            }else{
                session.UnSetTpinPref();
            }

            Toast.makeText(
                    getActivity(),
                    "Settings Applied Successfully",
                    Toast.LENGTH_LONG).show();

        }

        if(v.getId() == R.id.chkus){
            if(chkus.isChecked()){
                session.setUser();
            }else{
                session.UnSetUser();
            }

            Toast.makeText(
                    getActivity(),
                    "Settings Applied Successfully",
                    Toast.LENGTH_LONG).show();

        }

        if(v.getId() == R.id.ivgrid){
            session.setString("VTYPE","grid");
            getActivity().finish();


            // After logout redirect user to Loing Activity
            Intent i = new Intent(getActivity(), FMobActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Staring Login Activity
            startActivity(i);
        }
        if(v.getId() == R.id.ivlist){
            session.setString("VTYPE","list");
            getActivity().finish();


            Intent i = new Intent(getActivity(), FMobActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Staring Login Activity
            startActivity(i);
        }
        if(v.getId() == R.id.tdispedit){

            Fragment  fragment = new ActivitiesFrag();
            String title = "My Actions";
            ((FMobActivity)getActivity()).addFragment(fragment,title);

        }


    }

    public void SetDialog(String msg,String title){
        new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(msg)

                .negativeText("Close")
                .show();
    }
    public void SetHome(){
        Fragment fragment = new MyAccountFrag();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //  String tag = Integer.toString(title);
        fragmentTransaction.replace(R.id.container_body, fragment, "Welcome");
        fragmentTransaction.addToBackStack("Welcome");
        fragmentTransaction.commit();
        ((MainActivity)getActivity())
                .setActionBarTitle("Welcome");
    }
    public void SetLastL(){

    }

    public void loadImage(){
        File destination = new File(Environment.getExternalStorageDirectory(),
                "FBNProf.jpg");

        new DownloadImg().execute("");

    }
    class DownloadImg extends AsyncTask<String, String, String> {
        Bitmap bmp=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // prgDialog.show();
        }

        // Download Music File from Internet
        @Override
        protected String doInBackground(String... f_url) {


            try{
                if(!(getActivity() == null)) {
                    String url = ApplicationConstants.UNENC_URL + "profile/getpic.action/1/";
                    String usid = Utility.gettUtilUserId(getActivity());
                    url = url + usid;
                    bmp = downloadBitmap(url);
                }
            }catch(Exception e){


               // Toast.makeText(getContext(), "Error While Downloading File", Toast.LENGTH_LONG);
            }
            return "34";
        }


        private Bitmap downloadBitmap(String url) {
            HttpURLConnection urlConnection = null;
            try {
                Log.i("thumb", url);
                URL uri = new URL(url);
                urlConnection = (HttpURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setUseCaches(false);
                urlConnection.connect();

           /* int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }*/

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                urlConnection.disconnect();
                Log.w("thumb dnwld", "Error downloading image from " + url);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }





        @Override
        protected void onPostExecute(String file_url) {
            //  prgDialog.dismiss();

            if(bmp != null)
            {
                iv.setImageBitmap(bmp);
            }


        }
    }

}
