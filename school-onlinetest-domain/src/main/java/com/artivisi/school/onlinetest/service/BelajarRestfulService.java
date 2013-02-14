package com.artivisi.school.onlinetest.service;

import java.util.List;

import com.artivisi.school.onlinetest.domain.ApplicationConfig;
import com.artivisi.school.onlinetest.domain.Menu;
import com.artivisi.school.onlinetest.domain.Permission;
import com.artivisi.school.onlinetest.domain.Pertanyaan;
import com.artivisi.school.onlinetest.domain.Peserta;
import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BelajarRestfulService extends MonitoredService {
	// konfigurasi aplikasi
	void save(ApplicationConfig ac);
	void delete(ApplicationConfig ac);
	ApplicationConfig findApplicationConfigById(String id);
    Page<ApplicationConfig> findAllApplicationConfigs(Pageable pageable);
	Long countAllApplicationConfigs();
	Long countApplicationConfigs(String search);
	Page<ApplicationConfig> findApplicationConfigs(String search, Pageable pageable);
        
    // menu
    void save(Menu m);
    void delete(Menu m);
    Menu findMenuById(String id);
    List<Menu> findTopLevelMenu();
    Page<Menu> findAllMenu(Pageable pageable);
    Long countAllMenu();
    List<Menu> findMenuByParent(Menu m);
    List<Menu> findMenuNotInRole(Role r);
    
    // permission
    void save(Permission m);
    void delete(Permission m);
    Permission findPermissionById(String id);
    Page<Permission> findAllPermissions(Pageable pageable);
    Long countAllPermissions();
    List<Permission> findPermissionsNotInRole(Role r);
    
    // role
    void save(Role role);
    void delete(Role role);
    Role findRoleById(String id);
    Page<Role> findAllRoles(Pageable pageable);
    Long countAllRoles();
    
    // user
    void save(User m);
    void delete(User m);
    User findUserById(String id);
    User findUserByUsername(String username);
    Page<User> findAllUsers(Pageable pageable);
    Long countAllUsers();
   
    // ujian
    void save (Ujian ujian);
    void delete (Ujian ujian);
    Ujian findByIdUjian (String id);
    Page<Ujian> findAllUjian (Pageable pageable);
    Long countAllUjians();
        
    // peserta
    void save(Peserta peserta);
    void delete(Peserta peserta);
    Peserta findPesertaById(String id);
    Page<Peserta> findAllPesertas(Pageable pageable);
    Long countAllPesertas();
    
    // soal
    void save(Soal soal);
    void delete(Soal soal);
    User findSoalById(String id);
    Page<Soal> findAllSoals(Pageable pageable);
    Long countAllSoals();
    
    // pertanyaan
    void save(Pertanyaan pertanyaan);
    void delete(Pertanyaan pertanyaan);
    User findPertanyaanById(String id);
    Page<Soal> findAllPertanyaans(Pageable pageable);
    Long countAllPertanyaans();
}