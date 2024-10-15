package com.arquitectura.arqii;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferences1 extends AppCompatActivity {

    private TextView datos;
    private EditText txt_buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datos = findViewById(R.id.tv_datos);


        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String jsonArrayString = preferences.getString("registros", "[]");

        StringBuilder estudiantesInformacion = new StringBuilder();

        if (jsonArrayString != null) {
            try {
                JSONArray registros = new JSONArray(jsonArrayString);
                for (int i = 0; i < registros.length(); i++) {
                    JSONObject registro = registros.getJSONObject(i);
                    String nombre = registro.getString("nombre");
                    String asignatura = registro.getString("asignatura");
                    String fecha = registro.getString("fecha");
                    double nota1 = registro.getDouble("nota1");
                    double nota2 = registro.getDouble("nota2");
                    double nota3 = registro.getDouble("nota3");
                    double notaFinal = registro.getDouble("notaFinal");
                    String estado = registro.getString("estado");

                    // Agregar la información del estudiante a la cadena
                    estudiantesInformacion.append("Estudiante: ").append(nombre)
                            .append("\nAsignatura: ").append(asignatura)
                            .append("\nFecha: ").append(fecha)
                            .append("\n\nCorte 1: ").append("                              ").append(nota1)
                            .append("\nCorte 2: ").append("                              ").append(nota2)
                            .append("\nCorte 3: ").append("                              ").append(nota3)
                            .append("\nNota Final: ").append("                         ").append(notaFinal).append("     ").append(estado)
                            .append("\n\n");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                estudiantesInformacion.append("Error al recuperar los registros.");
            }
        }

        datos.setText(estudiantesInformacion.toString());
    }

    public void regresar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

