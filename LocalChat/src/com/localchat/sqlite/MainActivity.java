package com.localchat.sqlite;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.localchat.fragments.CadUserFragmentsInicio;
import com.localchat.fragments.DefaultFragment;
import com.localchat.fragments.ListFragmentMenu;
import com.localchat.fragments.ListFragmentUser;

public class MainActivity extends FragmentActivity implements ListFragmentMenu.OnUserSelectedListener{

	ListFragmentMenu listFragment = new ListFragmentMenu();
	public static final String PREF_NAME = "lChat_pref"; 
	
	public static final String PREF_USER_ID = "pref_userid";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		  // É um handset?
        if (findViewById(R.id.fragment_conteiner) != null) {

            // Caso esse objeto já tenha sido criado então não precisamos fazer nada,
            // ou então iremos sobrepor fragments
            if (savedInstanceState != null) {
                return;
            }
            // Adicionamos o nosso Fragment ao FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_conteiner, listFragment).commit();
        }else if (findViewById(R.id.fragment_content) != null) {
            // Tablet
        	
        	DefaultFragment detailsFragment = new DefaultFragment();
        	
			/** Pega a transação */
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			
			/** Coloca o fragment de menu do lado esquerdo e o fragment de conteúdo do lado direito */
			transaction.add(R.id.fragment_list, listFragment); 
			transaction.add(R.id.fragment_content, detailsFragment); 
			
			/** Confirma as transações */
			transaction.commit();
			
        }
	}

	@Override
	public void onUserSelected(int position) {

				int posicaoValida = position+1;
				
				Fragment newFragment = null;
		       switch(posicaoValida){
		        case 1:
		        	 newFragment = new ListFragmentUser();
		        	break;
		        case 2:
		        	newFragment = new CadUserFragmentsInicio();
		        	break;
		        case 3:
		        	Intent intent = new Intent(Intent.ACTION_MAIN); 
		        	finish();
		        	break;
		        }
		       
				if (newFragment != null) {

					/** Pega a transação */
					FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					
					if (findViewById(R.id.fragment_conteiner) != null) {
						/** Se for acessado de um smartphone o espaço fragment_conteiner existirá */
						
						/** Adiciona o fragment com o novo conteúdo no único espaço */
						transaction.replace(R.id.fragment_conteiner, newFragment); 
						
						/** Adiciona o fragment a backstack */
						transaction.addToBackStack(null);
						
					} else if (findViewById(R.id.fragment_content) != null) {
						/** Se for acessado de um tablet o espaço fragment_conteiner não existirá, existirá o menu e content */
						
						/** Coloca o fragment com o novo conteúdo do lado direito */
						transaction.replace(R.id.fragment_content, newFragment); 
					}

					/** Confirma a transação */
					transaction.commit();
				}
		       
	        
	    }



}