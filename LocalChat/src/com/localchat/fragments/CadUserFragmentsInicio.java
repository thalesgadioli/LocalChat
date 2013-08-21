package com.localchat.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.localchat.bd.User;
import com.localchat.bd.UserFacade;
import com.localchat.sqlite.MainActivity;
import com.localchat.sqlite.R;

public class CadUserFragmentsInicio  extends Fragment implements OnClickListener{
   
	public View Myview;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		Myview = inflater.inflate(R.layout.primeiro_cadastro, container, false);
    	
    	Button botao1 = (Button)Myview.findViewById(R.id.button5);
		botao1.setOnClickListener(this);

        // Renderezi o seguinte arquivo de layout
        return Myview;
        
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
if (arg0.getId() == R.id.button5) {
			
			User user = new User();
			
			
			EditText et1 = (EditText) Myview.findViewById(R.id.editText5);
			EditText et2 = (EditText) Myview.findViewById(R.id.editText6);
			
			String nome = et1.getText().toString();
			String fone = et2.getText().toString();
			
			user.setName(nome);
			user.setPhone(fone);
			
			long id = UserFacade.insert(user, getActivity());
			
			/*
			SharedPreferences.Editor editor = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE).edit();
			editor.putLong(MainActivity.PREF_USER_ID, id);
			editor.commit();
			*/
			Toast.makeText(getActivity(), "Operacao realizada com sucesso!", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(getActivity(), MainActivity.class);
			startActivity(intent);
			
			
		} 	
	}
}
