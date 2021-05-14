package com.exemple.demo.security;

import com.exemple.demo.exceptions.security.SecurityManagerException;

public interface SecurityManager extends AutoCloseable {
    /**
     * Open a session to the considered security service.
     *
     * @throws SecurityManagerException Thrown when connection to the security
     *         service cannot be established.
     */
    public void openSession() throws SecurityManagerException;

    /**
     * Close the session with the considered security service.
     *
     * @throws SecurityManagerException Thrown when connection to the security
     *         service cannot be closed.
     */
    public void close() throws SecurityManagerException;

    /**
     * Returns the role manager associated to this security manager.
     * A role manager provided methods to manage roles.
     *
     * @return The role manager associated to this security manager.
     */
    public RoleManager getRoleManager();

    /**
     * Returns the user manager associated to this security manager.
     * A user manager provided methods to manage users.
     *
     * @return The user manager associated to this security manager.
     */
    public UserManager getUserManager();
}
