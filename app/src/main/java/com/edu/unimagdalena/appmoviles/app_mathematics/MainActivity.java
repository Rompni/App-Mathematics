package com.edu.unimagdalena.appmoviles.app_mathematics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText x1, x2, y1, y2;
    TextView solve;
    static DecimalFormat formato1 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x1 = findViewById(R.id.valueX1);
        x2 = findViewById(R.id.valueX2);
        y1 = findViewById(R.id.valueY1);
        y2 = findViewById(R.id.valueY2);
        solve = findViewById(R.id.solves);

        findViewById(R.id.btnMidpoint).setOnClickListener(this);
        findViewById(R.id.btnQuadrant).setOnClickListener(this);
        findViewById(R.id.btnSlope).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.randomNumbers:
                x1.setText(formato1.format(Math.random()*(101)-50));
                x2.setText(formato1.format(Math.random()*(101)-50));
                y1.setText(formato1.format(Math.random()*(101)-50));
                y2.setText(formato1.format(Math.random()*(101)-50));
                break;

            case R.id.distance:

                if (x1.getText().toString().compareTo("") == 0 ||
                        x2.getText().toString().compareTo("") == 0 ||
                        y1.getText().toString().compareTo("") == 0 ||
                        y2.getText().toString().compareTo("") == 0) {
                    solve.setText("S: "+" Hay casillas vacias");

                }else {

                    double X1 = Double.parseDouble(x1.getText().toString());
                    double Y1 = Double.parseDouble(y1.getText().toString());
                    double X2 = Double.parseDouble(x2.getText().toString());
                    double Y2 = Double.parseDouble(y2.getText().toString());

                    double distancia = distance(X1, Y1, X2, Y2);
                    solve.setText("S: " + "Distancia = " + formato1.format(distancia));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (x1.getText().toString().compareTo("") == 0 ||
                x2.getText().toString().compareTo("") == 0 ||
                y1.getText().toString().compareTo("") == 0 ||
                y2.getText().toString().compareTo("") == 0) {
            solve.setText("S: "+" Hay casillas vacias");
            return;
        }

        double X1 = Double.parseDouble(x1.getText().toString());
        double X2 = Double.parseDouble(x2.getText().toString());
        double Y1 = Double.parseDouble(y1.getText().toString());
        double Y2 = Double.parseDouble(y2.getText().toString());

        switch (v.getId()){
            case R.id.btnMidpoint:

                String x =formato1.format ( midpoint(X1 ,X2));
                String y = formato1.format (midpoint(Y1 ,Y2));
                solve.setText("S: Punto Medio ( "+ x + ","+ y+ ") ");
                break;

            case R.id.btnSlope:
                solve.setText(slope(X1,Y1,X2,Y2));
                break;

            case R.id.btnQuadrant:
                solve.setText("S: " + quadrant(X1,Y1) +" y "+  quadrant(X2,Y2) );
                break;

        }

    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static double midpoint(Double z1, Double z2) {
        return (z1 + z2) / 2;
    }

    public static String slope(double x1, double y1, double x2, double y2) {
        if (x1 == x2)
            return "S: La pendiente para estos valores no esta definida";

        double sol = (y2-y1)/(x2-x1);
        return "S : La pendiente m = " + formato1.format(sol);
    }

    public static String quadrant(double z1, double z2) {

        if (z1 == 0 && z2 == 0)
            return "(" + z1 + "," + z2 + ") esta en el origen";

        if (z1 == 0) {
            if (z2 > 0) return "(" + z1 + "," + z2 + ") esta entre el I y II Cuadrante";
            else return "(" + z1 + "," + z2 + ") esta entre el III y IV Cuadrante";
        }

        if (z2 == 0) {
            if (z1 > 0) return "(" + z1 + "," + z2 + ") esta entre el I y IV Cuadrante";
            else return "(" + z1 + "," + z2 + ") esta entre el II y III Cuadrante";
        }

        if (z1 > 0) {
            if (z2 > 0) {
                return "(" + z1 + "," + z2 + ") esta en el I Cuadrante";
            } else if (z2 < 0) {
                return "(" + z1 + "," + z2 + ") esta en el IV Cuadrante";
            }
        }

        if (z1 < 0) {
            if (z2 > 0) {
                return "(" + z1 + "," + z2 + ") esta en el II Cuadrante";
            } else if (z2 < 0) {
                return "(" + z1 + "," + z2 + ") esta en el III Cuadrante";
            }
        }

        return "";
    }
}
