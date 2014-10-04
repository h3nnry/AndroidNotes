package h3nry.andreid.application;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class NoteActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID noteId = (UUID)getIntent().getSerializableExtra(NoteFragment.EXTRA_NOTE_ID);
		return NoteFragment.newInstance(noteId);
	}


}
