package com.hribol.bromium.replay.parsers;

import com.hribol.bromium.replay.actions.ClickCssSelector;
import com.hribol.bromium.replay.actions.WebDriverAction;

import java.util.Map;

import static com.hribol.bromium.replay.config.utils.Constants.CSS_SELECTOR;
import static com.hribol.bromium.replay.config.utils.Constants.EVENT;

/**
 * Parser for {@link ClickCssSelector}
 */
public class ClickCssSelectorParser implements WebDriverActionParameterParser {


    @Override
    public WebDriverAction create(Map<String, String> parameters, boolean expectHttpRequest) {
        String cssSelector = parameters.get(CSS_SELECTOR);
        String eventName = parameters.get(EVENT);
        return new ClickCssSelector(cssSelector, eventName, expectHttpRequest);
    }
}