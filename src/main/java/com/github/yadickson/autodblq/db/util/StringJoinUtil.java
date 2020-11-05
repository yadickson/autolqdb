/*
 * Copyright (C) 2020 Yadickson Soto
 *
 * See <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package com.github.yadickson.autodblq.db.util;

import java.util.function.Function;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author Yadickson Soto
 */
@Named
@Singleton
public class StringJoinUtil implements Function<String, String> {
    
    private final StringTrimUtil stringTrimUtil;
    
    @Inject
    public StringJoinUtil(final StringTrimUtil stringTrimUtil) {
        this.stringTrimUtil = stringTrimUtil;
    }
    
    @Override
    public String apply(final String input) {
        return stringTrimUtil.apply(input).replaceAll("\\s+", ",");
    }
    
}