package com.hribol.bromium.common.record;

import com.hribol.bromium.core.TestScenarioSteps;
import com.hribol.bromium.core.suppliers.VisibleWebDriverSupplier;
import com.hribol.bromium.core.utils.JavascriptInjector;
import com.hribol.bromium.record.RecordRequestFilter;
import com.hribol.bromium.record.RecordResponseFilter;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.filters.ResponseFilter;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by hvrigazov on 09.03.17.
 */
public abstract class RecordBrowserBase {

    private RecordManager recordManager;
    private String pathToDriverExecutable;
    private String jsInjectionCode;
    private int timeout;
    private final String baseUrl;

    public RecordBrowserBase(String pathToDriverExecutable,
                             JavascriptInjector javascriptInjector,
                             int timeout,
                             String baseUrl) throws IOException {
        this.pathToDriverExecutable = pathToDriverExecutable;
        this.jsInjectionCode = javascriptInjector.getInjectionCode();
        this.timeout = timeout;
        this.baseUrl = baseUrl;
    }

    public abstract String getSystemProperty();
    public abstract VisibleWebDriverSupplier getVisibleWebDriverSupplier();

    private ResponseFilter responseFilter;
    private RecordRequestFilter recordRequestFilter;
    private ProxyDriverIntegrator proxyDriverIntegrator;

    public void record() throws IOException, InterruptedException, URISyntaxException {
        System.setProperty(getSystemProperty(), pathToDriverExecutable);
        URI uri = URI.create(baseUrl);
        this.responseFilter = new RecordResponseFilter(uri, jsInjectionCode);
        this.recordRequestFilter = new RecordRequestFilter();
        this.proxyDriverIntegrator = new ProxyDriverIntegrator(recordRequestFilter, responseFilter, getVisibleWebDriverSupplier(), timeout);
        WebDriver driver = proxyDriverIntegrator.getDriver();
        BrowserMobProxy proxy = proxyDriverIntegrator.getProxy();
        this.recordManager = new RecordManager(driver, proxy);
        recordManager.prepareRecord();
        recordManager.open(baseUrl);
    }

    public TestScenarioSteps getTestCaseSteps() {
        return recordRequestFilter.getApplicationSpecificActionList();
    }

    public void cleanUp() {
        recordManager.cleanUpRecord();
    }
}
