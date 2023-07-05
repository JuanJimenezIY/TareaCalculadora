package com.funcional.lista;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public sealed interface Lista<T> permits Nil, Cons {
    Lista NIL = new Nil();

    T head();
    Lista<T> tail( );
    boolean isEmpty( );

    static <T> Lista<T> of(T h, Lista<T> t) {
        return new Cons<>(h,t);
    }

    static <T> Lista<T> of(T... elems) {
        Lista<T> ret = Lista.NIL;
        for(int i=elems.length-1; i>=0; i--) {
            T h = elems[i];
            ret = Lista.of(h,ret);
        }
        return ret;
    }

    default Lista<T> append(T elem) {
        if(isEmpty()) {
            return Lista.of(elem);
        }
        else {
            return Lista.of( head(), tail().append(elem) );
        }
    }

    default Lista<T> prepend(T elem) {
        return Lista.of( elem, this);
    }

    default Lista<T> remove(T elem) {
        if( isEmpty() ) {
            return NIL;
        }

        if( head().equals(elem) ) {
            return tail();
        }
        else {
            return Lista.of(head(), tail().remove(elem));
        }
    }

    default Lista<T> drop(int n) {
        if(n<=0 || isEmpty()) {
            return this;
        }
        else {
            return tail().drop(n-1);
        }
    }

    default Lista<T> dropWhile(Predicate<T> p) {
        if( isEmpty() ) {
            return NIL;
        }

        if( p.test(head())  ) {
            return tail().dropWhile(p);
        }
        else {
            return this;
        }
    }

    default Lista<T> take(int n) {
        if(n<=0 || isEmpty() ) {
            return NIL;
        }
        else {
            return Lista.of(head(), tail().take(n-1));
        }
    }

    default Lista<T> takeWhile(Predicate<T> p) {
        if( isEmpty() || !p.test(head())) {
            return NIL;
        }

        return Lista.of(head(), tail().takeWhile(p));
    }

    default Lista<T> concat(Lista<T> ls) {
        if(isEmpty()) {
            return ls;
        }

        return Lista.of(head(), tail().concat(ls));
    }

    default Lista<T> replace( T elem, T newElem ) {
        if( isEmpty() ) {
            return Lista.NIL;
        }

        return head().equals(elem)
                ? Lista.of( newElem, tail() )
                : Lista.of( head(), tail().replace(elem, newElem) );
    }

    default Optional<T> contains(T elem) {
        if (isEmpty()) {
            return Optional.empty();
        }
        return head().equals(elem)
                ? Optional.of(head())
                : tail().contains(elem);
    }

    default void forEach(Consumer<T> fn) {
        if( !isEmpty() ) {
            fn.accept( head() );
            tail().forEach(fn);
        }
    }

    default Integer size() {
        if( isEmpty() ) {
            return 0;
        }
        else {
            return 1 + tail().size();
        }
    }
}

