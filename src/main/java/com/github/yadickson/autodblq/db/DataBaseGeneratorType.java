/*
 * Copyright (C) 2020 Yadickson Soto
 *
 * See <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package com.github.yadickson.autodblq.db;

/**
 *
 * @author Yadickson Soto
 */
public enum DataBaseGeneratorType {
    
    VERSION("version"),
    TABLE_DEFINITION("tables"),
    TABLE_PRIMARY_KEYS("primaryKeys"),
    TABLE_FOREIGN_KEYS("foreignKeys"),
    TABLE_CHECKS("checks"),
    TABLE_UNIQUES("uniques"),
    TABLE_INDEXES("indexes"),
    TABLE_DEFAULTS("defaults"),
    VIEW_DEFINITION("views"),
    FUNCTION_DEFINITION("functions"),
    TYPE_DEFINITION("types"),
    SEQUENCE_DEFINITION("sequences"),
    DATA_DEFINITION("dataTables");
    
    private final String message;

    private DataBaseGeneratorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
