package com.daw.contafin.user;

import com.daw.contafin.completedExercise.CompletedExercise;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
public class UserDto implements Serializable {

    private long id;
    private String name;
    private String email;
    private String passwordHash;
    private int level;
    private int points;
    private int streak;
    private int fluency;
    private int dailyGoal;
    private String lastConnection;
    private int lastUnit;
    private int lastLesson;
    private int [] progress;
    private int remainingGoals;
    private int exp = 0;
    private int needexp = 10;
    private byte[] image;
    private List<String> roles;
    private List<CompletedExercise> exercises;


    public UserDto() {

    }

    public UserDto(String name, String email, String password, String... roles) {
        this.name=name;
        this.email=email;
        this.passwordHash= new BCryptPasswordEncoder().encode(password);
        this.roles = new ArrayList<>(Arrays.asList(roles));
        this.level=1;
        this.points=0;
        this.streak=0;
        this.fluency=0;
        this.lastConnection= "-";
        this.lastUnit=0;
        this.lastLesson=0;
    }

    public UserDto(String name, String email, String password,int level, int points, int streak, int dailyGoal, String... roles) {
        this.name=name;
        this.email=email;
        this.passwordHash= new BCryptPasswordEncoder().encode(password);
        this.roles = new ArrayList<>(Arrays.asList(roles));
        this.level=level;
        this.points=points;
        this.streak=streak;
        this.dailyGoal= dailyGoal;
        this.remainingGoals= dailyGoal;
        this.lastConnection= "-";
        this.lastUnit=0;
        this.lastLesson=0;
    }

    public void upLevel() {
        if(exp >= needexp) {
            level = level +1;
            this.exp = exp - needexp;
            this.needexp = needexp +20;
        }
    }


    public String newConnection() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String newConecction = formatter.format(today);
        return newConecction;
    }

    public void updateStreak(User user, int completedLessons) {
        if(completedLessons == user.getDailyGoal()) {
            user.setStreak(user.getStreak()+1);
        }
    }

    public void updatePoints(User user, int points) {
        if(user.getPoints()+points >=0) {
            user.setPoints(user.getPoints()+points);
        }
        else {
            user.setPoints(0);
        }
    }
}
