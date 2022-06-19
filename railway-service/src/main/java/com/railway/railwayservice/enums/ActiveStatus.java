package com.railway.railwayservice.enums;

/**
 * The enum Active status.
 */
public enum ActiveStatus {
    /**
     * Active active status.
     */
    ACTIVE(true),
    /**
     * Inactive active status.
     */
    INACTIVE(false);

    private Boolean value;

    ActiveStatus(Boolean value) {
        this.value = value;
    }

    /**
     * Get value boolean.
     *
     * @return the boolean
     */
    public Boolean getValue(){
        return this.value;
    }
}
