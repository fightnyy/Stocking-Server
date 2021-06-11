package mse.exam.tutorial.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ChitoTest {

    @Test
    public void 치토_타임포인트_테스트()
    {
        Chito chito = new Chito();
        chito.setTimePoint(-1);
        assertThat(chito.getWeek()).isEqualTo(0);
        chito.setTimePoint(5);
        assertThat(chito.getWeek()).isEqualTo(5);
    }
}
