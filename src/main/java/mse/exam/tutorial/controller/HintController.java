package mse.exam.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import mse.exam.tutorial.dto.GradeDto;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.repository.UserRepository;
import mse.exam.tutorial.util.SecurityUtil;
import mse.exam.tutorial.dto.HintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
@Transactional
@Slf4j
public class HintController {
    //hello
    private UserRepository ur;
    
    @Autowired
    public HintController(UserRepository ur){
        this.ur = ur;
    }
    
    @PostMapping(value = "/hint", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveHint(@RequestBody HintDto hint)
    {
        log.error("HELLO : " + hint);
        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        User findUser = ur.findOneWithUserByUsername(currentUsername.get());
        findUser.setHint(hint.getNum());
        findUser.getChito().setWeek(findUser.getChito().getWeek()+1);
    }

    @PostMapping(value = "/save/final", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFinal(@RequestBody GradeDto grade){
        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        User findUser = ur.findOneWithUserByUsername(currentUsername.get());
        findUser.getGrades().add(grade.getNum());
        List<Double> grades = findUser.getGrades();
        double sum = 0;
        for (Double aDouble : grades) {
            sum += aDouble;
        }
        double avg = sum / grades.size();
        findUser.setAverage(avg);
        findUser.setHint(findUser.getHint()-grade.getUsedhint());
        findUser.getChito().setWeek(findUser
        .getChito().getWeek()+1);
    }
}
