//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TimerTask;


public class SimpleClock extends JFrame implements Runnable {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        Integer hours = 12;
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton format;
        JButton localGMT;
        String time;
        String day;
        String date;
        TimeZone currentTimeZone = TimeZone.getTimeZone("EST");

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(438, 320); //350 220
            this.setResizable(false);

            calendar = Calendar.getInstance();
            TimeZone est = TimeZone.getTimeZone("EST");
            TimeZone gmt = TimeZone.getTimeZone("GMT");

            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");

            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);

            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            format = new JButton();
            format.setFont(new Font("Ink Free",Font.BOLD,20));
            format.setText("Swap 12/24");
            format.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(hours == 12){
                        hours = 24;
                        timeFormat.applyPattern("kk:mm:ss a");
                    }
                    else{
                        hours = 12;
                        timeFormat.applyPattern("hh:mm:ss a");
                    }
                }
            });

            localGMT = new JButton();
            localGMT.setFont(new Font("Ink Free",Font.BOLD,20));
            localGMT.setText("Swap Local/GMT");
            localGMT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(currentTimeZone.equals(est)){
                        currentTimeZone = gmt;
                        timeFormat.setTimeZone(gmt);
                    }
                    else{
                        timeFormat.setCalendar(calendar.getInstance());
                        currentTimeZone = est;
                    }
                }
            });
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(format);
            this.add(localGMT);
            this.setVisible(true);

            //setTimer();
            new Thread(this).start();
        }
        /*
        public void setTimer() {
            while (true) {
                time = timeFormat.format(calendar.getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(calendar.getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(calendar.getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }

         */
        public static void main(String[] args) {
            new SimpleClock();
        }

    @Override
    public void run() {


        time = timeFormat.format(calendar.getInstance(currentTimeZone).getTime());
        //timeFormat.setTimeZone(currentTimeZone);
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);

        try {
            Thread.sleep(1000);
            run();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
