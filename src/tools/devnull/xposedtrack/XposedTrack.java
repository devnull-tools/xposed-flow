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

package tools.devnull.xposedtrack;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Marcelo Guimarães
 */
public class XposedTrack implements Xposer {

  private final Class type;

  public XposedTrack(Class type) {
    this.type = type;
  }

  @Override
  public XposerSelector hook(final String methodName) {
    return new BaseXposerSelector() {

      @Override
      public Xposer with(XC_MethodHook methodHook) {
        XposedHelpers.findAndHookMethod(
            type, methodName, parametersAnd(methodHook)
        );
        return XposedTrack.this;
      }
    };
  }

  @Override
  public XposerSelector hookConstructor() {
    return new BaseXposerSelector() {

      @Override
      public Xposer with(XC_MethodHook methodHook) {
        XposedHelpers.findAndHookConstructor(
            type, parametersAnd(methodHook)
        );
        return XposedTrack.this;
      }
    };
  }

  public static Xposer xpose(Class type) {
    return new XposedTrack(type);
  }

  public static Xposer xpose(String className, ClassLoader classLoader) {
    return new XposedTrack(XposedHelpers.findClass(className, classLoader));
  }

  public static Xposer xpose(String className) {
    return new XposedTrack(XposedHelpers.findClass(className, null));
  }

  private abstract class BaseXposerSelector implements XposerSelector {

    private Object[] parameters = new Object[0];

    @Override
    public final XposerSelector thatTakes(Object... parameters) {
      this.parameters = parameters;
      return this;
    }

    protected Object[] parametersAnd(XC_MethodHook methodHook) {
      List<Object> list = new ArrayList(Arrays.asList(parameters));
      list.add(methodHook);
      return list.toArray();
    }

  }

}
