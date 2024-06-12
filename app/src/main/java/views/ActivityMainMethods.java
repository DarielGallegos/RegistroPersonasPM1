package views;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public interface ActivityMainMethods {
    String insertPerson(int status, String name);
    ArrayAdapter<String> getAll(ArrayList<String> lista);
    String deletePerson(int status);
    String updatePerson(int status, String name);
}
