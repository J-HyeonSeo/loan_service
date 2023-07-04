package com.jhsfully.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Organization {
    NONE("00000"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002"),
    ORGANIZATION_THREE("00003");

    private final String code;

    public static Organization of(String code) {
        for (Organization organization : Organization.values()) {
            if (organization.getCode().equals(code)) {
                return organization;
            }
        }
        throw new IllegalArgumentException("Invalid Product code: " + code);
    }
}
