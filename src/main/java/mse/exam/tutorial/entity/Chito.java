package mse.exam.tutorial.entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;


@NoArgsConstructor(access= AccessLevel.PUBLIC)
@AllArgsConstructor
@Setter
@Getter
@Embeddable
@ToString
@Builder
@Slf4j
public class Chito {


    private Integer week=1;

    private Double grade=0.0;

    private Integer intelligence=10;

    private Integer health=10;

    private Integer speech=10;


    public void setTimePoint(Integer week) {
        if (week>0) {
            this.week = week;
        }
        else {
            log.warn("Chito의 TimePoint가 0이하 입니다. timepoint : {}",week);
            this.week=0;
        }
    }
}
