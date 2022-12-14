package sg.edu.nus.iss.workshop27.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review extends EditedComment {

    private String _id;
    private String user;
    private Integer gameId;
    private String boardGame;
    private List<EditedComment> edited;

    public Review() {

    }

    public Review(String user, Integer rating, String comment, Integer gameId, String boardGame) {
        this.user = user;
        super.setRating(rating);
        super.setComment(comment);
        this.gameId = gameId;
        this.boardGame = boardGame;
        super.setPosted(LocalDateTime.now());

    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public String getBoardGame() {
        return boardGame;
    }
    public void setBoardGame(String boardGame) {
        this.boardGame = boardGame;
    }
    public List<EditedComment> getEdited() {
        return edited;
    }
    public void setEdited(List<EditedComment> edited) {
        this.edited = edited;
    }

    //create method
    public static Review create(Document d) {
        Review r = new Review();
        r.set_id(d.getObjectId("_id").toString());
        r.setUser(d.getString("user"));
        r.setRating(d.getInteger("rating"));
        r.setComment(d.getString("comment"));
        r.setGameId(d.getInteger("gameId"));
        LocalDateTime lPosted = Instant.ofEpochMilli(d.getDate("posted").getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
        r.setPosted(lPosted);
        r.setBoardGame(d.getString("name"));

        return r;
    }

    public JsonObject toJSON() {

        List<JsonObject> js = this.getEdited()
        .stream()
        .map(c -> c.toJSON())
        .toList();

        return (JsonObject) Json.createObjectBuilder()
        .add("_id", get_id())
        .add("user", getUser())
        .add("rating", getRating())
        .add("comment", getComment())
        .add("gameId", getGameId())
        .add("posted", getPosted().toString())
        .add("boardGame", getBoardGame())
        .add("edited", js.toString())
        .build();
        


    }


    
}
