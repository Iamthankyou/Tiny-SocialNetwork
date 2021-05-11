package com.devteam.social_network.common;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;

public interface MessageResource {
    @Nullable
    String getMessage(String var1, @Nullable Object[] var2, @Nullable String var3);

    @Nullable
    String getMessage(String var1);

    String getMessage(String var1, @Nullable Object[] var2) throws NoSuchMessageException;

    String getMessage(MessageSourceResolvable var1) throws NoSuchMessageException;
}
