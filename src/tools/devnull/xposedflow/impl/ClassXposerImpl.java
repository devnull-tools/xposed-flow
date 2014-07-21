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

package tools.devnull.xposedflow.impl;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import tools.devnull.xposedflow.ClassXposer;
import tools.devnull.xposedflow.ClassXposerSelector;
import tools.devnull.xposedflow.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marcelo Guimarães
 */
public class ClassXposerImpl implements ClassXposer {

  private final Class type;
  private ExceptionHandler handler = new DefaultExceptionHandler();

  /**
   * Creates a new flow for the given class
   *
   * @param type the class to hook methods or constructors
   */
  public ClassXposerImpl(Class type) {
    this.type = type;
  }

  @Override
  public ClassXposerSelector hook(final String methodName) {
    return new BaseClassXposerSelector() {

      @Override
      public ClassXposer with(XC_MethodHook methodHook) {
        try {
          XposedHelpers.findAndHookMethod(
              type, methodName, parametersAnd(methodHook)
          );
        } catch (Throwable t) {
          handler.handleException(t);
        }
        return ClassXposerImpl.this;
      }
    };
  }

  @Override
  public ClassXposerSelector hookConstructor() {
    return new BaseClassXposerSelector() {

      @Override
      public ClassXposer with(XC_MethodHook methodHook) {
        try {
          XposedHelpers.findAndHookConstructor(
              type, parametersAnd(methodHook)
          );
        } catch (Throwable t) {
          handler.handleException(t);
        }
        return ClassXposerImpl.this;
      }
    };
  }

  @Override
  public ClassXposer onError(ExceptionHandler handler) {
    this.handler = handler;
    return this;
  }

  private abstract class BaseClassXposerSelector implements ClassXposerSelector {

    private Object[] parameters = new Object[0];

    @Override
    public final ClassXposerSelector thatTakes(Object... parameters) {
      this.parameters = parameters;
      return this;
    }

    @Override
    public ClassXposer by(XC_MethodHook methodHook) {
      return with(methodHook);
    }

    /**
     * Returns the parameters plus the method hook for passing them to the
     * helper methods in XposedHelpers.
     *
     * @param methodHook the method hook
     * @return the parameters that should be passed to the helper methods.
     */
    protected Object[] parametersAnd(XC_MethodHook methodHook) {
      List<Object> list = new ArrayList<Object>(Arrays.asList(parameters));
      list.add(methodHook);
      return list.toArray();
    }

  }

  private static class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Throwable throwable) {
      XposedBridge.log(throwable);
    }
  }

}
