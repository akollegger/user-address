package org.akollegger.trial.useraddy.controller;

import org.akollegger.trial.useraddy.model.Address;
import org.akollegger.trial.useraddy.model.TaskUser;
import org.akollegger.trial.useraddy.repository.AddressRepository;
import org.akollegger.trial.useraddy.repository.TaskUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RequestMapping("/taskusers")
@Controller
public class TaskUserController {
	
    @Autowired
    TaskUserRepository taskusers;

    @Autowired
    AddressRepository addresses;

    /**
     * POST /taskusers { "name": "name of the taskuser" }
     * 
     * Creates a new taskuser.
     * 
     * @param json
     * @return json containing the id of the newly created taskuser
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        TaskUser createdTaskUser = taskusers.save(TaskUser.fromJsonToTaskUser(json));
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>("{\"id\":" + createdTaskUser.getId() + "}", headers, HttpStatus.CREATED);
    }
   
    /**
     * GET /taskusers/{id}
     * 
     * Gets the full json for a taskuser.
     * 
     * @param id
     * @return json representation of the requested taskuser, or HttpStatus.NOT_FOUND
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        TaskUser taskuser = taskusers.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        if (taskuser == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(taskuser.toJson(), headers, HttpStatus.OK);
    }
    
    /**
     * Get /taskusers/
     * 
     * Gets a json array of all taskusers.
     * 
     * @return json array representation of all taskusers
     */
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(TaskUser.toJsonArray(taskusers.findAll()), headers, HttpStatus.OK);
    }

    /**
     * PUT /taskusers/ { "id": id, "title": "text of taskuser", idDone: true|false }
     * 
     * Updates an existing taskuser.
     * 
     * @param json full json representation of the taskuser to update
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (taskusers.save(TaskUser.fromJsonToTaskUser(json)) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    /**
     * DELETE /taskusers/{id}
     * 
     * Deletes an existing taskuser.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        TaskUser taskuser = taskusers.findOne(id);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (taskuser == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        taskusers.delete(taskuser);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/addresses/{address}", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addAddressToUser(@PathVariable("id") Long userId, @PathVariable("address") Long addressId, @RequestBody String json) {
        TaskUser taskUser = taskusers.findOne(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (taskUser == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        Address possibleAddress = addresses.findOne(addressId);
        if (possibleAddress == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        taskUser.addAddress(possibleAddress);
        Address foundAddress = addresses.save(possibleAddress);
        taskusers.save(taskUser);
        return new ResponseEntity<String>(foundAddress.toJson(), headers, HttpStatus.CREATED);
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

}
