package mse.exam.tutorial.service;

import lombok.extern.slf4j.Slf4j;
import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.repository.UserRepository;
import mse.exam.tutorial.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ActivityService {

    private final UserRepository ur;
    int upIntelli = 3;
    int upHealth = 3;
    int upInterv = 3;
    String past = null;
    String present = null;



    @Autowired
    public ActivityService(UserRepository ur)
    {
        this.ur = ur;
    }




    public Chito doStudy()  {
        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        User findUser = ur.findOneWithUserByUsername(currentUsername.get());
        Chito findChito = findUser.getChito();
        past = findUser.getPast();
        present = findUser.getPresent();
        if (past == null&&present==null)
        {
            findUser.setPast("st");
            findUser.setPresent("st");
            findUser.setCounter(1);
        }
        else if(findUser.getPast().equals(findUser.getPresent())) {
            findUser.setCounter(findUser.getCounter()+1);
        }
        else
        {
            findUser.setPast(findUser.getPresent());
            findUser.setPresent("st");
            findUser.setCounter(1);
        }
        if (findUser.getCounter()<=3) {
            findChito.setIntelligence(findChito.getIntelligence() + upIntelli);
            findChito.setHealth(findChito.getHealth()-1);
            findChito.setSpeech(findChito.getSpeech() - 1);

            if (findChito.getSpeech() < 0){
                findChito.setSpeech(1);
            }
            findChito.setWeek(findChito.getWeek() + 1);
            return findChito;
        }
        else {
            findChito.setIntelligence(findChito.getIntelligence() + 6);
            findChito.setWeek(findChito.getWeek() + 1);
            findChito.setHealth(findChito.getHealth() - 3);
            findChito.setSpeech(findChito.getSpeech() - 3);
            if (findChito.getSpeech() < 0)
            {
                findChito.setSpeech(0);
            }
            return findChito;
        }
    }


    public Chito doWorkout() {
        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        User findUser = ur.findOneWithUserByUsername(currentUsername.get());
        Chito findChito = findUser.getChito();
        past = findUser.getPast();
        present = findUser.getPresent();
        if (past == null&&present==null)
        {
            findUser.setPast("Wo");
            findUser.setPresent("Wo");
            findUser.setCounter(1);
        }
        else if(findUser.getPast().equals(findUser.getPresent())) {
            findUser.setCounter(findUser.getCounter()+1);
        }
        else
        {
            findUser.setPast(findUser.getPresent());
            findUser.setPresent("Wo");
            findUser.setCounter(1);
        }
        if (findUser.getCounter()<=3) {
            findChito.setHealth(findChito.getHealth() + upHealth);
            findChito.setIntelligence(findChito.getIntelligence()-1);
            findChito.setSpeech(findChito.getSpeech() - 1);

            if (findChito.getSpeech() < 0){
                findChito.setSpeech(0);
            }
            findChito.setWeek(findChito.getWeek() + 1);
            return findChito;
        }
        else {
            findChito.setHealth(findChito.getHealth() + 6);
            findChito.setWeek(findChito.getWeek() + 1);
            findChito.setIntelligence(findChito.getIntelligence() - 3);
            findChito.setSpeech(findChito.getSpeech() - 3);
            if (findChito.getSpeech() < 0)
            {
                findChito.setSpeech(0);
            }
            return findChito;
        }
    }

    public Chito doInterview() {
        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        User findUser = ur.findOneWithUserByUsername(currentUsername.get());
        Chito findChito = findUser.getChito();
        past = findUser.getPast();
        present = findUser.getPresent();
        if (past == null && present == null) {
            findUser.setPast("In");
            findUser.setPresent("In");
            findUser.setCounter(1);
        } else if (findUser.getPast().equals(findUser.getPresent())) {
            findUser.setCounter(findUser.getCounter() + 1);
        } else {
            findUser.setPast(findUser.getPresent());
            findUser.setPresent("In");
            findUser.setCounter(1);
        }
        if (findUser.getCounter() <= 3) {
            findChito.setSpeech(findChito.getSpeech() + upInterv);
            findChito.setIntelligence(findChito.getIntelligence() - 1);
            findChito.setHealth(findChito.getHealth() - 1);

            if (findChito.getSpeech() < 0) {
                findChito.setSpeech(0);
            }
            findChito.setWeek(findChito.getWeek() + 1);
            return findChito;
        } else {
            findChito.setSpeech(findChito.getSpeech() + 6);
            findChito.setWeek(findChito.getWeek() + 1);
            findChito.setHealth(findChito.getHealth() - 3);
            findChito.setIntelligence(findChito.getIntelligence() - 3);
            if (findChito.getSpeech() < 0) {
                findChito.setSpeech(0);
            }
            return findChito;
        }
    }
    
}
