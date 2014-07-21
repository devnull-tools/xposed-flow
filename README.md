Xposed Flow
===========

A fluent interface for using XposedBridge.

## Method hooks

To hook a method using the fluent interface, use the method `xpose`.

~~~
xpose("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader)
  .hook("updateClock")
  .with(new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        TextView tv = (TextView) param.thisObject;
        tv.setTextColor(Color.RED);
    }
});
~~~

The code can be even more elegant if we extract the `XC_MethodHook` that changes
the color to a method:

~~~
xpose("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader)
  .hook("updateClock")
  .by(changingTextTo(Color.RED));
~~~

This will bring more readability to your code.

## Replacing Resources

To replace resources, just "xpose" the `InitPackageResourcesParam`.

~~~
xpose(resparam)
  .at("com.android.systemui")
  .replace("config_maxLevelOfSignalStrengthIndicator")
  .with(6);
}
~~~

Note that the resource type only needs to be specified if the replacement is not
one of the supported types (currently `integer`, `bool`, `drawable` and
`dimension`).

