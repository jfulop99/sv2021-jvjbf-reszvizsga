package cinema;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private AtomicLong id = new AtomicLong();

    private List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public void deleteAllMovies() {
        movies.clear();
        id = new AtomicLong();
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(id.incrementAndGet(), command.getTitle(), command.getDate(), command.getMaxReservation());

        movies.add(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    public List<MovieDTO> getMovies(Optional<String> title) {
        return movies.stream()
                .filter(movie -> title.isEmpty() || movie.getTitle().equalsIgnoreCase(title.get()))
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    private Movie findMovieById(long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
    }

    public MovieDTO updateMovie(long id, UpdateDateCommand command) {

        Movie movie = findMovieById(id);

        movie.setDate(command.getDate());

        return modelMapper.map(movie, MovieDTO.class);

    }

    public MovieDTO getMovieById(long id) {
        return modelMapper.map(findMovieById(id), MovieDTO.class);
    }

    public MovieDTO createReservation(long id, CreateReservationCommand command) throws IllegalStateException{
        Movie movie = findMovieById(id);

        movie.createReservation(command.getReservedSpaces());

        return modelMapper.map(movie, MovieDTO.class);
    }
}
