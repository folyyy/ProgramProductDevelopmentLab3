package com.example.programproductdevelopmentlab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    StudentDatabase db = new StudentDatabase(this); // Initializing database
    // List of students
    ArrayList<String> students_list = new ArrayList<String>(
            Arrays.asList("Иванов Иван Иванович",
                    "Ковалёва Ярослава Павловна",
                    "Мухина Земфира Андреевна",
                    "Артемьева Надежда Данииловна",
                    "Евсеева Грета Протасьевна",
                    "Логинова Астра Юрьевна",
                    "Сидорова Иоанна Львовна",
                    "Елисеева Генриетта Геласьевна",
                    "Русакова Олеся Богдановна",
                    "Фомичёва Алия Антоновна",
                    "Романова Флора Геннадиевна",
                    "Ситникова Лиза Михайловна",
                    "Алексеев Яков Рубенович",
                    "Ермаков Аввакуум Донатович",
                    "Громов Исаак Ярославович",
                    "Кондратьев Болеслав Федосеевич",
                    "Савин Овидий Альвианович",
                    "Брагин Моисей Юрьевич",
                    "Молчанов Кондрат Денисович",
                    "Белов Всеволод Мартынович",
                    "Носов Игорь Федорович",
                    "Сергеев Аввакум Натанович")
    );
    Button outPut; // Button for outputting students data
    Button addNew; // Button for adding a new student in the database
    Button replaceName; // Button for replacing last student from database with "Иванов Иван Иванович"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getApplicationContext().deleteDatabase("STUDENT_DATABASE");
        db.deleteAllNotes(); // Deleting all previous notes from database
        db.add5Notes(students_list); // Adding 5 new notes to the database
        outPut = findViewById(R.id.firstButton);
        addNew = findViewById(R.id.secondButton);
        replaceName = findViewById(R.id.thirdButton);

        // Outputting students data
        outPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, ShowList.class);
            startActivity(i);
            }
        });

        // Adding a new student in the database
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNote(students_list);
            }
        });

        // Replacing last student from database with "Иванов Иван Иванович"
        replaceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateLastNote(students_list.get(0));
            }
        });

    }
}
