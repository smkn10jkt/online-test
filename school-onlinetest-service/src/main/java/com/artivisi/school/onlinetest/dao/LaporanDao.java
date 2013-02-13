/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.dao;

import com.artivisi.school.onlinetest.domain.Ujian;
import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author LILY
 */
public interface LaporanDao extends PagingAndSortingRepository<Ujian, String> {
    
}
