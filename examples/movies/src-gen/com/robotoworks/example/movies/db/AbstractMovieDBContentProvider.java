/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.movies.db;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import java.util.Set;
import com.robotoworks.mechanoid.db.MechanoidContentProvider;
import com.robotoworks.mechanoid.db.MechanoidSQLiteOpenHelper;
import com.robotoworks.mechanoid.db.DefaultContentProviderActions;
import com.robotoworks.mechanoid.db.ContentProviderActions;
import com.robotoworks.example.movies.db.AbstractMovieDBOpenHelper.Sources;
import com.robotoworks.example.movies.db.MoviesRecord;

public abstract class AbstractMovieDBContentProvider extends MechanoidContentProvider {

	public static final int MOVIES = 0;
	public static final int MOVIES_ID = 1;

	

	
	public static final int NUM_URI_MATCHERS = 2;

	@Override
    protected UriMatcher createUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieDBContract.CONTENT_AUTHORITY;

		matcher.addURI(authority, "movies", MOVIES);
		matcher.addURI(authority, "movies/#", MOVIES_ID);

		// User Actions
        return matcher;
    }
    
    @Override
    protected String[] createContentTypes() {
		String[] contentTypes = new String[NUM_URI_MATCHERS];

		contentTypes[MOVIES] = MovieDBContract.Movies.CONTENT_TYPE;
		contentTypes[MOVIES_ID] = MovieDBContract.Movies.ITEM_CONTENT_TYPE;
		
		return contentTypes;
    }

	@Override
	protected MechanoidSQLiteOpenHelper createOpenHelper(Context context) {
        return new MovieDBOpenHelper(context);
	}
	
	@Override
	protected Set<Uri> getRelatedUris(Uri uri) {
		return MovieDBContract.REFERENCING_VIEWS.get(uri);
	}
    
    @Override
    protected ContentProviderActions createActions(int id) {
    	switch(id) {
			case MOVIES: 
				return createMoviesActions();
			case MOVIES_ID:
				return createMoviesByIdActions();
			default:
				throw new UnsupportedOperationException("Unknown id: " + id);
    	}
    }
    
    protected ContentProviderActions createMoviesByIdActions() {
    	return new DefaultContentProviderActions(Sources.MOVIES, true, MoviesRecord.getFactory());
    }
    
    protected ContentProviderActions createMoviesActions() {
    	return new DefaultContentProviderActions(Sources.MOVIES, false, MoviesRecord.getFactory());
    }
    
}
