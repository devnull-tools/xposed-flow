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

import android.content.res.XResources;
import android.graphics.drawable.Drawable;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import tools.devnull.xposedflow.*;

/**
 * @author Marcelo Guimarães
 */
public class ResourceXposerImpl implements ResourceXposer {

  private final XC_InitPackageResources.InitPackageResourcesParam resparam;

  public ResourceXposerImpl(XC_InitPackageResources.InitPackageResourcesParam resparam) {
    this.resparam = resparam;
  }

  @Override
  public ResourceXposerSelector replace(String... resourceNames) {
    return new ResourceSelector(resourceNames);
  }

  @Override
  public ResourceXposer at(String packageName) {
    if (resparam.packageName.equals(packageName)) {
      return this;
    }
    return XposedFlowHelper.fakeResourceXposer(this);
  }

  @Override
  public Selector<XC_LayoutInflated, ResourceXposer> hookLayout(final String name) {
    return new Selector<XC_LayoutInflated, ResourceXposer>() {
      @Override
      public ResourceXposer with(XC_LayoutInflated xc_layoutInflated) {
        resparam.res.hookLayout(resparam.packageName, "layout", name, xc_layoutInflated);
        return ResourceXposerImpl.this;
      }

      @Override
      public ResourceXposer by(XC_LayoutInflated xc_layoutInflated) {
        return with(xc_layoutInflated);
      }
    };
  }

  private class ResourceSelector implements ResourceXposerSelector {

    private final String[] resourceNames;
    private String typeName;

    private ResourceSelector(String[] resourceNames) {
      this.resourceNames = resourceNames;
    }

    @Override
    public ResourceXposerSelector ofType(String typeName) {
      this.typeName = typeName;
      return this;
    }

    @Override
    public ResourceXposer with(float value, Dimension unit) {
      return ofType("dimen").with(unit.valueOf(value));
    }

    @Override
    public ResourceXposer with(final Drawable color) {
      return ofType("drawable").with(new DrawableLoader(color));
    }

    @Override
    public ResourceXposer with(String value) {
      return ofType("string").with((Object) value);
    }

    @Override
    public ResourceXposer with(int value) {
      return ofType("integer").with(Integer.valueOf(value));
    }

    @Override
    public ResourceXposer with(boolean value) {
      return ofType("bool").with(Boolean.valueOf(value));
    }

    @Override
    public ResourceXposer with(Object replacement) {
      for (String resourceName : resourceNames) {
        resparam.res.setReplacement(
            resparam.packageName, typeName, resourceName, replacement
        );
      }
      return ResourceXposerImpl.this;
    }
  }

  private static class DrawableLoader extends XResources.DrawableLoader {

    private final Drawable drawable;

    private DrawableLoader(Drawable drawable) {
      this.drawable = drawable;
    }

    @Override
    public Drawable newDrawable(XResources res, int id) throws Throwable {
      return drawable;
    }
  }

}
