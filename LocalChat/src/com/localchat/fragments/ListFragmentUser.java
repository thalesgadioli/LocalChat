package com.localchat.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.localchat.bd.User;
import com.localchat.bd.UserFacade;
import com.localchat.fragments.ListFragmentMenu.OnUserSelectedListener;
import com.localchat.sqlite.R;

public class ListFragmentUser extends Fragment {
	
	private ListView mList;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	

		List<User> users = UserFacade.get(getActivity());
		/*
		if (users!=null && users.size() > 0) {
			mList = (ListView) mList.findViewById(R.id.listView1);
			
			ArrayAdapter<User> mAdapter = new ArrayAdapter<User>(getActivity(), android.R.layout.simple_list_item_1, users);
			
			//mList.setAdapter(mAdapter);
			//mList.setOnItemClickListener((OnItemClickListener) getActivity());
		}
		*/
		Log.i("userFragment", "passei pelo ListFragmentUser");

        // Renderezi o seguinte arquivo de layout
        return inflater.inflate(R.layout.activity_list, container, false);
    }
	
	
	
	/*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("userFragment", "passei pelo UserFacade");
		List<User> users = UserFacade.get(getActivity());

		ArrayAdapter<User> mAdapter = new ArrayAdapter<User>(getActivity(), android.R.layout.simple_list_item_1, users);
        setListAdapter(mAdapter);
		
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
    */
	
	
}
