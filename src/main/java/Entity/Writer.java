package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter

public class Writer {
    private List<Article> writerArticles;
}
