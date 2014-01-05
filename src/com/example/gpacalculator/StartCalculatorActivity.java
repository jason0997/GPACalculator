package com.example.gpacalculator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	static String [] GPA_array;
	static String [] Level_array;
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

        paramsRemoveButton.addRule(RelativeLayout.RIGHT_OF ,R.id.add);
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
                    paramsRemoveButton.addRule(RelativeLayout.RIGHT_OF,R.id.add);
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
                    paramsRemoveButton.addRule(RelativeLayout.RIGHT_OF,R.id.add);
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
        paramsRemoveButton.addRule(RelativeLayout.RIGHT_OF,R.id.add);
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
		GPA_array = new String [Count+1];
		Level_array = new String [Count+1];
		String [] course_code_array = new String[Count+1];
		for(int i=0; i<=Count; i++){
			courseCode = "course_code_et_" + i;    
			courseCodeid=getResources().getIdentifier(courseCode, "id",getPackageName());
			EditText courseCodeET = (EditText)findViewById(courseCodeid);
			courseCodeInput = courseCodeET.getText().toString();
						
			mark = "mark_et_" + i;
			markid=getResources().getIdentifier(mark, "id",getPackageName());			
			EditText markET = (EditText)findViewById(markid);
			markInput = markET.getText().toString();

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Oops!");
			alertDialogBuilder.setCancelable(false)
			.setNegativeButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			});
			
			if(!isEmpty(courseCodeInput,markInput)){
				if(!isCourseCode(courseCodeInput)){
					alertDialogBuilder.setMessage("Invalid Course Code..  (E.g. CSC108H or MAT235Y)");		
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					return;
				}else if(!isMark(markInput)){
					alertDialogBuilder.setMessage("Invalid Mark..");		
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					return;
				}else{
					course_code_array[i]=courseCodeInput.toUpperCase();
					getGPA_LV(markInput,i);					
				}
			}
			else{
				alertDialogBuilder.setMessage("Empty space is not allowed!");		
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				return;
			}
		}
		Bundle bundle=new Bundle();
		bundle.putStringArray("course_code", course_code_array);
		bundle.putStringArray("GPA", GPA_array);
		bundle.putStringArray("Level", Level_array);
		bundle.putString("message", String.valueOf(Count));
		String [] result = getCGPA(course_code_array);
		bundle.putString("CGPA", result[0]);
		bundle.putString("credit", result[1]);
		intent.putExtras(bundle);
		startActivity(intent);
    }
    
    private void getGPA_LV(String mark, int index){
    	int score = Integer.parseInt(mark);
		if(score>=90){
			Level_array[index]="A+";
			GPA_array[index]="4.0";
		}else if(score>=85){
			Level_array[index]="A";
			GPA_array[index]="4.0";
		}else if(score>=80){
			Level_array[index]="A-";
			GPA_array[index]="3.7";
		}else if(score>=77){
			Level_array[index]="B+";
			GPA_array[index]="3.3";
		}else if(score>=73){
			Level_array[index]="B";
			GPA_array[index]="3.0";
		}else if(score>=70){
			Level_array[index]="B-";
			GPA_array[index]="2.7";
		}else if(score>=67){
			Level_array[index]="C+";
			GPA_array[index]="2.3";
		}else if(score>=63){
			Level_array[index]="C";
			GPA_array[index]="2.0";
		}else if(score>=60){
			Level_array[index]="C-";
			GPA_array[index]="1.7";
		}else if(score>=57){
			Level_array[index]="D+";
			GPA_array[index]="1.3";
		}else if(score>=53){
			Level_array[index]="D";
			GPA_array[index]="1.0";
		}else if(score>=50){
			Level_array[index]="D-";
			GPA_array[index]="0.7";
		}else{
			Level_array[index]="F";
			GPA_array[index]="0";
		}
    }
    
    private String [] getCGPA(String []course_code_array){
    	String strCrsCode =null;
    	Double GPA=0.0;
    	Double weigh=0.0;
    	Double sum=0.0;
    	Double count=0.0;
    	for(int i=0;i<=Count;i++){
			strCrsCode = course_code_array[i];
			GPA = Double.valueOf(GPA_array[i]);
			if(strCrsCode.charAt(strCrsCode.length()-1) == 'H' || strCrsCode.charAt(strCrsCode.length()-1) == 'h' )
				weigh = 0.5;
			else
				weigh = 1.0;
			sum = sum + GPA * weigh;
			if(!GPA_array[i].equals("0"))
				count = count + weigh;    		
    	}
		Double result = sum / count;
		result = (Double)(((int)Math.round((result * 100)))/100.0);
		String [] return_array = {String.valueOf(result),String.valueOf(count)};
		return return_array;
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
