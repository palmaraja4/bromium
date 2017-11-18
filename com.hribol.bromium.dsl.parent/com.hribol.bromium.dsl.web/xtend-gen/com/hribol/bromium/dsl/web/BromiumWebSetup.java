/**
 * generated by Xtext 2.13.0
 */
package com.hribol.bromium.dsl.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hribol.bromium.dsl.BromiumRuntimeModule;
import com.hribol.bromium.dsl.BromiumStandaloneSetup;
import com.hribol.bromium.dsl.ide.BromiumIdeModule;
import com.hribol.bromium.dsl.web.BromiumWebModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages in web applications.
 */
@SuppressWarnings("all")
public class BromiumWebSetup extends BromiumStandaloneSetup {
  @Override
  public Injector createInjector() {
    BromiumRuntimeModule _bromiumRuntimeModule = new BromiumRuntimeModule();
    BromiumIdeModule _bromiumIdeModule = new BromiumIdeModule();
    BromiumWebModule _bromiumWebModule = new BromiumWebModule();
    return Guice.createInjector(Modules2.mixin(_bromiumRuntimeModule, _bromiumIdeModule, _bromiumWebModule));
  }
}