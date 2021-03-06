/*
 * Copyright (C) 2012 jfrankie (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package adapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import firstmob.firstbank.com.subsidiary.R;

public class LoanListAdapter extends RecyclerView.Adapter<LoanListAdapter.MyViewHolder> {

	private List<LoanPOJO> planetList;
	private Context context;

    private LayoutInflater inflater;




    public LoanListAdapter(Context context, List<LoanPOJO> planetList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.planetList = planetList;


    }



    public void delete(int position) {
        planetList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.loan_newlist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       LoanPOJO p = planetList.get(position);
        holder.ldes.setText(p.getLdesc());
        holder.lacno.setText(p.getAcno());
        holder.lbal.setText(p.getLbal());
        holder.install.setText(p.getInstall());
        holder.prcloan.setText(p.getVamo());
        holder.term.setText(p.getPeriod());
        holder.lorate.setText(p.getIrate());
        holder.lst.setText(p.getStat());
        String nextd = p.getDueD();
        holder.dud.setText(getDateTimeStamp(nextd));
    }
    public static String getDateTimeStamp(String tdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMM yyy ");
        Date convertedCurrentDate = null;

        try {
            convertedCurrentDate = sdf.parse(tdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String strDate = sdf2.format(convertedCurrentDate);

        return strDate;
    }
    @Override
    public int getItemCount() {
        return planetList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView accid;
      public TextView curr;
        public TextView accamo;
      public  TextView lacno,term,lbal,prcloan,lorate,install,lst,ldes,dud;

        public MyViewHolder(View v) {
            super(v);
            lacno = (TextView) v.findViewById(R.id.sbpid);
            term = (TextView)  v.findViewById(R.id.vv2);
            prcloan = (TextView)  v.findViewById(R.id.vv);
            lbal = (TextView)  v.findViewById(R.id.sbpidk);
            lorate = (TextView)  v.findViewById(R.id.vvas);
            install = (TextView)  v.findViewById(R.id.vv3);
            lst = (TextView)  v.findViewById(R.id.lsd);
            ldes = (TextView)  v.findViewById(R.id.lodsc);
            dud = (TextView)  v.findViewById(R.id.df);
        }
    }


}
