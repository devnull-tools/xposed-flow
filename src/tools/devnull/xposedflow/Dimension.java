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

import android.content.res.XResources;
import android.util.TypedValue;

/**
 * @author Marcelo Guimarães
 */
public enum Dimension {

  /**
   * Raw Pixel
   */
  px(TypedValue.COMPLEX_UNIT_PX),
  /**
   * Device Independent Pixel
   */
  dip(TypedValue.COMPLEX_UNIT_DIP),
  /**
   * Scaled Pixel
   */
  sp(TypedValue.COMPLEX_UNIT_SP),
  /**
   * Points
   */
  pt(TypedValue.COMPLEX_UNIT_PT),
  /**
   * Inches
   */
  in(TypedValue.COMPLEX_UNIT_IN),
  /**
   * Millimeters
   */
  mm(TypedValue.COMPLEX_UNIT_MM);

  private final int unitType;

  Dimension(int unitType) {
    this.unitType = unitType;
  }

  public XResources.DimensionReplacement valueOf(float value) {
    return new XResources.DimensionReplacement(value, unitType);
  }

}
