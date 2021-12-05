package com.marbok.lrucache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LRUCacheTest {

  @Test
  public void test() {
    LRUCache<Integer, Integer> cache = new LRUCache<>(2);
    cache.put(1, 1); // cache is {1=1}
    cache.put(2, 2); // cache is {1=1, 2=2}
    assertEquals(Integer.valueOf(1), cache.get(1)); // return 1
    cache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    assertNull(cache.get(2)); // returns null (not found)
    cache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    assertNull(cache.get(1)); // return -1 (not found)
    assertEquals(Integer.valueOf(3), cache.get(3)); // return 3
    assertEquals(Integer.valueOf(4), cache.get(4)); // return 4
  }
}
