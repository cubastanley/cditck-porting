/*
 * Copyright (c) 2013, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, 2020 Payara Foundation and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.jboss.weld.tck.glassfish;

import org.jboss.arquillian.container.spi.client.container.DeploymentExceptionTransformer;
import org.jboss.arquillian.core.spi.LoadableExtension;
//import org.jboss.as.arquillian.container.ExceptionTransformer;

/**
 * Registers the exception transformer to properly identify deployment failures.
 *
 * @author J J Snyder (j.j.snyder@oracle.com)
 */
public class GlassFishExtension implements LoadableExtension {

    private static final String PAYARA_CLIENTUTILS_CLASS = "fish.payara.arquillian.container.payara.clientutils.PayaraClientUtil";
   
    public void register(ExtensionBuilder builder) {

        if (Validate.classExists(PAYARA_CLIENTUTILS_CLASS)) {
            builder.service(DeploymentExceptionTransformer.class, GlassFishDeploymentExceptionTransformer.class);
//            builder.service(ExceptionTransformer.class, GlassFishDeploymentExceptionTransformer.class);
            // Override the default NOOP exception transformer
//            builder.override(DeploymentExceptionTransformer.class,
//                             ExceptionTransformer.class,
//                             GlassFishDeploymentExceptionTransformer.class);
            // Observe container start and check EE resources
            builder.observer(GlassFishResourceManager.class);
        }
    }

}
