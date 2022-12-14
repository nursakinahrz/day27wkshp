package sg.edu.nus.iss.workshop27.repositories;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop27.models.EditedComment;
import sg.edu.nus.iss.workshop27.models.Review;

@Repository
public class ReviewRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review insertReview(Review r)  {
        return mongoTemplate.insert(r, "reviews");

    }

    public Review upsertEdited(String _id, EditedComment e) {
        ObjectId objId = new ObjectId(_id);
        Review existingR = mongoTemplate.findById(objId, Review.class, "reviews");

        if(existingR !=null) {
            EditedComment er = new EditedComment();
            er.setComment(e.getComment());
            er.setRating(e.getRating());
            er.setPosted(LocalDateTime.now());
            if(existingR.getEdited() !=null) {
                
                existingR.getEdited().add(er);

        }else{
            List<EditedComment> eArr= new LinkedList<EditedComment>();
            eArr.add(e);
            existingR.setEdited(eArr);

        }

        mongoTemplate.save(existingR);
    }
    
    return existingR;
    }
}
