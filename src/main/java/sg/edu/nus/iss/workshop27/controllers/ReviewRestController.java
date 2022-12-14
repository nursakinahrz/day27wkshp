package sg.edu.nus.iss.workshop27.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.workshop27.models.EditedComment;
import sg.edu.nus.iss.workshop27.models.Review;
import sg.edu.nus.iss.workshop27.services.ReviewService;

@RestController
@RequestMapping(path="/api/review")
public class ReviewRestController {

    @Autowired
    private ReviewService rvSvc;

    @PutMapping(path="{_id}")
    public ResponseEntity<String> updateEdited(@PathVariable String _id, @RequestBody EditedComment e) {
        JsonObject result = null;
        Review upsertRv = rvSvc.upsertEdited(_id, e);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("review", upsertRv.toJSON());
        result = builder.build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());

    }

    // to reconfirm about getmapping to see the updated record.
    
}
