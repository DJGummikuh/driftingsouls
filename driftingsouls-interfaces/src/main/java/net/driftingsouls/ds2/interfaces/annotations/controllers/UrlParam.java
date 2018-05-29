package net.driftingsouls.ds2.interfaces.annotations.controllers;

import java.lang.annotation.*;

/**
 * Ein URL-Parameter.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface UrlParam {
	/**
	 * Der Name des Parameters.
	 */
	String name() default "";
}
