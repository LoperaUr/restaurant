package com.pragma.restaurant.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<E, T> {
    List<E> searchAll() throws Exception;
    E searchById(Long id) throws Exception;
    E create(T data) throws Exception;
    E update(Long id, T data) throws Exception;
    boolean delete(Long id) throws Exception;
}
