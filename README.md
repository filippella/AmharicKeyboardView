# AmharicKeyboardView

Like/Star the project if you liked and used the code, make sure to follow me on git and subscribe on my youtube channel.

This project library helps in handling ethiopian keyboard input for a given edittext

 * Includes english keyboard
 * Includes symbols keyboard

Resolving artifacts using Gradle

```gradle
allprojects {
    repositories {
        jcenter()
        maven {
            url  "http://dl.bintray.com/filippella/maven"
        }
    }
}

```

Adding Gradle dependencies

```gradle
apply plugin: 'com.android....

android {
    compileSdkVersion XX
    buildToolsVersion "XX.X.X"

    defaultConfig {
        ....
    }
    buildTypes {
        ...
    }
}

dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.0'
    
    
    compile 'org.dalol.amharickeyboardlibrary:amharickeyboardlibrary:2.0.0@aar'
}

```

Usage inside an Actvity

```java
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.dalol.amharickeyboardlibrary.keyboard.manager.AmharicKeyboardManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AmharicKeyboardManager keyboardManager = new AmharicKeyboardManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edditText = (EditText) findViewById(R.id.inputTxt);
        keyboardManager.init(this, edditText, true);
        keyboardManager.setAnimationSpeed(250L);
    }

    @Override
    public void onBackPressed() {
        if(!keyboardManager.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

```
Usage inside a Fragment

```java
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.dalol.amharickeyboardlibrary.keyboard.manager.AmharicKeyboardManager;

public class MainFragment extends Fragment implements OnBackEventListener {

    private AmharicKeyboardManager keyboardManager = new AmharicKeyboardManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = createView();
        EditText edditText = (EditText) view.findViewById(R.id.inputTxt);
        keyboardManager.init(getActivity(), edditText, true);
        keyboardManager.setAnimationSpeed(250L);
        return view;
    }
    
    @Override
    public void onBackPressed() {
        if(!keyboardManager.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

```

License
-------

    Copyright 2017 Filippo Engidashet.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
