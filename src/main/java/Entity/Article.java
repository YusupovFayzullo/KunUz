package Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {
    private String title;
    private int id;
    private String body;
    private int writeId;
    private String time;
    private int moderatorId;
    private int readNumber;
    private boolean publishment;
    private String owner;

    @Override
    public String toString() {
        return "Article{" +"\n"+
                "title= " + title + "\n" +
                "id= " + id +"\n"+
                "body= " + body + "\n" +
                "writeId= " + writeId +"\n"+
                "author= " + owner +"\n"+
                "time=" + time + "\n" +
                "moderatorId=" + moderatorId +"\n"+
                "readNumber=" + readNumber +"\n"+
                "publishment=" + publishment +"\n"+
                '}';
    }
}
