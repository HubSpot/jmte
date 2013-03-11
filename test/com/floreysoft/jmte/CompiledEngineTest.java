package com.floreysoft.jmte;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.junit.Test;

import com.floreysoft.jmte.template.Template;

public class CompiledEngineTest extends AbstractEngineTest {
	
	public static final String LONG_TEMPLATE = "SOME TEXT"
			+ "${if address='Filbert'}${address}${else}NIX${end}"
			+ "${foreach strings string}${if string='String2'}${string}${end}${end}"
			+ "${if bean.trueCond}${address}${else}NIX${end}"
			+ "${if bean.trueCondObj}${address}${else}NIX${end}"
			+ "${if map}${address}${else}NIX${end}"
			+ "MORE TEXT"
			+ "${if hugo}${address}${else}${if address}${address}${else}NIX${end}${end}"
			+ "${if nix}Something${if address}${address}${end}${end}"
			+ "${if something}${else}${if something}${else}Something${if address}${address}${end}${end}${end}"
			+ "${foreach list item}${foreach item.list item2}${if item}${item2.property1}${end}${end}\n${end}";
	
	public String getLongTemplate() {
		return LONG_TEMPLATE;
	}
	
	public String getLargeTemplateExpected() {
		return "SOME TEXT" + "Filbert" + "String2" + "Filbert"
				+ "Filbert" + "Filbert" + "MORE TEXT" + "Filbert" + "1.12.1\n"
				+ "1.12.1\n";
	}

	protected Engine newEngine() {
		Engine engine = new Engine();
		engine.setUseCompilation(true);
		return engine;
	}

	@Test
	public void compiledClassLoaders() throws Exception {
		String templateSource = "${address}";
		Engine engine1 = new Engine();
		engine1.setUseCompilation(true);
		Engine engine2 = new Engine();
		engine2.setUseCompilation(true);

		// each engine has a class loader of its own leading to two classes
		// having the same name
		Template template1 = engine1.getTemplate(templateSource);
		Template template2 = engine2.getTemplate(templateSource);
		assertEquals(template1.getClass().getName(), template2.getClass().getName());
		// sill, both classes are not the same
		assertNotSame(template1.getClass(), template2.getClass());
		// but, both still work
		String transformed1 = template1.transform(DEFAULT_MODEL, DEFAULT_LOCALE);
		String transformed2 = template2.transform(DEFAULT_MODEL, DEFAULT_LOCALE);
		// and give the same result
		assertEquals(transformed1, transformed2);
	}
}