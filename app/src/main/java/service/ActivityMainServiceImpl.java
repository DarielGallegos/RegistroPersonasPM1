package service;

import static java.lang.Integer.parseInt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import controller.ActivityMainControllerImpl;
import model.Persona;
import model.TBL_PERSONA;
import repository.ConnectDB;

public class ActivityMainServiceImpl implements ActivityMainService {

    private final ActivityMainControllerImpl controller;
    private final ConnectDB connect;
    private static SQLiteDatabase db;

    public ActivityMainServiceImpl(Context context){
        this.controller = new ActivityMainControllerImpl(context);
        this.connect = new ConnectDB(context, TBL_PERSONA.DB_NAME,null, TBL_PERSONA.VERSION);
    }

    @Override
    public ArrayAdapter<String> getAll() {
        db = this.connect.getReadableDatabase();
        Persona person;
        ArrayList<Persona> list = new ArrayList<>();

        //Cursor para recorrer los datos de la tabla;
        Cursor cursor = db.rawQuery(TBL_PERSONA.EMPLOYEE_GET_ALL, null);
        while(cursor.moveToNext()){
            person = new Persona();
            person.setID(cursor.getInt(0));
            person.setNombre(cursor.getString(1));
            person.setApellido(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));
            person.setFoto(cursor.getString(5));
            list.add(person);
        }
        cursor.close();
        db.close();
        return this.controller.getPersons(list);
    }

    @Override
    public String addPerson(String name, String apellidos, int age, String correo, String direccion) {
        String msg;
        try{
            this.db = connect.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TBL_PERSONA.NOMBRES, name);
            values.put(TBL_PERSONA.APELLIDOS, apellidos);
            values.put(TBL_PERSONA.EDAD, age);
            values.put(TBL_PERSONA.EMAIL, correo);
            values.put(TBL_PERSONA.DIRECCION, direccion);
            Long status = db.insert(TBL_PERSONA.TBL_PERSONAS, TBL_PERSONA.ID, values);
            return this.controller.addPerson(parseInt(status.toString()), name);
        }catch(SQLiteException e) {
            msg = "Error al insertar: " + e.getMessage();
            throw new SQLiteException(msg, e);
        }
    }

    @Override
    public String deletePerson(int id) {
        String msg;
        try{
            this.db = connect.getWritableDatabase();
            int status = db.delete(TBL_PERSONA.TBL_PERSONAS, TBL_PERSONA.ID + " = " + id, null);
            return this.controller.deletePerson(status);
        }catch(SQLiteException e){
            msg = "Error al eliminar";
            throw new SQLiteException(msg, e);
        }
    }

    @Override
    public String updatePerson(int id, String name, String apellidos, int age, String correo, String direccion) {
        String msg;
        try{
            this.db = connect.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TBL_PERSONA.NOMBRES, name);
            values.put(TBL_PERSONA.APELLIDOS, apellidos);
            values.put(TBL_PERSONA.EDAD, age);
            values.put(TBL_PERSONA.EMAIL, correo);
            values.put(TBL_PERSONA.DIRECCION, direccion);
            int status = db.update(TBL_PERSONA.TBL_PERSONAS, values, TBL_PERSONA.ID + " = " + id, null);
            return this.controller.updatePerson(status, name);
        }catch(SQLiteException e){
            msg = "Error al actualizar";
            throw new SQLiteException(msg, e);
        }
    }

    @Override
    public Persona getbyId(int id) {
        Persona person = new Persona();
        this.db = connect.getReadableDatabase();
        Cursor cursor = db.rawQuery(TBL_PERSONA.EMPLOYEE_GET_BY_ID,  new String[]{String.valueOf(id)});
        while(cursor.moveToNext()){
            person = new Persona();
            person.setID(cursor.getInt(0));
            person.setNombre(cursor.getString(1));
            person.setApellido(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));
            person.setDescripcion(cursor.getString(5));
        }
        return person;
    }
}
