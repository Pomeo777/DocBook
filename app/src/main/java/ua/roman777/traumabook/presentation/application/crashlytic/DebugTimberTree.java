package ua.roman777.traumabook.presentation.application.crashlytic;

import android.content.Context;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import timber.log.Timber;

public class DebugTimberTree extends Timber.DebugTree {


    public DebugTimberTree(Context context) {

    }
    @Override
    protected @Nullable String createStackElementTag(@NotNull StackTraceElement element) {
        return String.format(Locale.getDefault(), "[TIMBER] %s.%s() [#%d]", super.createStackElementTag(element),
                element.getMethodName(), element.getLineNumber());
    }

    @Override
    protected void log(int priority, String tag, @NotNull String message, @Nullable Throwable throwable) {
        super.log(priority, tag, message, throwable);
    }
}
