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
import de.robv.android.xposed.XposedHelpers;

/**
 * An abstraction of a method hook that support defining an
 * {@link de.robv.android.xposed.XposedHelpers#setAdditionalInstanceField(Object, String, Object)
 * instance field} for activating it.
 *
 * @author Marcelo Guimarães
 */
public class MethodHook extends XC_MethodHook {

  private String activateProperty;

  public MethodHook() {
    super();
  }

  public MethodHook(int priority) {
    super(priority);
  }

  /**
   * Sets the boolean instance field name that should be {@code true} if this
   * hook should be activated.
   *
   * @param key the instance field name
   * @return a reference for this instance
   */
  public final MethodHook activeOn(String key) {
    this.activateProperty = key;
    return this;
  }

  @Override
  protected final void beforeHookedMethod(MethodHookParam param) throws Throwable {
    if (activateProperty == null ||
        (Boolean) XposedHelpers.getAdditionalInstanceField(this, activateProperty)) {
      doBefore(param);
    }
  }

  @Override
  protected final void afterHookedMethod(MethodHookParam param) throws Throwable {
    if (activateProperty == null ||
        (Boolean) XposedHelpers.getAdditionalInstanceField(this, activateProperty)) {
      doAfter(param);
    }
  }

  /**
   * Do stuff before calling the real method.
   */
  protected void doBefore(MethodHookParam param) throws Throwable {

  }

  /**
   * Do stuff after calling the real method.
   */
  protected void doAfter(MethodHookParam param) throws Throwable {

  }

}
