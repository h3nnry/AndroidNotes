package h3nry.andreid.application;

import java.util.Date;
import java.util.UUID;

public class Note {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	
	public Note(){
		//Generate unical id;
		mId = UUID.randomUUID();
		mDate = new Date();
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}

	public void setSolved(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
	
	public boolean isSolved() {
		return mSolved;
 	}
	
}
