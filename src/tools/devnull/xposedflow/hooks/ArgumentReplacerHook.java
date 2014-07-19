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

package tools.devnull.xposedflow.hooks;

import de.robv.android.xposed.XC_MethodHook;

import java.util.HashMap;
import java.util.Map;

/**
 * A method hook that replaces arguments with new values.
 *
 * @author Marcelo Guimarães
 */
public class ArgumentReplacerHook extends XC_MethodHook {

  private Map<Integer, Object> arguments = new HashMap<Integer, Object>();

  /**
   * Replaces the parameter at {@code index} position with a new value.
   *
   * @param index the parameter index
   * @return a component for selecting the new value
   */
  public ValueSelector replace(final int index) {
    return new ValueSelector() {
      @Override
      public ArgumentReplacerHook with(Object value) {
        arguments.put(index, value);
        return ArgumentReplacerHook.this;
      }
    };
  }

  /**
   * @see #replace(int)
   */
  public ValueSelector replacing(int index) {
    return replace(index);
  }

  @Override
  protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
    for (Map.Entry<Integer, Object> arg : arguments.entrySet()) {
      param.args[arg.getKey()] = arg.getValue();
    }
  }

  /**
   * Interface for defining values.
   */
  public interface ValueSelector {

    /**
     * Defines the value that will replace the selected argument.
     *
     * @param value the value to replace the argument
     * @return a reference for the method hook so you can select more indexes
     */
    ArgumentReplacerHook with(Object value);
  }

}
