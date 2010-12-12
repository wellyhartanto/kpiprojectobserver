package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;

public class TestData {

    public TestData() {
	// TODO Auto-generated constructor stub
    }

    public static Application createTestData() {

	Application app = new Application();

	List<Package> packages = new ArrayList<Package>();

	for (int i = 0; i < 7; i++) {
	    Package p = new Package();
	    p.setName("sk.tuke.fei.kpi.package" + i);
	    List<Class> cl = new ArrayList<Class>();
	    for (int j = 0; j < 7; j++) {
		Class c = new Class();
		c.setName("class" + i + "." + j);

		List<Method> methods = new ArrayList<Method>();
		List<Field> fields = new ArrayList<Field>();

		for (int x = 0; x < 3; x++) {

		    Method m = new Method();
		    m.setName("method" + x);
		    methods.add(m);

		    Field f = new Field();
		    f.setName("field" + x);
		    fields.add(f);
		}
		c.setMethods(methods);
		c.setFields(fields);

		cl.add(c);
	    }
	    List<Interface> inf = new ArrayList<Interface>();
	    for (int j = 0; j < 5; j++) {
		Interface c = new Interface();
		c.setName("interface" + i + "." + j);

		List<Method> methods = new ArrayList<Method>();
		List<Field> fields = new ArrayList<Field>();

		for (int x = 0; x < 2; x++) {

		    Method m = new Method();
		    m.setName("method" + x);
		    methods.add(m);

		    Field f = new Field();
		    f.setName("field" + x);
		    fields.add(f);
		}
		c.setMethods(methods);
		c.setFields(fields);

		inf.add(c);
	    }

	    List<Enum> en = new ArrayList<Enum>();
	    for (int j = 0; j < 4; j++) {
		Enum c = new Enum();
		c.setName("enum" + i + "." + j);

		String[] s = { "value1", "value2" };

		c.setValues(s);

		en.add(c);
	    }

	    List<Package> pcg = new ArrayList<Package>();
	    for (int j = 0; j < 3; j++) {
		Package c = new Package();
		c.setName(p.getName() + "sub" + i + "." + j);
		pcg.add(c);

		List<Class> cl2 = new ArrayList<Class>();
		for (int z = 0; z < 3; z++) {
		    Class c2 = new Class();
		    c2.setName("class" + i + "." + z);

		    List<Method> methods = new ArrayList<Method>();
		    List<Field> fields = new ArrayList<Field>();

		    for (int x = 0; x < 3; x++) {

			Method m = new Method();
			m.setName("method" + x);
			methods.add(m);

			Field f = new Field();
			f.setName("field" + x);
			fields.add(f);
		    }
		    c2.setMethods(methods);
		    c2.setFields(fields);

		    cl2.add(c2);
		}
		List<Interface> inf2 = new ArrayList<Interface>();
		for (int z = 0; z < 2; z++) {
		    Interface c2 = new Interface();
		    c2.setName("interface" + i + "." + z);

		    List<Method> methods = new ArrayList<Method>();
		    List<Field> fields = new ArrayList<Field>();

		    for (int x = 0; x < 2; x++) {

			Method m = new Method();
			m.setName("method" + x);
			methods.add(m);

			Field f = new Field();
			f.setName("field" + x);
			fields.add(f);
		    }
		    c2.setMethods(methods);
		    c2.setFields(fields);
		    inf2.add(c2);
		}

		List<Enum> en2 = new ArrayList<Enum>();
		for (int z = 0; z < 1; z++) {
		    Enum c2 = new Enum();
		    c2.setName("enum" + i + "." + z);
		    String[] s = { "value1", "value2" };

		    c2.setValues(s);
		    en2.add(c2);
		}

		c.setClasses(cl2);
		c.setInterfaces(inf2);
		c.setEnums(en2);
		pcg.add(c);

	    }

	    p.setPackages(pcg);
	    p.setClasses(cl);
	    p.setInterfaces(inf);
	    p.setEnums(en);
	    packages.add(p);
	}

	app.setPackages(packages);
	app.setClasses(packages.get(0).getClasses());
	app.setEnums(packages.get(0).getEnums());
	app.setInterfaces(packages.get(0).getInterfaces());
	app.setFilename("defaultfile.owl");
	app.setName("MyExampleApplication");

	return app;

    }

    public static Application createTestData2() {

	Application app = new Application();

	List<Package> packages = new ArrayList<Package>();

	for (int i = 0; i < 7; i++) {
	    Package p = new Package();
	    p.setName("sk.tuke.fei.kpi.package" + i);
	    List<Class> cl = new ArrayList<Class>();
	    for (int j = 0; j < 7; j++) {
		Class c = new Class();
		c.setName("class" + i + "." + j);

		List<Method> methods = new ArrayList<Method>();
		List<Field> fields = new ArrayList<Field>();

		for (int x = 0; x < 3; x++) {

		    Method m = new Method();
		    m.setName("method" + x);
		    methods.add(m);

		    Field f = new Field();
		    f.setName("field" + x);
		    fields.add(f);
		}
		c.setMethods(methods);
		c.setFields(fields);

		cl.add(c);
	    }
	    List<Interface> inf = new ArrayList<Interface>();
	    for (int j = 0; j < 5; j++) {
		Interface c = new Interface();
		c.setName("interface" + i + "." + j);

		List<Method> methods = new ArrayList<Method>();
		List<Field> fields = new ArrayList<Field>();

		for (int x = 0; x < 2; x++) {

		    Method m = new Method();
		    m.setName("method" + x);
		    methods.add(m);

		    Field f = new Field();
		    f.setName("field" + x);
		    fields.add(f);
		}
		c.setMethods(methods);
		c.setFields(fields);

		inf.add(c);
	    }

	    List<Enum> en = new ArrayList<Enum>();
	    for (int j = 0; j < 4; j++) {
		Enum c = new Enum();
		c.setName("enum" + i + "." + j);

		String[] s = { "value1", "value2" };

		c.setValues(s);

		en.add(c);
	    }

	    List<Package> pcg = new ArrayList<Package>();
	    for (int j = 0; j < 3; j++) {
		Package c = new Package();
		c.setName(p.getName() + "sub" + i + "." + j);
		pcg.add(c);

		List<Class> cl2 = new ArrayList<Class>();
		for (int z = 0; z < 3; z++) {
		    Class c2 = new Class();
		    c2.setName("class" + i + "." + z);

		    List<Method> methods = new ArrayList<Method>();
		    List<Field> fields = new ArrayList<Field>();

		    for (int x = 0; x < 3; x++) {

			Method m = new Method();
			m.setName("method" + x);
			methods.add(m);

			Field f = new Field();
			f.setName("field" + x);
			fields.add(f);
		    }
		    c2.setMethods(methods);
		    c2.setFields(fields);

		    cl2.add(c2);
		}
		List<Interface> inf2 = new ArrayList<Interface>();
		for (int z = 0; z < 2; z++) {
		    Interface c2 = new Interface();
		    c2.setName("interface" + i + "." + z);

		    List<Method> methods = new ArrayList<Method>();
		    List<Field> fields = new ArrayList<Field>();

		    for (int x = 0; x < 2; x++) {

			Method m = new Method();
			m.setName("method" + x);
			methods.add(m);

			Field f = new Field();
			f.setName("field" + x);
			fields.add(f);
		    }
		    c2.setMethods(methods);
		    c2.setFields(fields);
		    inf2.add(c2);
		}

		List<Enum> en2 = new ArrayList<Enum>();
		for (int z = 0; z < 1; z++) {
		    Enum c2 = new Enum();
		    c2.setName("enum" + i + "." + z);
		    String[] s = { "value1", "value2" };

		    c2.setValues(s);
		    en2.add(c2);
		}

		c.setClasses(cl2);
		c.setInterfaces(inf2);
		c.setEnums(en2);
		pcg.add(c);

	    }

	    p.setPackages(pcg);
	    p.setClasses(cl);
	    p.setInterfaces(inf);
	    p.setEnums(en);
	    packages.add(p);
	}

	app.setPackages(packages);
	app.setClasses(packages.get(0).getClasses());
	app.setEnums(packages.get(0).getEnums());
	app.setInterfaces(packages.get(0).getInterfaces());
	app.setFilename("defaultfile.owl");
	app.setName("MyExampleApplication");

	return app;

    }
}
