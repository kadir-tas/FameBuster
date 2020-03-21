package com.famebuster.util;
import android.os.SystemClock;
import androidx.collection.ArrayMap;

import java.util.concurrent.TimeUnit;

/**
 * Utility class that decides whether we should fetch some data or not.
 */
public class RateLimiter<KEY> {

    private ArrayMap<KEY, Long> timestamps;
    private Long timeout;

    public RateLimiter(int timeout, TimeUnit timeUnit){
        this.timestamps = new ArrayMap<>();
        this.timeout = timeUnit.toMillis((long) timeout);

    }

    public synchronized Boolean shouldFetch(KEY key){
        Long lastFetched = timestamps.get(key);
        Long now = now();
        if(lastFetched == null){
            timestamps.put(key,now);
            return true;
        }
        if(now - lastFetched > timeout){
            timestamps.put(key,now);
            return true;
        }
        return false;
    }

    public synchronized void reset(KEY key){
        timestamps.remove(key);
    }

    private Long now(){
        return SystemClock.uptimeMillis();
    }
    
}

