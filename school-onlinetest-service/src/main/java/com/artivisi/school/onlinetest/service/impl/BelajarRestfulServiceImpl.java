package com.artivisi.school.onlinetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.artivisi.school.onlinetest.dao.ApplicationConfigDao;
import com.artivisi.school.onlinetest.dao.UjianDao;
import com.artivisi.school.onlinetest.dao.MenuDao;
import com.artivisi.school.onlinetest.dao.PermissionDao;
import com.artivisi.school.onlinetest.dao.PertanyaanDao;
import com.artivisi.school.onlinetest.dao.PesertaDao;
import com.artivisi.school.onlinetest.dao.PilihanDao;
import com.artivisi.school.onlinetest.dao.RoleDao;
import com.artivisi.school.onlinetest.dao.SoalDao;
import com.artivisi.school.onlinetest.dao.TopikDao;
import com.artivisi.school.onlinetest.dao.UserDao;
import com.artivisi.school.onlinetest.domain.ApplicationConfig;
import com.artivisi.school.onlinetest.domain.Menu;
import com.artivisi.school.onlinetest.domain.Permission;
import com.artivisi.school.onlinetest.domain.Pertanyaan;
import com.artivisi.school.onlinetest.domain.Peserta;
import com.artivisi.school.onlinetest.domain.Pilihan;
import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.Topik;
import com.artivisi.school.onlinetest.domain.Ujian;
import com.artivisi.school.onlinetest.domain.User;
import com.artivisi.school.onlinetest.service.BelajarRestfulService;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unchecked")
@Service("belajarRestfulService")
@Transactional
public class BelajarRestfulServiceImpl implements BelajarRestfulService {

	@Autowired
    private ApplicationConfigDao applicationConfigDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UjianDao ujianDao;
    @Autowired
    private SoalDao soalDao;
    @Autowired
    private TopikDao topikDao;
    @Autowired
    private PesertaDao pesertaDao;
    @Autowired
    private PilihanDao pilihanDao;
    @Autowired
    private PertanyaanDao pertanyaanDao;

    @Override
    public void save(ApplicationConfig ac) {
        applicationConfigDao.save(ac);
    }

    @Override
    public void delete(ApplicationConfig ac) {
        if (ac == null || ac.getId() == null) {
            return;
        }
        applicationConfigDao.delete(ac);
    }

    @Override
    public ApplicationConfig findApplicationConfigById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return applicationConfigDao.findOne(id);
    }

    @Override
    public Page<ApplicationConfig> findAllApplicationConfigs(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }
        return applicationConfigDao.findAll(pageable);
    }

    @Override
    public Long countAllApplicationConfigs() {
        return applicationConfigDao.count();
    }

    @Override
    public Page<ApplicationConfig> findApplicationConfigs(String search, Pageable pageable) {
        if (!StringUtils.hasText(search)) {
            return findAllApplicationConfigs(pageable);
        }
        
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }

        return applicationConfigDao.search("%" + search + "%", pageable);
    }

    @Override
    public Long countApplicationConfigs(String search) {
        if (!StringUtils.hasText(search)) {
            return countAllApplicationConfigs();
        }
        return applicationConfigDao.count("%" + search + "%");
    }

    @Override
    public void save(Menu m) {
        menuDao.save(m);
    }

    @Override
    public void delete(Menu m) {
        menuDao.delete(m);
    }

    @Override
    public Menu findMenuById(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return menuDao.findOne(id);
    }

    @Override
    public List<Menu> findTopLevelMenu() {
        return menuDao.findTopLevelMenu();
    }
    
    @Override
    public Page<Menu> findAllMenu(Pageable pageable) {
        return menuDao.findAll(pageable);
    }
    
    @Override
    public Long countAllMenu() {
        return menuDao.count();
    }

    @Override
    public List<Menu> findMenuByParent(Menu m) {
        if(m == null || !StringUtils.hasText(m.getId())) {
            return new ArrayList<Menu>();
        }
        return menuDao.findMenuByParent(m.getId());
    }
    
    @Override
    public List<Menu> findMenuNotInRole(Role role){
        if(role == null){
            return new ArrayList<Menu>();
        }
        
        Role r = findRoleById(role.getId());
        if(r == null || r.getMenuSet().isEmpty()){
            return new ArrayList<Menu>();
        }
        
        List<String> ids = new ArrayList<String>();
        for (Menu m : r.getMenuSet()) {
            ids.add(m.getId());
        }
        
        return menuDao.findByIdNotIn(ids);
    }

    @Override
    public void save(Permission m) {
        permissionDao.save(m);
    }

    @Override
    public void delete(Permission m) {
        permissionDao.delete(m);
    }

    @Override
    public Permission findPermissionById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return permissionDao.findOne(id);
    }

    @Override
    public Page<Permission> findAllPermissions(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }
        return permissionDao.findAll(pageable);
    }

    @Override
    public Long countAllPermissions() {
        return permissionDao.count();
    }
    
    @Override
    public List<Permission> findPermissionsNotInRole(Role role) {
        if(role == null){
            return new ArrayList<Permission>();
        }
        
        Role r = findRoleById(role.getId());
        if(r == null || r.getPermissionSet().isEmpty()){
            return new ArrayList<Permission>();
        }
        
        List<String> ids = new ArrayList<String>();
        for (Permission p : r.getPermissionSet()) {
            ids.add(p.getId());
        }
        
        return permissionDao.findByIdNotIn(ids);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public Role findRoleById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        
        Role r = roleDao.findOne(id);
        if(r != null){
            r.getPermissionSet().size();
            r.getMenuSet().size();
        }
        
        return r;
    }

    @Override
    public Page<Role> findAllRoles(Pageable pageable) {
        return roleDao.findAll(pageable);
    }

    @Override
    public Long countAllRoles() {
        return roleDao.count();
    }

    @Override
    public void save(User m) {
        userDao.save(m);
    }

    @Override
    public void delete(User m) {
        userDao.delete(m);
    }

    @Override
    public User findUserById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return userDao.findOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        if(!StringUtils.hasText(username)){
            return null;
        }
        return userDao.findByUsername(username);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Long countAllUsers() {
        return userDao.count();
    }

    @Override
    public void save(Ujian ujian) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Ujian ujian) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ujian findByIdUjian(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Ujian> findAllUjian(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countAllUjians() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Peserta peserta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Peserta peserta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Peserta findPesertaById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Peserta> findAllPesertas(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countAllPesertas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Soal soal) {
        soalDao.save(soal);
    }

    @Override
    public void delete(Soal soal) {
        soalDao.delete(soal);
    }

    @Override
    public Soal findSoalById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        return soalDao.findOne(id);
    }

    @Override
    public Page<Soal> findAllSoals(Pageable pageable) {
        return soalDao.findAll(pageable);
    }

    @Override
    public Long countAllSoals() {
        return soalDao.count();
    }

    @Override
    public void save(Pertanyaan pertanyaan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Pertanyaan pertanyaan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pertanyaan findPertanyaanById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Pertanyaan> findAllPertanyaans(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countAllPertanyaans() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Topik topik) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Topik topik) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Topik findTopikById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Topik> findAllTopiks(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countAllTopiks() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Pilihan pilihan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Pilihan pilihan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pilihan findPilihanById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<Pilihan> findAllPilihans(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long countAllPilihans() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
