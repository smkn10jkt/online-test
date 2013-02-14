/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Topik;
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
 * @author user
 */
public class TopikController {
    
    @Autowired
    private BelajarRestfulService belajarRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/topik/{id}")
    @ResponseBody
    public Topik findTopikById(@PathVariable String id){
        Topik a = belajarRestfulService.findTopikById(id);
        if(a == null){
            throw new IllegalStateException();
        }
        return a;
        
    }
    
    @RequestMapping(value = "/topik", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody @Valid Topik a, HttpServletRequest request, HttpServletResponse response){
        belajarRestfulService.save(a);
        String requestUrl = request.getRequestURI().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, a.getId());
        response.setHeader("Loaction", uri.toASCIIString());
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/topik/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update (@PathVariable String id, @RequestBody @Valid Topik a){
         Topik x = belajarRestfulService.findTopikById(id);
         if(x == null){
             logger.warn("Mata Pelajaran dengan id [{}] tidak ditemukan !",id);
             throw new IllegalStateException();
         }
         a.setId(x.getId());
         belajarRestfulService.save(a);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/topik/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        Topik x = belajarRestfulService.findTopikById(id);
        if(x == null){
            logger.warn("Mata Pelajaran dengan id [{id}] tidak ditemukan !",id);
            throw new IllegalStateException();
        }
        belajarRestfulService.delete(x);
    }
    
    @RequestMapping(value = "/topik", method = RequestMethod.GET)
    @ResponseBody
    public List<Topik> findAll(Pageable pageable, HttpServletResponse response){
        List<Topik> hasil = belajarRestfulService.findAllTopiks(pageable).getContent();
        for(Topik b :hasil){
        }
        return hasil;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle(){
        logger.debug("Resource dengan URI tersebut tidak ditemukan !");
    }
    
    
}
