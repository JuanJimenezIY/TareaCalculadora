package com.funcional.tree;

import java.util.function.Consumer;

import com.funcional.lista.Lista;

public sealed interface BinTree<T> permits ConsBinTree, LeafBinTree {

    BinTree Leaf = new LeafBinTree();

    T value();
    BinTree<T> left();
    BinTree<T> right();

    static <T> BinTree<T> of(T value, BinTree<T> left, BinTree<T> right) {
        return new ConsBinTree<>(value,left,right);
    }

    static <T> BinTree<T> of(T value) {
        return new ConsBinTree<>(value);
    }

    Integer size();

    void print(int level);
    
    default void forEach(Consumer<T> fn) {
        if(this!=Leaf) {
            fn.accept( value() );
            left().forEach(fn);
            right().forEach(fn);
        }
    }


    static <T> BinTree<T> buildTree(Lista<T> ls) {
        if( ls.isEmpty() ) {
            return BinTree.Leaf;
        }
        else {
            var h = ls.head();
            var t = ls.tail();

            int k = t.size() / 2;

            var leftList = t.take(k);
            var rightList = t.drop(k);

            return BinTree.of(h, buildTree(leftList), buildTree(rightList));
        }
    }
    

}
