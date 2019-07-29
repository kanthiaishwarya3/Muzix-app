package com.stackroute.controller;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController
{

@Autowired
private TrackRepository trackRepository;

    // Declaration
   TrackService trackService;


public TrackController(TrackService trackService)
{
    this.trackService = trackService;
}


// Implementing POST method
@PostMapping("track")
public ResponseEntity<?> saveTrack(@RequestBody Track track)
{
    ResponseEntity responseEntity;
    try{
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity("Successfully created", HttpStatus.CREATED);
    }
    catch (Exception e)
    {
        responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return responseEntity;

}

// Implementing GET method
@GetMapping("track")
    public ResponseEntity<?> getAllTracks()
{
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
}

// Implementing PUT method
@PutMapping("track")
public ResponseEntity<?> updateTrack(@RequestBody Track track)
{
    ResponseEntity responseEntity;
    try{
        trackService.updateTrack(track);
        responseEntity=new ResponseEntity("Successfully updated", HttpStatus.CREATED);
    }
    catch (Exception e)
    {
        responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return responseEntity;
}

@GetMapping("tracklist")
public ResponseEntity<?> sendGet() throws Exception
{
    return new ResponseEntity<String>(trackService.getUrlData(),HttpStatus.OK);
}

// Implementing DELETE method
@DeleteMapping(value="/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id)
{
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

// Retrieving Data by name field
    @GetMapping(value="/track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable("name") String name)
    {
        return new ResponseEntity<List<Track>>(trackService.trackByName(name),HttpStatus.OK);
    }

}
