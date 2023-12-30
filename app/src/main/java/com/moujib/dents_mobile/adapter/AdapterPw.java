package com.moujib.dents_mobile.adapter;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moujib.dents_mobile.R;
import com.moujib.dents_mobile.beans.PW;

import java.util.List;

public class AdapterPw extends BaseAdapter {
    private List<PW> pws;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener2 onItemClickListener2;

    public AdapterPw(List<PW> pws, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.pws = pws;
    }

    public interface OnItemClickListener {
        void onItemClick(byte[] pdfBytes, String pdfFileName);
    }

    public interface OnItemClickListener2 {
        void onItemClick(long pwId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemClickListener2(OnItemClickListener2 listener) {
        this.onItemClickListener2 = listener;
    }

    @Override
    public int getCount() {
        return pws.size();
    }

    @Override
    public Object getItem(int i) {
        return pws.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pws.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_tp, null);
        }

        TextView id = convertView.findViewById(R.id.id);
        TextView title = convertView.findViewById(R.id.titile);
        TextView objectif = convertView.findViewById(R.id.objectif);
        ImageView imageButton = convertView.findViewById(R.id.btnd);
        ImageButton btngo = convertView.findViewById(R.id.btngo);

        String pdfFileName = (pws.get(position).getTitle() != null) ? pws.get(position).getTitle() + ".pdf" : "Untitled.pdf";

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    String encodedDocs = pws.get(position).getDocs();
                    if (encodedDocs != null) {
                        byte[] decodedBytes = Base64.decode(encodedDocs, Base64.DEFAULT);
                        onItemClickListener.onItemClick(decodedBytes, pdfFileName);
                    } else {
                        Log.d("pdf", "aucun pdf associé");
                    }
                }
            }
        });

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long pwId = pws.get(position).getId();
                if (onItemClickListener2 != null) {
                    onItemClickListener2.onItemClick(pwId);
                }
            }
        });

        id.setText(String.valueOf(pws.get(position).getId()));
        title.setText(pws.get(position).getTitle());
        objectif.setText(pws.get(position).getObjectif());

        return convertView;
    }
}
