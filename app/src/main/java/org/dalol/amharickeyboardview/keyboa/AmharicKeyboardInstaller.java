/*
 * Copyright (c) 2016 Filippo Engidashet
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

package org.dalol.amharickeyboardview.keyboa;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.dalol.amharickeyboardview.adapter.ActivityLiceCycleAdapter;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class AmharicKeyboardInstaller {

    private final Application mApplication;
    private AmharicKeyboardManager mKeyboardManager;

    private AmharicKeyboardInstaller(Application application) {
        mApplication = application;
        mKeyboardManager = new AmharicKeyboardManager();
        initializeCallback(application);
    }

    private void initializeCallback(Application application) {
        application.registerActivityLifecycleCallbacks(new ActivityLiceCycleAdapter() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                mKeyboardManager.handleActivity(activity);
            }
        });
    }
}
