/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.render.annotation;

import br.com.prototype.advancedprinter.event.EventTypeEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;



/**
 *
 * @author Ronaldo Totini
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventRenderType {
    EventTypeEnum event() default EventTypeEnum.NONE;
}
