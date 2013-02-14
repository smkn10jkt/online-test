package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author nikko
 */
@Controller
public class SoalController {
    
    @Autowired
    private BelajarRestfulService belajarRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/soal/{id}")
    @ResponseBody
    public Soal findById(@PathVariable String id){
        Soal x = belajarRestfulService.findSoalById(id);
        if(x == null){
            throw new IllegalStateException();
        }
        return x;
    }
    
    @RequestMapping(value = "/soal", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Soal x, HttpServletRequest request, HttpServletResponse response){
        belajarRestfulService.save(x);
        String requestUrl = request.getRequestURI().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/soal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Soal x){
        Soal a = belajarRestfulService.findSoalById(id);
        if(a == null){
            logger.warn("Soal dengan id [{}] tidak ditemukan", id);
            throw  new IllegalStateException();
        }
        x.setId(a.getId());
        belajarRestfulService.save(x);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/soal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Soal a = belajarRestfulService.findSoalById(id);
        if (a == null) {
            logger.warn("Soal dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        belajarRestfulService.delete(a);
    }
    
    @RequestMapping(value = "/soal", method = RequestMethod.GET)
    @ResponseBody
    public List<Soal> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        List<Soal> hasil = belajarRestfulService.findAllSoals(pageable).getContent();

        return hasil;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
