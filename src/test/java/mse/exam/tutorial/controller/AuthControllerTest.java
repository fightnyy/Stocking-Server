package mse.exam.tutorial.controller;

import mse.exam.tutorial.BaseTest;
import mse.exam.tutorial.dto.LoginDto;
import mse.exam.tutorial.dto.TokenDto;
import mse.exam.tutorial.dto.UserDto;
import mse.exam.tutorial.entity.Authority;
import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.jwt.TokenProvider;
import mse.exam.tutorial.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class AuthControllerTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthController authController;


    @Autowired
    private WebApplicationContext context;


    //controller 테스트 말고 더 작은 단위로 테스트를 해보자
    @Test
    void 회원가입_하기() throws Exception {
        //given
        UserDto userDto = new UserDto("youngyun", "12345", "YYYYY");

        //when
        //then

        mockMvc.perform(post("/api/signup")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("회원가입"));

    }
    @Test
    @DisplayName("로그인")
    void LoginTest() throws Exception{
        //given
        UserDto userDto = new UserDto("youngyun1", "54256", "YYYYY");
        LoginDto loginDto = new LoginDto(userDto.getUsername(), userDto.getPassword());
        //when
        User user = userService.signup(userDto);
        Optional<User> savedUser = userService.getUserWithAuthorities(user.getUsername());

        //then
        mockMvc.perform((post("/api/authenticate")
                .content(objectMapper.writeValueAsString(loginDto)))
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("로그인"));
    }

}
