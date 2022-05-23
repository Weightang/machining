package com.sanyao.machin

import android.content.pm.PackageManager

object SystemServices {
  val packageManager: PackageManager by lazy { MachiningApp.CONTEXT.packageManager }
}