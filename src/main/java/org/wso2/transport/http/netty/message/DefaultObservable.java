/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.transport.http.netty.message;

import io.netty.handler.codec.http.HttpContent;

/**
 * Default implementation of the message observer.
 */
public class DefaultObservable implements Observable {

    private Listener listener;

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * This method should only be called for HTTP/1.1.
     */
    @Override
    public void removeListener() {
            listener = null;
    }

    @Override
    public void notifyAddListener(HttpContent httpContent) {
        if (listener != null) {
            listener.onAdd(httpContent);
        }
    }

    @Override
    public void notifyGetListener(HttpContent httpContent) {
        if (listener != null) {
            listener.onRemove(httpContent);
        }
    }

    @Override
    public void notifyReadInterest() {
        if (listener != null) {
            listener.resumeReadInterest();
        }
    }
}
