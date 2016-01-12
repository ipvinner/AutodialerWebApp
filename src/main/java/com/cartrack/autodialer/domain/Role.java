package com.cartrack.autodialer.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by vinner on 27.08.2015.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
