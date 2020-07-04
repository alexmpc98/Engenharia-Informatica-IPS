package smartWatch;

import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alexandre & Sérgio
 */
public class Clock {

    private int hour;
    private int minute;
    private int second;

    public Clock() {

        hour = LocalDateTime.now().getHour();
        minute = LocalDateTime.now().getMinute();
        second = LocalDateTime.now().getSecond();
    }

    public int getHour() {
        return LocalDateTime.now().getHour();
    }

    public int getMinute() {
        return LocalDateTime.now().getMinute();
    }

    public int getSecond() {
        return LocalDateTime.now().getSecond();
    }

}
