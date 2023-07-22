package com.example.demo3.repository.impl;

import com.example.demo3.entity.TestEntity;
import com.example.demo3.repository.TestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TestRepositoryImpl implements TestRepository {

    @PersistenceContext(name = "demo3-PersistenceUnit")
    private EntityManager entityManager;

    @Override
    public TestEntity find(Long id) {
        return entityManager.find(TestEntity.class, id);
    }

    @Override
    public List<TestEntity> findAll() {
        return entityManager.createQuery("select t from TestEntity t", TestEntity.class)
                .getResultList();
    }

    @Override
    public TestEntity create(TestEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TestEntity update(TestEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete TestEntity t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
