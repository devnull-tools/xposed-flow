/*
 * The MIT License
 *
 * Copyright (c) 2014 Marcelo Guimarães <ataxexe@gmail.com>
 *
 * ----------------------------------------------------------------------
 * Permission  is hereby granted, free of charge, to any person obtaining
 * a  copy  of  this  software  and  associated  documentation files (the
 * "Software"),  to  deal  in the Software without restriction, including
 * without  limitation  the  rights to use, copy, modify, merge, publish,
 * distribute,  sublicense,  and/or  sell  copies of the Software, and to
 * permit  persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this  permission  notice  shall be
 * included  in  all  copies  or  substantial  portions  of the Software.
 *                        -----------------------
 * THE  SOFTWARE  IS  PROVIDED  "AS  IS",  WITHOUT  WARRANTY OF ANY KIND,
 * EXPRESS  OR  IMPLIED,  INCLUDING  BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN  NO  EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM,  DAMAGES  OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT  OR  OTHERWISE,  ARISING  FROM,  OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE   OR   THE   USE   OR   OTHER   DEALINGS  IN  THE  SOFTWARE.
 */

package tools.devnull.xposedflow;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import tools.devnull.xposedflow.impl.ClassXposerImpl;
import tools.devnull.xposedflow.impl.ResourceXposerImpl;

/**
 * @author Marcelo Guimarães
 */
public class XposedFlow {

  /**
   * Starts the fluent interface for the given type.
   *
   * @param type the class to process hooks
   */
  public static ClassXposer xpose(Class type) {
    return new ClassXposerImpl(type);
  }

  /**
   * Starts the fluent interface for the given type.
   *
   * @param className   the name of the class to process hooks
   * @param classLoader the ClassLoader to load the class
   */
  public static ClassXposer xpose(String className, ClassLoader classLoader) {
    return new ClassXposerImpl(XposedHelpers.findClass(className, classLoader));
  }

  /**
   * Starts the fluent interface for the given type.
   *
   * @param className the name of the class to process hooks
   */
  public static ClassXposer xpose(String className) {
    return new ClassXposerImpl(XposedHelpers.findClass(className, null));
  }

  /**
   * Starts the fluent interface for the given InitPackageResourcesParam.
   */
  public static ResourceXposer xpose(XC_InitPackageResources.InitPackageResourcesParam resparams) {
    return new ResourceXposerImpl(resparams);
  }

}
