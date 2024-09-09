package com.example.autocheckup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final ArrayList<String> mVeiculos;

    public CustomAdapter(Context context, ArrayList<String> veiculos) {
        super(context, R.layout.activity_home, veiculos);
        mContext = context;
        mVeiculos = veiculos;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_home, parent, false);
        }

        TextView textVehiclerIdentifier = convertView.findViewById(R.id.textVehicleIdentifier);
        textVehiclerIdentifier.setText(mVeiculos.get(position));

        return convertView;
    }
}

