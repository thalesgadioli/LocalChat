package com.localchat.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListFragmentMenu extends ListFragment{
	
	private OnUserSelectedListener mCallback;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,  new String[]{"Usuários Online", 
																	"Cadastro de Usuário", 
																	"Sair"}));
		
	}
	
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnUserSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    "Ei leseira, tua activity deve implementar a classe OnUserSelectedListener");
        }
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onUserSelected(position);

        getListView().setItemChecked(position, true);
    }
    
    public interface OnUserSelectedListener {

        public void onUserSelected(int position);
        

    }
	

}