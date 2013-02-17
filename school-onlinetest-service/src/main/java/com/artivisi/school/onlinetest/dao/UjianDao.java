/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Ujian;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author LILY
 */
public interface UjianDao extends PagingAndSortingRepository<Ujian, String> {
    
}
