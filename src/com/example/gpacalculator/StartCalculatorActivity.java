package com.example.gpacalculator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class StartCalculatorActivity extends Activity {
	private RelativeLayout  mLayout;
	private Button addButton;
	private Button submitButton;
	private Button removeButton;
    final RelativeLayout.LayoutParams paramsAddButton = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    final RelativeLayout.LayoutParams paramsSubmitButton = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    final RelativeLayout.LayoutParams paramsRemoveButton = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	static private int Count;
    public final static String EXTRA_MESSAGE = "com.example.gpacalculator.MESSAGE";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_calculator);
        
        mLayout = (RelativeLayout) findViewById(R.id.start_layout);
        addButton = (Button) findViewById(R.id.add);
        submitButton = (Button) findViewById(R.id.submit);
        removeButton = (Button) findViewById(R.id.remove);
        addButton.setOnClickListener(onAddClick());
        removeButton.setOnClickListener(onRemoveClick());
        
        paramsAddButton.addRule(RelativeLayout.BELOW,R.id.course_code_et_0);
        addButton.setLayoutParams(paramsAddButton);
        
        paramsSubmitButton.addRule(RelativeLayout.ALIGN_RIGHT,R.id.mark_et_0);
        paramsSubmitButton.addRule(RelativeLayout.ALIGN_BASELINE,R.id.add);
        submitButton.setLayoutParams(paramsSubmitButton);       

        paramsRemoveButton.addRule(RelativeLayout.ALIGN_RIGHT ,R.id.course_code_et_0);
        paramsRemoveButton.addRule(RelativeLayout.ALIGN_BASELINE,R.id.add);
        submitButton.setLayoutParams(paramsSubmitButton);       
        
        Count = 0;
        // Show the Up button in the action bar.
        setupActionBar();
    }

    private OnClickListener onRemoveClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(Count>0){
	            	String preCourseCode = "course_code_et_" + (Count - 1);
	            	int preCourseCodeid = getResources().getIdentifier(preCourseCode, "id", getPackageName());	            	            	
	            	String preMark = "mark_et_" + (Count - 1);
	            	int preMarkid = getResources().getIdentifier(preMark, "id", getPackageName());

	            	paramsAddButton.addRule(RelativeLayout.BELOW,preCourseCodeid);
                    addButton.setLayoutParams(paramsAddButton);

                    paramsSubmitButton.addRule(RelativeLayout.BELOW,preMarkid);
                    paramsSubmitButton.addRule(RelativeLayout.ALIGN_RIGHT,preMarkid);
                    submitButton.setLayoutParams(paramsSubmitButton);                  	                	

                    paramsRemoveButton.addRule(RelativeLayout.BELOW,preCourseCodeid);
                    paramsRemoveButton.addRule(RelativeLayout.ALIGN_RIGHT,preCourseCodeid);
                    removeButton.setLayoutParams(paramsRemoveButton);                  	                	                    	            	

            		String oldCourseCode = "course_code_et_" + Count;
	            	int oldCourseCodeid = getResources().getIdentifier(oldCourseCode, "id", getPackageName());
	            	EditText courseCodeET = (EditText)findViewById(oldCourseCodeid);
	            	courseCodeET.setVisibility(EditText.GONE);
	            	mLayout.removeView(courseCodeET);
	
	            	String oldMark = "mark_et_" + Count;
	            	int oldMarkid = getResources().getIdentifier(oldMark, "id", getPackageName());
	            	EditText markET = (EditText)findViewById(oldMarkid);
	            	mLayout.removeView(markET);
	            		 	            	
	            	Count = Count - 1;
            	}
            }
        };
    }	
	
    private OnClickListener onAddClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
            	String oldCourseCode = "course_code_et_" + Count;    
            	int oldCourseCodeid=getResources().getIdentifier(oldCourseCode, "id",getPackageName());
                final RelativeLayout.LayoutParams paramsCourseCode = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                paramsCourseCode.addRule(RelativeLayout.BELOW,oldCourseCodeid);
                paramsCourseCode.width=(int)(findViewById(oldCourseCodeid)).getWidth();

            	String oldMark = "mark_et_" + Count;    
            	int oldMarkid=getResources().getIdentifier(oldMark, "id",getPackageName());
                final RelativeLayout.LayoutParams paramsMark = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                paramsMark.addRule(RelativeLayout.BELOW,oldMarkid);
                paramsMark.addRule(RelativeLayout.ALIGN_LEFT,oldMarkid);
                paramsMark.width=(int)(findViewById(oldMarkid)).getWidth();

                if(Count<11){
                    mLayout.addView(createNewCourseCode(),paramsCourseCode);
	                mLayout.addView(createNewMark(),paramsMark);
	                Count = Count + 1;
                }else{
                	paramsAddButton.addRule(RelativeLayout.BELOW,R.id.course_code_et_11);
                    addButton.setLayoutParams(paramsAddButton);
                    
                    paramsSubmitButton.addRule(RelativeLayout.BELOW,R.id.mark_et_11);
                    paramsSubmitButton.addRule(RelativeLayout.ALIGN_RIGHT,R.id.mark_et_0);
                    submitButton.setLayoutParams(paramsSubmitButton);                  	                	

                    paramsRemoveButton.addRule(RelativeLayout.BELOW,R.id.course_code_et_11);
                    paramsRemoveButton.addRule(RelativeLayout.ALIGN_RIGHT,R.id.course_code_et_0);
                    removeButton.setLayoutParams(paramsRemoveButton);                  	                	                    
                }             
            }
        };
    }
    
    @SuppressLint("NewApi")
	private EditText createNewCourseCode() {
        final EditText edittext = new EditText(this);
        edittext.setHint("E.g. CSC108H");
        edittext.setSingleLine();
       
        String newCourseCode = "course_code_et_" + (Count+1);
    	int newCourseCodeid=getResources().getIdentifier(newCourseCode, "id",getPackageName());
        edittext.setId(newCourseCodeid);
        
        paramsAddButton.addRule(RelativeLayout.BELOW,newCourseCodeid);
        addButton.setLayoutParams(paramsAddButton);

        paramsRemoveButton.addRule(RelativeLayout.BELOW,newCourseCodeid);
        paramsRemoveButton.addRule(RelativeLayout.ALIGN_RIGHT,R.id.course_code_et_0);
        removeButton.setLayoutParams(paramsRemoveButton);
        
        return edittext;
    }

    
    @SuppressLint("NewApi")
	private EditText createNewMark() {
        final EditText edittext = new EditText(this);
        edittext.setHint("E.g. 78");
        edittext.setSingleLine();
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);

        String newMark = "mark_et_" + (Count+1);
    	int newMarkid=getResources().getIdentifier(newMark, "id",getPackageName());
        edittext.setId(newMarkid);        

        paramsSubmitButton.addRule(RelativeLayout.BELOW,newMarkid);
        paramsSubmitButton.addRule(RelativeLayout.ALIGN_RIGHT,R.id.mark_et_0);
        submitButton.setLayoutParams(paramsSubmitButton);
        
        return edittext;
    }   
    
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
        	getActionBar().setDisplayHomeAsUpEnabled(true);
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
    
   
    public void Submit_GPA(View view){
		Intent intent = new Intent(this, ResultActivity.class);
		String courseCode = null;
		String mark = null;
		int courseCodeid = 0;
		int markid = 0;
		String courseCodeInput = null;
		String markInput =null;
		for(int i=0; i<=Count; i++){
			courseCode = "course_code_et_" + i;    
			courseCodeid=getResources().getIdentifier(courseCode, "id",getPackageName());
			EditText courseCodeET = (EditText)findViewById(courseCodeid);
			courseCodeInput = courseCodeET.getText().toString();
						
			mark = "mark_et_" + i;
			markid=getResources().getIdentifier(mark, "id",getPackageName());			
			EditText markET = (EditText)findViewById(markid);
			markInput = markET.getText().toString();
			
			if(!isEmpty(courseCodeInput,markInput)){
				if(!isCourseCode(courseCodeInput)){
					intent.putExtra(EXTRA_MESSAGE,"Invalid CourseCode format");								
				}else if(!isMark(markInput)){
					intent.putExtra(EXTRA_MESSAGE,"Invalid Mark format");								
				}else{
					intent.putExtra(EXTRA_MESSAGE, "Valid!");
				}
			}
			else
				intent.putExtra(EXTRA_MESSAGE,"Empty!!!");			
		}
		startActivity(intent);
    }
    
    private boolean isEmpty(String courseCode, String mark){
    	if(courseCode.matches("") || mark.matches(""))
    			return true;
    	return false;
    }
    
    private boolean isCourseCode(String courseCode){
    	if(courseCode.matches("^[A-Za-z]{3}[0-9]{3}[YHyh]$"))
    		return true;
    	else
    		return false;
    }

    private boolean isMark(String mark){
    	if(mark.matches("^100$|^[1-9][0-9]$|^[0-9]$"))
    		return true;
    	else
    		return false;
    }

}
