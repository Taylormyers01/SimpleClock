//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(438, 320); //350 220
            this.setResizable(false);
    
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
            format.setFont(new Font("Ink Free",Font.BOLD,24));
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

            
    
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(format);
            this.setVisible(true);
    
            //setTimer();
            new Thread(this).start();
        }
    
        public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }

    @Override
    public void run() {
        time = timeFormat.format(Calendar.getInstance().getTime());
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
