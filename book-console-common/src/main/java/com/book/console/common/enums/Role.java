package com.book.console.common.enums;

/**
 * Created by zino on 28/11/2016.
 */
public enum Role {
    NORMAL(1, "普通用户"),
    MANAGER(64, "管理员"),
    ADMIN(127, "超级管理员");

    Role(int role, String description) {
        this.role = role;
        this.description = description;
    }

    /**
     * 是否有权限
     * @param permissionRole
     * @param userRole
     * @return
     */
    public static boolean hasPermission(Role permissionRole, int userRole) {
        return ADMIN.getRole() == userRole || ((userRole & permissionRole.getRole()) == permissionRole.getRole());
    }

    public static boolean hasPermission(Role[] permissionRoles, int userRole) {
        for (Role role : permissionRoles) {
            if(hasPermission(role, userRole)) {
                return true;
            }
        }
        return false;
    }

    private int role;
    private String description;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
