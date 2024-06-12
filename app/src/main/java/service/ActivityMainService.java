package service;

import android.widget.ArrayAdapter;

import model.Persona;

public interface ActivityMainService {
    ArrayAdapter<String> getAll();
    String addPerson(String name, String apellidos, int age, String correo, String direccion);
    String deletePerson(int id);
    String updatePerson(int id, String name, String apellidos, int age, String correo, String direccion);
    Persona getbyId(int id);
}

