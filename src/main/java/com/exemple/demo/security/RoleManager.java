package com.exemple.demo.security;

import com.exemple.demo.exceptions.security.RoleAlreadyRegisteredException;
import com.exemple.demo.exceptions.security.SecurityManagerException;

public interface RoleManager {
    /**
     * Select the role with the identifier specified in parameter.
     *
     * @param roleIdentifier    The identifier of the role to returns.
     * @return                  The selected role.
     *
     * @exception SecurityManagerException
     *      Thrown if the searched role don't exists.
     */
    public Role selectRoleById( int roleIdentifier ) throws SecurityManagerException ;

    /**
     * Select the role with the name specified in parameter.
     *
     * @param roleName      The name of the role to returns.
     * @return              The selected role.
     *
     * @exception SecurityManagerException
     *      Thrown if the searched role don't exists.
     */
    public Role selectRoleByName( String roleName ) throws SecurityManagerException ;

    /**
     * Insert a new role into the used security system.
     *
     * @param roleName      The name of the new role.
     * @return              The new role.
     *
     * @exception SecurityManagerException
     *      Thrown if the role cannot be inserted into the security system.
     * @exception RoleAlreadyRegisteredException
     *      Thrown if the specified role name already exists in the security system.
     */
    public Role insertRole( String roleName )
            throws SecurityManagerException, RoleAlreadyRegisteredException;

    /**
     * Update the informations for this role (actually, only the role name).
     *
     * @param role  The role to update.
     *
     * @exception SecurityManagerException
     *      Thrown if the role cannot be updated into the security system.
     */
    public void updateRole( Role role ) throws SecurityManagerException ;

    /**
     * Delete, on the security system, the specified role.
     *
     * @param role  The role to delete.
     * @exception SecurityManagerException
     *      Thrown if the specified role cannot be deleted from the security system.
     */
    public void deleteRole( Role role ) throws SecurityManagerException;
}
