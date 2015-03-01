/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.recipes.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.net.Uri;
import com.robotoworks.example.recipes.content.RecipesDBContract.RecipesWithAuthors;
import com.robotoworks.example.recipes.content.RecipesDBContract.RecipesWithAuthors.Builder;
import com.robotoworks.mechanoid.util.Closeables;
import com.robotoworks.mechanoid.db.ActiveRecord;
import com.robotoworks.mechanoid.db.ActiveRecordFactory;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.db.AbstractValuesBuilder;

public class RecipesWithAuthorsRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<RecipesWithAuthorsRecord> sFactory = new ActiveRecordFactory<RecipesWithAuthorsRecord>() {
		@Override
		public RecipesWithAuthorsRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}

        @Override
                    public Uri getContentUri() {
                        return RecipesWithAuthors.CONTENT_URI;
                    }
                };

    			public static ActiveRecordFactory<RecipesWithAuthorsRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<RecipesWithAuthorsRecord> CREATOR 
    	= new Parcelable.Creator<RecipesWithAuthorsRecord>() {
        public RecipesWithAuthorsRecord createFromParcel(Parcel in) {
            return new RecipesWithAuthorsRecord(in);
        }

        public RecipesWithAuthorsRecord[] newArray(int size) {
            return new RecipesWithAuthorsRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	RecipesWithAuthors._ID,
    	RecipesWithAuthors.RECIPE_TITLE,
    	RecipesWithAuthors.RECIPE_DESCRIPTION,
    	RecipesWithAuthors.AUTHOR_ID,
    	RecipesWithAuthors.AUTHOR_NAME
    };
    
    public interface Indices {
    	int _ID = 0;
    	int RECIPE_TITLE = 1;
    	int RECIPE_DESCRIPTION = 2;
    	int AUTHOR_ID = 3;
    	int AUTHOR_NAME = 4;
    }
    
    private String mRecipeTitle;
    private boolean mRecipeTitleDirty;
    private String mRecipeDescription;
    private boolean mRecipeDescriptionDirty;
    private long mAuthorId;
    private boolean mAuthorIdDirty;
    private String mAuthorName;
    private boolean mAuthorNameDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setRecipeTitle(String recipeTitle) {
    	mRecipeTitle = recipeTitle;
    	mRecipeTitleDirty = true;
    }
    
    public String getRecipeTitle() {
    	return mRecipeTitle;
    }
    public void setRecipeDescription(String recipeDescription) {
    	mRecipeDescription = recipeDescription;
    	mRecipeDescriptionDirty = true;
    }
    
    public String getRecipeDescription() {
    	return mRecipeDescription;
    }
    public void setAuthorId(long authorId) {
    	mAuthorId = authorId;
    	mAuthorIdDirty = true;
    }
    
    public long getAuthorId() {
    	return mAuthorId;
    }
    public void setAuthorName(String authorName) {
    	mAuthorName = authorName;
    	mAuthorNameDirty = true;
    }
    
    public String getAuthorName() {
    	return mAuthorName;
    }
    
    public RecipesWithAuthorsRecord() {
    	super(RecipesWithAuthors.CONTENT_URI);
	}
	
	private RecipesWithAuthorsRecord(Parcel in) {
    	super(RecipesWithAuthors.CONTENT_URI);
    	
		setId(in.readLong());
		
		mRecipeTitle = in.readString();
		mRecipeDescription = in.readString();
		mAuthorId = in.readLong();
		mAuthorName = in.readString();
		
		boolean[] dirtyFlags = new boolean[4];
		in.readBooleanArray(dirtyFlags);
		mRecipeTitleDirty = dirtyFlags[0];
		mRecipeDescriptionDirty = dirtyFlags[1];
		mAuthorIdDirty = dirtyFlags[2];
		mAuthorNameDirty = dirtyFlags[3];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mRecipeTitle);
		dest.writeString(mRecipeDescription);
		dest.writeLong(mAuthorId);
		dest.writeString(mAuthorName);
		dest.writeBooleanArray(new boolean[] {
			mRecipeTitleDirty,
			mRecipeDescriptionDirty,
			mAuthorIdDirty,
			mAuthorNameDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = RecipesWithAuthors.newBuilder();

		if(mRecipeTitleDirty) {
			builder.setRecipeTitle(mRecipeTitle);
		}
		if(mRecipeDescriptionDirty) {
			builder.setRecipeDescription(mRecipeDescription);
		}
		if(mAuthorIdDirty) {
			builder.setAuthorId(mAuthorId);
		}
		if(mAuthorNameDirty) {
			builder.setAuthorName(mAuthorName);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mRecipeTitleDirty = dirty;
		mRecipeDescriptionDirty = dirty;
		mAuthorIdDirty = dirty;
		mAuthorNameDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setRecipeTitle(c.getString(Indices.RECIPE_TITLE));
		setRecipeDescription(c.getString(Indices.RECIPE_DESCRIPTION));
		setAuthorId(c.getLong(Indices.AUTHOR_ID));
		setAuthorName(c.getString(Indices.AUTHOR_NAME));
	}
	
	public static RecipesWithAuthorsRecord fromCursor(Cursor c) {
	    RecipesWithAuthorsRecord item = new RecipesWithAuthorsRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static RecipesWithAuthorsRecord fromBundle(Bundle bundle, String key) {
		bundle.setClassLoader(RecipesWithAuthorsRecord.class.getClassLoader());
		return bundle.getParcelable(key);
	}
	
	public static RecipesWithAuthorsRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mechanoid.getContentResolver();
	    
	    try {
	        c = resolver.query(RecipesWithAuthors.CONTENT_URI.buildUpon()
			.appendPath(String.valueOf(id)).build(), PROJECTION, null, null, null);
	        
	        if(!c.moveToFirst()) {
	            return null;
	        }
	        
	        return fromCursor(c);
	    } finally {
	        Closeables.closeSilently(c);
	    }
	}
}
