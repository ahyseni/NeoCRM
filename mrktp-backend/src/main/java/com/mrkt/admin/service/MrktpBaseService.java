package com.mrkt.admin.service;

import com.mrkt.admin.exception.MrktpNotFoundException;

import java.io.Serializable;

public interface MrktpBaseService<K extends Serializable> {

    void delete(K id) throws MrktpNotFoundException;
}
