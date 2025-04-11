package com.petr.postcode_api.user;

public enum Role {
    ADMIN, USER;

    // Helper method to convert from Spring Security authority
    public static Role fromAuthority(String authority) {
        String roleName = authority.startsWith("ROLE_") 
            ? authority.substring(5) 
            : authority;
        return Role.valueOf(roleName);
    }
}
