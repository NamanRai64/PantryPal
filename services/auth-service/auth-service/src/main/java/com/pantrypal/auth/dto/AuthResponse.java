package com.pantrypal.auth.dto;

import com.pantrypal.auth.entity.Role;

public record AuthResponse(
        String token,
        String tokenType,
        Long userId,
        String email,
        String fullName,
        Role role,
        String workspaceType
) {
    public AuthResponse(String token, Long userId, String email, String fullName, Role role, String workspaceType) {
        this(token, "Bearer", userId, email, fullName, role, workspaceType);
    }
}