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

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Helpers to improve code readability
 *
 * @author Marcelo Guimarães
 */
public class XposedFlowHelper {

  /**
   * Returns a new ColorDrawable for the given color
   *
   * @param color the color
   * @return a new ColorDrawable that represents the given color.
   */
  public static Drawable color(int color) {
    return new ColorDrawable(color);
  }

  public static ResourceXposer fakeResourceXposer(final ResourceXposer realResourceXposer) {
    return new ResourceXposer() {
      @Override
      public ResourceXposer at(String packageName) {
        return this;
      }

      @Override
      public ResourceXposerSelector replace(String... resourceNames) {
        return new ResourceXposerSelector() {
          @Override
          public ResourceXposerSelector ofType(String typeName) {
            return this;
          }

          @Override
          public ResourceXposer with(float value, Dimension unit) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer with(Drawable drawable) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer with(String value) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer with(int value) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer with(boolean value) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer with(Object replacement) {
            return realResourceXposer;
          }
        };
      }

      @Override
      public Selector<XC_LayoutInflated, ResourceXposer> hookLayout(String name) {
        return new Selector<XC_LayoutInflated, ResourceXposer>() {
          @Override
          public ResourceXposer with(XC_LayoutInflated xc_layoutInflated) {
            return realResourceXposer;
          }

          @Override
          public ResourceXposer by(XC_LayoutInflated xc_layoutInflated) {
            return realResourceXposer;
          }
        };
      }
    };
  }
}
