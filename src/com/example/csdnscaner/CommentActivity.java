package com.example.csdnscaner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CommentActivity extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comments);
		
		ListView commentlist=(ListView) findViewById(R.id.comments_list);
		List<HashMap<String, String>> commentHasList=new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, String> commentHashMap=new HashMap<String, String>();
			commentHashMap.put("commentator_from", "连云港朋友：");
			commentHashMap.put("comment_ptime", "2012-03-22 20:21:22");
			commentHashMap.put("comment_content", "这篇文章值得分享");
			commentHasList.add(commentHashMap);
			}
		SimpleAdapter commentSimpleAdapter=new SimpleAdapter(this, commentHasList, R.layout.comments_list_item, 
				new String[]{"commentator_from","comment_ptime","comment_content"},
		       new int[]{R.id.commentator_from,R.id.comment_ptime,R.id.comment_content}
			);
		commentlist.setAdapter(commentSimpleAdapter);
	
}
}
