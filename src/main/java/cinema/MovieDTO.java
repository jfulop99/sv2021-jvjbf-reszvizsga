package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private long id;

    private String Title;

    private LocalDateTime date;

//    private int maxSpaces;

    private int freeSpaces;


}
