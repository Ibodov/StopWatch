package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    private int seconds = 0; //хранится кол. прошедших секунд
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer(); //для обнов. секундомера используется отдельный метод. запускается при создании активити
    }

    public void onClickStart(View view) { //вызывается при нажатии кнопки старт
        running = true; //запустить секундомер
    }

    public void onClickStop(View view) {  //вызывается при нажатии кнопки стоп
        running = false; //остановить секундомер
    } //

    public void onClickReset(View view) { //вызывается при нажатии кнопки резет
        running = false;  //остановить секундомер
        seconds = 0; //обнулит переменную секундомер
    }
    private void runTimer() {
        TextView timeView; //декл.
        timeView = findViewById(R.id.time_view); //получить ссылку на надпись
        Handler handler = new Handler();
        handler.post(new Runnable() { // Использовать handler для передачи кода на выполнение
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs); //отфор. seconds в часы, минуты и секунды.
                timeView.setText(time); //Задать текст надписи
                if (running) {
                    seconds++; //Если значение running истино, увеличить переменную seconds
                }
                handler.postDelayed(this, 1000); // Запланировать повторное выполнение кода с задержкой в 1 секунду.
            }
        });
    }
}