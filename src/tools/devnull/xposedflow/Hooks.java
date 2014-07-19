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

import de.robv.android.xposed.XC_MethodHook;
import tools.devnull.xposedflow.hooks.ArgumentReplacerHook;

/**
 * A set of method hooks for general use.
 *
 * @author Marcelo Guimarães
 */
public class Hooks {

  private Hooks() {

  }

  /**
   * Creates a new method hook that replaces the arguments before calling
   * the real method.
   *
   * @param newArgs the new arguments to use
   * @return a new instance of the method hook
   */
  public static XC_MethodHook replacingArgsWith(final Object... newArgs) {
    return new XC_MethodHook() {
      @Override
      protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.args = newArgs;
      }
    };
  }

  /**
   * Creates a new method hook that replaces the arguments at the given indexes
   * by a new value.
   */
  public static ArgumentReplacerHook.ValueSelector replacing(int index) {
    return new ArgumentReplacerHook().replace(index);
  }

  /**
   * Creates a new method hook that always returns the given value.
   * <p/>
   * This hook never lets the original method to execute.
   *
   * @param value  the value to return
   */
  public static XC_MethodHook returning(final Object value) {
    return new XC_MethodHook() {
      @Override
      protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.setResult(value);
      }
    };
  }

}
