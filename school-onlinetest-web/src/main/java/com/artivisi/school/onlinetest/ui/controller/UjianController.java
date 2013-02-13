/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.domain.User;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author LILY
 */
@Controller
public class UjianController {
     @Autowired
    private BelajarRestfulService belajarRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/Ujian/{id}")
    @ResponseBody
    public Ujian findByIdUjian(@PathVariable String id) {
        Ujian u = belajarRestfulService.FindByIdUjian(id);
        if (u == null) {
            throw new IllegalStateException();
        }
        return u;}

}
