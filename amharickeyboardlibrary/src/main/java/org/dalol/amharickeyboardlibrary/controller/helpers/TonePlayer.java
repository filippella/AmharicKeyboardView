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

package org.dalol.amharickeyboardlibrary.controller.helpers;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/27/2016
 */
public class TonePlayer {

    private static TonePlayer INSTANCE = new TonePlayer();

    private SoundPool soundPool;
    private AudioManager audioManager;
    private Context context;
    private float actVolume;
    private float maxVolume;
    private float volume;
    private int counter;
    private boolean loaded;
    private int soundID;
    private boolean plays;
    private int filename;

    private TonePlayer() {
    }

    public static TonePlayer newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TonePlayer();
        }
        return INSTANCE;
    }

//    public void loadSound(Context context, int filename) {
//        this.context = context;
//        this.filename = filename;
//        int soundID = 0;
//        if (soundPool == null) {
//            soundPool = buildSoundPool();
//        }
//        try {
//            soundID = soundPool.load(context, filename, 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void loadSound(Context context, int filename) {
        this.context = context;
        this.filename = filename;

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        Activity activity = (Activity) context;
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        counter = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build();
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    loaded = true;
                }
            });
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    loaded = true;
                }
            });
        }

        soundID = soundPool.load(context, filename, 1);
    }

    public void playSound() {
        // Is the sound loaded does it already play?
        if (loaded && !plays && soundPool != null) {
            soundPool.play(soundID, maxVolume, maxVolume, 1, 0, 1f);
            counter = counter++;
            //plays = true;
        } else if (!loaded || soundPool == null) {
            loadSound(context, filename);
        }
    }

    public void stopSound() {
        if (plays) {
            soundPool.stop(soundID);
            plays = false;
        }
    }

//    @SuppressWarnings("deprecation")
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private SoundPool buildSoundPool() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_GAME)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .build();
//
//            soundPool = new SoundPool.Builder()
//                    .setMaxStreams(25)
//                    .setAudioAttributes(audioAttributes)
//                    .build();
//            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//                @Override
//                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                    loaded = true;
//                }
//            });
//        } else {
//            buildBeforeAPI21();
//        }
//        return soundPool;
//    }

    public void cleanup() {
        soundPool = null;
        audioManager = null;
        context = null;
    }
}
