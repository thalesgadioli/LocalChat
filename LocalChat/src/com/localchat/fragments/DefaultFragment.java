package com.localchat.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.localchat.sqlite.R;


public class DefaultFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_default, container, false);
    	
		/** Preenche o conteúdo do textview com a informação de qual menu foi clicado */
		//TextView txt = (TextView) view.findViewById(R.id.frag);
		//txt.setText("Conteúdo do menu ");

        // Renderezi o seguinte arquivo de layout
        return view;
    }

}