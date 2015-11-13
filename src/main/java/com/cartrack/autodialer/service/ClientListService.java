package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.ClientList;

import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
public interface ClientListService {

    List<ClientList> getAll();

    ClientList getById(int id);
}
