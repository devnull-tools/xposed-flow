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

import android.graphics.drawable.Drawable;

/**
 * A selector for replacing resources.
 *
 * @author Marcelo Guimarães
 */
public interface ResourceXposerSelector {

  /**
   * Sets the resource type to replace.
   *
   * @param typeName the type of the resource to replace
   * @return a reference to this object.
   */
  ResourceXposerSelector ofType(String typeName);

  /**
   * Replaces the resource with a new dimension. This method overrides the
   * {@link #ofType(String) resource type} by setting {@code dimen}.
   *
   * @param value the dimension value
   * @param unit  the dimension unit
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(float value, Dimension unit);

  /**
   * Replaces the resource with a new drawable. This method overrides the
   * {@link #ofType(String) resource type} by setting {@code drawable}.
   *
   * @param drawable the drawable to replace
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(Drawable drawable);

  /**
   * Replaces the resource with a new value. This method overrides the
   * {@link #ofType(String) resource type} by setting {@code string}.
   *
   * @param value the value to replace
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(String value);

  /**
   * Replaces the resource with a new value. This method overrides the
   * {@link #ofType(String) resource type} by setting {@code integer}.
   *
   * @param value the value to replace
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(int value);

  /**
   * Replaces the resource with a new value. This method overrides the
   * {@link #ofType(String) resource type} by setting {@code bool}.
   *
   * @param value the value to replace
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(boolean value);

  /**
   * Replaces the resource with the given object.
   *
   * @param replacement the object to replace
   * @return an instance of a ResourceXposer for replacing other resources.
   */
  ResourceXposer with(Object replacement);

}
