/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.service.impl;

import com.artivisi.school.onlinetest.domain.Peserta;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *
 * @author nikko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:com/artivisi/school/onlinetest/**/applicationContext.xml"})
public class PesertaServiceTestIT {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @Test
    public void testFindAll(){
        Page<Peserta>hasil = belajarRestfulService.findAllPesertas(new PageRequest(0, 10));
        assertEquals(new Integer(1), new Integer(hasil.getNumberOfElements()));
    }
    
}
