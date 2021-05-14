package com.exemple.demo.security;

public interface Role {
    /**
     * Returns the unique identifier for this role.
     *
     * @return The unique identifier.
     */
    public int getIdentifier();

    /**
     * Returns the name of this role.
     *
     * @return Role name.
     *
     * @see fr.koor.security.Role#setRoleName
     */
    public String getRoleName();

    /**
     * Changes the name of this role.
     *
     * @param newRoleName   The new name of the role.
     *
     * @see fr.koor.security.Role#getRoleName
     */
    public void setRoleName( String newRoleName );
}
