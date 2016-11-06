package org.dalol.amharickeyboardlibrary.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.dalol.amharickeyboardlibrary.R;
import org.dalol.amharickeyboardlibrary.base.BaseKeyboardLayoutManager;
import org.dalol.amharickeyboardlibrary.controller.helpers.AmharicKeyboardProperty;
import org.dalol.amharickeyboardlibrary.controller.helpers.MediaPlayerManager;
import org.dalol.amharickeyboardlibrary.controller.helpers.TonePlayer;
import org.dalol.amharickeyboardlibrary.model.keyinfo.IKeyTypography;
import org.dalol.amharickeyboardlibrary.model.callback.OnKeyClickListener;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.TableKeyboardManager;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.KeyButton;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.Row;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public class AmharicKeyboardView extends RelativeLayout {

    private static final String TAG = AmharicKeyboardView.class.getSimpleName();
    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean showing;
    private SoundPool soundPool;

    private OnKeyClickListener mKeyClickListener;

    private char[] mAmharicChars = {'\u134A', '\u120A', '\u1356'};

    private AmharicKeyboardProperty mKeyboardProperty;
    private boolean mKeyboardReady;
    private int soundID;
    private boolean loaded;
    private int currentVolume;
    private TonePlayer tonePlayer;
    private MediaPlayerManager playerManager;
    private BaseKeyboardLayoutManager mKeyboardManager;

    public AmharicKeyboardView(Context context) {
        this(context, null, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    public void setKeyboardManager(BaseKeyboardLayoutManager manager) {
        mKeyboardManager = manager;
    }

    private void createKeyboard() {
        removeAllViews();

        Toast.makeText(getContext(), "Configuration -> " + mKeyboardProperty.getConfiguration(), Toast.LENGTH_SHORT).show();

        TableKeyboardManager keyboardManager = (TableKeyboardManager) mKeyboardManager;

        int ROW = keyboardManager.getRowsCount();
        int COLS = keyboardManager.getColsCount();



        int keyWidth = Math.round(getWidth() / COLS);
        int keyHeight = Math.round(getHeight() / ROW);

        int size = getResources().getDimensionPixelOffset(R.dimen.key_padding_size);

        List<Row> rows = keyboardManager.getRows();

        int rowSize = rows.size();
        boolean tableTruthGrid[][] = new boolean[ROW][COLS];

        for (int i = 0; i < rowSize; i++) {
            Row row = rows.get(i);
            List<KeyButton> buttonList = row.getKeyButtons();
            for (int j = 0; j < buttonList.size(); j++) {

                boolean b = tableTruthGrid[i][j];

                KeyButton keyButton = buttonList.get(j);

                TextView child = createChild(size);

                FrameLayout childContainer = new FrameLayout(getContext());


                int keyWidthCalc = keyWidth * keyButton.getColumnCount();
                int keyHeightCalc = keyHeight * keyButton.getRowCount();

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(keyWidthCalc, keyHeightCalc);
                int left = keyButton.getLeft();


                Log.d(TAG, String.format("filippo-boolean[%d][%d][%b]", i, j, b) + " Left -> " + left);

                params.leftMargin = left * keyWidth;
                params.topMargin = i * keyHeightCalc;
                Random random = new Random();
                childContainer.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                childContainer.setLayoutParams(params);
                childContainer.setPadding(size, size, size, size);
                childContainer.addView(child);
                addView(childContainer);

            }
        }


//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j < COLS; j++) {
//
//
//
//                TextView child = new TextView(getContext());
//                child.setText("x");
//                child.setTextSize(16f);
//                child.setGravity(Gravity.CENTER);
//                child.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
//                FrameLayout.LayoutParams childParams =
//                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//                childParams.gravity = Gravity.CENTER;
//                //child.measure(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//                child.setLayoutParams(childParams);
//                child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
//                int size = getResources().getDimensionPixelOffset(R.dimen.key_padding_size);
//                child.setPadding(size, size, size, size);
//                child.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "Clicking on the key!", Toast.LENGTH_SHORT).show();
////                        if (mKeyClickListener != null) {
////                            playMusic();
////                            Editable editable = mKeyClickListener.getComponent();
////                            if (editable != null && editable.length() > 0) {
////                                int start = mKeyClickListener.getSelectionStart();
////                                if (start == -1) return;
////                                editable.delete(start - 1, start);
////                            }
////                        }
//                    }
//                });
//
//                FrameLayout childContainer = new FrameLayout(getContext());
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(keyWidth, keyHeight);
//                params.leftMargin = j * keyWidth;
//                params.topMargin = i * keyHeight;
//                Random random = new Random();
//                //childContainer.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//                childContainer.setLayoutParams(params);
//                childContainer.setPadding(size, size, size, size);
//                childContainer.addView(child);
//                addView(childContainer);
//            }
//        }
    }

    private TextView createChild(int size) {
        TextView child = new TextView(getContext());
        child.setText("x");
        child.setTextSize(16f);
        child.setGravity(Gravity.CENTER);
        child.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        FrameLayout.LayoutParams childParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        childParams.gravity = Gravity.CENTER;
        //child.measure(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        child.setLayoutParams(childParams);
        child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
        child.setPadding(size, size, size, size);
        return child;
    }

    /**
     * This method is used to setup the various object configurations
     *
     * @param context can be also referenced through {@link #getContext()}
     */
    private void initialize(Context context) {
        verifyInitMode();
        tonePlayer = TonePlayer.newInstance();
        tonePlayer.loadSound(context, R.raw.effect_tick);

//        AudioManager audio = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//        currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        mKeyboardProperty = new AmharicKeyboardProperty(context);
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mRootView = (ViewGroup) mWindow.getDecorView().findViewById(android.R.id.content);
        //setBackgroundColor(Color.parseColor("#3b494c"));
        //setBackgroundColor(Color.parseColor("#474745"));
//        setBackgroundColor(Color.parseColor("#636363"));
//        setBackgroundColor(Color.parseColor("#44546A"));
//        setBackgroundColor(Color.parseColor("#526A76"));
        setBackgroundColor(Color.parseColor("#3299FF"));
//        setBackgroundColor(Color.parseColor("#666666"));
        //setBackgroundColor(Color.parseColor("#DADBE0"));

        initializeSoundPlayer();

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeyClickListener != null) {
                    //playMusic();
                    EditText editText = mKeyClickListener.getComponent();
                    if (editText != null) {
                        Editable editableText = editText.getText();
                        int start = editText.getSelectionStart();
                        if (start == -1) return;
                        editableText.insert(start, Character.toString(mAmharicChars[new Random().nextInt(mAmharicChars.length)]));
                        mKeyClickListener.onTextChanged(editableText.toString());
                    }
                }
            }
        });

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }


                    createKeyboard();

                    int width = getWidth();
                    int height = getHeight();
                    if(width > 0 && height > 0) {
                        createKeyboard(width, height);
                    }
                    //createKeyboard("Global listener");
                }
            }
        });

//        Button child = new Button(getContext());
//        child.setText("X");
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
//        params.leftMargin = 0;
//        params.topMargin = 0;
//        child.setLayoutParams(params);
//        child.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mKeyClickListener != null) {
//                    playMusic();
//                    Editable editable = mKeyClickListener.getComponent();
//                    if (editable != null && editable.length() > 0) {
//                        int start = mKeyClickListener.getSelectionStart();
//                        if(start == -1) return;
//                        editable.delete(start - 1, start);
//                    }
//                }
//            }
//        });
//        addView(child, params);
//
//        Button child2 = new Button(getContext());
//        child2.setText("M");
//        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(150, 150);
//        params2.leftMargin = 150;
//        params2.topMargin = 0;
//        //child2.setLayoutParams(params2);
//
//        child2.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playerManager = new MediaPlayerManager();
//                playerManager.init("http://www.orthodoxmezmur.com/audio/Mindaye%20Berhanu%20Adagne%20Geta.mp3");
//            }
//        });
//
//        addView(child2, params2);
//
//        Button child3 = new Button(getContext());
//        child3.setText("C");
//        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(150, 150);
//        params3.leftMargin = 300;
//        params3.topMargin = 150;
//        //child3.setLayoutParams(params2);
//
//        child3.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (playerManager != null) {
//                    playerManager.stop();
//                }
//            }
//        });
//
//        addView(child3, params3);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //createKeyboard("onLayout");
    }

    private void playMusic() {
        tonePlayer.playSound();
//        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//        float actualVolume = (float) audioManager
//                .getStreamVolume(AudioManager.STREAM_MUSIC);
//        float maxVolume = (float) audioManager
//                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        float volume = actualVolume / maxVolume;
//        // Is the sound loaded already?
//        if (loaded) {
//            soundPool.play(soundID, maxVolume, maxVolume, 1, 0, 1f);
//            Log.e("Test", "Played sound");
//        }
//
//        audioManager
//                .getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    private void initializeSoundPlayer() {
        AudioManager audioManager =
                (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);


        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
        soundID = soundPool.load(getContext(), R.raw.effect_tick, 1);
    }

    public void setMaxVolume() {
//
//
//        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        float percent = 0.7f;
//        int seventyVolume = (int) (maxVolume*percent);
//        audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
    }

    private void verifyInitMode() {
        if (isInEditMode()) {
            return;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getWidth();
        int height = getHeight();
//        if(width > 0 && height > 0) {
//            createKeyboard(width, height);
//        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * ORIGINAL METHOD
     *
     * @param width
     * @param height
     */
    private void createKeyboard(int width, int height) {

//        removeAllViews();
//
//        Toast.makeText(getContext(), "Configuration -> " + mKeyboardProperty.getConfiguration(), Toast.LENGTH_SHORT).show();
//
//        TableKeyboardManager keyboardManager = (TableKeyboardManager) mKeyboardManager;
//
//        int ROW = keyboardManager.getRowsCount();
//        int COLS = keyboardManager.getColsCount();
//
//
//
//        int keyWidth = Math.round(getWidth() / COLS);
//        int keyHeight = Math.round(getHeight() / ROW);
//
//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j < COLS; j++) {
//
//                TextView child = new TextView(getContext());
//                child.setText("x");
//                child.setTextSize(16f);
//                child.setGravity(Gravity.CENTER);
//                child.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
//                FrameLayout.LayoutParams childParams =
//                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//                childParams.gravity = Gravity.CENTER;
//                //child.measure(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//                child.setLayoutParams(childParams);
//                child.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_key_bg));
//                int size = getResources().getDimensionPixelOffset(R.dimen.key_padding_size);
//                child.setPadding(size, size, size, size);
//                child.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "Clicking on the key!", Toast.LENGTH_SHORT).show();
////                        if (mKeyClickListener != null) {
////                            playMusic();
////                            Editable editable = mKeyClickListener.getComponent();
////                            if (editable != null && editable.length() > 0) {
////                                int start = mKeyClickListener.getSelectionStart();
////                                if (start == -1) return;
////                                editable.delete(start - 1, start);
////                            }
////                        }
//                    }
//                });
//
//                FrameLayout childContainer = new FrameLayout(getContext());
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(keyWidth, keyHeight);
//                params.leftMargin = j * keyWidth;
//                params.topMargin = i * keyHeight;
//                Random random = new Random();
//                //childContainer.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//                childContainer.setLayoutParams(params);
//                childContainer.setPadding(size, size, size, size);
//                childContainer.addView(child);
//                addView(childContainer);
//            }
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(getMeasuredWidth(), 350);
    }

    public void setKeyboardMap(Map<IKeyTypography, List<IKeyTypography>> keyboardMap) {
        for (Map.Entry<IKeyTypography, List<IKeyTypography>> descriptionEntry : keyboardMap.entrySet()) {
            List<IKeyTypography> value = descriptionEntry.getValue();
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (tonePlayer != null) {
            tonePlayer.cleanup();
        }
    }

    private void createKeyboard(String s) {
        removeAllViews();





        Toast.makeText(getContext(), "Creating Keyboard... " + s, Toast.LENGTH_SHORT).show();
    }

    public void setKeyClickListener(OnKeyClickListener keyClickListener) {
        mKeyClickListener = keyClickListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean valToReturn = false;
        if (showing) {
            hideKeyboard();
            valToReturn = true;
        }
        return valToReturn;
    }

    public void showKeyboard() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mKeyboardProperty.getKeyboardHeight());
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);

        hideKeyboard();
        mRootView.addView(this);
        showing = true;
    }

    public void hideKeyboard() {
        mRootView.removeView(this);
        showing = false;
    }

    /**
     * This method is used to initialize the parent view inorder to attach a custom keyboard
     *
     * @param window
     */
    private void initializeRootView(Window window) {
        mRootView = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                }
                onCustomKeyboardReady();
            }
        });
    }

    private void onCustomKeyboardReady() {
        mKeyboardReady = true;
    }
}
