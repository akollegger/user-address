package org.akollegger.trial.useraddy.controller;

import org.akollegger.trial.useraddy.model.Address;
import org.akollegger.trial.useraddy.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RequestMapping("/addresses")
@Controller
public class AddressController {
	
    @Autowired
    AddressRepository addresses;

    /**
     * POST /addresses { "text": "text of the address" }
     * 
     * Creates a new address.
     * 
     * @param json
     * @return json containing the id of the newly created address
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Address createdAddress = addresses.save(Address.fromJsonToAddress(json));
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>("{\"id\":" + createdAddress.getId() + "}", headers, HttpStatus.CREATED);
    }
   
    /**
     * GET /addresses/{id}
     * 
     * Gets the full json for a address.
     * 
     * @param id
     * @return json representation of the requested address, or HttpStatus.NOT_FOUND
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Address address = addresses.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        if (address == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(address.toJson(), headers, HttpStatus.OK);
    }
    
    /**
     * Get /addresses/
     * 
     * Gets a json array of all addresses.
     * 
     * @return json array representation of all addresses
     */
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Address.toJsonArray(addresses.findAll()), headers, HttpStatus.OK);
    }

    /**
     * PUT /addresses/ { "id": id, "title": "text of address", idDone: true|false }
     * 
     * Updates an existing address.
     * 
     * @param json full json representation of the address to update
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (addresses.save(Address.fromJsonToAddress(json)) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    /**
     * DELETE /addresses/{id}
     * 
     * Deletes an existing address.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Address address = addresses.findOne(id);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (address == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        addresses.delete(address);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
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
