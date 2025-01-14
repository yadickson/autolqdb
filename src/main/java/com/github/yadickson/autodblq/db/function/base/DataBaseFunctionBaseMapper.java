/*
 * Copyright (C) 2020 Yadickson Soto
 *
 * See <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package com.github.yadickson.autodblq.db.function.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.yadickson.autodblq.db.function.base.model.FunctionBase;
import com.github.yadickson.autodblq.db.function.base.model.FunctionBaseBean;
import com.github.yadickson.autodblq.logger.LoggerManager;
import com.github.yadickson.autodblq.util.*;

/**
 *
 * @author Yadickson Soto
 */
@Named
public class DataBaseFunctionBaseMapper implements Function<List<FunctionBaseBean>, List<FunctionBase>> {

    private final LoggerManager loggerManager;
    private final StringTrimUtil stringTrimUtil;
    private final StringToLowerCaseUtil stringToLowerCaseUtil;
    private final StringToSnakeCaseUtil stringToSnakeCaseUtil;
    private final StringToContentUtil stringToContentUtil;
    private final StringToBooleanUtil stringToBooleanUtil;

    @Inject
    public DataBaseFunctionBaseMapper(
            LoggerManager loggerManager, StringTrimUtil stringTrimUtil, StringToLowerCaseUtil stringToLowerCaseUtil, StringToSnakeCaseUtil stringToSnakeCaseUtil, final StringToContentUtil stringToContentUtil,
            final StringToBooleanUtil stringToBooleanUtil
    ) {
        this.loggerManager = loggerManager;
        this.stringTrimUtil = stringTrimUtil;
        this.stringToLowerCaseUtil = stringToLowerCaseUtil;
        this.stringToSnakeCaseUtil = stringToSnakeCaseUtil;
        this.stringToContentUtil = stringToContentUtil;
        this.stringToBooleanUtil = stringToBooleanUtil;
    }

    @Override
    public List<FunctionBase> apply(final List<FunctionBaseBean> tableBeans) {
        final List<FunctionBase> tables = new ArrayList<>();

        for (FunctionBaseBean tableBean : tableBeans) {
            FunctionBase table = processFunction(tableBean);
            tables.add(table);
        }

        return tables;
    }

    private FunctionBase processFunction(final FunctionBaseBean functionBean) {
        final String realSchema = stringTrimUtil.apply(functionBean.getSchema());
        final String schema = stringToLowerCaseUtil.apply(functionBean.getSchema());
        final String realName = stringTrimUtil.apply(functionBean.getName());
        final String name = stringToLowerCaseUtil.apply(functionBean.getName());
        final String realContent = stringTrimUtil.apply(functionBean.getContent());
        final String newContent = stringToContentUtil.apply(stringToSnakeCaseUtil.apply(functionBean.getContent()));
        final Boolean isFunction = stringToBooleanUtil.apply(functionBean.getIsfunction());
        final String newSchema = stringToSnakeCaseUtil.apply(functionBean.getSchema());
        final String newName = stringToSnakeCaseUtil.apply(functionBean.getName());
        final String returnType = stringToSnakeCaseUtil.apply(functionBean.getReturntype());

        loggerManager.debug("[DataBaseFunctionBaseMapper] Schema: " + realSchema);
        loggerManager.debug("[DataBaseFunctionBaseMapper] Name: " + realName);
        loggerManager.debug("[DataBaseFunctionBaseMapper] Content: " + realContent);
        loggerManager.debug("[DataBaseFunctionBaseMapper] Is Function: " + isFunction);
        loggerManager.debug("[DataBaseFunctionBaseMapper] Return Type: " + returnType);

        return new FunctionBase(realSchema, schema, realName, name, realContent, newContent, isFunction, newSchema, newName, returnType);
    }

}
