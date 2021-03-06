/*
 * Copyright (c) 2013, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.io.IOException;

/**
 * @test
 * @bug 6557093
 * @summary Check SSL config file permission for out-of-the-box management
 * @author Taras Ledkov
 *
 * @library /test/lib
 *
 * @build jdk.test.lib.Platform AbstractFilePermissionTest Dummy
 * @run main/timeout=300 PasswordFilePermissionTest
 */
public class PasswordFilePermissionTest extends AbstractFilePermissionTest {

    private PasswordFilePermissionTest() {
        super("jmxremote.passwordconfig");
    }

    public void testSetup() throws IOException {
        createFile(mgmt,
                "# management.properties",
                "com.sun.management.jmxremote.ssl=false",
                "com.sun.management.jmxremote.password.file=" + file2PermissionTest.toFile().getAbsolutePath());

        createFile(file2PermissionTest,
                "# jmxremote.password\n");
    }

    public static void main(String[] args) throws Exception {
        PasswordFilePermissionTest test = new PasswordFilePermissionTest();

        test.runTest(args);
    }

}
