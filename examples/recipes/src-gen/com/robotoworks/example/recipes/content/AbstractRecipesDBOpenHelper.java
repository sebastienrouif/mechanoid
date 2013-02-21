/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.recipes.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.robotoworks.mechanoid.db.MechanoidSQLiteOpenHelper;
import com.robotoworks.mechanoid.db.SQLiteMigration;

import com.robotoworks.example.recipes.content.migrations.DefaultRecipesDBMigrationV2;
import com.robotoworks.example.recipes.content.migrations.DefaultRecipesDBMigrationV3;

public abstract class AbstractRecipesDBOpenHelper extends MechanoidSQLiteOpenHelper {
	public static final String DATABASE_NAME = "RecipesDB.db";

	public static final int VERSION = 3;

	public interface Sources {
		String RECIPES = "recipes";
		String AUTHORS = "authors";
		String INGREDIENTS = "ingredients";
		String RECIPES_WITH_AUTHORS = "recipes_with_authors";
		String RECIPES_AND_INGREDIENTS = "recipes_and_ingredients";
	}

	public AbstractRecipesDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
			"create table recipes ( " +
			"_id integer primary key autoincrement, " +
			"title text, " +
			"description text, " +
			"author_id integer " +
			") "
		);	
		db.execSQL(
			"create table authors ( " +
			"_id integer primary key autoincrement, " +
			"name text " +
			") "
		);	
		db.execSQL(
			"create table ingredients ( " +
			"_id integer primary key autoincrement, " +
			"recipe_id integer, " +
			"quantity text, " +
			"ingredient text " +
			") "
		);	
		db.execSQL(
			"create view recipes_with_authors as " +
			"select " +
			"r._id as _id, " +
			"r.title as recipe_title, " +
			"r.description as recipe_description, " +
			"r.author_id as author_id, " +
			"a.name as author_name " +
			"from recipes as r " +
			"left join authors as a " +
			"on r.author_id = a._id "
		);	
		db.execSQL(
			"create view recipes_and_ingredients as " +
			"select " +
			"_id as _id, " +
			"0 as row_type, " +
			"title as title, " +
			"description as description, " +
			"null as ingredient_quantity, " +
			"_id || \"-0\" as sort_key " +
			"from recipes " +
			"union " +
			"select " +
			"_id as _id, " +
			"1 as row_type, " +
			"ingredient as title, " +
			"null as description, " +
			"quantity as ingredient_quantity, " +
			"recipe_id || \"-\" || _id as sort_key " +
			"from ingredients " +
			"order by sort_key asc "
		);	
	}

	@Override
	protected SQLiteMigration createMigration(int version) {
		switch(version) {
			case 2:
				return createRecipesDBMigrationV2();
			case 3:
				return createRecipesDBMigrationV3();
			default:
				throw new IllegalStateException("No migration for version " + version);
		}
	}
	
	protected SQLiteMigration createRecipesDBMigrationV2() {
		return new DefaultRecipesDBMigrationV2();
	}
	protected SQLiteMigration createRecipesDBMigrationV3() {
		return new DefaultRecipesDBMigrationV3();
	}
}
