package org.coolWeather.app.db;

import java.util.ArrayList;
import java.util.List;

import org.coolWeather.app.model.City;
import org.coolWeather.app.model.County;
import org.coolWeather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//写这个单例类的作用:把一些常用的数据库操作封装起来，以方便我们后面使用。
public class CoolWeatherDB {

	/*数据库名*/
	public static final String DB_NAME = "coolWeather.db";
	
	/*数据库版本*/
	public static final int VERSION = 1;
	
	//单例模式
	public static CoolWeatherDB coolWeatherDB; 
	
	private SQLiteDatabase db;
	
	//单例模式:
	/*将构造器私有化*/		
	private CoolWeatherDB (Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
	}
	
	//单例模式:
	/*获取CoolWeatherDB的实例*/         
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return  coolWeatherDB;
	}
	
	/*将Province实例存储到数据库*/
	public void saveProvince(Province province) {//此方法在Utility中将解析出来的数据存储到Province表
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code",province.getProvinceCode());
			db.insert("t_Province", null, values);
		}
	}
	
	/*从数据库读取全国所有的省份信息*/
	public List<Province> loadProvince(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("t_Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		
		return list;
	}
	
	/*将City实例存储到数据库*/
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("t_City", null, values);
		}
	}
	
	/*从数据库读取某省下所有的城市信息*/
	public List<City> loadCities(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("t_City", null, "province_id = ?", new String[]{ String.valueOf(provinceId) }, null, null, null);
		if(cursor.moveToFirst()){
			do {
				City city =new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/*将County实例存储到数据库*/
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code",county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("t_County", null, values);
		}
	}
	
	/*从数据库读取某城市下所有的县信息*/
	public List<County> loadCounty(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("t_County", null, "city_id = ? ", new String[]{String.valueOf(cityId)}, null, null, null);
		if (cursor.moveToFirst()) {
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
}
