package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class TrackServiceImpl implements TrackService
{
    HttpURLConnection httpURLConnection;

   static String GET_URL="http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=f098f9bd38bdd064215c5393533829c8&format=json";

    TrackRepository trackRepository;

    // Providing implementation for all methods of track
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }




    @Override
    public Track saveTrack(Track track) throws Exception
    {
        if(trackRepository.existsById(track.getId()))
        {
            throw new Exception("Track Already exist");
        }
        Track savetrack=trackRepository.save(track);

        if(savetrack==null)
        {
            throw new Exception("Track already present");
        }
        return savetrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track)  throws Exception
    {
        if (trackRepository.existsById(track.getId())) {
            Track trackobj = trackRepository.findById(track.getId()).get();
            trackobj.setComment(track.getComment());
            trackRepository.save(trackobj);
            return trackobj;
        } else {
            throw new Exception("Track not found");
        }
    }

    @Override
    public void deleteTrack(int id)
    {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> trackByName(String name) {
        return trackRepository.trackByName(name);
    }

    @Override
    public String getUrlData() throws Exception
    {
        String message="";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
       con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            message=response.toString();
        } else {
            message="GET request not worked";
        }
        return message;
    }
}