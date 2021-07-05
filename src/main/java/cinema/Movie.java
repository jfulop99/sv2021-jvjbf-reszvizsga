package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;

    private String Title;

    private LocalDateTime date;

    private int maxReservation;

    private int freeSpaces;

    public Movie(long id, String title, LocalDateTime date, int maxReservation) {
        this.id = id;
        Title = title;
        this.date = date;
        this.maxReservation = maxReservation;
        freeSpaces = maxReservation;
    }

    public void createReservation(int reservedSpaces) {
        if (freeSpaces >= reservedSpaces) {
            freeSpaces -= reservedSpaces;
        }
        else {
            throw new IllegalStateException("Not enough spaces");
        }
    }

}
