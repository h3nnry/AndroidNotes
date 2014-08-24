package h3nry.andreid.application;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class NoteFragment extends Fragment {
	private Note mNote;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNote = new Note();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_note, parent, false);
		
		mTitleField = (EditText)v.findViewById(R.id.note_title);
		mTitleField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(
					CharSequence c, int start, int before, int count){
				mNote.setTitle(c.toString());
			}
			
			public void beforeTextChanged(
					CharSequence c, int start, int count, int after) {
				//
			}
			public void afterTextChanged(Editable c) {
				//
			}
		});
		mDateButton = (Button)v.findViewById(R.id.note_date);
		Calendar calendar = Calendar.getInstance();
		//String dateAndroid = android.text.format.DateFormat.getLongDateFormat(this.getActivity()).toString();
//		mDateButton.setText(dateAndroid);
		mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mNote.getDate()).toString());
		//mDateButton.setText(mNote.getDate().toString());
		mDateButton.setEnabled(false);
		
		mSolvedCheckBox = (CheckBox)v.findViewById(R.id.note_solved);
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
				//setting flag to solve the note
//				mNote.setSolved(isChecked);
			}
		});
		return v;
	}

}
