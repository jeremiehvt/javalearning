package com.exemple.demo;

import com.exemple.demo.exceptions.security.SecurityManagerException;
import com.exemple.demo.security.*;
import com.exemple.demo.security.SecurityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ClassToValidateTest {
    @Mock
    private User user;

    @Mock
    private Role role;

    @Mock
    private UserManager userManager;

    @Mock
    private RoleManager roleManager;

    @Mock
    private SecurityManager securityManager;

    @BeforeEach
    public void setUp() throws SecurityManagerException {
        // On instancie et on injecte les mocks.
        MockitoAnnotations.openMocks(this);

        // Et on les assemble les uns au autres.
        Mockito.when( roleManager.selectRoleByName( "admin" ) ).thenReturn( role );
        Mockito.when( user.isMemberOfRole( role ) ).thenReturn( true );

        Mockito.when( securityManager.getUserManager() ).thenReturn( userManager );
        Mockito.when( securityManager.getRoleManager() ).thenReturn( roleManager );
    }

    @Test
    public void testScenarioSuccess() throws Exception {
//        // On instancie et on injecte les mocks.
//        MockitoAnnotations.openMocks(this);
//
//        // Et on les assemble les uns au autres.
//        Mockito.when( roleManager.selectRoleByName( "admin" ) ).thenReturn( role );
//        Mockito.when( user.isMemberOfRole( role ) ).thenReturn( true );
//
//        Mockito.when( securityManager.getUserManager() ).thenReturn( userManager );
//        Mockito.when( securityManager.getRoleManager() ).thenReturn( roleManager );

        // On lance notre test.
        ClassToValidate codeToValidate = new ClassToValidate();
        codeToValidate.setSecurityManager( securityManager );

        boolean result = codeToValidate.doSomething( user );

        // On vérifie que tout a fonctionné correctement.
        Assertions.assertTrue( result );
        Mockito.verify( securityManager, Mockito.times( 1 ) ).getRoleManager();
    }
}
