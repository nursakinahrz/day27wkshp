package sg.edu.nus.iss.workshop27.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop27.models.EditedComment;
import sg.edu.nus.iss.workshop27.models.Review;
import sg.edu.nus.iss.workshop27.repositories.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository rvRepo;

    public Review insertReview(Review r) {
        return rvRepo.insertReview(r);
    }

    public Review upsertEdited(String _id, EditedComment c) {
        return rvRepo.upsertEdited(_id, c);
    }
    
}
