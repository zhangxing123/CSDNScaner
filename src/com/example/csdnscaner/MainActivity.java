package com.example.csdnscaner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.csdnscaner.Util.DensityUtil;
import com.example.csdnscaner.Util.FileService;











import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
     private int columnWidth_px=250; 
     private int columnWidth_dp;
     int width=0;
     private static final String TAG = "ASYNC_TASK";
 	Document doc;
 	private List<HashMap<String, String>> htmlContent;
	private MyTask mTask;
	GridView cateGridView;
	ListView newListView;

	SimpleAdapter newSimpleAdapter;
	List<HashMap<String, String>> newsHashMaps;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
       newsHashMaps=new ArrayList<HashMap<String,String>>();
		mTask = new MyTask();
		mTask.execute("http://blog.csdn.net/");
		columnWidth_dp=DensityUtil.px2dip(columnWidth_px);
		String [] categoriesArry=getResources().getStringArray(R.array.categories);
		List<HashMap<String, String>> cateArrayList=new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < categoriesArry.length; i++) {
			HashMap<String, String> hashMap=new HashMap<String, String>();
			hashMap.put("categ", categoriesArry[i]);
			cateArrayList.add(hashMap);
		}
		
		cateAdapter cateAdapter=new cateAdapter(this, cateArrayList, R.layout.cate_title, new String[]{"categ"}, new int[]{R.id.category_title});
		 cateGridView=new GridView(this);
		cateGridView.setColumnWidth(columnWidth_dp);
		cateGridView.setNumColumns(GridView.AUTO_FIT);
		cateGridView.setGravity(Gravity.CENTER);
		width = columnWidth_dp* (cateArrayList.size()+1);
		LayoutParams params=new LayoutParams(width,LayoutParams.MATCH_PARENT);
		cateGridView.setLayoutParams(params);
		cateGridView.setAdapter(cateAdapter);
		cateGridView.setOnItemClickListener(new OnItemClickListener() {
			TextView categoryTitle=null;
			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView caTextView=(TextView) view;
		
				for (int i = 0; i < parent.getCount(); i++) {
					TextView categoryTitle = (TextView) parent.getChildAt(i);
					categoryTitle.setTextColor(getResources().getColor(
							R.drawable.category_item_title_selector));
					categoryTitle.setBackgroundDrawable(null);
				}
				caTextView.setTextColor(getResources().getColor(R.drawable.white));
				caTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_item_background));
			}
		});
		LinearLayout categoriesLayout=(LinearLayout) findViewById(R.id.category_layout);
		categoriesLayout.addView(cateGridView);
		Button cateButton=(Button) findViewById(R.id.category_arrow_right);
		final HorizontalScrollView categHorizontalScrollView=(HorizontalScrollView) findViewById(R.id.category_scorllview);
		cateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				categHorizontalScrollView.fling(10*columnWidth_dp);
			}
		});

		for (int i = 0; i < 10; i++) {
			HashMap<String, String> hsHashMap= new HashMap<String, String>();
			hsHashMap.put("newslist_item_title", "2016年最值得关注的5大机器人趋势");
			hsHashMap.put("newslist_item_digest", "《麻省理工科技评论》网站今日评出了2016年全球最值得关注的5大机器人趋势，其中中国的机器人革命排名首位。");
			hsHashMap.put("newslist_item_source", "新浪科技 微博");
			hsHashMap.put("newslist_item_ptime", "2016年01月01日 13:22");
		newsHashMaps.add(hsHashMap);
		}
	newListView=(ListView) findViewById(R.id.category_news_list);
	 newSimpleAdapter=new SimpleAdapter(this, newsHashMaps, R.layout.newsitem,
			                 new String[]{"newslist_item_title","newslist_item_digest","newslist_item_source","newslist_item_ptime"}, 
			                 new int[]{R.id.newslist_item_title,R.id.newslist_item_digest,R.id.newslist_item_source,R.id.newslist_item_ptime});
	newListView.setAdapter(newSimpleAdapter);
	newListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,NewsDetailsActivity.class);
			String uriString=newsHashMaps.get(position).get("uriString");
			String itemTitle=newsHashMaps.get(position).get("newslist_item_title");
			i.putExtra("uriString",uriString);
			i.putExtra("itemTitle",itemTitle);
			startActivity(i);
		}
	});
	}
	
	class cateAdapter extends SimpleAdapter{

		public cateAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}
		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view=super.getView(position, convertView, parent);
			if (position==0) {
				TextView textView=(TextView) view;
				textView.setTextColor(getResources().getColor(R.drawable.white));
				textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_item_background));
			}
			return view;
			
		}
	}
	private class MyTask extends AsyncTask<String, Integer, String> {
    	//onPreExecute方法用于在执行后台任务前做一些UI操作
    	@Override
    	protected void onPreExecute() {
    		Log.i(TAG, "onPreExecute() called");
    		
    
    	
    	}
    	
    	//doInBackground方法内部执行后台任务,不可在此方法内修改UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			try {
				URL url = new URL(params[0]);
	            URLConnection conn = url.openConnection();
	            conn.connect();
	            InputStream in = conn.getInputStream();
	            InputStreamReader input = new InputStreamReader(in, "UTF-8");
	            BufferedReader buf = new BufferedReader(input);
	            String nextLine = buf.readLine();
					StringBuffer content = new StringBuffer();
					int i = 0;
				      while(nextLine != null){
			             content.append(nextLine);
			                nextLine = buf.readLine();
			            	publishProgress(i++);
							//为了演示进l度,休眠500毫秒
//							Thread.sleep(100); 
				      }
				      String dataString=new String(content);
			         FileService fileService=new FileService(MainActivity.this);
			         fileService.save("cmpdata", dataString);
//			         fileService.print(new String(content));
			         
					
					return dataString;
				
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return null;
		}
		
		//onProgressUpdate方法用于更新进度信息
		@Override
    	protected void onProgressUpdate(Integer... progresses) {
			Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
	
			
    	}
    	
		//onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		@Override
		protected void onPostExecute(String result) {
			Log.i(TAG, "onPostExecute(Result result) called");
		
			 doc=Jsoup.parse(result);
			
		    newsHashMaps.clear();
			 Elements mainContents=doc.getElementsByClass("main_list");
			 Elements contents=mainContents.get(0).getElementsByTag("dl");
			 for (int i = 0; i < contents.size(); i++) {
			 Element temElement=contents.get(i);
			 Elements temA=temElement.getElementsByTag("a");
			 Element titilElement=temA.get(0);
			 String title=titilElement.text();
			 String titleId=titilElement.attr("href").substring(20);
			 Element nameElement=temA.get(2);
			 String articleName=nameElement.text();
			 Element timeElement=temElement.getElementsByTag("em").get(0);
			 String timeString=timeElement.text();
			 String uriString="http://blog.csdn.net/"+articleName+
					 "/article/details/"+titleId;
				HashMap<String, String> hsHashMap= new HashMap<String, String>();
    			hsHashMap.put("newslist_item_title", title);
    			hsHashMap.put("newslist_item_digest", "《麻省理工科技评论》网站今日评出了2016年全球最值得关注的5大机器人趋势，其中中国的机器人革命排名首位。");
    			hsHashMap.put("newslist_item_source", articleName);
    			hsHashMap.put("newslist_item_ptime", timeString);
    			hsHashMap.put("uriString", uriString);
			 newsHashMaps.add(hsHashMap);
		     newSimpleAdapter.notifyDataSetChanged();
			 }
			

		}
		
		//onCancelled方法用于在取消执行中的任务时更改UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");
			
		}
    }
	
}
