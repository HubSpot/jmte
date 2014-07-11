package com.floreysoft.jmte.realLife;

import com.floreysoft.jmte.NamedRenderer;
import com.floreysoft.jmte.RenderFormatInfo;

import java.util.Locale;

/**
 * @author marcelomorales.name@gmail.com
 *         Date: 7/11/14
 */
public class LineRenderer implements NamedRenderer {

    @Override
    public String render(Object o, String format, Locale locale) {
        return Integer.toString(((Integer) o).intValue() + 1);
    }

    @Override
    public String getName() {
        return "lineNumber";
    }

    @Override
    public RenderFormatInfo getFormatInfo() {
        return null;
    }

    @Override
    public Class<?>[] getSupportedClasses() {
        return new Class<?>[] {Integer.class};
    }
}
