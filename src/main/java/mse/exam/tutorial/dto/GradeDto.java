package mse.exam.tutorial.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    @NotNull
    private Double num;

    @NotNull
    private int usedhint;
}
