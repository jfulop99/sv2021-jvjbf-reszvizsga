package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieCommand {

    @NotBlank(message = "Cannot be blank")
    private String Title;

    private LocalDateTime date;

    @Min(value = 20, message = "Minimum value is 20")
    private int maxReservation;

}
