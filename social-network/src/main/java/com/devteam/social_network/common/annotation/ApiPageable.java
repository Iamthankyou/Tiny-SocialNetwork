package com.devteam.social_network.common.annotation;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(
        name = "page",
        dataType = "int",
        paramType = "query",
        value = "zero-based page index.",
        defaultValue = "0",
        example = "0"
        ),
        @ApiImplicitParam(
                name = "size",
                dataType = "int",
                paramType = "query",
                value = "the size of page to be returned",
                defaultValue = "10",
                example = "10"
        ),
        @ApiImplicitParam(
                name = "sort",
                allowMultiple = true,
                dataType = "string",
                paramType = "query",
                value = "sorting criteria in the format: property (,asc|desc)."
        )
})
public @interface ApiPageable {
}
