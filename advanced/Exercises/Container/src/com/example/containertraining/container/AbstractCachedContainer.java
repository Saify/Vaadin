package com.example.containertraining.container;

import java.util.Map;
import java.util.UUID;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import com.vaadin.data.Item;
import com.vaadin.data.util.AbstractContainer;

@SuppressWarnings("serial")
public abstract class AbstractCachedContainer<T> extends AbstractContainer {

    private static final String CACHE_NAME = "ItemCache";
    private static final String CACHED_SIZE_KEY = "size";

    private static final int ITEM_TIME_TO_LIVE_SECONDS = 30;
    private static final int SIZE_TIME_TO_LIVE_SECONDS = 5;
    private static final int MAX_SIZE = 5000;

    private Cache cache;

    public AbstractCachedContainer() {
        initCache();
    }

    @Override
    public Item getItem(Object itemId) {
        int index = (Integer) itemId;

        // Try to hit the cache first
        Element element = cache.get(index);

        if (element == null) {
            // Not in cache, fetch a batch of items
            Map<Integer, Item> items = fetchItemBatch(index);

            debug("Caching " + items.size() + " objects.");

            // Add items to cache
            for (int idx : items.keySet()) {
                cache.put(new Element(idx, items.get(idx)));
            }

            // Get the requested element from cache
            element = cache.get(index);
        }

        return (Item) element.getObjectValue();
    }

    @Override
    public int size() {
        // Check if size is in cache
        Element element = cache.get(CACHED_SIZE_KEY);
        if (element != null) {
            // If it is, just return the cached size
            return (Integer) element.getObjectValue();
        } else {
            // If not, fetch the size and cache it
            int count = getCount();
            debug("Stored size to cache.");
            cache.put(new Element(CACHED_SIZE_KEY, count, false, 0,
                    SIZE_TIME_TO_LIVE_SECONDS));
            return count;
        }
    }

    /**
     * Fetches an indexed batch of items from the data provider. Batch size can
     * be freely chosen.
     * 
     * @param requestedIndex
     *            Index of the originally requested item. The item with this
     *            index must be included in the returned map.
     * @return A map of items from the data provider,where the mapping key
     *         should be the index of the item.
     */
    protected abstract Map<Integer, Item> fetchItemBatch(int requestedIndex);

    /**
     * Fetches the current item count from the data provider.
     * 
     * @return Current size from the data provider
     */
    protected abstract int getCount();

    /**
     * Create, configure and add the EhCache instance. Uses a random name
     * extension for each cache instance so we get a unique cache for each
     * container.
     */
    private void initCache() {
        String actualCacheName = CACHE_NAME + UUID.randomUUID().toString();
        CacheConfiguration conf = new CacheConfiguration(actualCacheName,
                MAX_SIZE);
        conf.setTimeToLiveSeconds(ITEM_TIME_TO_LIVE_SECONDS);
        cache = new Cache(conf);
        CacheManager.getInstance().addCache(cache);
    }

    /**
     * Clears the items from the cache.
     */
    protected void clearCache(){
        cache.removeAll();
    }

    private void debug(String msg) {
        System.err.println("ContactDAO -> " + msg);
    }
}
