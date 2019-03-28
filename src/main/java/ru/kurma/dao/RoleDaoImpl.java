package ru.kurma.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kurma.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public Role findRoleById(Integer id) {
        return entityManager.find(Role.class, id);
    }
}
