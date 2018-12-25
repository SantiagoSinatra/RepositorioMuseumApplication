package com.dhsantiagosinatra.museumapplication.util;

public interface ResultListener<T> {
    void finish(T result);
}
