package pl.dritms.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pl.dritms.model.Behaviour;
import pl.dritms.model.Role;
import pl.dritms.model.Setting;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "szo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Role.TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                "name text," +
                "description text" +
                ")");

        db.execSQL("create table " + Behaviour.TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                "roleId integer, " +
                "name text," +
                "description text," +
                "FOREIGN KEY(roleId) REFERENCES role(id)" +
                ")");

        db.execSQL("create table " + Setting.TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                "name text," +
                "value text" +
                ")");
        db.execSQL(
                "insert into " + Setting.TABLE_NAME + "(name, value) values ('darkMode', 'true')"
        );
    }

    public void addRole(String name, String description){
        addRole(new Role(name, description));
    }

    public void addBehaviour(String name, String description, long roleId){
        addBehaviour(new Behaviour(name, description, roleId));
    }

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        Cursor c = getRolesCursor();
        while (c.moveToNext()){
            roles.add(toRole(c));
        }
        return roles;
    }

    public Role getRoleById(long roleId){
        for(Role r: getRoles()){
            if(r.getId() == roleId){
                return r;
            }
        }
        throw new IllegalArgumentException("No role for id: " + roleId);
    }

    public List<Behaviour> getBehaviours(Role role){
        return getBehaviours(role.getId());
    }

    public List<Behaviour> getBehaviours(long roleId){
        List<Behaviour> behaviours = new ArrayList<>();
        Cursor c = getBehavioursCursor(roleId);
        while (c.moveToNext()){
            behaviours.add(toBehaviour(c));
        }
        return behaviours;
    }

    private void addRole(Role role){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Role.TABLE_NAME, null, toContentValues(role));
    }

    private void addBehaviour(Behaviour behaviour){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Behaviour.TABLE_NAME, null, toContentValues(behaviour));
    }

    private Role toRole(Cursor c) {
        return new Role(c.getLong(0), c.getString(1), c.getString(2));
    }

    private Behaviour toBehaviour(Cursor c) {
        return new Behaviour(c.getLong(0), c.getLong(1), c.getString(2), c.getString(3));
    }

    private Cursor getRolesCursor(){
        String [] columns = {"id", "name", "description"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Role.TABLE_NAME, columns, null, null, null, null, "id desc");
    }

    private Cursor getBehavioursCursor(long roleId){
        String [] columns = {"id", "roleId", "name", "description"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Behaviour.TABLE_NAME, columns, "roleId = " + roleId, null, null, null, "id desc");
    }


    private ContentValues toContentValues(Role role){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", role.getName());
        contentValues.put("description", role.getDescription());
        return contentValues;
    }

    private ContentValues toContentValues(Behaviour behaviour){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", behaviour.getName());
        contentValues.put("description", behaviour.getDescription());
        contentValues.put("roleId", behaviour.getRoleId());
        return contentValues;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private long getSettingId(String name) {
        Cursor c = getSettingsCursor();
        while (c.moveToNext()){
            if(c.getString(1).equalsIgnoreCase(name)){
                return c.getLong(0);
            }
        }
        throw new IllegalArgumentException("There is no setting with " + name);
    }

    public String getSetting(String name) {
        Cursor c = getSettingsCursor();
        while (c.moveToNext()){
            if(c.getString(1).equalsIgnoreCase(name)){
                return c.getString(2);
            }
        }
        throw new IllegalArgumentException("There is no setting with " + name);
    }

    public boolean getSettingBoolean(String name) {
        return getSetting(name).equals("true");
    }

    public void updateSettings(String name, String value) {
        long settingId = getSettingId(name);
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", settingId);
        contentValues.put("name", name);
        contentValues.put("value", value);
        SQLiteDatabase db = getWritableDatabase();
        db.update(Setting.TABLE_NAME, contentValues, "id = " + settingId,null );
    }

    private Cursor getSettingsCursor(){
        String [] columns = {"id", "name", "value"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Setting.TABLE_NAME, columns,null, null, null, null, null);
    }
}
