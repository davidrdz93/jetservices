package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.User;

import java.util.List;


public interface UserService
{
    List<User> retrieveUsers(String nome, String cognome, String username);
}
