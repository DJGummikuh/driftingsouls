/*
 *	Drifting Souls 2
 *	Copyright (c) 2007 Christopher Jung
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.driftingsouls.ds2.server.scripting.roles;

import net.driftingsouls.ds2.interfaces.annotations.roles.Attribute;
import net.driftingsouls.ds2.server.scripting.ScriptParserContext;
import net.driftingsouls.ds2.server.scripting.roles.interpreter.Interpreter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Testet das javax.script-Interface fuer das Rollensystem
 *
 * @author Christopher Jung
 */
public class RoleScriptEngineTest {
    /**
     * Fuegt die notwendigen Rollen dem Interpreter hinzu
     */
    @Before
    public void setUpRoles() {
        Interpreter.addRole("DummyRole", DummyRole.class);
    }

    /**
     * Setzt das executed-Flag der Dummy-Rolle zurueck
     */
    @Before
    public void setUp() {
        DummyRole.executed = false;
    }

    /**
     * Entfernt alle registrierten Rollen aus dem Interpreter
     */
    @After
    public void tearDown() {
        Interpreter.cleanUpRoles();
    }

    /**
     * Testet das Ausfuehren einer einfachen Rollendefinition
     *
     * @throws ScriptException
     */
    @Test
    public void testSimpleRole() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("DSRoles");

        assertThat(engine, not(nullValue()));
        assertThat(engine, instanceOf(RoleEngine.class));

        engine.setContext(new ScriptParserContext());

        final String roleDef = "role: DummyRole\n" +
                "Attribute2: 5\n" +
                "Attribute1: \"Test\\\"0123\\\\Test\"";

        engine.eval(roleDef);

        assertThat(DummyRole.executed, is(true));
    }

    /**
     * Dummy-Rolle fuer Tests
     */
    public static class DummyRole implements Role {
        protected static boolean executed = false;
        @Attribute("Attribute1")
        protected String attribute1;
        @Attribute("Attribute2")
        protected Long attribute2;
        @Attribute("NotAvailable")
        protected Long attribute3 = 345L;
        protected String noAttribute = "notSet";

        @Override
        public void execute(ScriptContext context) {
            assertThat(executed, is(false));

            executed = true;

            assertThat(attribute1, is("Test\"0123\\Test"));
            assertThat(attribute2, is(Long.valueOf(5)));
            assertThat(noAttribute, is("notSet"));
        }
    }
}
