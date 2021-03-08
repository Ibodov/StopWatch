package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    private int seconds = 0; //хранится кол. прошедших секунд
    private boolean running;
    private boolean wasRunning; //переменная для хранения инфо о том, работал ли
                                // секундмер перед вызовом метода onStop()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        //runTimer(); //для обнов. секундомера используется отдельный метод. запускается при создании активити
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning"); // Восстанавливаем состаяное
                                // переменной wasRunning, если активность создается заново.
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning); //Сохранить перменной
    }


    //Реализация остановки секундомера в onStop
/*
    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running; //Сохранить информацию о том, работал ли секундомер на момент вызова метода onStop()
        running = false;
    }
*/

@Override
    protected void onPause() {
    super.onPause();
    wasRunning = running; //Сохранить информацию о том, работал ли секундомер на момент вызова метода onStop()
    running = false;
}

    @Override
    protected void onStart() { //Реализация метода onStart(). Если секундомер работал, то отсчет вренмени возобнавляется.
        super.onStart();
        if(wasRunning) {
            running = true;
        }
    }

/*
    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning) {
            running = true;
    }
*/
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