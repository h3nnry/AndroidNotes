package h3nry.andreid.application;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
public class DatePickerFragment extends DialogFragment{
	public static final String EXTRA_DATE = "h3nry.andreid.application.date";
	private Date mDate;
	
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	private void sendResult(int resultCode) {
		if(getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		
		//Create object Calendar for getting year, month and day;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener(){
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				//change year,month, day in object Date;
				mDate = new GregorianCalendar(year, month, day).getTime();
				
				//renew arguments for saving
				//picked value on turning the dispozitive
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
				
		return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
						
					}
				}).create();
	}
}
