package com.shop.shop.management.domain.constants;

public enum SecurityRoleEnum {

    USER(1),
    ADMIN(2);

    private int val;

    SecurityRoleEnum(int val){
        this.val = val;
    }

    public int value(){
        return this.val;
    }

    public static String valueOf(int val) {
        for (SecurityRoleEnum role : SecurityRoleEnum.values()) {
            if (role.value() == val) {
                return role.name();
            }
        }
        return "No valid role";
    }
}
