/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.calculator.helpers;

import org.opendolphin.core.comm.Codec;
import org.opendolphin.core.server.ServerConnector;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerModelStore;

import javax.inject.Inject;
import javax.inject.Provider;

public class ServerDolphinProvider implements Provider<ServerDolphin> {
    @Inject
    private ServerModelStore serverModelStore;

    @Inject
    private ServerConnector serverConnector;

    @Inject
    private Codec codec;

    @Override
    public ServerDolphin get() {
        serverConnector.setCodec(codec);
        serverConnector.setServerModelStore(serverModelStore);
        ServerDolphin serverDolphin = new ServerDolphin(serverModelStore, serverConnector);
        serverDolphin.registerDefaultActions();
        return serverDolphin;
    }
}
