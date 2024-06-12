package controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Persona;
import views.ActivityMainMethods;

public class ActivityMainControllerImpl implements ActivityMainController {
    private final ActivityMainMethods view;
    public ActivityMainControllerImpl(Context context) {
        this.view = (ActivityMainMethods) context;
    }

    @Override
    public ArrayAdapter<String> getPersons(List<Persona> list) {
        ArrayList<String> listFilter = (ArrayList<String>) list.stream().map(x -> x.getID() + " " + x.getNombre() + " " + x.getApellido()).collect(Collectors.toList());
        return this.view.getAll(listFilter);
    }

    @Override
    public String addPerson(int status, String name) {
        return this.view.insertPerson(status, name);
    }

    @Override
    public String deletePerson(int status) {
        return this.view.deletePerson(status);
    }

    @Override
    public String updatePerson(int status, String name) {
        return this.view.updatePerson(status, name);
    }
}
