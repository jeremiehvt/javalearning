package com.exemple.demo;

/**
 * 
 * @author jeremiehvt
 * description : normalement ne doit avoir que une seule methode
 * mais pour les besoins de l'exercice elle en a plusieures
 */
@FunctionalInterface
public interface FuncInterface {
	void testMethod(String message);
	static void say(String message) {
		System.out.println(message);
	}
	default void talk(String message) {
		System.out.println(message);
	}
}
