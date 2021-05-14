package com.exemple.demo.security;

import com.exemple.demo.exceptions.security.SecurityManagerException;

public class ClassToValidate {
    private SecurityManager securityManager;

    // Un setter pour injecter un securityManager.
    public void setSecurityManager( SecurityManager securityManager ) {

        if ( securityManager == null ) {
            throw new NullPointerException( "securityManager cannot be null" );
        }
        this.securityManager = securityManager;

    }

    // La méthode à tester.
    public boolean doSomething( User user ) throws SecurityManagerException {

        Role role = securityManager.getRoleManager().selectRoleByName( "admin" );
        if ( ! user.isMemberOfRole( role ) ) {
            throw new SecurityManagerException( "Only admin is autorized" );
        }

        // Imaginez ici du code plus complexe.

        return true;

    }
}
