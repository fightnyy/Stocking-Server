package mse.exam.tutorial.controller;

import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/activity")
@PreAuthorize("isAuthenticated()")
@Transactional
public class ActivityController {

    private final ActivityService as;

    @Autowired
    public ActivityController(ActivityService as) {
        this.as = as;
    }

    @PostMapping("/study")
    public Chito doStudy() {
        return as.doStudy();
    }


    @PostMapping("/workout")
    public Chito doWorkout() {

        return as.doWorkout();
    }

    @PostMapping("/interview")
    public Chito doInterview() {
        return as.doInterview();
    }
}
