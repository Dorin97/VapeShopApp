package com.android.tonyvu.sc.model;

/**
 * Implements this interface for any product which can be added to shopping cart
 */
public interface Saleable {
    Float getPrice();

    String getName();
}
