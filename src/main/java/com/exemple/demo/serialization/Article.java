package com.exemple.demo.serialization;

public class Article {
	private int idArticle;
    private String description;
    private String brand;
    private double price;
    
    // Requis pour le moteur de sérialisation/désérialisation JSON
    public Article() {
        this( 1, "unknown", "unknown", 0 ); 
    }
    
    public Article( int idArticle, String description, String brand, double price ) {
        this.idArticle = idArticle;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    @Override public String toString() {
        return "Article [idArticle=" + idArticle + ", description=" + 
               description + ", brand=" + brand + ", price=" + price + "]";
    }
}
