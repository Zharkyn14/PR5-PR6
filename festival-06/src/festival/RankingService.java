package festival;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public final class RankingService {
    private final RankingStrategy strategy;
    public RankingService(RankingStrategy strategy) {
        this.strategy = java.util.Objects.requireNonNull(strategy);
    }
    public String ruleName() { return strategy.name(); }
    public List<RankedMovie> rank(List<Movie> movies) {
        List<RankedMovie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.hasRatings()) {
                double score = strategy.score(movie);
                result.add(new RankedMovie(movie, score));
            }
        }
        result.sort(Comparator.comparingDouble(RankedMovie::score)
                .reversed()
                .thenComparingInt(row -> row.movie().getId()));
        return result;
    }
}