package h3nry.andreid.application;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
	public static final String EXTRA_NOTE_ID = "h3nry.andreid.application.note_id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_DATE) {
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mNote.setDate(date);
			updateDate();
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID noteId = (UUID)getArguments().getSerializable(EXTRA_NOTE_ID);
		mNote = NoteLab.get(getActivity()).getNote(noteId);
	}
	
	public void returnResult(){
		getActivity().setResult(Activity.RESULT_OK, null);
	}
	
	public static NoteFragment newInstance(UUID noteId){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_NOTE_ID, noteId);
		NoteFragment fragment = new NoteFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	public void updateDate() {
		mDateButton.setText(mNote.getDate().toString());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_note, parent, false);
		
		mTitleField = (EditText)v.findViewById(R.id.note_title);
		mTitleField.setText(mNote.getTitle());
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
//		mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mNote.getDate()).toString());
		updateDate();
		//mDateButton.setText(mNote.getDate().toString());
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mNote.getDate());
				dialog.setTargetFragment(NoteFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		mSolvedCheckBox = (CheckBox)v.findViewById(R.id.note_solved);
		mSolvedCheckBox.setChecked(mNote.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
				//setting flag to solve the note
//				mNote.setSolved(isChecked);
			}
		});
		return v;
	}

}
