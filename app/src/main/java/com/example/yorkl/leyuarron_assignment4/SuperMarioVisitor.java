package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by aaron on 6/5/2017.
 */

public interface SuperMarioVisitor {
    public int visit(Enemy enemy);
    public int visit(Item item);
}
