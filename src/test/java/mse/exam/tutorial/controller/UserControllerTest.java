package mse.exam.tutorial.controller;

import mse.exam.tutorial.BaseTest;
import mse.exam.tutorial.dto.UserDto;
import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WithUserDetails(value = "user1")
public class UserControllerTest extends BaseTest {
    static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);
    @MockBean
    ActivityController ac;
    @MockBean
    UserService userService;
    User loginUser;

    @BeforeTransaction
    public void 로그인하기()
    {
        UserDto userDto = new UserDto("user1","user1","nick_user");
        UserDto admin = new UserDto("admin","admin","admin");
        userService.signup(userDto);
    }

}
