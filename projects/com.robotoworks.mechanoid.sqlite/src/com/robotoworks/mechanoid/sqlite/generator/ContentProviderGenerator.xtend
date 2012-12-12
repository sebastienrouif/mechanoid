package com.robotoworks.mechanoid.sqlite.generator

import com.robotoworks.mechanoid.sqlite.sqliteModel.CreateTableStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.CreateViewStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.MigrationBlock
import com.robotoworks.mechanoid.sqlite.sqliteModel.Model

import static extension com.robotoworks.mechanoid.sqlite.generator.Extensions.*
import static extension com.robotoworks.mechanoid.common.util.Strings.*
import com.robotoworks.mechanoid.sqlite.sqliteModel.ActionStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.ActionStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.ActionStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.ActionStatement

class ContentProviderGenerator {
		def CharSequence generate(Model model, MigrationBlock snapshot) '''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;
			
			import java.util.ArrayList;
			
			import android.content.ContentProviderOperation;
			import android.content.ContentProviderResult;
			import android.content.ContentValues;
			import android.content.Context;
			import android.content.OperationApplicationException;
			import android.content.UriMatcher;
			import android.database.Cursor;
			import android.database.sqlite.SQLiteDatabase;
			import android.net.Uri;
			import com.robotoworks.mechanoid.content.MechanoidContentProvider;
			import com.robotoworks.mechanoid.sqlite.MechanoidSQLiteOpenHelper;

			�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
			import �model.packageName�.�model.database.name.pascalize�Contract.�tbl.name.pascalize�;
			�ENDFOR�
			�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
			import �model.packageName�.�model.database.name.pascalize�Contract.�vw.name.pascalize�;
			
			�ENDFOR�
			
			�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
			import �model.packageName�.actions.�tbl.name.pascalize�Actions;
			�IF tbl.hasAndroidPrimaryKey�
			import �model.packageName�.actions.�tbl.name.pascalize�ByIdActions;
			�ENDIF�
			�ENDFOR�
			�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
			import �model.packageName�.actions.�vw.name.pascalize�Actions;
			�IF vw.hasAndroidPrimaryKey�
			import �model.packageName�.actions.�vw.name.pascalize�ByIdActions;
			�ENDIF�
			�ENDFOR�
			�IF model.database.config !=null�
			
			�FOR a : model.database.config.statements.filter([it instanceof ActionStatement])�
			import �model.packageName�.actions.�a.name.pascalize�Actions;
			�ENDFOR�
			
			�ENDIF�
			public abstract class Abstract�model.database.name.pascalize�ContentProvider extends MechanoidContentProvider {
			
			    private static final UriMatcher sUriMatcher;
				private static final String[] sContentTypes;
				private static final Class<?>[] sActions;
			    
				�var counter=-1�
				�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
				private static final int �tbl.name.underscore.toUpperCase� = �counter=counter+1�;
				�IF tbl.hasAndroidPrimaryKey�
				private static final int �tbl.name.underscore.toUpperCase�_ID = �counter=counter+1�;
				�ENDIF�
				�ENDFOR�

				�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
				private static final int �vw.name.underscore.toUpperCase� = �counter=counter+1�;
				�IF vw.hasAndroidPrimaryKey�
				private static final int �vw.name.underscore.toUpperCase�_ID = �counter=counter+1�;
				�ENDIF�				
				�ENDFOR�
				
				�IF model.database.config.statements !=null�
				�FOR a : model.database.config.statements.filter([it instanceof ActionStatement])�
				private static final int �a.name.underscore.toUpperCase� = �counter=counter+1�;
				�ENDFOR�
				�ENDIF�			
				public static final int NUM_URI_MATCHERS = �counter + 1�;
			
				static {
					sUriMatcher = buildUriMatcher();
				
					sContentTypes = new String[NUM_URI_MATCHERS];

					�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
					sContentTypes[�tbl.name.underscore.toUpperCase�] = �tbl.name.pascalize�.CONTENT_TYPE;
					�IF tbl.hasAndroidPrimaryKey�
					sContentTypes[�tbl.name.underscore.toUpperCase�_ID] = �tbl.name.pascalize�.ITEM_CONTENT_TYPE;
					�ENDIF�
					�ENDFOR�
					�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
					sContentTypes[�vw.name.underscore.toUpperCase�] = �vw.name.pascalize�.CONTENT_TYPE;
					�IF vw.hasAndroidPrimaryKey�
					sContentTypes[�vw.name.underscore.toUpperCase�_ID] = �vw.name.pascalize�.ITEM_CONTENT_TYPE;
					�ENDIF�
					�ENDFOR�
					
					sActions = new Class<?>[NUM_URI_MATCHERS];

					�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
					sActions[�tbl.name.underscore.toUpperCase�] = �tbl.name.pascalize�Actions.class;
					�IF tbl.hasAndroidPrimaryKey�
					sActions[�tbl.name.underscore.toUpperCase�_ID] = �tbl.name.pascalize�ByIdActions.class;
					�ENDIF�
					�ENDFOR�
					�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
					sActions[�vw.name.underscore.toUpperCase�] = �vw.name.pascalize�Actions.class;
					�IF vw.hasAndroidPrimaryKey�
					sActions[�vw.name.underscore.toUpperCase�_ID] = �vw.name.pascalize�ByIdActions.class;
					�ENDIF�
					�ENDFOR�
					
					�IF model.database.config !=null�
					�FOR a : model.database.config.statements.filter([it instanceof ActionStatement])�
					sActions[�a.name.underscore.toUpperCase�] = �a.name.pascalize�Actions.class;
					�ENDFOR�
					�ENDIF�
					
				}
				
			    private static UriMatcher buildUriMatcher() {
			        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
			        final String authority = �model.database.name.pascalize�Contract.CONTENT_AUTHORITY;
			
					// Tables
					�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
					matcher.addURI(authority, "�tbl.name�", �tbl.name.underscore.toUpperCase�);
					�IF tbl.hasAndroidPrimaryKey�
					matcher.addURI(authority, "�tbl.name�/#", �tbl.name.underscore.toUpperCase�_ID);
					�ENDIF�
					�ENDFOR�
			
					// Views
					�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
					matcher.addURI(authority, "�vw.name�", �vw.name.underscore.toUpperCase�);
					�IF vw.hasAndroidPrimaryKey�
					matcher.addURI(authority, "�vw.name�/#", �vw.name.underscore.toUpperCase�_ID);
					�ENDIF�
					�ENDFOR�

					// User Actions
					�IF model.database.config !=null�
					�FOR a : model.database.config.statements.filter([it instanceof ActionStatement])�
					�var stmt = a as ActionStatement�
					matcher.addURI(authority, "�stmt.path�", �a.name.underscore.toUpperCase�); 
					�ENDFOR�
					�ENDIF�
			        return matcher;
			    }
			
				@Override
				public String getType(Uri uri) {
			        final int match = sUriMatcher.match(uri);
			
					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					return sContentTypes[match];
				}
			
				@Override
				public int delete(Uri uri, String selection, String[] selectionArgs) {
					final int match = sUriMatcher.match(uri);

					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					return createActions(sActions[match]).delete(this, uri, selection, selectionArgs);
				}
			
				@Override
				public Uri insert(Uri uri, ContentValues values) {
			
					final int match = sUriMatcher.match(uri);

					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					return createActions(sActions[match]).insert(this, uri, values);
				}
				
				@Override
			    public int bulkInsert(Uri uri, ContentValues[] values) {
			    	
					final int match = sUriMatcher.match(uri);

					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					return createActions(sActions[match]).bulkInsert(this, uri, values);
			    }
			
				@Override
				protected MechanoidSQLiteOpenHelper createOpenHelper(Context context) {
			        return new �model.database.name.pascalize�OpenHelper(context);
				}
			
				@Override
				public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
					final int match = sUriMatcher.match(uri);

					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					Cursor cursor = createActions(sActions[match]).query(this, uri, projection, selection, selectionArgs, sortOrder);
			
					if(cursor != null) {
						cursor.setNotificationUri(getContext().getContentResolver(), uri);
					}
					
					return cursor;
				}
			
				@Override
				public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
					final int match = sUriMatcher.match(uri);

					if(match == UriMatcher.NO_MATCH) {
						throw new UnsupportedOperationException("Unknown uri: " + uri);
					}
					
					return createActions(sActions[match]).update(this, uri, values, selection, selectionArgs);
				}
			
			    @Override
			    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
			            throws OperationApplicationException {
			        final SQLiteDatabase db = getOpenHelper().getWritableDatabase();
			        db.beginTransaction();
			        try {
			            final int numOperations = operations.size();
			            final ContentProviderResult[] results = new ContentProviderResult[numOperations];
			            for (int i = 0; i < numOperations; i++) {
			                results[i] = operations.get(i).apply(this, results, i);
			            }
			            db.setTransactionSuccessful();
			            return results;
			        } finally {
			            db.endTransaction();
			        }
			    }
			}
			'''
			
		def CharSequence generateStub(Model model, MigrationBlock snapshot) '''
			/*******************************************************************************
			 * Copyright (c) 2012, Robotoworks Limited
			 * All rights reserved. This program and the accompanying materials
			 * are made available under the terms of the Eclipse Public License v1.0
			 * which accompanies this distribution, and is available at
			 * http://www.eclipse.org/legal/epl-v10.html
			 * 
			 *******************************************************************************/
			package �model.packageName�;
			
			import �model.packageName�.Abstract�model.database.name.pascalize�ContentProvider;
			
			public class �model.database.name.pascalize�ContentProvider extends Abstract�model.database.name.pascalize�ContentProvider {}
		'''
}