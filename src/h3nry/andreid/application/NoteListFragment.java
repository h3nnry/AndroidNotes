package h3nry.andreid.application;



import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


public class NoteListFragment extends ListFragment {
	private ArrayList<Note> mNotes;
	private static final String TAG = "NoteListFragment";
	private static final int REQUEST_NOTE = 1;
	
	
	@Override
	public void onResume(){
		super.onResume();
		((NoteAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_NOTE) {
			//Prelucrare resultat;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		getActivity().setTitle(R.string.notes_title);
		mNotes = NoteLab.get(getActivity()).getNotes();
		
		NoteAdapter adapter = new NoteAdapter(mNotes);
		setListAdapter(adapter);
	}		

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//Obtinerea obiectului Note din adapter;
		Note c = ((NoteAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, c.getTitle() + " was clicked");
		//start NoteActivity
		Intent i = new Intent(getActivity(), NotePagerActivity.class);
		startActivityForResult(i, REQUEST_NOTE);
		i.putExtra(NoteFragment.EXTRA_NOTE_ID, c.getId());
		startActivity(i);
	}
	private class NoteAdapter extends ArrayAdapter<Note> {
	
		public NoteAdapter(ArrayList<Note> notes) {
			super(getActivity(), 0, notes);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup part) {
			//If we have not get the view
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_note, null);				
			}
			
			//Settings view of the object Note
			Note c = getItem(position);
			
			TextView titleTextView =
					(TextView)convertView.findViewById(R.id.note_list_item_dateTextView);
			titleTextView.setText(c.getTitle());
			TextView dateTextView = 
					(TextView)convertView.findViewById(R.id.note_list_item_dateTextView);
			dateTextView.setText(c.getDate().toString());
			CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.note_list_item_solvedCheckBox);//ERROR 'crime_list_item_solvedCheckBox'
            solvedCheckBox.setChecked(c.isSolved());
			
			return convertView;
		}
	}

}
