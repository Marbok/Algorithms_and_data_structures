package com.marbok.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<Key, Value> {

  private Map<Key, Node> cache;
  private Node head;
  private Node tail;
  private int cacheSize = 0;
  private int capacity;

  public LRUCache(int capacity) {
    cache = new HashMap<>(capacity);
    this.capacity = capacity;
    head = new Node(null, null);
    tail = new Node(null, null);
    head.next = tail;
    tail.prev = head;
  }

  public Value get(Key key) {
    Node node = getNode(key);
    if (node == null) {
      return null;
    }
    return node.value;
  }

  public void put(Key key, Value value) {
    Node node = getNode(key);
    if (node == null) {
      Node newNode = new Node(key, value);
      if (cacheSize == capacity) {
        Node remNode = tail.prev;
        removeNode(remNode);
        cache.remove(remNode.key);
      } else {
        cacheSize++;
      }
      addHead(newNode);
      cache.put(key, newNode);
    } else {
      node.value = value;
    }
  }

  private Node getNode(Key key) {
    Node node = cache.get(key);
    if (node == null) {
      return null;
    }

    if (node == head.next) {
      return node;
    }

    removeNode(node);
    addHead(node);

    return node;
  }

  private void removeNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void addHead(Node node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  private class Node {

    private Node prev;
    private Node next;
    private Key key;
    private Value value;

    public Node(Key key, Value value) {
      this.value = value;
      this.key = key;
    }
  }
}
