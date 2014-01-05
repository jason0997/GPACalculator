package com.example.gpacalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle=this.getIntent().getExtras();
		RelativeLayout rl = new RelativeLayout(this);

		String[] course_code_array=bundle.getStringArray("course_code");
		String[] GPA_array=bundle.getStringArray("GPA");
		String[] Level_array=bundle.getStringArray("Level");
		String CGPA = bundle.getString("CGPA");
		String credit = bundle.getString("credit");
		
		TextView [] course_code_textview = new TextView [course_code_array.length];
		TextView [] GPA_textview = new TextView [GPA_array.length];
		TextView [] Level_textview = new TextView [Level_array.length];
		RelativeLayout.LayoutParams [] course_code_params = new RelativeLayout.LayoutParams[course_code_array.length];
		RelativeLayout.LayoutParams [] GPA_params = new RelativeLayout.LayoutParams[GPA_array.length];
		RelativeLayout.LayoutParams [] Level_params = new RelativeLayout.LayoutParams[Level_array.length];
		
		TextView course_code_instr_tv = new TextView(this);
		course_code_instr_tv.setText("Course");
		RelativeLayout.LayoutParams course_code_instr_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		course_code_instr_params.leftMargin=20;
		course_code_instr_params.topMargin=70;
		rl.addView(course_code_instr_tv,course_code_instr_params);

		TextView GPA_instr_tv = new TextView(this);
		GPA_instr_tv.setText("GPA");
		RelativeLayout.LayoutParams GPA_instr_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		GPA_instr_params.leftMargin=200;
		GPA_instr_params.topMargin=70;
		rl.addView(GPA_instr_tv,GPA_instr_params);		

		TextView Level_instr_tv = new TextView(this);
		Level_instr_tv.setText("Level");
		RelativeLayout.LayoutParams Level_instr_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		Level_instr_params.leftMargin=300;
		Level_instr_params.topMargin=70;
		rl.addView(Level_instr_tv,Level_instr_params);		

		
		for(int i=0;i<course_code_array.length;i++){
			course_code_textview[i] = new TextView(this);
			course_code_textview[i].setText(course_code_array[i]);
			course_code_params[i] = new RelativeLayout.LayoutParams(200,100);
			course_code_params[i].leftMargin = 20;
			course_code_params[i].topMargin = 120+i * 60;
			if(i==course_code_array.length-1)
					course_code_textview[i].setId(R.id.last_course_code);
			rl.addView(course_code_textview[i], course_code_params[i]);
			
			GPA_textview[i] = new TextView(this);
			GPA_textview[i].setText(GPA_array[i]);
			GPA_params[i] = new RelativeLayout.LayoutParams(200,100);
			GPA_params[i].leftMargin = 220;
			GPA_params[i].topMargin = 120+i * 60;
			rl.addView(GPA_textview[i], GPA_params[i]);		

			Level_textview[i] = new TextView(this);
			Level_textview[i].setText(Level_array[i]);
			Level_params[i] = new RelativeLayout.LayoutParams(200,100);
			Level_params[i].leftMargin = 320;
			Level_params[i].topMargin = 120+i * 60;
			rl.addView(Level_textview[i], Level_params[i]);				
		}

		TextView CGPA_textview = new TextView(this);
		TextView credit_textview = new TextView(this);
		RelativeLayout.LayoutParams CGPA_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams credit_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		credit_textview.setId(R.id.CGPA);
		credit_params.leftMargin=20;
		credit_params.addRule(RelativeLayout.BELOW,R.id.last_course_code);
		credit_textview.setText("You recevied: 		" + credit + " credits");
		rl.addView(credit_textview,credit_params);

		CGPA_params.leftMargin=20;
		CGPA_params.addRule(RelativeLayout.BELOW, R.id.CGPA);
		CGPA_textview.setText("Your CGPA: 		" + CGPA);
		rl.addView(CGPA_textview,CGPA_params);
		
		setContentView(rl);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
