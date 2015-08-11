package org.coolWeather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/*Province见表语句*/
    public static final String CREATE_PROVINCE = "create table t_Province ("
													+"id integer primary key auto increment,"
													+"province_name text,"
													+"province_code text)";
    
    /*City见表语句*/
    public static final String CREATE_CITY = "create table t_City ("
													+"id integer primary key auto increment,"
													+"city_name text,"
													+"city_code text)"
													+"province_id integer)";
    
    /*Country见表语句*/
    public static final String CREATE_COUNTRY = "create table t_COUNTRY ("
													+"id integer primary key auto increment,"
													+"country_name text,"
													+"country_code text)"
													+"city_id integer)";
	
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_PROVINCE);//创建Province表
      db.execSQL(CREATE_CITY);//创建City表
      db.execSQL(CREATE_COUNTRY);//创建Country表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      
	}

}
