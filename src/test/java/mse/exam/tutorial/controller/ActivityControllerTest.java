package mse.exam.tutorial.controller;

import mse.exam.tutorial.BaseTest;
import mse.exam.tutorial.dto.UserDto;
import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.exception.NoUserFoundException;
import mse.exam.tutorial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WithUserDetails(value = "user1")
@Transactional
public class ActivityControllerTest extends BaseTest {
    static final Logger log = LoggerFactory.getLogger(ActivityControllerTest.class);
    @Autowired ActivityController ac;
    @Autowired UserService userService;
    User loginUser;

    @BeforeTransaction
    public void afterLogin()
    {
        if (!userService.getUserWithAuthorities("user1").isPresent()) {
            UserDto userDto = new UserDto("user1", "user1", "nick_user");
            loginUser = userService.signup(userDto);
        }
    }

    @Test
    @DisplayName("공부하기")
    public void studyTest() throws Exception
    {
        mockMvc.perform(post("/api/activity/study").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("공부하기",
                        responseFields(
                                fieldWithPath("week")
                                        .description("The Week That Chito Used"),
                                fieldWithPath("grade")
                                        .description("Grade that chito makes"),
                                fieldWithPath("intelligence")
                                        .description("intelligence of chito"),
                                fieldWithPath("health")
                                        .description("health for chito"),
                                fieldWithPath("speech")
                                        .description("how fluent chito is"))));
    }


    @Test
    @DisplayName("운동하기")
    public void doExercise() throws Exception
    {

        mockMvc.perform(post("/api/activity/study").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("운동하기",
                        responseFields(
                                fieldWithPath("week")
                                        .description("The Week That Chito Used"),
                                fieldWithPath("grade")
                                        .description("Grade that chito makes"),
                                fieldWithPath("intelligence")
                                        .description("intelligence of chito"),
                                fieldWithPath("health")
                                        .description("health for chito"),
                                fieldWithPath("speech")
                                        .description("how fluent chito is"))));


        Chito outChito = ac.doWorkout();
        log.error("outChito = {}", outChito);
        if (userService.getUserWithAuthorities("user1").isPresent()) {
            loginUser = userService.getUserWithAuthorities("user1").get();
        }
        log.error("after out chito and loginUser would be : {}",loginUser.toString());
        assertThat(outChito.getHealth()).isEqualTo(53);
        assertThat(loginUser.getChito().getHealth()).isEqualTo(53);
    }

    @Test
    @DisplayName("인터뷰하기")
    public void doInterview() throws Exception
    {
        mockMvc.perform(post("/api/activity/study").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("공부하기",
                        responseFields(
                                fieldWithPath("week")
                                        .description("The Week That Chito Used"),
                                fieldWithPath("grade")
                                        .description("Grade that chito makes"),
                                fieldWithPath("intelligence")
                                        .description("intelligence of chito"),
                                fieldWithPath("health")
                                        .description("health for chito"),
                                fieldWithPath("speech")
                                        .description("how fluent chito is"))));

        Chito outChito = ac.doInterview();
        log.debug("outChito = {}",outChito);
        loginUser = userService.getUserWithAuthorities("user1").orElseThrow(NoUserFoundException::new);
        assertThat(outChito.getSpeech()).isEqualTo(53);
        assertThat(loginUser.getChito().getSpeech()).isEqualTo(53);
    }
}
