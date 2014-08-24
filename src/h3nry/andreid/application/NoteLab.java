package h3nry.andreid.application;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class NoteLab {
	private ArrayList<Note> mNotes;
	
	private static NoteLab sNoteLab;
	private Context mAppContext;
	
	private NoteLab(Context appContext) {
		mAppContext = appContext;
		mNotes = new ArrayList<Note>();
		
		for (int i = 0; i< 100; i++) {
			Note c = new Note();
			c.setTitle("Note #" + i);
			c.setSolved(i % 2 == 0); //For each second object
			mNotes.add(c);
		}
	}
	
	public static NoteLab get(Context c) {
		if (sNoteLab == null) {
			sNoteLab = new NoteLab(c.getApplicationContext());
		}
		return sNoteLab;
	}
	
	public ArrayList<Note> getNotes() {
		return mNotes;
	}
	
	public Note getNote(UUID id) {
		for (Note c : mNotes) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}
}
