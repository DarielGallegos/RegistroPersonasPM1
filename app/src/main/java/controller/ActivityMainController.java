package controller;

import android.widget.ArrayAdapter;

import java.util.List;

import model.Persona;

public interface ActivityMainController {
    ArrayAdapter<String> getPersons(List<Persona> list);
    String addPerson(int status, String name);
    String deletePerson(int status);
    String updatePerson(int status, String name);
}
