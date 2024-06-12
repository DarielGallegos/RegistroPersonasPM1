package com.example.registropersonas;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import model.Persona;
import service.ActivityMainServiceImpl;
import views.ActivityMainMethods;

public class MainActivity extends AppCompatActivity implements ActivityMainMethods {
    ActivityMainServiceImpl service;
    public Persona e = new Persona();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = new ActivityMainServiceImpl(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText txtName = findViewById(R.id.txtNombre);
        Button btnSave = findViewById(R.id.btnAdd);
        EditText txtLastName = findViewById(R.id.txtApellidos);
        EditText txtAge = findViewById(R.id.txtEdad);
        EditText txtCorreo = findViewById(R.id.txtCorreo);
        EditText txtDir = findViewById(R.id.txtDireccion);
        ListView lstPeople = findViewById(R.id.listaPersonas);
        lstPeople.setAdapter(service.getAll());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Foto), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e.getID() != 0) {
                    Toast.makeText(MainActivity.this,
                            service.updatePerson(e.getID(), txtName.getText().toString(), txtLastName.getText().toString(), parseInt(txtAge.getText().toString()), txtCorreo.getText().toString(), txtDir.getText().toString())
                            , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            service.addPerson(txtName.getText().toString(), txtLastName.getText().toString(), parseInt(txtAge.getText().toString()), txtCorreo.getText().toString(), txtDir.getText().toString())
                            , Toast.LENGTH_SHORT).show();
                }
                lstPeople.setAdapter(service.getAll());
                flush(txtName, txtLastName, txtAge, txtCorreo, txtDir);
            }
        });

        lstPeople.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        flush(txtName, txtLastName, txtAge, txtCorreo, txtDir);
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        int ID = Integer.parseInt(selectedItem.split(" ")[0]);
                        e = service.getbyId(ID);
                        txtName.setText(e.getNombre());
                        txtLastName.setText(e.getApellido());
                        txtAge.setText(String.valueOf(e.getEdad()));
                        txtCorreo.setText(e.getCorreo());
                        txtDir.setText(e.getDescripcion());
                    }
                }
        );
        lstPeople.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        flush(txtName, txtLastName, txtAge, txtCorreo, txtDir);
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        int ID = Integer.parseInt(selectedItem.split(" ")[0]);
                        Toast.makeText(MainActivity.this, service.deletePerson(ID), Toast.LENGTH_SHORT).show();
                        lstPeople.setAdapter(service.getAll());
                        return false;
                    }
                }
        );

    }

    private void flush(EditText txtName, EditText txtLastName, EditText txtAge, EditText txtCorreo, EditText txtDir) {
        txtName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtCorreo.setText("");
        txtDir.setText("");
    }

    @Override
    public String insertPerson(int status, String name) {
        return status == -1 ? "Error al insertar registro" : "Registro insertado correctamente persona: " + name;
    }

    @Override
    public ArrayAdapter<String> getAll(ArrayList<String> lista) {
        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
    }

    @Override
    public String deletePerson(int status) {
        return (status == -1) ? "Error al eliminar registro" : "Registro eliminado correctamente";
    }

    @Override
    public String updatePerson(int status, String name) {
        return (status == -1) ? "Error al actualizar registro" : "Registro actualizado correctamente persona: " + name;
    }
}